cd ServiceRegistry
start "ServiceRegistry" .\mvnw spring-boot:run
timeout 10
cd MarketService
start "MarketService" .\mvnw spring-boot:run
cd ..
cd NewRefService
start "RefdataService" .\mvnw spring-boot:run
cd ..
cd NotificationService
start "NotificationService" .\mvnw spring-boot:run
cd ..
cd TradingService
start "TradingService" .\mvnw spring-boot:run
cd ..
cd APIGateway
start "APIGateway" .\mvnw spring-boot:run
cd ..
