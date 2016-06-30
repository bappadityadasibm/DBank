package org.retailer.shoplocator.domain.repo;

import java.util.List;

import org.retailer.shoplocator.domain.model.ShopLocation;
import org.springframework.data.repository.CrudRepository;

public interface ShopLocationRepoImpl extends CrudRepository<ShopLocation, Long>{
	
}
