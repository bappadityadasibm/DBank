package org.retailer.shopmanagement.domain.service;

import org.retailer.shopmanagement.domain.ito.ShopITO;

public interface ShopService {
	public ShopITO saveShop(String shopName, String  shopAddressNumber, String shopAddressPostcode);
	public ShopITO findById(long id);
	
}
