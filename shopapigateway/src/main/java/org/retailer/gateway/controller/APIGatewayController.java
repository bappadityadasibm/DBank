package org.retailer.gateway.controller;

import org.retailer.gateway.service.GatewayService;
import org.retailer.gateway.to.AddShopRequestTO;
import org.retailer.gateway.to.ShopResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class act as API Gateway for any interaction by client which wants to access the microservices.
 * This class exposes composite services which aggregates responses from multiple micro services. 
 * 
 * @author Bappaditya Das
 * 
 * 
 */
@RestController
public class APIGatewayController {

	
	@Autowired
    private GatewayService gatewayService;

	/**
	 * 
	 * Controller method to handle addition of a new shop through RESTful POST to /shops
	 * @param AddShopRequestTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.POST, value = "/shops")
	public ShopResponseTO addShop(@RequestBody AddShopRequestTO requestTO) throws Exception{
		System.out.println("Entered Gateway Controller -> addShop()!!!!!!");	
		String shopName = requestTO.getShopName();
		String shopAddressNumber = requestTO.getShopAddressNumber();
		String shopAddressPostcode = requestTO.getShopAddressPostcode();
		ShopResponseTO shopResponseTO = null;
		try
		{
			shopResponseTO = gatewayService.saveShop(shopName,shopAddressNumber,shopAddressPostcode);
		}
		catch(Exception ex)
		{
			throw new Exception(ex.getMessage());
		}
		
		return shopResponseTO;
	}
	
	/**
	 * Controller method to retrieve nearest shop from the given longitude and latitude through RESTful GET request to /locateshop
	 * @param latitude
	 * @param longitude
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/locateshop/{latitude}/{longitude}")
	public ShopResponseTO findNearestShop(@PathVariable String latitude, @PathVariable String longitude) throws Exception
	{
		System.out.println("Entered Gateway Controller -> findNearestShop()!!!!!!");	
		System.out.println("Gateway input longitude:"+longitude);
		ShopResponseTO shopResponseTO = null;
		try
		{
			shopResponseTO = gatewayService.findNearestShopLocation(latitude,longitude);
		}
		catch(Exception ex)
		{
			throw new Exception(ex.getMessage());
		}
		
		return shopResponseTO;
	}
}
