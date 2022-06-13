# ViseApp - Spring

Proyecto para el Back de SpringViseDemo
Basado en el siguiente ejecicio https://www.codejava.net/frameworks/spring-boot/spring-boot-crud-example-with-spring-mvc-spring-data-jpa-thymeleaf-hibernate-mysql

## Instrucciones

Bajar el repositorio y ejecutar en IDE de preferencia.

Usar la ultima version de [Releases](https://github.com/Alan-Horta/AngularViseDemo/releases/tag/test) se necesita colocar en un servidor apache por ejemplo WAMP donde fue probado.
Si no descargar el [repository](https://github.com/Alan-Horta/AngularViseDemo) y ejecutar manualmente con angular cli.

### MySQL

La aplicacion requiere el uso de MySql, el default path esta configurado a `spring.datasource.url=jdbc:mysql://localhost/sales?useSSL=false` en `application.properties` por si en dado caso se requiera modificar
La aplicacion requiere que exista un Schema con el nombre `sales` y este debe de tener la siguiente estructura 
`CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `brand` varchar(45) NOT NULL,
  `madein` varchar(45) NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;`
de acerudo a las instrucciones

### Internationalization

Este proyecto de Spring no cuenta con una interfaz directa, se necesita el [Front de Angular](https://github.com/Alan-Horta/AngularViseDemo/releases/tag/test), para la internationalization del back se requiere enviar `"Accept-Language"` en el header de la consulta aunque este proyecto solo soporta `es` y `en`

### IP

La IP que necesita el Front por default para conectarse a Spring es `http://localhost:8080/api/productos`, si se requiere otra, dirigirse a las [intrucciones](https://github.com/Alan-Horta/AngularViseDemo) del Front para su cambio

