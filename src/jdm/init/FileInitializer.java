package jdm.init;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileInitializer {
  public static void createFile(String filePath, String fileContent) throws IOException {
    String fileName = Paths.get(filePath).getFileName().toString();
    File file = new File(filePath);
    if (file.exists()) {
      try (Scanner scanner = new Scanner(System.in)) {
        System.out.println(String.format("%s already exists. Do you want to override it? (y/n)", fileName));
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("n")) {
          return;
        }

        FileWriter writer = new FileWriter(file);
        writer.write(fileContent);
        writer.close();
      }
    } else {
      FileWriter writer = new FileWriter(file);
      writer.write(fileContent);
      writer.close();
    }
    System.out.println(String.format("%s initialized successfully.", fileName));
  }
}
