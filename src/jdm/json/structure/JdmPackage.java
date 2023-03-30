package jdm.json.structure;
public class JdmPackage {
  private String artifact;
  private String group;
  private String version;

  public String getArtifact() {
    return artifact;
  }

  public void setArtifact(String artifact) {
    this.artifact = artifact;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  @Override
  public String toString() {
    return "Package [artifact=" + artifact + ", group=" + group + ", version=" + version + "]";
  }

}
