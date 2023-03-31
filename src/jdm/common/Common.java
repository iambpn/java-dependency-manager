package jdm.common;

import java.nio.file.Paths;

public class Common {
  public final static String rootDir = System.getProperty("user.dir");
  public final static String runCmd = "java %s";
  public final static String compileCmd = "javac -cp %s -d %s  %s";
  public final static String repoBaseURL = "https://repo1.maven.org/maven2";

  public final static String defaultLibPath = Paths.get("lib").toString();
  public final static String defaultMainPath = Paths.get("src", "Main.java").toString();
  public final static String defaultCompilePath = Paths.get("bin").toString();

  public final static String jdmJsonPath = Paths.get(rootDir, "jdm.json").toString();
  public final static String vscodeSettingPath = Paths.get(rootDir, ".vscode", "settings.json").toString();

  public final static String jdmJsonTemplate = "{\n" +
      "  \"name\": \"\",\n" +
      "  \"description\": \"\",\n" +
      "  \"main\": \"src/Main.java\",\n" +
      "  \"target\": \"bin\",\n" +
      "  \"classPaths\": [],\n" +
      "  \"packages\": []\n" +
      "}";
  public final static String vscodeSettingsTemplate = "{\n" +
      "  \"java.project.sourcePaths\": [\"src\"],\n" +
      "  \"java.project.outputPath\": \"bin\",\n" +
      "  \"java.project.referencedLibraries\": [\"lib/*\"]\n" +
      "}";

  public static String joinString(String[] splittedStr, String joinAt) {
    String joinedString = "";
    for (String str : splittedStr) {
      joinedString += joinAt + str;
    }
    return joinedString.substring(1);
  }
}
