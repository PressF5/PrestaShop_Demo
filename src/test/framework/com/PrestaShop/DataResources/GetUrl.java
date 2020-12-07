package com.PrestaShop.DataResources;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:ProjectData/URLsData.properties")
public interface GetUrl extends Config {

	@Key("UrlAdmin")
	String urlAdmin();

	@Key("UrlSite")
	String urlSite();

}
