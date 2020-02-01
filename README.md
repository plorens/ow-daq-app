# ow-daq-app
Projekt i implementacja systemu akwizycji danych o temperaturze otoczenia w oparciu o  sieć 1-Wire

|  guest panel           |  admin panel |
|---------------------|----------------------|
|![Screen_home](images/Screen_home.png)| ![Screen_adapters](images/Screen_adapters.png)|
|![Screen_readings](images/Screen_readings.png)| ![Screen_sites](images/Screen_sites.png)|
|![Screen_chart](images/Screen_chart.png)| ![Screen_sensors](images/Screen_sensors.png)|
|| ![Screen_eventlogs](images/Screen_eventlogs.png)|
|| ![Screen_users](images/Screen_users.png)|

 ![ow-daq_schema](images/ow-daq_schema.png)
 
  ![ow-daq_model](images/ow-daq_model.png)

### Tech

* Java SE 8
* Spring boot
* Apache Maven
* Oracle Database 18c Express Edition (XE) 
* OneWireAPI
* Thymeleaf


### Development

Adding maven libraries

```
mvn install:install-file -Dfile=OneWireAPI.jar -DgroupId=additional.libs -DartifactId=OneWireAPI-lib -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true
```
```
mvn install:install-file -Dfile=RXTXcomm.jar -DgroupId=additional.libs -DartifactId=RXTXcomm-lib -Dversion=1.0  -Dpackaging=jar -DgeneratePom=true
```
```
mvn install:install-file -Dfile= ojdbc8.jar -DgroupId=com.oracle -DartifactId=ojdbc8 -
Dversion=12.2.0.1 -Dpackaging=jar
```




### Installation

xxx:
```

```


### Todos

 - xxx
 - xxx
