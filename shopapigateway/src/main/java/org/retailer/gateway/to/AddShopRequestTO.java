package org.retailer.gateway.to;

/**
 * Transfer Object for sending request to add Shop Details. Retail Manager uses this to call Gateway for Add Shop request( RESTful POST to /shops).
 * @author Bappaditya Das
 *
 */
public class AddShopRequestTO {
	
	private String shopName;
	private String shopAddressNumber;
	private String shopAddressPostcode;
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
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
