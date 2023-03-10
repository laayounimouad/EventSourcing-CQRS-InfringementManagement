
## Class Diagram
![](pictures/1.png)
## MicroServices 
![](pictures/2.png)
## RadarService
Create radar :![](pictures/3.png)
Update radar :![](pictures/4.png)
GetAll Radars:![](pictures/5.png)
## ImmatriculationServie
Create Owner :![](pictures/6.png)
Create Vehicule:![](pictures/7.png)
Update Owner:![](pictures/8.png)
Update Vehicule:![](pictures/9.png)
GetAll Owners:![](pictures/10.png)
GetAllVehicules:![](pictures/11.png)
Get Vehicules By Owner :![](pictures/12.png)
## InfractionService
create Infraction : ![](pictures/13.png)
getAll infractions: ![](pictures/14.png)
## dependencies
- All:
  - jpa
  - web
  - lombok
  - h2 / mysql
  ```xml
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
    ```
- DiscoveryService : 
  - eureka-server
- GatewayService :
  - eureka-client
  - gateway (spring-cloud-starter-gateway)
- RadarService :
  - eureka-client
  - jpa
  - web
  - lombok
  - h2
  - Axon 
    ```xml
    <dependency>
            <groupId>org.axonframework</groupId>
            <artifactId>axon-spring-boot-starter</artifactId>
            <version>4.6.2</version>
            <exclusions>
                <exclusion>
                    <groupId>org.axonframework</groupId>
                    <artifactId>axon-server-connector</artifactId>
                </exclusion>
            </exclusions>
    </dependency>
    ```
  - optional : mapstruct (create mappers)
    ```xml
    <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
            <version>${mapstruct-version}</version>
    </dependency>
    ```
  - optional : springdoc-openapi (api doc)
    ```xml
    <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-ui</artifactId>
            <version>1.5.2</version>
    </dependency>
    ```
- ImmatriculationService :
  - Same As previous service
- InfractionService :
  - Same As previous service
  - OpenFeign
  - ```
          <dependency>
			  <groupId>org.springframework.cloud</groupId>
			  <artifactId>spring-cloud-starter-openfeign</artifactId>
		  </dependency>
    ```
- Common-Api : maven project
  - Lombok
    ```xml
    <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.24</version>
            <optional>true</optional>
    </dependency>
    ```
  - Axon


## RadarService
### Command Side
 1. Create Controller:
    ```java
    @Controller
    @RequestMapping("/commands/radar")
    @AllArgsConstructor
    public class RadarCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping("/create")
    private CompletableFuture<String> createRadar(@RequestBody CreateRadarRequestDTO request){
        return commandGateway.send(new CreateRadarCommand(
                UUID.randomUUID().toString(),
                request.getMax_vitesse(),
                request.getLongitude(),
                request.getLatitude()
        ));
    }
    }
    ```
  2. Create CreateRadarRequestDTO
   ```java
   @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public class CreateRadarRequestDTO {
    private double max_vitesse;
    private double longitude;
    private double latitude;
  } 
  ```
  3. Create CreateRadarCommand in common
        ```java
        public class CreateRadarCommand extends BaseCommand<String> {
      @Getter private double max_vitesse;
      @Getter private double longitude;
      @Getter private double latitude;

       public CreateRadarCommand(String id, double max_vitesse, double longitude, double latitude) {
        super(id);
        this.max_vitesse = max_vitesse;
        this.longitude = longitude;
        this.latitude = latitude;
       }
      } 
      ```
  4. Create RadarAggregate
   ```java
   @Aggregate
    public class RadarAggregate {
    @AggregateIdentifier
    private String id;
    private double max_vitesse;
    private double longitude;
    private double latitude;

    public RadarAggregate() {
    }


    @CommandHandler
    public RadarAggregate(CreateRadarCommand command){
        if(command.getMax_vitesse()<0) throw new EntryNegativeException("speed should be positive");
        AggregateLifecycle.apply(new RadarCreatedEvent(
                command.getId(),
                command.getMax_vitesse(),
                command.getLongitude(),
                command.getLatitude()
        ));
    }

    @EventSourcingHandler
    public void on(RadarCreatedEvent event){
        this.id=event.getId();
        this.latitude= event.getLatitude();
        this.longitude= event.getLongitude();
        this.max_vitesse=event.getMax_vitesse();
    }
    }
    ```
  5. Create RadarCreatedEvent
  ```java
    public class RadarCreatedEvent extends BaseEvent<String> {
    @Getter private double max_vitesse;
    @Getter private double longitude;
    @Getter private double latitude;

    public RadarCreatedEvent(String id, double max_vitesse, double longitude, double latitude) {
        super(id);
        this.max_vitesse = max_vitesse;
        this.longitude = longitude;
        this.latitude = latitude;
    }
  }
  ```
!**xStream** Error -> add
   ```java
   @Bean
	public XStream xStream() {
		XStream xStream = new XStream();

		xStream.allowTypesByWildcard(new String[] { "**" });
		return xStream;
	}
  ```   

6. properties :
  ```properties
  spring.application.name=radar-service
  spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:${MYSQL_HOST:3306}/radarDB?createDatabaseIfNotExist=true
  spring.datasource.username=${MYSQL_USER:root}
  spring.datasource.password=${MYSQL_PASSWORD:}
  spring.jpa.hibernate.ddl-auto=update

  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
  server.port=7002
  #eureka.instance.prefer-ip-address=true
  #spring.cloud.discovery.enabled=true
  ```

  

### Gateway service
add class GatewayConfig :
```java
@Configuration
public class GatewayConfig {
    @Bean
    DiscoveryClientRouteDefinitionLocator discoveryClientRouteDefinitionLocator(ReactiveDiscoveryClient reactiveDiscoveryClient,
                                                                                DiscoveryLocatorProperties discoveryLocatorProperties){
        return new DiscoveryClientRouteDefinitionLocator(reactiveDiscoveryClient, discoveryLocatorProperties);
    }
}
```
add properties :
```properties
eureka.client.register-with-eureka=true
server.port=7000
spring.application.name=GATEWAY-SERVICE
```

### Discovery server
add to main class `@EnableEurekaServer`
add to properties :
```properties
server.port=8761
eureka.instance.prefer-ip-address=true
eureka.client.fetch-registry=false
eureka.client.register-with-eureka=false
```

!problems
 2.5.13
   @CrossOrigin("*")

  could not find concurrent : fix  --add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/java.util.concurrent=ALL-UNNAMED

  java.lang.IllegalStateException: loadBalancerClientFactory
    fix : check that you have the same spring version in all projects

Xstream : fix 
```java

	@Bean
	public XStream xStream() {
		XStream xStream = new XStream();

		xStream.allowTypesByWildcard(new String[] { "**" });
		return xStream;
	}
```
spring version :
<version>2.7.6</version>
<spring-cloud.version>2021.0.5</spring-cloud.version>