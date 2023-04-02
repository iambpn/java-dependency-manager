package jdm.common;

import java.nio.file.Paths;

public class Common {
  public final static String rootDir = System.getProperty("user.dir");
  public final static String repoBaseURL = "https://repo1.maven.org/maven2";

  public final static String defaultLibPath = Paths.get("lib").toString();

  public final static String jdmJsonPath = Paths.get(rootDir, "jdm.json").toString();
  public final static String vscodeSettingPath = Paths.get(rootDir, ".vscode", "settings.json").toString();

  public final static String jdmJsonTemplate = "{\n" +
      "  \"name\": \"\",\n" +
      "  \"description\": \"\",\n" +
      "  \"packages\": []\n" +
      "}";
  public final static String vscodeSettingsTemplate = "{\n" +
      "  \"java.project.sourcePaths\": [\"src\"],\n" +
      "  \"java.project.outputPath\": \"bin\",\n" +
      "  \"java.project.referencedLibraries\": [\"lib/*\"]\n" +
      "}";
}
