Problem: As mentioned in given pdf.

Solution: The solution comprises of two Micro Services( Shop Locator and Shop Management) and a API Gateway which is the single entry point for all clients. Three Spring Boot applications
have been created realizing two micro services and the gateway. As per the problem statement, Shop details consist of Shop Name, Shop Address Number, Shop Address Post Code, longitude and latitude. Database per Service has been used as Data Management pattern. To keep each Micro Services persistence data private to it, I have divided the logical data model representing a Shop into two physical relational table. Shop Management Micro Service works with Shop entity comprising of shop name, shop address number and shop address post code. Shop Locator Micro Service works with Shop Location entity comprising of Shop Id, longitude and latitude. 

The API Gateway exposes two client specific APIs, namely Add a Shop( RESTful POST to /shops) and Locate nearest Shop ( RESTful GET to /locateshop/{latitude}/{longitude} ). 
API for adding a shop is a composite service which calls two fine grained APIs( RESTful POST to /add of Shop Management Micro Service and RESTful POST to /addShopLocation of Shop Locator Micro Service).
API for locate nearest shop is also another composite service which aggregates response of two fine grained APIs ( RESTful GET to /nearestShop of Shop Locator Micro Service and RESTful GET to /find of Shop Management Micro Service).

Code Base: Three Maven Projects have been created for two micro services and gateway. 

Build Instruction: Apache Maven 3.3.3 has been used as build tool. 
	1. Go to shopapigateway folder and execute the command -> mvn clean install
	2. Go to shopmanagementservice folder and execute the command -> mvn clean install
	3. Go to shoplocatorservice folder and execute the command -> mvn clean install
	
	For 2 and 3, unit test cases will also run when we execute the above mentioned commands. 
	
Run Instruction : 
	1. Open three console.
	2. In one console go to shopapigateway\target folder and execute the command --> java -jar shopapigateway-0.0.1-SNAPSHOT.jar
	3. In another console go to shopmanagementservice\target folder and execute the command --> java -jar shopmanagementservice-0.0.1-SNAPSHOT.jar
	4. In another console go to shoplocatorservice\target folder and execute the command --> java -jar shoplocatorservice-0.0.1-SNAPSHOT.jar
	
Test Instruction :

	Use Chrome Postman or SOAPUI to test two APIs.
	
	A) Valid sample Data for Add a Shop API ->
	1. Method = POST, Endpoint = http://localhost:8084, Resource = /shops, 
	payload = 
	
	{
		"shopName":"palki",
		"shopAddressNumber": "kendua main road kolkata india",
		"shopAddressPostcode": "700084"
	}
	
	2. Method = POST, Endpoint = http://localhost:8084, Resource = /shops, 
	payload = 
	
	{
		"name": "bhimnag",
		"shopAddressNumber": "B B Ganguly Street kolkata india",
		"shopAddressPostcode": "700012"
	}
	
	
	B) Valid sample Data for Locate nearest Shop API ->
	1. Method = GET, Endpoint = http://localhost:8084, Resource = /locateshop/22.472/88.381
	Note: 22.472 is latitude and 88.381 is longitude.