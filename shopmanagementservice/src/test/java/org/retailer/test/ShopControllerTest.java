package org.retailer.test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.retailer.shopmanagement.Application;
import org.retailer.shopmanagement.domain.ito.ShopITO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

/**
 * Test class for Shop Management Micro Service.
 * @author Bappaditya Das
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class ShopControllerTest {

    @Value("${local.server.port}")
    private int port;

	private RestTemplate template;

	@Before
	public void setUp() throws Exception {
		
		template = new TestRestTemplate();
	}

	@Test
	public void testAddShop() throws Exception {
		 URL uri = new URL("http://localhost:" + port + "/add");
		
		 ShopITO shopITO = new ShopITO();
		 shopITO.setName("Bhim Nag");
		 shopITO.setShopAddressNumber("B B Ganguly Street");
		 shopITO.setShopAddressPostcode("700012");
		 ShopITO shopAdded = template.postForObject(uri.toString(), shopITO, ShopITO.class);
		 
		 assertThat(shopAdded.getName(),equalTo("Bhim Nag"));
		 assertThat(shopAdded.getShopAddressNumber(),equalTo("B B Ganguly Street"));
		 assertThat(shopAdded.getShopAddressPostcode(),equalTo("700012"));
	}
	
	@Test
	public void testGetShop() throws Exception {
		 URL uri1 = new URL("http://localhost:" + port + "/add");
		
		 ShopITO shop1 = new ShopITO();
		 shop1.setName("Popular Sweet");
		 shop1.setShopAddressNumber("kendua main road");
		 shop1.setShopAddressPostcode("700084");
		 template.postForObject(uri1.toString(), shop1, ShopITO.class);
		 
		 URL uri2 = new URL("http://localhost:" + port + "/add");
			
		 ShopITO shop2 = new ShopITO();
		 shop2.setName("Krishna Sweets");
		 shop2.setShopAddressNumber("Hill Cart Road");
		 shop2.setShopAddressPostcode("734001");
		 template.postForObject(uri2.toString(), shop2, ShopITO.class);  
	    
	     URL url = new URL("http://localhost:" + port + "/find/3");
	     ShopITO returnedShop = template.getForObject(url.toString(), ShopITO.class);
	    
	     assertThat(returnedShop.getName(),equalTo("Krishna Sweets"));
	     assertThat(returnedShop.getShopAddressNumber(),equalTo("Hill Cart Road"));
	     assertThat(returnedShop.getShopAddressPostcode(),equalTo("734001"));
	    
	    
	}
}
