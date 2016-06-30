package org.retailer.shoplocator.domain.service;

import org.retailer.shoplocator.domain.ito.ShopLocationITO;

public interface ShopLocationService {
	public ShopLocationITO findNearestShop(String latitude, String longitude);
	public ShopLocationITO createShopLocation(String shopAddressNumber,String shopAddressPostcode, long shopId) throws Exception;
}
