package com.rim.integration.config;

public class ImpexConfig {

	private String pathToRead;
	private String pathToWrite;
	private String filePrefix;
	private String archivePath;
	private String dateFormat;
	private String delim;
	private boolean headerIncluded = true;
	
	
	public String getPathToRead() {
		return pathToRead;
	}
	public void setPathToRead(String pathToRead) {
		this.pathToRead = pathToRead;
	}
	public String getPathToWrite() {
		return pathToWrite;
	}
	public void setPathToWrite(String pathToWrite) {
		this.pathToWrite = pathToWrite;
	}
	public String getFilePrefix() {
		return filePrefix;
	}
	public void setFilePrefix(String filePrefix) {
		this.filePrefix = filePrefix;
	}
	public String getArchivePath() {
		return archivePath;
	}
	public void setArchivePath(String archivePath) {
		this.archivePath = archivePath;
	}
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public String getDelim() {
		return delim;
	}
	public void setDelim(String delim) {
		this.delim = delim;
	}
	public boolean isHeaderIncluded() {
		return headerIncluded;
	}
	public void setHeaderIncluded(boolean headerIncluded) {
		this.headerIncluded = headerIncluded;
	}
	
	
}
