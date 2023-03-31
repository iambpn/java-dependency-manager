package jdm.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;

import jdm.json.structure.JdmJson;

public class JdmJsonReader {
  public static JdmJson readJdmJson() throws FileNotFoundException {
    String JdmJsonPath = System.getProperty("user.dir") + "/jdm.json";

    File jsonFile = new File(JdmJsonPath);
    if (!jsonFile.exists()) {
      throw new FileNotFoundException("jdm.json file not found.");
    }

    Gson gson = new Gson();
    FileReader reader = new FileReader(jsonFile);
    JdmJson jdmJson = gson.fromJson(reader, JdmJson.class);
    return jdmJson;
  }
}
