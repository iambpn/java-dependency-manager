package jdm.json.structure;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import jdm.common.Common;

public class JdmJson {
  private String group;
  private String artifact;
  private String version;
  private String description;
  private List<String> classPaths = new ArrayList<>();
  private List<JdmPackage> packages = new ArrayList<>();

  public JdmJson() {
    this.classPaths.add(Paths.get(Common.defaultLibPath, "*").toString());
  }

  public List<JdmPackage> getPackages() {
    return packages;
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

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getArtifact() {
    return artifact;
  }

  public void setArtifact(String artifact) {
    this.artifact = artifact;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  @Override
  public String toString() {
    return "JdmJson [group=" + group + ", artifact=" + artifact + ", version=" + version + ", description="
        + description + ", classPaths=" + classPaths + ", packages=" + packages + "]";
  }

}