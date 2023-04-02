package jdm.downloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import jdm.common.Common;

public class JdmDownloader {
  private String outputPath;
  private String basePath;

  public JdmDownloader(String outPath, String basePath) {
    this.outputPath = outPath;
    this.basePath = basePath;
  }

  public void downlaodLib(String group, String artifact, String version) throws IOException, InterruptedException {
    String url = String.format("%s/%s/%s/%s/%s-%s.jar",
        this.basePath,
        String.join("/", group.split("\\.")),
        artifact,
        version, artifact, version);
    String outputFileName = String.format("%s-%s-%s.jar", group, artifact, version);

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
    HttpResponse<InputStream> response = client.send(request,
        HttpResponse.BodyHandlers.ofInputStream());
    if (response.statusCode() == 200) {
      InputStream inStream = response.body();
      FileOutputStream outStream = new FileOutputStream(this.outputPath + outputFileName);

      byte[] buffer = new byte[4096];
      int bytesRead;

      while ((bytesRead = inStream.read(buffer)) != -1) {
        outStream.write(buffer, 0, bytesRead);
      }

      outStream.close();
      inStream.close();
      System.out.println(outputFileName + " --  Done");
    } else {
      System.out.println("Failed to download data: " + response.statusCode());
      System.out.println(url);
    }
  }
}