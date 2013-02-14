PruebaBCI
=========

Datos de configuración de las aplicaciones de demostración
--------------------------------------------------------------------------------

-
Para la aplicación PruebaTerminalBD hay que configurar la variable de entorno CLASSPATH del modo:
export CLASSPATH=.:<dir_a_libreria_mysql-connector>/mysql-connector-java-5.1.23-bin.jar:$CLASSPATH

Diario
--------
-
Configurando el proyecto en Eclipse usando el plugin Egit.
Leyendo sobre grafos.
Varias cosas probadas, estructuras ArrayList y Map.

-
Tras implementar el algoritmo A* descubro que no me funciona bien.
Pruebo en varias ocasiones a subsanar el error pero no lo consigo.
Decido que una solución viable es implementar el algoritmo Dijkstra en lugar de emplear más tiempo en buscar el error del algoritmo A*.

-
Leyendo acerca de Dijkstra. 
Adaptando las estructuras MAP de java usadas en A* para Dijkstra. Se nota que es la primera vez que trabajo con estructuras MAP, porque he tenido problemas con el valor de la estructura MAP. Se le puede pasar un objeto como valor. Intenté pasarle una Object[] para guardar dentro dos objetos diferentes y MAP se lo tragó. Ahora bien, no se comportó la estructura como debía. Cambio de paradigma. Le paso dos objetos String. El primero la clave, el segundo el valor. El valor contiene la cadena <nombreCiudadOrigen><espacioBlanco><distanciaDesdeOrigenKM>. El valor hay que procesarlo con la función split de la clase String.
Falta poco para terminarlo.

-
Realizo varios 'syso' para depurar la salida del programa. La ruta entre dos ciudades no es buena ya que hago pruebas en papel y compruebo con la salida por consola y los resultados son diferentes. Realizo más 'seso' y descubro que me falta machacar el valor de una variable en cada iteración de un bucle. Corrijo y compruebo. Ahora funciona. Últimos retoques de la parte 1, javadoc, quitar 'syso', etc.

-
Javadoc finalizado de la clase Ciudad.

-
Javadoc finalizado de la clase Mapa.

-
Documentando el fichero que contiene la funcion main.
Cambio la función equals de la clase String por compareTo de la misma
clase porque ésta última es más ligera. Cambio la función substring de
la clase String por split de la misma clase porque ésta última es
más ligera. 

-
El fichero que tiene la funcion main esta comentado por completo. He terminado de implementar el menú por consola. He probado la aplicación en varias ocasiones y funciona bien. 
Proximos pasos:
Descargar el SGBD MySQL en el Mac el instalarla.
Crear la BD de la aplicación, es decir, las tablas que contengan las ciudades y carreteras empleando un script que automatice el proceso.
Conectar el Eclipse con la base de datos a través del JDBC.

-
Tomcat 7 instalado en el Mac e integrado en eclipse para poder encender y apagar de manera cómoda. 
Me creo un usuario en el servidor que puede desempeñar todos los roles para poder tener acceso completo a la funcionalidad del servidor.
  <role rolename="admin"/>
  <role rolename="admin-gui"/>
  <role rolename="admin-script"/>
  <role rolename="managuer"/>
  <role rolename="managuer-gui"/>
  <user username="jfrascon" password="jfrascon" roles="admin,admin-gui,admin-script,manager,manager-gui"/>
  
-
SGBD MySQL instalado en el Mac y enlazado con Eclipse para manejarlo de manera cómoda. BD pruebabiicode creada con usuario root y clave ``''. Tablas Carreteras y Ciudades creadas. Datos de ciudades y carreteras insertados en las tablas. Crear una función que main que use la base de datos en lugar de leer de fichero.

-
Función main que accede a la BD acabada. Probada la aplicación
PruebaTerminalBD y funciona exactamente igual que la versión que lee de fichero, PruebaTerminal. Es necesario modificar el CLASSPATH de JAVA para que sepa donde buscar la librería mysql-connector-java-5.1.23-bin.jar.
