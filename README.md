PruebaBCI
=========

Configurando el proyecto en Eclipse usando el plugin Egit.

Leyendo sobre grafos.

Varias cosas probadas, estructuras ArrayList y Map.

Tras implementar el algoritmo A* descubro que no me funciona bien.
Pruebo en varias ocasiones a subsanar el error pero no lo consigo.
Decido que una soluci�n viable es implementar el algoritmo Dijkstra en lugar de emplear m�s tiempo en buscar el error del algoritmo A*.

Leyendo acerca de Dijkstra. 
Adaptando las estructuras MAP de java usadas en A* para Dijkstra. Se nota que es la primera vez que trabajo con estructuras MAP, porque he tenido problemas con el valor de la estructura MAP. Se le puede pasar un objeto como valor. Intent� pasarle una Object[] para guardar dentro dos objetos diferentes y MAP se lo trag�. Ahora bien, no se comport� la estructura como deb�a. Cambio de paradigma. Le paso dos objetos String. El primero la clave, el segundo el valor. El valor contiene la cadena <nombreCiudadOrigen><espacioBlanco><distanciaDesdeOrigenKM>. El valor hay que procesarlo con la funci�n split de la clase String.
Falta poco para terminarlo.