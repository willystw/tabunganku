package com.willystw.tabunganku.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UploadCategoryIconResponse {

  @JsonProperty("file_name")
  private String fileName;

  private String message;

  public UploadCategoryIconResponse() {
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
}
