package org.retailer.shopmanagement.domain.repo;

import java.util.List;

import org.retailer.shopmanagement.domain.model.Shop;
import org.springframework.data.repository.CrudRepository;

public interface ShopRepoImpl extends CrudRepository<Shop, Long>{
	
}
