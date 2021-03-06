package org.retailer.gateway.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.retailer.gateway.service.GatewayService;
import org.retailer.gateway.to.AddShopLocationRequestTO;
import org.retailer.gateway.to.ShopLocationTO;
import org.retailer.gateway.to.ShopResponseTO;
import org.retailer.gateway.to.ShopTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * Implements the business functionality for saving shop and finding nearest shop w.r.t. given longitude and latitude. 
 * @author Bappaditya Das
 *
 */
@Service
@Transactional
public class GatewayServiceImpl implements GatewayService{
	
	/**
	 * Method to save shop details. First it saves primary shop details through Shop Management Micro Service.
	 * Then it uses the created shop id to save shop location ( longitude and latitude) along with the corresponding 
	 * shop id through Shop Locator Micro Service.
	 * @param shopName
	 * @param shopAddressNumber
	 * @param shopAddressPostcode
	 * 
	 * @return ShopResponseTO
	 * 
	 */
	public ShopResponseTO saveShop(String shopName, String  shopAddressNumber, String shopAddressPostcode) throws ParseException
	{
		final String shopManagementUri = "http://localhost:8083/add";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    ShopTO shopTO = new ShopTO();
	    shopTO.setName(shopName);
	    shopTO.setShopAddressNumber(shopAddressNumber);
	    shopTO.setShopAddressPostcode(shopAddressPostcode);
	    ShopTO addedShop = restTemplate.postForObject(shopManagementUri, shopTO, ShopTO.class);
	    Long shopId = addedShop.getId();
	    System.out.println("fetched shop id="+shopId);
	    
	    final String shopLocationUri = "http://localhost:8082/addShopLocation";
	     
	    AddShopLocationRequestTO addShopLocationRequestTO = new AddShopLocationRequestTO();
	    addShopLocationRequestTO.setShopAddressNumber(shopAddressNumber);
	    addShopLocationRequestTO.setShopAddressPostcode(shopAddressPostcode);
	    addShopLocationRequestTO.setShopId(shopId);
	    ShopLocationTO addedShopLocation = restTemplate.postForObject(shopLocationUri, addShopLocationRequestTO, ShopLocationTO.class);
	    
	    ShopResponseTO shopResponseTO = new ShopResponseTO();
	    shopResponseTO.setId(shopId);
	    shopResponseTO.setName(addedShop.getName());
	    shopResponseTO.setShopAddressNumber(addedShop.getShopAddressNumber());
	    shopResponseTO.setShopAddressPostcode(addedShop.getShopAddressPostcode());
	    shopResponseTO.setLatitude(addedShopLocation.getLatitude());
	    shopResponseTO.setLongitude(addedShopLocation.getLongitude());
	    
		return shopResponseTO;
	}
	
	/**
	 * Method to find nearest shop location. First a Shop Locator Micro Service is called get Shop Location 
	 * ( latitude and longitude) and Shop Id. Later this Shop Id is used to call Shop Management Micro Service
	 * to get the primary Shop Details.
	 * 
	 * Response from these two Micro Service opearions are aggregated and returned as Response.
	 * 
	 * @param latitude
	 * @param longitude
	 * @return Shop Response Transfer Object
	 * 
	 */
	public ShopResponseTO findNearestShopLocation(String latitude,String longitude)
	{
		final String shopLocationUri = "http://localhost:8082/nearestShop/{latitude}/{longitude}";
		RestTemplate restTemplate = new RestTemplate();

	    Map<String, String> shopLocationServiceParams = new HashMap<String, String>();
	    shopLocationServiceParams.put("latitude", latitude);
	    shopLocationServiceParams.put("longitude",longitude);
	     
	    ShopLocationTO shopLocationTO = restTemplate.getForObject(shopLocationUri, ShopLocationTO.class, shopLocationServiceParams);
	    
	    System.out.println("Fetched Nearest Shop id="+shopLocationTO.getShopId());
	    
	    final String shopServiceUri = "http://localhost:8083/find/{id}";
	    Map<String, String> shopServiceParams = new HashMap<String, String>();
	    shopServiceParams.put("id", String.valueOf(shopLocationTO.getShopId()));
	    
	    ShopTO shopTO = restTemplate.getForObject(shopServiceUri, ShopTO.class, shopServiceParams);
	    
	    ShopResponseTO shopResponseTO = new ShopResponseTO();
	    shopResponseTO.setId(shopTO.getId());
	    shopResponseTO.setLatitude(shopLocationTO.getLatitude());
	    shopResponseTO.setLongitude(shopLocationTO.getLongitude());
	    shopResponseTO.setName(shopTO.getName());
	    shopResponseTO.setShopAddressNumber(shopTO.getShopAddressNumber());
	    shopResponseTO.setShopAddressPostcode(shopTO.getShopAddressPostcode());
	    
	    return shopResponseTO;
	   
	}
	
}
