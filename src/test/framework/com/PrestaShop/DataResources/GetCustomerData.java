package com.PrestaShop.DataResources;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:ProjectData/CustomerData.properties")
public interface GetCustomerData extends Config{
	
	@Key("FirstName")
	String firstName();

	@Key("LastName")
	String lastName();
	
	@Key("Email")
	String email();

	@Key("Address")
	String address();
	
	@Key("Postcode")
	String postcode();

	@Key("City")
	String city();	
}