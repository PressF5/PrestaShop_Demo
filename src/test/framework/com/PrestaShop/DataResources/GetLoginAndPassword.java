package com.PrestaShop.DataResources;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:ProjectData/DataLoginInAdmin.properties")
public interface GetLoginAndPassword extends Config{
	
	@Key("LoginAdmin")
	String login();
	
	@Key("PasswordAdmin")
	String password();
}
