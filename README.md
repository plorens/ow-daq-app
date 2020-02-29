# One wire data acquisition

**Projekt i implementacja systemu akwizycji danych o temperaturze otoczenia w oparciu o  sieć 1-Wire** 

Celem projektu jest zbudowanie systemu akwizycji danych pomiarowych (temperatura)
współpracującego z siecią 1-Wire i posiadającego wielodostępne interfejsy - GUI dla człowieka oraz API dla
użytkowników aplikacyjnych (np. wizualizacje w LabVIEW)

System ma współpracować z dowolną, konfigurowalną ilością czujników temperatury, ustawienia systemu
jak i dane pomiarowe mają być przechowywane w zdalnej, relacyjnej bazie danych.

|Model & schema|
|------------------------------------|
|![ow-daq_model](images/ow-daq_model.png)|
|![ow-daq_schema](images/ow-daq_schema.png)|




|guest panel|
|------------------------------------|
|![Screen_home](images/Screen_home.png)|
|![Screen_readings](images/Screen_readings.png) |admin panel|
|![Screen_chart](images/Screen_chart.png)|



|admin panel|
|------------------------------------|
|![Screen_adapters](images/Screen_adapters.png)|
|![Screen_sites](images/Screen_sites.png)|
|![Screen_sensors](images/Screen_sensors.png)|
|![Screen_eventlogs](images/Screen_eventlogs.png)|
|![Screen_users](images/Screen_users.png)|


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



### Todos

 - Dodanie indykatorów zdarzeń alarmowych (przekroczeń wartości temperatury, awarii lub niedostępności czujników)
 - Dodanie obsługi czujników wilgotności 
