package com.relateIntegration.relate.webservice;

import java.util.Calendar;
import java.util.Date;

import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.AddressLineLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.AddressLineType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.AddressLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.AddressType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.ContactInformationLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.ContactInformationType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.CustomerLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.CustomerNameLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.CustomerNameType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.CustomerType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.EMailLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.EMailType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.IndividualLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.IndividualType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.InstrumentLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.InstrumentType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.LastUpdateInfoType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.PersonalPreferencesType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.PersonalSummaryType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.PostalCodeLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.PostalCodeType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.SocioEconomicProfileType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.TelephoneLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.TelephoneType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.TerritoryLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.TerritoryType;



public class RelateCustomerFactory 
{
	public static final String EMPTY_STRING = "";

	public static CustomerType getCustomerInstance() {
		CustomerType customer = new CustomerType();

		customer.setRetailStoreID(EMPTY_STRING);
		customer.setInstrument(getInstrumentInstance());
		customer.setCustomerID(EMPTY_STRING);
		customer.setOrgName(EMPTY_STRING);
		customer.setCustOrgTypcode(EMPTY_STRING);
		customer.setEmployeeID(EMPTY_STRING);
		customer.setBusinessName(EMPTY_STRING);
		customer.setCustomerClass(EMPTY_STRING);
		customer.setSource(EMPTY_STRING);
		customer.setProspect(true);
		customer.setCustomerNumber(EMPTY_STRING);
		customer.setCustomerReference(EMPTY_STRING);
		customer.setPersonalPreferences(new PersonalPreferencesType());
		customer.setSignupDate(new Date());
		customer.setLastUpdateInfo(getLastUpdateInfoInstance());

		return customer;
	}

	private static LastUpdateInfoType getLastUpdateInfoInstance() 
	{
		LastUpdateInfoType lastUpdateInfoType = new LastUpdateInfoType();
		Calendar calender = Calendar.getInstance();
		calender.setTime(new Date());
		
		lastUpdateInfoType.setUpdateDate(calender);
		lastUpdateInfoType.setUpdateUserID(EMPTY_STRING);

		return lastUpdateInfoType;
	}

	public static IndividualType getIndividualInstance() {
		IndividualType individual = new IndividualType();

		individual.setName(new CustomerNameType());
		individual.setSuffix(EMPTY_STRING);
		individual.setSortingName(EMPTY_STRING);
		individual.setNickName(EMPTY_STRING);
		individual.setSalutation(EMPTY_STRING);
		individual.setContactInformation(new ContactInformationType());
		individual.setPersonalSummary(getPersonalSummaryInstance());
		individual.setSocioEconomicProfile(getSocialEconomicProfileInstance());

		return individual;
	}

	public static AddressType getAddressInstance() {
		AddressType address = new AddressType();

		address.setAddressLine1(getAddressLineInstance());
		address.setAddressLine2(getAddressLineInstance());
		address.setAddressLine3(getAddressLineInstance());
		address.setAddressLine4(getAddressLineInstance());
		address.setApartmentNumber(EMPTY_STRING);
		address.setCity(getAddressLineInstance());
		address.setCountry(EMPTY_STRING);
		address.setTerritory(getTerritoryInstance());
		address.setPostalCode(getPostalCodeInstance());
		address.setLabel(EMPTY_STRING);
		address.setContactPreferenceCode(EMPTY_STRING);
		address.setPrimaryFlag(false);
		address.setTypeCode(EMPTY_STRING);
		address.setDeleteAllByType(EMPTY_STRING);
		address.setValidFlag(true);
		address.setSequenceNumber(EMPTY_STRING);

		return address;
	}

	public static EMailType getEMailInstance() {
		EMailType email = new EMailType();

		email.setEMailAddress(EMPTY_STRING);
		email.setPrimaryFlag(false);
		email.setTypeCode(EMPTY_STRING);
		email.setDeleteAllByType(EMPTY_STRING);
		email.setLabel(EMPTY_STRING);
		email.setContactPreferenceCode(EMPTY_STRING);
		email.setSequenceNumber(EMPTY_STRING);

		return email;
	}

	public static TelephoneType getTelephoneInstance() {
		TelephoneType telephone = new TelephoneType();

		telephone.setPhoneNumber(EMPTY_STRING);
		telephone.setExtension(EMPTY_STRING);
		telephone.setPrimaryFlag(false);
		telephone.setTypeCode(EMPTY_STRING);
		telephone.setDeleteAllByType(EMPTY_STRING);
		telephone.setLabel(EMPTY_STRING);
		telephone.setContactPreferenceCode(EMPTY_STRING);
		telephone.setSequenceNumber(EMPTY_STRING);

		return telephone;
	}

	public static InstrumentType getInstrumentInstance() {
		InstrumentType instrument = new InstrumentType();

		instrument.setCardNumber(EMPTY_STRING);
		instrument.setCardSerialNumber(EMPTY_STRING);

		return instrument;
	}

	public static AddressLineType getAddressLineInstance() {
		AddressLineType addressLine = new AddressLineType();

		addressLine.setAddressLineType(EMPTY_STRING);

		return addressLine;
	}

	public static TerritoryType getTerritoryInstance() {
		TerritoryType territory = new TerritoryType();

		territory.setTerritoryType(EMPTY_STRING);

		return territory;
	}

	public static PostalCodeType getPostalCodeInstance() {
		PostalCodeType postalCode = new PostalCodeType();

		postalCode.setPostalCodeType(EMPTY_STRING);

		return postalCode;
	}

	public static  PersonalSummaryType getPersonalSummaryInstance() {
		PersonalSummaryType personalSummary = new PersonalSummaryType();

		personalSummary.setGenderType(EMPTY_STRING);
		personalSummary.setBirthDate(new Date());
		personalSummary.setMaritalStatusCode(EMPTY_STRING);
		personalSummary.setEthnicity(EMPTY_STRING);
		personalSummary.setRent(false);
		personalSummary.setLanguageCode(EMPTY_STRING);

		return personalSummary;
	}

	public static SocioEconomicProfileType getSocialEconomicProfileInstance() {
		SocioEconomicProfileType socioEconomicProfile = new SocioEconomicProfileType();
		socioEconomicProfile.setAnnualIncomeAmount(EMPTY_STRING);
		socioEconomicProfile.setNetWorth(EMPTY_STRING);
		socioEconomicProfile.setHighestEducationalLevelName(EMPTY_STRING);

		return socioEconomicProfile;
	}
	
	
	
	// for Lookup
	public static CustomerLookupType getCustomerLookupInstance() {
		CustomerLookupType customer = new CustomerLookupType();

		customer.setInstrument(getInstrumentLookupInstance());
		customer.setCustomerID(EMPTY_STRING);
		customer.setSegmentID(EMPTY_STRING);
		
		return customer;
	}


	public static IndividualLookupType getIndividualLookupInstance() {
		IndividualLookupType individual = new IndividualLookupType();

		individual.setName(new CustomerNameLookupType());
		individual.setContactInformation(new ContactInformationLookupType());

		return individual;
	}

	public static AddressLookupType getAddressInstanceLookup() {
		AddressLookupType address = new AddressLookupType();

		address.setAddressLine1(getAddressLineLookupInstance());
		address.setAddressLine2(getAddressLineLookupInstance());
		address.setAddressLine3(getAddressLineLookupInstance());
		address.setAddressLine4(getAddressLineLookupInstance());
		address.setApartmentNumber(EMPTY_STRING);
		address.setCity(getAddressLineLookupInstance());
		address.setCountry(EMPTY_STRING);
		address.setTerritory(getTerritoryLookupInstance());
		address.setPostalCode(getPostalCodeLookupInstance());
		address.setTypeCode(EMPTY_STRING);
		address.setSequenceNumber(EMPTY_STRING);

		return address;
	}

	public static EMailLookupType getEMailLookupInstance() {
		EMailLookupType email = new EMailLookupType();

		email.setEMailAddress(EMPTY_STRING);
		email.setTypeCode(EMPTY_STRING);
		
		return email;
	}

	public static TelephoneLookupType getTelephoneLookupInstance() {
		TelephoneLookupType telephone = new TelephoneLookupType();

		telephone.setPhoneNumber(EMPTY_STRING);
		telephone.setExtension(EMPTY_STRING);
		telephone.setTypeCode(EMPTY_STRING);
		telephone.setSequenceNumber(EMPTY_STRING);

		return telephone;
	}

	public static InstrumentLookupType getInstrumentLookupInstance() {
		InstrumentLookupType instrument = new InstrumentLookupType();

		instrument.setCardNumber(EMPTY_STRING);
		instrument.setCardSerialNumber(EMPTY_STRING);

		return instrument;
	}

	public static AddressLineLookupType getAddressLineLookupInstance() {
		AddressLineLookupType addressLine = new AddressLineLookupType();

		addressLine.setString(EMPTY_STRING);

		return addressLine;
	}

	public static TerritoryLookupType getTerritoryLookupInstance() {
		TerritoryLookupType territory = new TerritoryLookupType();

		territory.setString(EMPTY_STRING);

		return territory;
	}

	public static PostalCodeLookupType getPostalCodeLookupInstance() {
		PostalCodeLookupType postalCode = new PostalCodeLookupType();

		postalCode.setString(EMPTY_STRING);

		return postalCode;
	}
}
