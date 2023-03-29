import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class JDM {
  final String outputPath = "./lib/";
  final String basePath = "https://repo1.maven.org/maven2";

  public static void main(String[] args) {
    JDM jdm = new JDM();
    jdm.downloadUrl("org.apache.httpcomponents", "httpclient", "4.5.14");
  }

  private void downloadUrl(String group, String artifact, String version) {
    String url = String.format("%s/%s/%s/%s/%s-%s.jar", this.basePath, this.joinString(group.split("\\."), "/"),
        artifact,
        version, artifact, version);
    String outputFileName = String.format("%s-%s-%s.jar", group, artifact, version);

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
    try {
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
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private String joinString(String[] splittedStr, String joinAt) {
    String joinedString = "";
    for (String str : splittedStr) {
      joinedString += joinAt + str;
    }
    return joinedString.substring(1);
  }
}