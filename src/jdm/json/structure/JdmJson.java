package jdm.json.structure;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import jdm.common.Common;

public class JdmJson {
  private String name;
  private String description;
  private String main = Common.defaultMainPath;
  private String target = Common.defaultCompilePath;
  private List<String> classPaths = new ArrayList<>();
  private List<JdmPackage> packages = new ArrayList<>();

  public JdmJson() {
    this.classPaths.add(Paths.get(Common.defaultLibPath, "*").toString());
  }

  public List<JdmPackage> getPackages() {
    return packages;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setPackages(List<JdmPackage> packages) {
    this.packages = packages;
  }

  public String getMain() {
    return main;
  }

  public void setMain(String main) {
    this.main = main;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  public List<String> getClassPaths() {
    return classPaths;
  }

  public void setClassPaths(List<String> classPaths) {
    this.classPaths = classPaths;
  }

  @Override
  public String toString() {
    return "JdmJson [name=" + name + ", description=" + description + ", main=" + main + ", target=" + target
        + ", classPaths=" + classPaths + ", packages=" + packages + "]";
  }
}