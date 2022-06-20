package com.harvard.studyappmodule.studymodel;

import io.realm.RealmObject;

public class ConsentDocObj extends RealmObject {
  private String version;
  private String type;
  private String content;

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
