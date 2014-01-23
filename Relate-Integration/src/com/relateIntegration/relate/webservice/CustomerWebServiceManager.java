package com.relateIntegration.relate.webservice;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub;
import com.dtv.csx.webservices.customer.RelateProcessingExceptionException0;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.AddOrUpdateCustomer;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.AddOrUpdateCustomerE;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.AddOrUpdateCustomerResponseE;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.CustIdentifier;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.CustomerResponseType;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.MergeCustomers;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.MergeCustomersE;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.MergeCustomersResponseE;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.RetrieveCustomer;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.RetrieveCustomerE;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.RetrieveCustomerResponseE;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.SearchCustomers;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.SearchCustomersE;
import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub.SearchCustomersResponseE;
import com.relateIntegration.util.RIMConstants;




public class CustomerWebServiceManager 
{
	public static CustomerServicesApiServiceStub relateWSStub;
	
	public static void setRelateWSStub(CustomerServicesApiServiceStub relateWSStub) {
		CustomerWebServiceManager.relateWSStub = relateWSStub;
	}

	public static String addOrUdateCustomer (Map<String, Object> customerInformation , int relateIntegrationId)
	{
		AddOrUpdateCustomerE addOrUpdateCustomerE = new AddOrUpdateCustomerE();
		AddOrUpdateCustomer customer =new AddOrUpdateCustomer();
		
		customer.setCustomerData(CustomerWebserviceSupport.getCustomerData(customerInformation , relateIntegrationId));
		
		addOrUpdateCustomerE.setAddOrUpdateCustomer(customer);
		AddOrUpdateCustomerResponseE responseE =null;
		String responseId=null;
		
		try 
		{
			responseE = (AddOrUpdateCustomerResponseE ) relateWSStub.addOrUpdateCustomer(addOrUpdateCustomerE);
		}
		catch (RemoteException e) 
		{
			//System.out.println("Error occurred in addOrUdateCustomer process for relate: " + e.getMessage() );
		} 
		catch (RelateProcessingExceptionException0 e) 
		{
			//System.out.println("Error occurred in addOrUdateCustomer process for relate: " + e.getMessage() );
		}
		
		if(responseE!=null && responseE.getAddOrUpdateCustomerResponse()!=null)
		{
			responseId = responseE.getAddOrUpdateCustomerResponse().getCustomerId();
		}
		return responseId;
		
	}
	
	

	public static List<Object> searchCustomers (Map<String,Object> customerInformation , int relateIntegrationId)
	{
		SearchCustomersE customersE= new SearchCustomersE();
		SearchCustomers customers= new SearchCustomers();
		
		customers.setCustomerData(CustomerWebserviceSupport.getCustomerDataLookup(customerInformation , relateIntegrationId));
			
		customersE.setSearchCustomers(customers);
		SearchCustomersResponseE responseE =null;
		
		try 
		{
			responseE = (SearchCustomersResponseE)relateWSStub.searchCustomers(customersE);
		} 
		catch (RemoteException e) 
		{
			//System.out.println("Error occurred in searchCustomers process for relate: " + e.getMessage() );
		}
		catch (RelateProcessingExceptionException0 e) 
		{
			//System.out.println("Error occurred in searchCustomers process for relate: " + e.getMessage() );
		}
		if(responseE!=null && responseE.getSearchCustomersResponse()!=null)
		{
			CustomerResponseType customersResponse[] =responseE.getSearchCustomersResponse().get_return().getCustomer();
			return CustomerWebserviceSupport.getCustomerDataMapsFromLookupResponse(customersResponse);		
		}
		return null;
		
	}
	
	public static List<Object>  retrieveCustomer(int alternateId)
	{
		RetrieveCustomerE customersE= new RetrieveCustomerE();
		RetrieveCustomer customer= new RetrieveCustomer();
		
		customer.setAlternateKeyList(CustomerWebserviceSupport.getAlternateKeyLookup(String.valueOf(alternateId)));

		customersE.setRetrieveCustomer(customer);
		RetrieveCustomerResponseE responseE =null;

		
		try 
		{
			responseE = (RetrieveCustomerResponseE)relateWSStub.retrieveCustomer(customersE);
		} 
		catch (RemoteException e) 
		{
			//System.out.println("Error occurred in retrieveCustomer process for relate: " + e.getMessage() );
		}
		catch (RelateProcessingExceptionException0 e) 
		{
			//System.out.println("Error occurred in retrieveCustomer process for relate: " + e.getMessage() );
		}
		if(responseE!=null && responseE.getRetrieveCustomerResponse()!=null)
		{
			CustomerResponseType[] customersResponse = new CustomerResponseType[1];
			customersResponse[0] = responseE.getRetrieveCustomerResponse().get_return();
			return CustomerWebserviceSupport.getCustomerDataMapsFromLookupResponse(customersResponse);
		}
		return null;
		
	}
	
	public static String  mergeCustomers(CustIdentifier custSource, CustIdentifier[] custTarget)
	{
		MergeCustomers mergeCustomers = new MergeCustomers();
		MergeCustomersE mergeCustomersE = new MergeCustomersE();
		
		mergeCustomers.setMergeSource(custSource);
		mergeCustomers.setMergeTargetSet(custTarget);
		
		mergeCustomersE.setMergeCustomers(mergeCustomers);
		MergeCustomersResponseE responseE =null;
		String responseId=null;
		
		try 
		{
			responseE = (MergeCustomersResponseE)relateWSStub.mergeCustomers(mergeCustomersE);
		} 
		catch (RemoteException e) 
		{
			//System.out.println("Error occurred in mergeCustomer process for relate: " + e.getMessage() );
		}
		catch (RelateProcessingExceptionException0 e) 
		{
			//System.out.println("Error occurred in mergeCustomer process for relate: " + e.getMessage() );
		}
		if(responseE!=null && responseE.getMergeCustomersResponse()!=null)
		{
			responseId = responseE.getMergeCustomersResponse().getCustomerId();
		}
		return responseId;
		
	}
	
	@SuppressWarnings("unchecked")
	public static String mergeCustomers(List<Object> customerList , String relateIntegrationId) 
	{
		CustIdentifier[] custIndentifiers = new CustIdentifier[customerList.size()];
		int index=0;
		String backUpSourceCustomerId="";
		CustIdentifier sourceCustomer = null;
		boolean sourceTargetExists= false;
		Iterator<Object> custIter = customerList.iterator();
		while(custIter.hasNext())
		{
			Map<String , Object> temp = (HashMap<String, Object>)custIter.next();
			CustIdentifier tempCustIdendtifer = new CustIdentifier();
			if( temp.containsKey(RIMConstants.RequestMapping.AlTERNATE_KEY_TYPECODE) && temp.get(RIMConstants.RequestMapping.AlTERNATE_KEY_TYPECODE).toString().equals(relateIntegrationId) && !sourceTargetExists )
			{
				sourceCustomer = new CustIdentifier();
				sourceCustomer.setAlternateKeyTypeCode(RIMConstants.RequestMapping.AlTERNATE_KEY_TYPECODE);
				sourceCustomer.setAlternateKey(temp.get(RIMConstants.RequestMapping.AlTERNATE_KEY_TYPECODE).toString());
				sourceTargetExists=true;
			}
			else if(temp.containsKey(RIMConstants.RequestMapping.CUSTOMER_KEY))
			{
				tempCustIdendtifer.setCustomerId(temp.get(RIMConstants.RequestMapping.CUSTOMER_KEY).toString());
				custIndentifiers[index++]= tempCustIdendtifer;
				backUpSourceCustomerId = temp.get(RIMConstants.RequestMapping.CUSTOMER_KEY).toString();
			}
		}	
		
		if(!sourceTargetExists)
		{	
			sourceCustomer = new CustIdentifier();
			sourceCustomer.setCustomerId(backUpSourceCustomerId);
		}
		
		//merge customers 
		String mergeCustomerId = "";
		mergeCustomerId = mergeCustomers(sourceCustomer, custIndentifiers);
		
		return mergeCustomerId;
		
	}
	
	
	
	
	

}