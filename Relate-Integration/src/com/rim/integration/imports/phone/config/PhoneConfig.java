package com.rim.integration.imports.phone.config;

import com.rim.integration.config.ImpexConfig;

public class PhoneConfig extends ImpexConfig
{
	private String phoneColumn;
	private String phoneOptInStatusColumn;
	private String timeStampColumn;
	
	public String getPhoneColumn() {
		return phoneColumn;
	}
	public void setPhoneColumn(String phoneColumn) {
		this.phoneColumn = phoneColumn;
	}
	public String getPhoneOptInStatusColumn() {
		return phoneOptInStatusColumn;
	}
	public void setPhoneOptInStatusColumn(String phoneOptInStatusColumn) {
		this.phoneOptInStatusColumn = phoneOptInStatusColumn;
	}
	public String getTimeStampColumn() {
		return timeStampColumn;
	}
	public void setTimeStampColumn(String timeStampColumn) {
		this.timeStampColumn = timeStampColumn;
	}

}
