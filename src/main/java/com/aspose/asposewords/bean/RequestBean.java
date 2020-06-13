package com.aspose.asposewords.bean;

import java.io.Serializable;

public class RequestBean implements Serializable {

	private static final long serialVersionUID = -2407907493077119910L;
	
	private String remoteUrl;
	
	private String fileName;
	
	private String storageUrl;
	
	private String templateCD;

	public String getRemoteUrl() {
		return remoteUrl;
	}

	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStorageUrl() {
		return storageUrl;
	}

	public void setStorageUrl(String storageUrl) {
		this.storageUrl = storageUrl;
	}

	public String getTemplateCD() {
		return templateCD;
	}

	public void setTemplateCD(String templateCD) {
		this.templateCD = templateCD;
	}
}