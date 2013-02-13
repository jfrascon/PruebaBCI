PruebaBCI
=========

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