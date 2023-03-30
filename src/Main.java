import jdm.downloader.JdmDownloader;
import jdm.json.JdmJsonReader;
import jdm.json.structure.JdmJson;
import jdm.json.structure.JdmPackage;

public class Main {
  public static void main(String[] args) {
    final String outputPath = "./lib/";
    final String basePath = "https://repo1.maven.org/maven2";
    try {
      JdmDownloader downloader = new JdmDownloader(outputPath, basePath);

      JdmJson json = JdmJsonReader.readJdmJson();

      for (JdmPackage pkg : json.getPackages()) {
        System.out.println(String.format("Downloading %s...", pkg.getArtifact()));
        downloader.downlaodLib(pkg.getGroup(), pkg.getArtifact(), pkg.getVersion());
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
