# QINDEL TEST APPLICATION

Qindel test application

## Statement:

### Contexto:

En la base de datos de comercio electrónico de la compañía disponemos de la tabla PRICES que refleja el 
precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas. A 
continuación se muestra un ejemplo de la tabla con los campos relevantes:

|BRAND_ID|START_DATE|END_DATE|PRICE_LIST|PRODUCT_ID|PRIORITY|PRICE|CURRENCY|
|------- |----------|--------|----------|----------|--------|-----|--------|
|1|2020-06-14-00.00.00|2020-12-31-23.59.59|1|35455|0|35.50|EUR|
|1|2020-06-14-15.00.00|2020-06-14-18.30.00|2|35455|1|25.45|EUR|
|1|2020-06-15-00.00.00|2020-06-15-11.00.00|3|35455|1|30.50|EUR|
|1|2020-06-15-16.00.00|2020-12-31-23.59.59|4|35455|1|38.95|EUR|

### Explicación de los campos:
BRAND_ID: foreign key de la cadena del grupo (1 = ZARA).

START_DATE: END_DATE: rango de fechas en el que aplica el precio tarifa indicado.

PRICE_LIST: IdenQficador de la tarifa de precios aplicable.

PRODUCT_ID: IdenQficador código de producto.

PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica 
la de mayor prioridad (mayor valor numérico).

PRICE: precio final de venta.

CURR: ISO de la moneda.

### Objetivos

Construir una aplicación/servicio en SpringBoot que provea una endpoint REST de consulta que:

- Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador 
de cadena.

- Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, 
fechas de aplicación y precio final a aplicar.

- Se debe utilizar una base de datos en memoria (h2) e inicializar con los datos del ejemplo, (se 
pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere, elegir el tipo de dato 
que se considere adecuado para los mismos).
 
- Desarrollar unos test al Endpoint REST que validen las siguientes peticiones al servicio con los 
datos del ejemplo:
 
   - Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
   - Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
   - Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
   - Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
   - Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA

## Technologies used:

- Microsoft Windows 10 Professional Edition x64
- Java 17.
- Spring Boot 3.2.4
- Embedded H2 database 2.1.214

## Execution:

- Go to baseline and run "mvn spring-boot:run"
- Go to baseline\target and run "java -jar ./QindelEcommerceTest-1.0.0-RELEASE.jar"

## Testing:

- Open a browser and access to the following URL "http://localhost:7000/swagger-ui/index.html"

- You can see this. 
![alt text](https://github.com/jeag2002/QindelEcommerceTest/blob/master/swagger-ui.jpg?raw=true)

- Also you can see the data stored in the H2 database requesting the following URL "http://localhost:7000/h2-console"

- You can see this (user: sa, password: sa)
![alt text](https://github.com/jeag2002/QindelEcommerceTest/blob/master/h2-console-1.jpg?raw=true)

## Methods:

* <h3>GET /rest/listPrizes</h3>

List of all prizes ordered by PRIORITY desc


* <h3>GET /rest/listPrizesFiltered</h3>

List of all prizes filtered by the criteria below ordered by PRIORITY desc.
(you can choose one, two or three criteria, no matter the order)

|parameter|description|suggested value|
|---------|-----------|---------------|
|productId|product identification|35455|
|brand|name of the brand of the product (accept capital/no capital letters and substrings)|ZARA|
|appDate|date limit in yyyy-MM-dd HH:mm:ss format|2020-06-14 10:00:00|


## Tips:

* define MODE=DB2; in spring.datasource.url=jdbc:h2:mem:commerce-test;MODE=DB2. Its necessary in process of loading records with dates in this format yyyy-MM-dd-HH.mm.ss

* set UTC in application.properties (spring.jpa.properties.hibernate.jdbc.time_zone=UTC), to normalizing the use of the same timezone in all the application.
