package com.relateIntegration.relate.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.AddressLineLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.AddressLineType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.AddressLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.AddressReturnType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.AddressType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.AlternateKeyLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.AlternateKeyReturnType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.AlternateKeyType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.ContactInformationLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.ContactInformationReturnType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.ContactInformationType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.ContactPreferenceContactType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.ContactPreferenceType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.CustomAttributeReturnType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.CustomAttributeType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.CustomerLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.CustomerNameLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.CustomerNameLookupTypeEntry;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.CustomerNameType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.CustomerNameTypeEntry;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.CustomerResponseType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.CustomerType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.EMailLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.EMailType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.EntityInformationLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.EntityInformationType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.IndividualLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.IndividualReturnType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.IndividualType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.NameLocationLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.NameLocationType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.PersonalPreferencesType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.PersonalSummaryReturnType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.PostalCodeLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.PostalCodeType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.SocioEconomicProfileReturnType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.TelephoneLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.TelephoneType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.TerritoryLookupType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.TerritoryType;
import com.relateIntegration.util.RIMConstants;

public class CustomerWebserviceSupport {
	
	
	//methods for addOrUpdate
	public static CustomerType getCustomerData(Map<String, Object> customerInformation , int relateIntegrationId)
	{
		CustomerType customerData = RelateCustomerFactory.getCustomerInstance();
		if(customerInformation.containsKey(RIMConstants.RequestMapping.CUSTOMER_KEY) && !customerInformation.get(RIMConstants.RequestMapping.CUSTOMER_KEY).toString().isEmpty())
		{	
			customerData.setCustomerID(customerInformation.get(RIMConstants.RequestMapping.CUSTOMER_KEY).toString());
		}
		
		//check what is the alternate id and set it.
		String alternateId = "";
		if(relateIntegrationId > 0)
		{
			alternateId = String.valueOf(relateIntegrationId);
			customerData.setAlternateKey(getAlternateKey(alternateId));
		}
		customerData.setEntityInformation(getEntityInformation(customerInformation));
		
		PersonalPreferencesType personalPreferences =new PersonalPreferencesType();
		//email
		if(customerInformation.containsKey(RIMConstants.RequestMapping.EMAIL_OPT_IN_KEY) && !customerInformation.get(RIMConstants.RequestMapping.EMAIL_OPT_IN_KEY).toString().isEmpty())
		{
			ContactPreferenceType contactPrefernceEmail = new ContactPreferenceType();
			contactPrefernceEmail.setContactType(ContactPreferenceContactType.Email);
			
			if(customerInformation.get(RIMConstants.RequestMapping.EMAIL_OPT_IN_KEY).toString().equalsIgnoreCase("true"))
				contactPrefernceEmail.setPermission(true);
			else
				contactPrefernceEmail.setPermission(false);
			
			personalPreferences.addContactPreference(contactPrefernceEmail);
		}
		
		
		
		//phone
		if(customerInformation.containsKey(RIMConstants.RequestMapping.PHONE_OPT_IN_KEY) && !customerInformation.get(RIMConstants.RequestMapping.PHONE_OPT_IN_KEY).toString().isEmpty())
		{
			ContactPreferenceType contactPreferncePhone = new ContactPreferenceType();
			contactPreferncePhone.setContactType(ContactPreferenceContactType.Phone);
			
			if(customerInformation.get(RIMConstants.RequestMapping.PHONE_OPT_IN_KEY).toString().equalsIgnoreCase("true"))
				contactPreferncePhone.setPermission(true);
			else
				contactPreferncePhone.setPermission(false);
			
			personalPreferences.addContactPreference(contactPreferncePhone);
		}
		
		//obsessions
		 CustomAttributeType[] obsessions = getObsessions(customerInformation);
		 if(obsessions!=null)
			 customerData.setCustomAttribute(obsessions);
		 
		customerData.setPersonalPreferences(personalPreferences);

		
		return customerData;
	}
	
	public static CustomAttributeType[] getObsessions( Map<String, Object> customerInformation)
	{
		ArrayList<CustomAttributeType> list = new ArrayList<CustomAttributeType>();
		
		//custom attributes obsession
		CustomAttributeType obsession_accessories = null;
		if(customerInformation.containsKey(RIMConstants.RequestMapping.OBSESSION_ACCESSORIES_KEY) && !customerInformation.get(RIMConstants.RequestMapping.OBSESSION_ACCESSORIES_KEY).toString().isEmpty())
		{
			String[] value = new String[1];
			if(customerInformation.get(RIMConstants.RequestMapping.OBSESSION_ACCESSORIES_KEY).toString().equalsIgnoreCase("true"))
				value[0]= "true";
			else
				value[0]="false";
			
			obsession_accessories = new CustomAttributeType();
			obsession_accessories.setName(RIMConstants.RequestMapping.OBSESSION_ACCESSORIES_KEY);
			obsession_accessories.setAttributeValue(value);
			list.add(obsession_accessories);
		}
		CustomAttributeType obsession_shoes = null;
		if(customerInformation.containsKey(RIMConstants.RequestMapping.OBSESSION_SHOES_KEY) && !customerInformation.get(RIMConstants.RequestMapping.OBSESSION_SHOES_KEY).toString().isEmpty())
		{
			String[] value = new String[1];
			if(customerInformation.get(RIMConstants.RequestMapping.OBSESSION_SHOES_KEY).toString().equalsIgnoreCase("true"))
				value[0]= "true";
			else
				value[0]="false";
			
			obsession_shoes = new CustomAttributeType();
			obsession_shoes.setName(RIMConstants.RequestMapping.OBSESSION_SHOES_KEY);
			obsession_shoes.setAttributeValue(value);
			list.add(obsession_shoes);
		}
		CustomAttributeType obsession_clothes = null;
		if(customerInformation.containsKey(RIMConstants.RequestMapping.OBSESSION_CLOTHES_KEY) && !customerInformation.get(RIMConstants.RequestMapping.OBSESSION_CLOTHES_KEY).toString().isEmpty())
		{
			String[] value = new String[1];
			if(customerInformation.get(RIMConstants.RequestMapping.OBSESSION_CLOTHES_KEY).toString().equalsIgnoreCase("true"))
				value[0]= "true";
			else
				value[0]="false";
			
			obsession_clothes = new CustomAttributeType();
			obsession_clothes.setName(RIMConstants.RequestMapping.OBSESSION_CLOTHES_KEY);
			obsession_clothes.setAttributeValue(value);
			list.add(obsession_clothes);
		}
		CustomAttributeType obsession_fashion = null;
		if(customerInformation.containsKey(RIMConstants.RequestMapping.OBSESSION_FASHION_KEY) && !customerInformation.get(RIMConstants.RequestMapping.OBSESSION_FASHION_KEY).toString().isEmpty())
		{
			String[] value = new String[1];
			if(customerInformation.get(RIMConstants.RequestMapping.OBSESSION_FASHION_KEY).toString().equalsIgnoreCase("true"))
				value[0]= "true";
			else
				value[0]="false";
			
			obsession_fashion = new CustomAttributeType();
			obsession_fashion.setName(RIMConstants.RequestMapping.OBSESSION_FASHION_KEY);
			obsession_fashion.setAttributeValue(value);
			list.add(obsession_fashion);
		}
		
		
		if(list.size()>0)
			{
				CustomAttributeType[] obsessions = new CustomAttributeType[list.size()];
				return  list.toArray(obsessions);
			}
		else
			return null;
	}
	
	public static AlternateKeyType[] getAlternateKey( String alertnateId) 
	{
		AlternateKeyType[] alternateKey =new AlternateKeyType[1];
		alternateKey[0] = new AlternateKeyType();
		
		alternateKey[0].setAlternateID(alertnateId);
		alternateKey[0].setTypeCode(RIMConstants.RequestMapping.AlTERNATE_KEY_TYPECODE);
		return alternateKey;
	}
	
	public static EntityInformationType getEntityInformation( Map<String, Object> customerInformation)
	{
		EntityInformationType entityInformation= new EntityInformationType();
		IndividualType individual = RelateCustomerFactory.getIndividualInstance();
		ContactInformationType contactInfo = new ContactInformationType();
		
		contactInfo.addAddress(getAddressInfo(customerInformation));
		contactInfo.addEMail(getEmailInformation(customerInformation));
		contactInfo.addTelephone(getPhoneInformation(customerInformation));
		
		individual.setContactInformation(contactInfo);
		individual.setName(getName(customerInformation));
		
		entityInformation.setIndividual(individual);
		
		
		return entityInformation;
	}
	
	public static CustomerNameType getName(Map<String, Object> customerInformation)
	{
		CustomerNameType name = new CustomerNameType();
		CustomerNameTypeEntry[] nameEntry = new CustomerNameTypeEntry[2];
		if(customerInformation.containsKey(RIMConstants.RequestMapping.FIRST_NAME_KEY) && !customerInformation.get(RIMConstants.RequestMapping.FIRST_NAME_KEY).toString().isEmpty())
		{	
			nameEntry[0] = new CustomerNameTypeEntry();
			nameEntry[0].setLocation(NameLocationType.First);
			nameEntry[0].setString(customerInformation.get(RIMConstants.RequestMapping.FIRST_NAME_KEY).toString());
		}
		
		if(customerInformation.containsKey(RIMConstants.RequestMapping.LAST_NAME_KEY) && !customerInformation.get(RIMConstants.RequestMapping.LAST_NAME_KEY).toString().isEmpty())
		{	
			nameEntry[1] = new CustomerNameTypeEntry();
			nameEntry[1].setLocation(NameLocationType.Last);
			nameEntry[1].setString(customerInformation.get(RIMConstants.RequestMapping.LAST_NAME_KEY).toString());
		}
		
		name.setName(nameEntry);
		
		return name;
	}
	
	public static EMailType getEmailInformation(Map<String, Object> customerInformation) 
	{
		EMailType emailType = RelateCustomerFactory.getEMailInstance();
		if(customerInformation.containsKey(RIMConstants.RequestMapping.EMAIL_KEY) && !customerInformation.get(RIMConstants.RequestMapping.EMAIL_KEY).toString().isEmpty())
		{	
			emailType.setPrimaryFlag(true);
			emailType.setContactPreferenceCode("promotions");
			emailType.setEMailAddress(customerInformation.get(RIMConstants.RequestMapping.EMAIL_KEY).toString());
		}
		return emailType;
	}
	
	public static TelephoneType getPhoneInformation( Map<String, Object> customerInformation) 
	{
		TelephoneType telephoneType = RelateCustomerFactory.getTelephoneInstance();
		if(customerInformation.containsKey(RIMConstants.RequestMapping.PHONE_NUMBER_KEY) && !customerInformation.get(RIMConstants.RequestMapping.PHONE_NUMBER_KEY).toString().isEmpty())
		{	
			telephoneType.setContactPreferenceCode("promotions");
			telephoneType.setPrimaryFlag(true);
			telephoneType.setPhoneNumber(customerInformation.get(RIMConstants.RequestMapping.PHONE_NUMBER_KEY).toString());
		}
		if(customerInformation.containsKey(RIMConstants.RequestMapping.PHONE_EXTENSION_KEY) && !customerInformation.get(RIMConstants.RequestMapping.PHONE_EXTENSION_KEY).toString().isEmpty())
		{	
			telephoneType.setExtension(customerInformation.get(RIMConstants.RequestMapping.PHONE_EXTENSION_KEY).toString());
		}
		return telephoneType;
	}
	
	public static AddressType getAddressInfo(Map<String, Object> customerInformation) 
	{	
			AddressType address = RelateCustomerFactory.getAddressInstance(); 
			address.setPrimaryFlag(true);
			address.setValidFlag(true);
			
			if(customerInformation.containsKey(RIMConstants.RequestMapping.ADDRESSLINE1_KEY) && !customerInformation.get(RIMConstants.RequestMapping.ADDRESSLINE1_KEY).toString().isEmpty())
			{	
				AddressLineType addressLine = RelateCustomerFactory.getAddressLineInstance();
				addressLine.setAddressLineType(customerInformation.get(RIMConstants.RequestMapping.ADDRESSLINE1_KEY).toString());
				address.setAddressLine1(addressLine);
			}
			if(customerInformation.containsKey(RIMConstants.RequestMapping.ADDRESSLINE2_KEY) && !customerInformation.get(RIMConstants.RequestMapping.ADDRESSLINE2_KEY).toString().isEmpty())
			{
				AddressLineType addressLine = RelateCustomerFactory.getAddressLineInstance();
				addressLine.setAddressLineType(customerInformation.get(RIMConstants.RequestMapping.ADDRESSLINE2_KEY).toString());
				address.setAddressLine2(addressLine);
			}
			if(customerInformation.containsKey(RIMConstants.RequestMapping.ADDRESSLINE3_KEY) && !customerInformation.get(RIMConstants.RequestMapping.ADDRESSLINE3_KEY).toString().isEmpty())
			{
				AddressLineType addressLine = RelateCustomerFactory.getAddressLineInstance();
				addressLine.setAddressLineType(customerInformation.get(RIMConstants.RequestMapping.ADDRESSLINE3_KEY).toString());
				address.setAddressLine3(addressLine);
			}
			if(customerInformation.containsKey(RIMConstants.RequestMapping.ADDRESSLINE4_KEY) && !customerInformation.get(RIMConstants.RequestMapping.ADDRESSLINE4_KEY).toString().isEmpty())
			{
				AddressLineType addressLine = RelateCustomerFactory.getAddressLineInstance();
				addressLine.setAddressLineType(customerInformation.get(RIMConstants.RequestMapping.ADDRESSLINE4_KEY).toString());
				address.setAddressLine4(addressLine);
			}
			if(customerInformation.containsKey(RIMConstants.RequestMapping.APARTMENT_NUMBER_KEY) && !customerInformation.get(RIMConstants.RequestMapping.APARTMENT_NUMBER_KEY).toString().isEmpty())
			{
				address.setApartmentNumber(customerInformation.get(RIMConstants.RequestMapping.APARTMENT_NUMBER_KEY).toString());
			}
			if(customerInformation.containsKey(RIMConstants.RequestMapping.CITY_KEY) && !customerInformation.get(RIMConstants.RequestMapping.CITY_KEY).toString().isEmpty())
			{
				AddressLineType addressLine = RelateCustomerFactory.getAddressLineInstance();
				addressLine.setAddressLineType(customerInformation.get(RIMConstants.RequestMapping.CITY_KEY).toString());
				address.setCity(addressLine);
			}
			if(customerInformation.containsKey(RIMConstants.RequestMapping.COUNTRY_KEY) && !customerInformation.get(RIMConstants.RequestMapping.COUNTRY_KEY).toString().isEmpty())
			{
				address.setCountry(customerInformation.get(RIMConstants.RequestMapping.COUNTRY_KEY).toString());
			}
			if(customerInformation.containsKey(RIMConstants.RequestMapping.COUNTY_KEY) && !customerInformation.get(RIMConstants.RequestMapping.COUNTY_KEY).toString().isEmpty())
			{
				address.setCounty(customerInformation.get(RIMConstants.RequestMapping.COUNTY_KEY).toString());
			}
			if(customerInformation.containsKey(RIMConstants.RequestMapping.POSTAL_CODE_KEY) && !customerInformation.get(RIMConstants.RequestMapping.POSTAL_CODE_KEY).toString().isEmpty())
			{
				PostalCodeType postalCode = RelateCustomerFactory.getPostalCodeInstance();
				postalCode.setPostalCodeType(customerInformation.get(RIMConstants.RequestMapping.POSTAL_CODE_KEY).toString());
				address.setPostalCode(postalCode);
			}
			if(customerInformation.containsKey(RIMConstants.RequestMapping.TERRITORY_KEY) && !customerInformation.get(RIMConstants.RequestMapping.TERRITORY_KEY).toString().isEmpty())
			{
				TerritoryType territory = RelateCustomerFactory.getTerritoryInstance();
				territory.setTerritoryType(customerInformation.get(RIMConstants.RequestMapping.TERRITORY_KEY).toString());
				address.setTerritory(territory);
			}
			
		return address;
	}
	

	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Methods for lookup
	public static CustomerLookupType getCustomerDataLookup(Map<String, Object> customerInformation , int relateIntegrationId)
	{
		CustomerLookupType customerDataLookup = RelateCustomerFactory.getCustomerLookupInstance();
		//customerDataLookup.setCustomerID(customerId);
		String alternateId = "";
		if(relateIntegrationId > 0)
		{
			alternateId = String.valueOf(relateIntegrationId);
			customerDataLookup.setAlternateKey(getAlternateKeyLookup(alternateId));
		}
		customerDataLookup.setEntityInformation(getEntityInformationLookup(customerInformation));
		
		if(customerInformation.containsKey(RIMConstants.RequestMapping.CUSTOMER_KEY) && !customerInformation.get(RIMConstants.RequestMapping.CUSTOMER_KEY).toString().isEmpty())
		{	
			customerDataLookup.setCustomerID(customerInformation.get(RIMConstants.RequestMapping.CUSTOMER_KEY).toString());
		}
		
		return customerDataLookup;
	}
	
	public static CustomerNameLookupType getNameLookup(Map<String, Object> customerInformation)
	{
		CustomerNameLookupType name = new CustomerNameLookupType();
		CustomerNameLookupTypeEntry[] nameLookup = new CustomerNameLookupTypeEntry[2];
		if(customerInformation.containsKey(RIMConstants.RequestMapping.FIRST_NAME_KEY) && !customerInformation.get(RIMConstants.RequestMapping.FIRST_NAME_KEY).toString().isEmpty())
		{	
			nameLookup[0] = new CustomerNameLookupTypeEntry();
			nameLookup[0].setLocation(NameLocationLookupType.First);
			nameLookup[0].setString(customerInformation.get(RIMConstants.RequestMapping.FIRST_NAME_KEY).toString());
		}
		
		if(customerInformation.containsKey(RIMConstants.RequestMapping.LAST_NAME_KEY) && !customerInformation.get(RIMConstants.RequestMapping.LAST_NAME_KEY).toString().isEmpty())
		{	
			nameLookup[1] = new CustomerNameLookupTypeEntry();
			nameLookup[1].setLocation(NameLocationLookupType.Last);
			nameLookup[1].setString(customerInformation.get(RIMConstants.RequestMapping.LAST_NAME_KEY).toString());
		}
		name.setName(nameLookup);
		
		return name;
	}
	
	public static EMailLookupType getEmailInformationLookup(Map<String, Object> customerInformation) 
	{
		EMailLookupType emailLookupType = RelateCustomerFactory.getEMailLookupInstance();
		if(customerInformation.containsKey(RIMConstants.RequestMapping.EMAIL_KEY) && !customerInformation.get(RIMConstants.RequestMapping.EMAIL_KEY).toString().isEmpty())
		{	
			emailLookupType.setEMailAddress(customerInformation.get(RIMConstants.RequestMapping.EMAIL_KEY).toString());
		}
		return emailLookupType;
	}
	
	public static TelephoneLookupType getPhoneInformationLookup( Map<String, Object> customerInformation) 
	{
		TelephoneLookupType telephoneLookupType = RelateCustomerFactory.getTelephoneLookupInstance();
		if(customerInformation.containsKey(RIMConstants.RequestMapping.PHONE_NUMBER_KEY) && !customerInformation.get(RIMConstants.RequestMapping.PHONE_NUMBER_KEY).toString().isEmpty())
		{	
			telephoneLookupType.setPhoneNumber(customerInformation.get(RIMConstants.RequestMapping.PHONE_NUMBER_KEY).toString());
		}
		if(customerInformation.containsKey(RIMConstants.RequestMapping.PHONE_EXTENSION_KEY) && !customerInformation.get(RIMConstants.RequestMapping.PHONE_EXTENSION_KEY).toString().isEmpty())
		{	
			telephoneLookupType.setExtension(customerInformation.get(RIMConstants.RequestMapping.PHONE_EXTENSION_KEY).toString());
		}
		return telephoneLookupType;
	}

	public static AddressLookupType getAddressInfoLookup( Map<String, Object> customerInformation) 
	{	
			AddressLookupType address = RelateCustomerFactory.getAddressInstanceLookup(); 
			
			if(customerInformation.containsKey(RIMConstants.RequestMapping.ADDRESSLINE1_KEY) && !customerInformation.get(RIMConstants.RequestMapping.ADDRESSLINE1_KEY).toString().isEmpty())
			{	
				AddressLineLookupType addressLine = RelateCustomerFactory.getAddressLineLookupInstance();
				addressLine.setString(customerInformation.get(RIMConstants.RequestMapping.ADDRESSLINE1_KEY).toString());
				address.setAddressLine1(addressLine);
			}
			if(customerInformation.containsKey(RIMConstants.RequestMapping.ADDRESSLINE2_KEY) && !customerInformation.get(RIMConstants.RequestMapping.ADDRESSLINE2_KEY).toString().isEmpty())
			{
				AddressLineLookupType addressLine = RelateCustomerFactory.getAddressLineLookupInstance();
				addressLine.setString(customerInformation.get(RIMConstants.RequestMapping.ADDRESSLINE2_KEY).toString());
				address.setAddressLine2(addressLine);
			}
			if(customerInformation.containsKey(RIMConstants.RequestMapping.ADDRESSLINE3_KEY) && !customerInformation.get(RIMConstants.RequestMapping.ADDRESSLINE3_KEY).toString().isEmpty())
			{
				AddressLineLookupType addressLine = RelateCustomerFactory.getAddressLineLookupInstance();
				addressLine.setString(customerInformation.get(RIMConstants.RequestMapping.ADDRESSLINE3_KEY).toString());
				address.setAddressLine3(addressLine);
			}
			if(customerInformation.containsKey(RIMConstants.RequestMapping.ADDRESSLINE4_KEY) && !customerInformation.get(RIMConstants.RequestMapping.ADDRESSLINE4_KEY).toString().isEmpty())
			{
				AddressLineLookupType addressLine = RelateCustomerFactory.getAddressLineLookupInstance();
				addressLine.setString(customerInformation.get(RIMConstants.RequestMapping.ADDRESSLINE4_KEY).toString());
				address.setAddressLine4(addressLine);
			}
			if(customerInformation.containsKey(RIMConstants.RequestMapping.APARTMENT_NUMBER_KEY) && !customerInformation.get(RIMConstants.RequestMapping.APARTMENT_NUMBER_KEY).toString().isEmpty())
			{
				address.setApartmentNumber(customerInformation.get(RIMConstants.RequestMapping.APARTMENT_NUMBER_KEY).toString());
			}
			if(customerInformation.containsKey(RIMConstants.RequestMapping.CITY_KEY) && !customerInformation.get(RIMConstants.RequestMapping.CITY_KEY).toString().isEmpty())
			{
				AddressLineLookupType addressLine = RelateCustomerFactory.getAddressLineLookupInstance();
				addressLine.setString(customerInformation.get(RIMConstants.RequestMapping.CITY_KEY).toString());
				address.setCity(addressLine);
			}
			if(customerInformation.containsKey(RIMConstants.RequestMapping.COUNTRY_KEY) && !customerInformation.get(RIMConstants.RequestMapping.COUNTRY_KEY).toString().isEmpty())
			{
				address.setCountry(customerInformation.get(RIMConstants.RequestMapping.COUNTRY_KEY).toString());
			}
			if(customerInformation.containsKey(RIMConstants.RequestMapping.COUNTY_KEY) && !customerInformation.get(RIMConstants.RequestMapping.COUNTY_KEY).toString().isEmpty())
			{
				address.setCounty(customerInformation.get(RIMConstants.RequestMapping.COUNTY_KEY).toString());
			}
			if(customerInformation.containsKey(RIMConstants.RequestMapping.POSTAL_CODE_KEY) && !customerInformation.get(RIMConstants.RequestMapping.POSTAL_CODE_KEY).toString().isEmpty())
			{
				PostalCodeLookupType postalCode = new PostalCodeLookupType();
				postalCode.setString(customerInformation.get(RIMConstants.RequestMapping.POSTAL_CODE_KEY).toString());
				address.setPostalCode(postalCode);
			}
			if(customerInformation.containsKey(RIMConstants.RequestMapping.TERRITORY_KEY) && !customerInformation.get(RIMConstants.RequestMapping.TERRITORY_KEY).toString().isEmpty())
			{
				TerritoryLookupType territory = new TerritoryLookupType();
				territory.setString(customerInformation.get(RIMConstants.RequestMapping.TERRITORY_KEY).toString());
				address.setTerritory(territory);
			}
			
		return address;
	}
	
	public static EntityInformationLookupType getEntityInformationLookup( Map<String, Object> customerInformation)
	{
		EntityInformationLookupType entityInformation= new EntityInformationLookupType();
		IndividualLookupType individual = RelateCustomerFactory.getIndividualLookupInstance();
		ContactInformationLookupType contactInfo = new ContactInformationLookupType();
		
		contactInfo.addAddress(getAddressInfoLookup(customerInformation));
		contactInfo.addEMail(getEmailInformationLookup(customerInformation));
		contactInfo.addTelephone(getPhoneInformationLookup(customerInformation));
		
		individual.setContactInformation(contactInfo);
		individual.setName(getNameLookup(customerInformation));
		
		entityInformation.setIndividual(individual);
		
		
		return entityInformation;
	}

	public static AlternateKeyLookupType[] getAlternateKeyLookup( String alertnateId) 
	{
		AlternateKeyLookupType[] alternateKey =new AlternateKeyLookupType[1];
		alternateKey[0] =new AlternateKeyLookupType();
		alternateKey[0].setAlternateID(alertnateId);
		alternateKey[0].setTypeCode(RIMConstants.RequestMapping.AlTERNATE_KEY_TYPECODE);
		return alternateKey;
	}

	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	// Methods to extract the data map from the lookup response
	public static List<Object> getCustomerDataMapsFromLookupResponse(CustomerResponseType customerResponseType[])
	{
		List<Object> allUsers = new ArrayList<Object>(); 
		for (CustomerResponseType cust : customerResponseType) 
		{
			Map<String,Object> lookupAttributes =new HashMap<String, Object>();
			
			if(cust.getRetailStoreID()!=null && !cust.getRetailStoreID().isEmpty())
				lookupAttributes.put(RIMConstants.RequestMapping.RETAIL_STORE_ID_KEY, cust.getRetailStoreID());
			if(cust.getCustomerID()!=null && !cust.getCustomerID().isEmpty())
				lookupAttributes.put(RIMConstants.RequestMapping.CUSTOMER_KEY, cust.getCustomerID());
			if(cust.getOrgName()!=null && !cust.getOrgName().isEmpty())
				lookupAttributes.put(RIMConstants.RequestMapping.ORG_NAME_KEY, cust.getOrgName());
			if(cust.getCustOrgTypcode()!=null && !cust.getCustOrgTypcode().isEmpty())
				lookupAttributes.put(RIMConstants.RequestMapping.CUST_ORG_TYPCODE_KEY, cust.getCustOrgTypcode());
			if(cust.getEmployeeID()!=null && !cust.getEmployeeID().isEmpty())
				lookupAttributes.put(RIMConstants.RequestMapping.EMPLOYEE_ID_KEY, cust.getEmployeeID());
			if(cust.getBusinessName()!=null && !cust.getBusinessName().isEmpty())
				lookupAttributes.put(RIMConstants.RequestMapping.BUSSINESS_NAME_KEY, cust.getBusinessName());
			if(cust.getCustomerClass()!=null && !cust.getCustomerClass().isEmpty())
				lookupAttributes.put(RIMConstants.RequestMapping.CUSTOMER_CLASS_KEY, cust.getCustomerClass());
			if(cust.getSource()!=null && !cust.getSource().isEmpty())
				lookupAttributes.put(RIMConstants.RequestMapping.SOURCE_KEY, cust.getSource());
			lookupAttributes.put(RIMConstants.RequestMapping.PROSPECT_KEY, cust.getProspect());
			if(cust.getCustomerNumber()!=null && !cust.getCustomerNumber().isEmpty())
				lookupAttributes.put(RIMConstants.RequestMapping.CUSTOMER_NUMBER_KEY, cust.getCustomerNumber());
			if(cust.getCustomerReference()!=null && !cust.getCustomerReference().isEmpty())
				lookupAttributes.put(RIMConstants.RequestMapping.CUSTOMER_REFERENCE_KEY, cust.getCustomerReference());
			
			//instrument data
/*			if(cust.getCustomerCards()!=null)
			lookupAttributes.put("cardNumber",instrument.getCardNumber());
			lookupAttributes.put("cardSerialNumber",instrument.getCardSerialNumber());*/
			
			//last-update Info
			if(cust.getLastUpdateInfo()!=null)
			{
				if(cust.getLastUpdateInfo().getUpdateUserID()!=null)
					lookupAttributes.put(RIMConstants.RequestMapping.LAST_UPDATE_USER_ID_KEY, cust.getLastUpdateInfo().getUpdateUserID());
				if(cust.getLastUpdateInfo().getUpdateDate()!=null)
					lookupAttributes.put(RIMConstants.RequestMapping.LAST_UPDATE_DATE_KEY, cust.getLastUpdateInfo().getUpdateDate());
			}
			
			//alternate key (considering their would be only one alternateKey as relateModuleId for the particular emailId)
			if(cust.getAlternateKey()!=null && cust.getAlternateKey().length > 0)
			{
				for(AlternateKeyReturnType altKey : cust.getAlternateKey())
				{
					if(altKey.getTypeCode().equalsIgnoreCase(RIMConstants.RequestMapping.AlTERNATE_KEY_TYPECODE))
					{
						lookupAttributes.put(RIMConstants.RequestMapping.AlTERNATE_KEY_TYPECODE, altKey.getAlternateID());
						break;
					}
				}
			}
			
			//get Obsessions
			if(cust.getCustomAttribute()!=null)
			{
				getObsessionDetailMap(cust.getCustomAttribute(), lookupAttributes);
			}
			
			//affliation
			//cust.getAffiliation()
			
			if(cust.getEntityInformation()!=null && cust.getEntityInformation().getIndividual()!=null)
			{
				getEntityInformationDetailMap(cust.getEntityInformation().getIndividual(), lookupAttributes);
			}
			allUsers.add(lookupAttributes);
		}
		
		
		return allUsers;	
	}
	
	private static void getObsessionDetailMap(CustomAttributeReturnType[] obsessions,Map<String, Object> lookupAttributes) 
	{
		if(obsessions!=null && obsessions.length>0)
		{
			for(CustomAttributeReturnType obsession : obsessions)
			{
				String name = obsession.getName()!=null ? obsession.getName() : "";
				String value = (obsession.getAttributeValue()!=null && obsession.getAttributeValue().length > 0) ? obsession.getAttributeValue()[0] : "";
				
				if(!name.isEmpty() && !value.isEmpty())
					lookupAttributes.put(name.toLowerCase(), value);
				
			}
		}		
	}

	public static void getEntityInformationDetailMap(IndividualReturnType individual ,Map< String ,Object > lookupAttributes) 
	{

		if(individual.getSuffix()!=null && !individual.getSuffix().isEmpty())
			lookupAttributes.put(RIMConstants.RequestMapping.SUFFIX_KEY, individual.getSuffix());
		if(individual.getSortingName()!=null && !individual.getSortingName().isEmpty())
			lookupAttributes.put(RIMConstants.RequestMapping.SORTING_NAME_KEY, individual.getSortingName());
		if(individual.getNickName()!=null && !individual.getNickName().isEmpty())
			lookupAttributes.put(RIMConstants.RequestMapping.NICKNAME_KEY, individual.getNickName());
		if(individual.getSalutation()!=null && !individual.getSalutation().isEmpty())
			lookupAttributes.put(RIMConstants.RequestMapping.SALUTATION_KEY,individual.getSalutation());
		
		// PersonalSummary
		if(individual.getPersonalSummary()!=null)
		{
			PersonalSummaryReturnType personalSummary = individual.getPersonalSummary();
			if(personalSummary.getGenderType()!=null && !personalSummary.getGenderType().isEmpty())
				lookupAttributes.put(RIMConstants.RequestMapping.GENDER_KEY,personalSummary.getGenderType());
			if(personalSummary.getBirthDate()!=null)
				lookupAttributes.put(RIMConstants.RequestMapping.BIRTHDATE_KEY,personalSummary.getBirthDate());
			if(personalSummary.getMaritalStatusCode()!=null && !personalSummary.getMaritalStatusCode().isEmpty())
				lookupAttributes.put(RIMConstants.RequestMapping.MARITAL_STATUS_KEY, personalSummary.getMaritalStatusCode());
			if(personalSummary.getEthnicity()!=null && !personalSummary.getEthnicity().isEmpty())
				lookupAttributes.put(RIMConstants.RequestMapping.ENTHICITY_KEY, personalSummary.getEthnicity());
			lookupAttributes.put(RIMConstants.RequestMapping.RENT_KEY, personalSummary.getRent());
			if(personalSummary.getLanguageCode()!=null && !personalSummary.getLanguageCode().isEmpty())
				lookupAttributes.put(RIMConstants.RequestMapping.LANGUAGE_KEY,personalSummary.getLanguageCode());
		}
		
		//SocioEconaomic-Profile
		if(individual.getSocioEconomicProfile()!=null)
		{
			SocioEconomicProfileReturnType socioEconomicProfile = individual.getSocioEconomicProfile();
			if(socioEconomicProfile.getAnnualIncomeAmount()!=null && !socioEconomicProfile.getAnnualIncomeAmount().isEmpty())
				lookupAttributes.put(RIMConstants.RequestMapping.ANNUAL_INCOME_ACCOUNT_KEY,socioEconomicProfile.getAnnualIncomeAmount());
			if(socioEconomicProfile.getNetWorth()!=null && !socioEconomicProfile.getNetWorth().isEmpty())
				lookupAttributes.put(RIMConstants.RequestMapping.NET_WORTH_KEY, socioEconomicProfile.getNetWorth());
			if(socioEconomicProfile.getHighestEducationalLevelName()!=null && !socioEconomicProfile.getHighestEducationalLevelName().isEmpty())
				lookupAttributes.put(RIMConstants.RequestMapping.HIGHEST_EDUCATIONAL_LEVEL_KEY,socioEconomicProfile.getHighestEducationalLevelName());
			
		}
		
		//ContactInformation
		if(individual.getContactInformation()!=null)
		{
			ContactInformationReturnType contactInfo = individual.getContactInformation();
			
			//address
			if(contactInfo.getAddress()!=null && contactInfo.getAddress()[0]!=null)
			{
				AddressReturnType address  = contactInfo.getAddress()[0];
				if(address.getAddressLine1()!=null && !address.getAddressLine1().isEmpty())
					lookupAttributes.put(RIMConstants.RequestMapping.ADDRESSLINE1_KEY,address.getAddressLine1());
				if(address.getAddressLine2()!=null && !address.getAddressLine2().isEmpty())
					lookupAttributes.put(RIMConstants.RequestMapping.ADDRESSLINE2_KEY,address.getAddressLine2());
				if(address.getAddressLine3()!=null && !address.getAddressLine3().isEmpty())
					lookupAttributes.put(RIMConstants.RequestMapping.ADDRESSLINE3_KEY,address.getAddressLine3());
				if(address.getAddressLine4()!=null && !address.getAddressLine4().isEmpty())
					lookupAttributes.put(RIMConstants.RequestMapping.ADDRESSLINE4_KEY,address.getAddressLine4());
				if(address.getApartmentNumber()!=null && !address.getApartmentNumber().isEmpty())	
					lookupAttributes.put(RIMConstants.RequestMapping.APARTMENT_NUMBER_KEY,address.getApartmentNumber());
				if(address.getCity()!=null && !address.getCity().isEmpty())
					lookupAttributes.put(RIMConstants.RequestMapping.CITY_KEY,address.getCity());
				if(address.getCountry()!=null && !address.getCountry().isEmpty())
					lookupAttributes.put(RIMConstants.RequestMapping.COUNTRY_KEY,address.getCountry());
				if(address.getCounty()!=null && !address.getCounty().isEmpty())
					lookupAttributes.put(RIMConstants.RequestMapping.COUNTY_KEY,address.getCounty());
				if(address.getTerritory()!=null && !address.getTerritory().isEmpty())
					lookupAttributes.put(RIMConstants.RequestMapping.TERRITORY_KEY,address.getTerritory());
				if(address.getPostalCode()!=null && !address.getPostalCode().isEmpty())
					lookupAttributes.put(RIMConstants.RequestMapping.POSTAL_CODE_KEY, address.getPostalCode());
			}
			
			//email
			if(contactInfo.getEMail()!=null && contactInfo.getEMail()[0]!=null)
			{
				lookupAttributes.put(RIMConstants.RequestMapping.EMAIL_KEY,contactInfo.getEMail()[0].getEMailAddress());
			}
			
			//telephone
			if(contactInfo.getTelephone()!=null && contactInfo.getTelephone()[0]!=null)
			{
				lookupAttributes.put(RIMConstants.RequestMapping.PHONE_NUMBER_KEY,contactInfo.getTelephone()[0].getPhoneNumber());
				lookupAttributes.put(RIMConstants.RequestMapping.PHONE_EXTENSION_KEY,contactInfo.getTelephone()[0].getExtension());
			}
		}
		
	}

}
