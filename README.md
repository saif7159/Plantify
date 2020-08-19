# Plantify
My personal project idea 
I have considered a shopping portal for plants, It contains three main services:
  1. user-service: It connects to the user database and stores login and user information, It also does user authentication using spring sercurity and generates a JWT Token
                    (Note: The JWT authorization takes place on zuul gateway in order secure the requests from gateway itself).
  2. catalogue-service: Performs the CRUD operation for plants and accessory catalogue.
  3. cart-service: Communicates with user-service and catalogue-service both using feign to get the product and user detail in order to add the item to the cart.
  
Other files:
  1. eureka-server: For discovery service.
  2. config-server: Config server for reading application.yml
  3. zuul-gateway: Gateway which also authorizes JWT token and secures the requests to the services.
  4. configuration-files: application.yml files for config server to read.
