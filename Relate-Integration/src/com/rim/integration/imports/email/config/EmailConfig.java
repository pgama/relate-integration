package com.rim.integration.imports.email.config;

import com.rim.integration.config.ImpexConfig;

public class EmailConfig extends ImpexConfig
{
	private String emailColumn;
	private String emailOptInStatusColumn;
	private String timeStampColumn;
	
	public String getEmailColumn() {
		return emailColumn;
	}
	public void setEmailColumn(String emailColumn) {
		this.emailColumn = emailColumn;
	}
	public String getEmailOptInStatusColumn() {
		return emailOptInStatusColumn;
	}
	public void setEmailOptInStatusColumn(String emailOptInStatusColumn) {
		this.emailOptInStatusColumn = emailOptInStatusColumn;
	}
	public String getTimeStampColumn() {
		return timeStampColumn;
	}
	public void setTimeStampColumn(String timeStampColumn) {
		this.timeStampColumn = timeStampColumn;
	}

}
