package com.relateIntegration.services.response;

import java.util.List;

public class LookupResponse extends RIMResponse
{
		private List<Object> customerList;

		public List<Object> getCustomerList() {
			return customerList;
		}
		public void setCustomerList(List<Object> customerList) {
			this.customerList = customerList;
		}
}
