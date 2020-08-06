
# Reto: Lista de tareas personalizada. Solución: Rubí Rincón Peña (rojorubi@gmail.com)


Esta prueba técnica consistirá en crear una pequeña aplicación backend que exponga una API REST que permita a un usuario gestionar una lista de tareas personalizada.

La aplicación debe permitir tanto la creación de tareas nuevas, como el borrado y la edición de tareas existentes. Asímismo, una tarea ya realizada debe poder marcarse como finalizada.

El candidato tendrá que desarrollar este ejercicio utilizando Java y Spring Framework, dejando a su libre elección tanto las versiones a utilizar como el resto de tecnologías que puedan necesitarse para completar la funcionalidad, como por ejemplo, la tecnología con la que se va a construir el proyecto (Maven, Gradle, Ant, etc).

Como entregable final, se facilitará el acceso al código fuente original, en el formato elegido por el candidato, así como los pasos a seguir para poner la aplicación en marcha.


## Instrucciones para levantar y probar el microservicio

Utilizar versión del directorio coding-test-things-to-do/apache-maven-3.6.3-bin.tar.gz o descargar en http://maven.apache.org/install.html 

```bash
$ tar xzvf apache-maven-3.6.3-bin.tar.gz
```

```bash
$ export PATH=/Library/apache-maven-3.6.3/bin:$PATH
```

```bash
$ git clone git@github.com:rojorubi/coding-test-things-to-do.git
```

```bash
$ mvn install
```

```bash
$ mvn spring-boot:run
```

La aplicación comenzará cargando un total de cinco tareas.

A partir de aquí podemos acceder a la url http://localhost:8080/swagger-ui.html para poder ver la documentación de los endpoints implementados en una misma clase controller:



## /create
	Endpoint que permite crear una tarea y asigna un identificador de forma automática a al tarea
## /finish
	Endpoint que permite cambiar el estado de una tarea a finalizada a partir del id de la tarea
## /getAllTask
	Endpoint que obtiene un listado completo de todas las tareas
## /task/{taskId}
	Endpoint que permite borrar una tarea de forma definitva a partir del id de la tarea
## /update/{taskId}
	Endpoint que permite editar una tarea



## Dependencias utilizadas:

```diff
+ Swagger 2-2.9.2
```
Swagger es un conjunto de herramientas de software de código abierto para diseñar, construir, documentar y usar servicios web RESTful, desarrollado por SmartBear Software. Incluye documentación automatizada, generación de código y generación de casos de prueba.

```diff
+ Lombok 1.18.8
```
Lombok es una librería que usa un conjunto de anotaciones reducido que nos permite ahorrar en código escrito.


```diff
+ Log4j 1.2.17
```
Biblioteca open source desarrollada en Java por la Apache Software Foundation que permite escribir mensajes de registro, cuyo propósito es dejar constancia de una determinada transacción en tiempo de ejecución.

```diff
+ Dozer 5.5.1 
```
(aunque no se ha necesitado)
Librería Java para mapear Java Beans que copia de manera recursiva los datos, de un objeto a otro.
