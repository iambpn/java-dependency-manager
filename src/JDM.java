import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringJoiner;

import jdm.common.Common;
import jdm.downloader.JdmDownloader;
import jdm.init.FileInitializer;
import jdm.json.JdmJsonReader;
import jdm.json.structure.JdmJson;
import jdm.json.structure.JdmPackage;

public class JDM {
  private JdmJson json;

  public JDM() {
    try {
      this.json = JdmJsonReader.readJdmJson();
    } catch (FileNotFoundException e) {
      System.out.println("jdm.json file not found.");
      System.exit(1);
    }
  }

  public static void main(String[] args) {
    JDM jdm = new JDM();

    if (args.length == 0) {
      printUsage();
      System.exit(1);
      return;
    }

    int exitCode = 0;
    switch (args[0]) {
      case "help":
      case "--help":
      case "-h":
        printUsage();
        break;
      case "init":
        if (args.length > 1) {
          exitCode = jdm.init(args[1]);
        } else {
          System.out.println("Invalid Arguments. Expected 2 but got 1.");
          printUsage();
          exitCode = 1;
        }
        break;
      case "run":
        exitCode = jdm.run();
        break;
      case "build":
        exitCode = jdm.build();
        break;
      case "fetch":
        exitCode = jdm.fetch();
        break;
      default:
        System.out.println("Unknown Command: " + args[0]);
        printUsage();
        exitCode = 1;
    }

    System.exit(exitCode);
    return;
  }

  private int fetch() {
    JdmDownloader downloader = new JdmDownloader(
        Paths.get(Common.rootDir, Common.defaultLibPath).toString(),
        Common.repoBaseURL);
    for (JdmPackage pkg : this.json.getPackages()) {
      System.out.println(String.format("Downloading %s...", pkg.getArtifact()));
      try {
        downloader.downlaodLib(pkg.getGroup(), pkg.getArtifact(), pkg.getVersion());
      } catch (IOException | InterruptedException e) {
        System.out.println(String.format("%s - Download Failed!", pkg.getArtifact()));
        System.out.println("Caused by: " + e.getMessage());
      }
    }
    return 0;
  }

  private int build() {
    String classPaths = this.buildClassPaths(this.json.getClassPaths());
    String mainFileParentPath = Paths.get(Common.rootDir, json.getMain()).getParent().resolve("*").toString();
    String targetPath = Paths.get(Common.rootDir, this.json.getTarget()).toString();
    try {
      System.out.println("Build: " + String.format(Common.compileCmd, classPaths, targetPath, mainFileParentPath));
      return runShellCommand(String.format(Common.compileCmd, classPaths, targetPath, mainFileParentPath));
    } catch (IOException | InterruptedException e) {
      System.out.println("ERROR WHILE BUILDING PROJECT.");
      System.out.println("Caused by: " + e.getMessage());
      return 1;
    }
  }

  private int run() {
    int exitCode = this.build();

    if (exitCode == 1) {
      return 1;
    }

    String mainFileName = Paths.get(Common.rootDir, json.getMain()).getFileName().toString();
    try {
      System.out.println(
          "Run: " + String.format(Common.runCmd, Paths.get(Common.rootDir, this.json.getTarget(), mainFileName)));
      return runShellCommand(
          String.format(Common.runCmd, Paths.get(Common.rootDir, this.json.getTarget(), mainFileName)));
    } catch (IOException | InterruptedException e) {
      System.out.println("ERROR WHILE RUNNING PROJECT.");
      System.out.println("Caused by: " + e.getMessage());
      return 1;
    }
  }

  private int init(String option) {
    try {
      switch (option.toLowerCase()) {
        case "jdm":
          FileInitializer.createFile(Common.jdmJsonPath, "{}");
          break;
        case "vscode":
          FileInitializer.createFile(Common.vscodeSettingPath, "{}");
          break;
        default:
          System.out.println("Unknown Option: " + option);
          printUsage();
          return 1;
      }
      return 0;
    } catch (IOException e) {
      System.out.println("ERROR: Could not initialize file.");
      System.out.println("Caused By: " + e.getMessage());
      return 1;
    }
  }

  private String buildClassPaths(List<String> classPaths) {
    StringJoiner classPathJoiner = new StringJoiner(File.pathSeparator);
    for (String classPath : classPaths) {
      classPathJoiner.add(Paths.get(Common.rootDir, classPath).toString());
    }
    return classPathJoiner.toString();
  }

  private static void printUsage() {
    System.out.printf("Usage: java JDM <command> [<args>]%n%n");
    System.out.println("Commands:");
    System.out.printf("  %-20s %s%n", "help [--help -h]", "Usage help");
    System.out.printf("  %-20s %s%n", "init <option>",
        "Initialize 'jdm.json' or '.vscode/settings.json'. Options: jdm, vscode");
    System.out.printf("  %-20s %s%n", "run", "Run the project");
    System.out.printf("  %-20s %s%n", "build", "Build the project");
    System.out.printf("  %-20s %s%n", "fetch", "Fetch dependencies");
  }

  private int runShellCommand(String command) throws IOException, InterruptedException {
    Process process = Runtime.getRuntime().exec(command);
    process.waitFor();
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    String line;
    while ((line = reader.readLine()) != null) {
      System.out.println(line);
    }
    reader.close();
    return process.exitValue();
  }
}
