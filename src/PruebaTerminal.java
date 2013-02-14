import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map.Entry;

public class PruebaTerminal {

	public static void main(String[] args) {

		boolean inicioCiudades = false;
		boolean inicioCarreteras = false;
		int numCarreterasProcesadas = 0;
		BufferedReader brFichero = null;
		BufferedReader brConsola = null;
		String cadenaLeida = null;
		String nombreCiudadA = null;
		String nombreCiudadB = null;
		String[] cadenasExtraidas = null;

		// Grafo de nodos de tipo ciudad.
		Mapa mapaEspania = new Mapa();
		// Lectura del fichero de ciudades y carreteras.
		try {
			brFichero = new BufferedReader(new FileReader(args[0]));
			// Posicionar el puntero del fichero sobre la cadena @Ciudades. Con este bucle evito las l’neas en blanco
			// que pudiera tener el fichero antes de la cadena "@Ciudades". Si la primera l’nea con texto del fichero no
			// es @Ciudades entonces el fichero tiene un formato inv‡lido y acabo con la funcion main.
			while (!inicioCiudades && (cadenaLeida = brFichero.readLine()) != null) {
				// Sin espacios en blanco. Una linea en blanco tras la funcion trim() se queda en "" (cadena vac’a).
				cadenaLeida = cadenaLeida.trim();
				if (cadenaLeida.compareTo("") != 0) {
					if (cadenaLeida.compareTo("@Ciudades") == 0) {
						inicioCiudades = true;
					} else {
						// Formato de fichero invalido.
						brFichero.close();
						System.out.println("Formato de fichero invalido");
						return;
					}
				}
			}
			// Si el fichero que debe contener las ciudades y carreteras est‡ vac’o el booleano inicioCiudades vale
			// false. Indicar que la funcion acabo sin exito.
			if (inicioCiudades) {
				// Leer las ciudades que se encuentras tras la cadena @Ciudades.
				while (!inicioCarreteras && (cadenaLeida = brFichero.readLine()) != null) {
					cadenaLeida = cadenaLeida.trim();
					// Si se trata de una linea vac’a no hacer nada.
					if (cadenaLeida.compareTo("") != 0) {
						if (cadenaLeida.compareTo("@Carreteras") == 0) {
							inicioCarreteras = true;
						}
						// Procesar informacion de la ciudad encontrada en el fichero. Crear un objeto ciudad y aniadir
						// dicha ciudad al mapa.
						else {
							cadenasExtraidas = cadenaLeida.split(" ");
							if (cadenasExtraidas.length < 3) {
								brFichero.close();
								System.out.println("Formato de fichero invalido");
								return;
							}
							mapaEspania.aniadirCiudad(new Ciudad(cadenasExtraidas[0], Float.parseFloat(cadenasExtraidas[1]), Float
									.parseFloat(cadenasExtraidas[2])));
							Ciudad ciudadAniadida = mapaEspania.obtenerCiudad(cadenasExtraidas[0]);
							System.out.println("Ciudad: " + ciudadAniadida.getNombreCiudad() + " " + ciudadAniadida.getCoordX() + " "
									+ ciudadAniadida.getCoordY());
						}
					}
				}
			} else {
				// Finalizar sin exito porque no se ha encontrado la seccion del fichero que contiene las ciudades, es
				// decir, no se ha encontrado la cadena @Ciudades.
				brFichero.close();
				return;
			}
			// Si no se han creado ciudades al llegar aqui el fichero no esta
			// bien construido.
			if (mapaEspania.numeroCiudadesMapa() == 0) {
				brFichero.close();
				return;
			}
			// Conitnuar procesando las carreteras que unen las ciudades.
			while ((cadenaLeida = brFichero.readLine()) != null) {
				cadenaLeida = cadenaLeida.trim();
				if (cadenaLeida.compareTo("") != 0) {
					cadenasExtraidas = cadenaLeida.split(" ");
					if (cadenasExtraidas.length < 2) {
						brFichero.close();
						System.out.println("Formato de fichero invalido");
						return;
					}
					mapaEspania.aniadirCarretera(cadenasExtraidas[0], cadenasExtraidas[1]);
					numCarreterasProcesadas += 1;
				}
			}
			// Si al salir del bucle el numero de carreteras procesadas es nulo, entonces tras la etiqueta
			// @Carreteras no habia nada y por tanto el fichero es erroneo.
			if (numCarreterasProcesadas == 0) {
				brFichero.close();
				System.out.println("Formato de fichero invalido");
				return;
			}

			// ----- ----- ----- ----- ----- CODIGO DEPURACION ----- ----- ----- ----- -----
			System.out.println("\nInspeccion de carreteras.\n");
			Iterator<Entry<String, Ciudad>> itSC = mapaEspania.obtenerCiudades().entrySet().iterator();
			// Codigo nemotecnico SC -> String Ciudad.
			Entry<String, Ciudad> entradaSC = null;
			// Codigo nemotecnico SC -> String Float.
			Iterator<Entry<String, Float>> itSF = null;
			Entry<String, Float> entradaSF = null;
			while (itSC.hasNext()) {
				entradaSC = itSC.next();
				System.out.println("Ciudad origen: " + entradaSC.getKey());
				if (entradaSC.getValue().numeroCiudadesAdyacentes() != 0) {
					itSF = entradaSC.getValue().obtenerCiudadesAdyacentes().entrySet().iterator();
					while (itSF.hasNext()) {
						entradaSF = itSF.next();
						System.out.println("Ciudad destino: " + entradaSF.getKey() + ", a una distancia de " + +entradaSF.getValue() + " km.");
					}
				} else {
					System.out.println("No esta conectada con otras ciudades.");
				}
				System.out.println("");
			}
			// ----- ----- ----- ----- ----- ----- ----- ----- ----- -----

			// Menu de consola.
			brConsola = new BufferedReader(new InputStreamReader(System.in));
			do {
				System.out.println("Que desea hacer?");
				System.out.println("1.- Calcular el camino mas corto entre dos ciudades.");
				System.out.println("2.- Finalizar la aplicacion.");
				System.out.print("Introduzca la opcion que desee: ");
				cadenaLeida = brConsola.readLine();
				System.out.print("\n");
				if (cadenaLeida.compareTo("1") == 0) {
					System.out.print("Introduzca la ciudad origen: ");
					nombreCiudadA = brConsola.readLine().trim();
					System.out.print("");
					System.out.print("Introduzca la ciudad destino: ");
					nombreCiudadB = brConsola.readLine().trim();
					System.out.println("");
					System.out.println("CAMINO: " + mapaEspania.obtenerCamino(mapaEspania.dijkstra(nombreCiudadA), nombreCiudadB) + ".\n");
				} else if (cadenaLeida.compareTo("2") == 0) {
					System.out.println("Gracias por utilizar la aplicacion.\n");
				} else {
					System.out.println("Opcion no reconocida.\n");
				}
			} while (cadenaLeida.compareTo("2") != 0);

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		} finally {
			try {
				if (brFichero != null) {
					brFichero.close();
				}
				brConsola.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
