package org.retailer.shopmanagement.domain.service.impl;

import org.retailer.shopmanagement.domain.ito.ShopITO;
import org.retailer.shopmanagement.domain.model.Shop;
import org.retailer.shopmanagement.domain.repo.ShopRepoImpl;
import org.retailer.shopmanagement.domain.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Domain Service for handling Shop Domain related activities. It interacts with corresponding
 * Domain Repository for Shop Domain.
 * @author Bappaditya Das
 *
 */

@Service
@Transactional
public class ShopServiceImpl implements ShopService{
	
	@Autowired
	private ShopRepoImpl shopRepo;
	
	/**
	 * Finds a Shop by Id
	 * @param id
	 * @return ShopITO
	 */
	public ShopITO findById(long id)
	{
		Shop shop = shopRepo.findOne(id);
		ShopITO shopITO = new ShopITO();
		shopITO.setId(shop.getId());
		shopITO.setName(shop.getName());
		shopITO.setShopAddressNumber(shop.getShopAddressNumber());
		shopITO.setShopAddressPostcode(shop.getShopAddressPostcode());
		return shopITO;
	
	}
	
	/**
	 * Creates a new Shop.
	 * @param shopName
	 * @param shopAddressNumber
	 * @param shopAddressPostcode
	 * @return ShopITO
	 */
	public ShopITO saveShop(String shopName, String  shopAddressNumber, String shopAddressPostcode)
	{
		Shop shop = new Shop();
		shop.setName(shopName);
		shop.setShopAddressNumber(shopAddressNumber);
		shop.setShopAddressPostcode(shopAddressPostcode);	
		shopRepo.save(shop);
		
		ShopITO shopITO = new ShopITO();
		shopITO.setId(shop.getId());
		shopITO.setName(shop.getName());
		shopITO.setShopAddressNumber(shop.getShopAddressNumber());
		shopITO.setShopAddressPostcode(shop.getShopAddressPostcode());
		return shopITO;
	}
	
}
