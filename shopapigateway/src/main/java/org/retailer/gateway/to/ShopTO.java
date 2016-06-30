package org.retailer.gateway.to;

/**
 * 
 * Response TO from Shop Management Micro Service
 * @author Bappaditya Das
 * 
 *
 */

public class ShopTO {
	private Long id;
	private String name;
	private String shopAddressNumber;
	private String shopAddressPostcode;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShopAddressNumber() {
		return shopAddressNumber;
	}
	public void setShopAddressNumber(String shopAddressNumber) {
		this.shopAddressNumber = shopAddressNumber;
	}
	public String getShopAddressPostcode() {
		return shopAddressPostcode;
	}
	public void setShopAddressPostcode(String shopAddressPostcode) {
		this.shopAddressPostcode = shopAddressPostcode;
	}
	
}
