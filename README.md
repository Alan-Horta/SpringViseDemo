# ViseApp - Spring

Proyecto para el Back de SpringViseDemo

## Instrucciones

Bajar el repositorio y ejecutar en IDE de preferencia.

Usar la ultima version de [Releases](https://github.com/Alan-Horta/AngularViseDemo/releases/tag/test) se necesita colocar en un servidor apache por ejemplo WAMP donde fue probado.
Si no descargar el [repository](https://github.com/Alan-Horta/AngularViseDemo) y ejecutar manualmente con angular cli.

### Internationalization

Este proyecto de Spring no cuenta con una interfaz directa, se necesita el [Front de Angular](https://github.com/Alan-Horta/AngularViseDemo/releases/tag/test), para la internationalization del back se requiere enviar `"Accept-Language"` en el header de la consulta aunque este proyecto solo soporta `es` y `en`

### IP

La IP que necesita el Front por default para conectarse a Spring es `http://localhost:8080/api/productos`, si se requiere otra, dirigirse a las [intrucciones](https://github.com/Alan-Horta/AngularViseDemo) del Front para su cambio

