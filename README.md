# RESTful web service con spring-boot

Servicio para la gestión de las operaciones de un hotel, el
cual consta de los siguientes componentes:

* Servicio de inventario.
* Servicio de consulta de disponibilidad de cupo.
* Servicio de reservación.
* Servicio de factura.

Cada semana se trabajará un módulo diferente en el que se mostrarán
progresivamente diferentes aspectos del diseño de las APIs REST, y
las posibilidades que spring-boot ofrece para ello.


### Software requerido
>- JDK 1.8+.
>- Maven 3.0+.
>- Firefox, Chrome o Postman.

### 	Construir un JAR ejecutable

Para construir un JAR ejecutable, desde la línea de comando ejecutamos el comando:

```
 mvn compile package
```
### Ejecutar JAR

Una vez generado el JAR, podemos correr la aplicación con:

```
java -jar target/spring-boot-sample-1.0-SNAPSHOT.jar
```
### Correr la aplicación con Maven

Ejecutando el siguiente comando podemos correr la aplicación sin generar el JAR:

```
mvn spring-boot:run
```
