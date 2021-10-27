# Proyecto Final de Software 3

##Instalacion
Base de datos
MySQL 		   -- > Base de datos relacional
Enlace de descarga -- > https://dev.mysql.com/downloads/mysql/

Lenguaje principal
Java 11  	   -- > El propio IntelliJ lo descarga al cargar el proyecto  o : https://www.oracle.com/java/technologies/javase-downloads.html
OpenJDK: http://jdk.java.net/archive/ o https://adoptopenjdk.net/

IDE
IntelliJ  	   -- > https://www.jetbrains.com/es-es/idea/download/  -- Se debe crear una cuenta con el correo institucional
-------------------------------------------------------------------------------------------------------------------------------
##Configuracion
-Se debe crear una base de datos en mysql con el nombre software3
 
-En los properties de cada capa de deben actualizar el usuario y contraseña del mysql o para evitar este paso tener como usuario root y contraseña root 

-A la hora de abrir el proyecto en el intellij, en la esquina inferior saldra la opcion de descargar e instalar los pluggins, dar click y esperar a que termine el proceso

-Si no descargo el java 11 antes, intellij permite realizar la descarga, en la esquina superior derecha saldra la opcion para descargarlo.

-Antes de correr la aplicacion, se debe configurar el run. Se debe seleccionar la opcion de run la cual esta dada en el menu superior, Luego seleccionar la opcion de edit configurations, en el apartado de environment, en working directory dar click en el mas y seleccionar la opcion de %MODULE_WORKING_DIR%. Aplicar y aceptar.

-Se deben tener activas las cookies del navegador.

-------------------------------------------------------------------------------------------------------------------------------
##Requirimientos funcionales

###Realizado
-login

-CRUD administrador

-CRUD usuario

-Registro de libro

-Entidades

-Mapeo en base de datos

-Test para pruebas unitarias

-Api rest de administrador, usuario y libro, para pruebas automatizadas

-Diseño de interfaces de lo mencionado anteriormente

###Pendiente
-El resto crud de libro

-CRUD de reserva

-Funcionalidad de devolucion, generar multas, realizar prestamo 

-Perfil de admnistrador (carga los registros)

-Perfil de ususario (carga las reservas, devoluciones, pagos, multas..)

-Detalles de interfaz 

-------------------------------------------------------------------------------------------------------------------------------

###Capa de persistencia
El modulo de persistencia representa los objetos del dominio de negocio del aplicativo, esto quiere decir que maneja el modelado de las clases que se requieren para dar solucion al proyecto solicitado.

En este modulo se maneja principalmente la conexion con la base de datos, en la carpeta entidades se encuentran las clases o entidades mencionadas anteriormente, tambien se encuentran los repositorios de cada entidad, los cuales permiten hacer las consultas a la base de datos por medio de Querys que principalmente referencian las sentencias que se ingresarian en MySQL para poder consultar los registros que se persisten en las tablas de la base de datos, manejando las consultas por medio de JPQL.

Por ultimo este modulo contiene un apartado de test donde se realizan las pruebas unitarias, tomando como base a anotacion de JPA @DataJpaTest la cual permite realizar las pruebas mencionadas anteriormente, tambien manejan los servicios de conexion a la base de datos donde se manejan ciertos metodos para almacenar datos en la base, entre estos estan save (insert into), delete(drop), para el actualizar se utiliza el mismo save ya que simplemente se debe recuperar un objeto y guardarlo de nuevo en la base,por ultimo se tiene los fyndny... donde se pueden condicionar las formas de recuperar los datos, ya sea por email, nombre, id, entre otros.

En el applicationProperties se hace la vinculacion con la base de datos, en este se debe agregar el usuario y la contraseña propios de MySQL, se recomienda utilizar root en ambos apartados, a su vez se debe crear con anterioridad la base de datos en el MySQL con el nombre software3.

Para correr cada test se requiere dar run individualmete a cada uno de los metodos implementados en la clase test correspondiente a una entidad.
Para correr la capa de persistencia se debe dirigir al "PersistenciaApplication" el cual hace la funcion de main en este modulo, al momento de efectuar
esta accion la base de datos se creara poblara con las tablas(entidades) y sus respectivas relaciones.


###Capa de negocio

El modulo de negocio representa la lógica de negocio y el acceso a los datos almacenados en la base de datos.

En este modulo se realizan implementan los servicios de cada entidad, cada servicio contiene los metodos necesarios para el desarrollo de la aplicacion, se manejaran dos "clases" por cada entidad, en este caso una sera de tipo Interface, la cual contendra simplemente los nombres de los metodos, la otra "clase"a implementar sera de tipo clase java, la cual implementara los metodos nombrados en la interface, ademas contendra la anotacion @Service la cual permitira manejar esta misma como un servicio de negocio, a su vez contendra la logica que permitira realizar los procesos que efectua cada metodo.

En el applicationProperties se hace la vinculacion con la base de datos, en este se debe agregar el usuario y la contraseña propios de MySQL, se recomienda utilizar root en ambos apartados, a su vez se debe crear con anterioridad la base de datos en el MySQL y llamarla en este archivo.

Este modulo tambien contiene una carpeta de test, los cuales permiten realizar las pruebas unitarias por cada metodo de los servicios, la cual maneja las mismas anotaciones de los test en la capa de persistencia pero llamando los metodos realizados en los servicios.

###Capa Web

EL modulo web es el que proporciona la interfaz grafica de usuario web para los clientes.

En este modulo se manejan todas las vistas que se llevaran a cabo, trabajadas con jsf y con componentes de primeFaces, para correr la aplicacion web se debe correr el webApplication e ingresar en el navegador localhost:8080.

Para tener en cuenta : se deben tener activas las cookies del navegador.

-------------------------------------------------------------------------------------------------------------------------------
