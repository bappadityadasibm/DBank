package org.retailer.shoplocator.domain.model;
import javax.persistence.*;

@Entity
public class ShopLocation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private long shopId;
	private double longitude;
	private double latitude;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getShopId() {
		return shopId;
	}
	public void setShopId(long  shopId) {
		this.shopId = shopId;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	
}
