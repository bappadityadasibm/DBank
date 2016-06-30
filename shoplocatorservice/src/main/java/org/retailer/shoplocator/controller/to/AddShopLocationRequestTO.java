package org.retailer.shoplocator.controller.to;
/**
 * Transfer Object for request to add a Shop Location through Shop Locator Micro Service.
 * @author Bappaditya Das
 *
 */
public class AddShopLocationRequestTO {
	private Long shopId;
	private String shopAddressNumber;
	private String shopAddressPostcode;
	
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
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