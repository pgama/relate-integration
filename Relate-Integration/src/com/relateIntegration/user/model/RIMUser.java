package com.relateIntegration.user.model;

import java.util.Date;

public class RIMUser {
	
	int relateIntegrationId ;
	String email;
	String firstName;
	String lastName;
	String addressLine1 ;
	String addressLine2 ;
	String city; 
	String state;
	String country;
	String zipCode;
	String emailOptInStatus;
	String phoneNumber;
	String obsession_clothes;
	String obsession_accessories;
	String obsession_shoes;
	String obsession_fashion;
	Date lastUpdatedTime;
	String existInRelate;
	String ocpCustId;
	
	public int getRelateIntegrationId() {
		return relateIntegrationId;
	}
	public void setRelateIntegrationId(int relateIntegrationId) {
		this.relateIntegrationId = relateIntegrationId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getEmailOptInStatus() {
		return emailOptInStatus;
	}
	public void setEmailOptInStatus(String emailOptInStatus) {
		this.emailOptInStatus = emailOptInStatus;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	public String getExistInRelate() {
		return existInRelate;
	}
	public void setExistInRelate(String existInRelate) {
		this.existInRelate = existInRelate;
	}
	public String getObsession_clothes() {
		return obsession_clothes;
	}
	public void setObsession_clothes(String obsessionClothes) {
		obsession_clothes = obsessionClothes;
	}
	public String getObsession_accessories() {
		return obsession_accessories;
	}
	public void setObsession_accessories(String obsessionAccessories) {
		obsession_accessories = obsessionAccessories;
	}
	public String getObsession_shoes() {
		return obsession_shoes;
	}
	public void setObsession_shoes(String obsessionShoes) {
		obsession_shoes = obsessionShoes;
	}
	public String getObsession_fashion() {
		return obsession_fashion;
	}
	public void setObsession_fashion(String obsessionFashion) {
		obsession_fashion = obsessionFashion;
	}
	public String getOcpCustId() {
		return ocpCustId;
	}
	public void setOcpCustId(String ocpCustId) {
		this.ocpCustId = ocpCustId;
	}

}
