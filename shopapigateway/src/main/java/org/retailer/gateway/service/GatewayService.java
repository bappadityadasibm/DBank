package org.retailer.gateway.service;

import org.retailer.gateway.to.ShopResponseTO;
import org.json.simple.parser.ParseException;
/**
 * Interface for GatewayService 
 * @author Bappaditya Das
 *  
 */
public interface GatewayService {
	public ShopResponseTO saveShop(String shopName, String  shopAddressNumber, String shopAddressPostcode) throws ParseException;
	public ShopResponseTO findNearestShopLocation(String latitude,String longitude);
	
}
