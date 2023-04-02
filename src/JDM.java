import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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

  public static void main(String[] args) throws IOException, InterruptedException {
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

  private static void printUsage() {
    System.out.printf("Usage: java JDM <command> [<args>]%n%n");
    System.out.println("Commands:");
    System.out.printf("  %-20s %s%n", "help [--help -h]", "Usage help");
    System.out.printf("  %-20s %s%n", "init <option>",
        "Initialize 'jdm.json' or '.vscode/settings.json'. Options: jdm, vscode");
    System.out.printf("  %-20s %s%n", "fetch", "Fetch dependencies");
  }
}
