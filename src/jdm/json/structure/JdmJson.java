package jdm.json.structure;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import jdm.common.Common;

public class JdmJson {
  private String name;
  private String description;
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

  public List<String> getClassPaths() {
    return classPaths;
  }

  public void setClassPaths(List<String> classPaths) {
    this.classPaths = classPaths;
  }

  @Override
  public String toString() {
    return "JdmJson [name=" + name + ", description=" + description + ", classPaths=" + classPaths + ", packages="
        + packages + "]";
  }
}