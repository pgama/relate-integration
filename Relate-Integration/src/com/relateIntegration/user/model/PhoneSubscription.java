package com.relateIntegration.user.model;

import java.util.Date;

public class PhoneSubscription 
{
	long phoneNumber;
	String phoneOptInStatus;
	Date lastUpdatedTime;
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneOptInStatus() {
		return phoneOptInStatus;
	}
	public void setPhoneOptInStatus(String phoneOptInStatus) {
		this.phoneOptInStatus = phoneOptInStatus;
	}
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	
}
