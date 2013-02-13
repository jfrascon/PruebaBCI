import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class PruebaTerminal {

	public static void main(String[] args) {

		BufferedReader br = null;
		String cadenaFichero = null;
		boolean inicioCiudades = false;
		boolean inicioCarreteras = false;
		String nombreCiudadA = null;
		String nombreCiudadB = null;
		float coordX = 0.0f;
		float coordY = 0.0f;
		int posEspacioBlanco1 = 0;
		int posEspacioBlanco2 = 0;
		int numCarreterasProcesadas = 0;

		Mapa mapaEspania = new Mapa();

		try {

			br = new BufferedReader(new FileReader(args[0]));

			// Posicionar el puntero del fichero sobre la cadena @Ciudades.
			// Con este bucle evito las l’neas en blanco que pudiera tener el
			// fichero antes de la cadena "@Ciudades". Si la primera l’nea con
			// texto del fichero no es @Ciudades entonces el fichero tiene un
			// formato inv‡lido y acabo con la funcion main.
			while (!inicioCiudades && (cadenaFichero = br.readLine()) != null) {

				// Sin espacios en blanco. Una linea en blanco tras la funcion
				// trim() se queda en "" (cadena vac’a).
				cadenaFichero = cadenaFichero.trim();

				if (!cadenaFichero.equals("")) {

					if (cadenaFichero.equals("@Ciudades")) {

						inicioCiudades = true;

					} else {

						// Formato de fichero invalido.
						br.close();
						System.out.println("Formato de fichero invalido");
						return;

					}
				}

			}

			// Si el fichero que debe contener las ciudades y carreteras est‡
			// vac’o el booleano inicioCiudades vale false. Indicar que la
			// funcion acabo sin exito.
			if (inicioCiudades) {

				// Leer las ciudades que se encuentras tras la cadena @Ciudades.
				while (!inicioCarreteras
						&& (cadenaFichero = br.readLine()) != null) {

					cadenaFichero = cadenaFichero.trim();
					// Si se trata de una linea vac’a no hacer nada.
					if (!cadenaFichero.equals("")) {

						if (cadenaFichero.equals("@Carreteras")) {

							inicioCarreteras = true;

						} else {

							posEspacioBlanco1 = cadenaFichero.indexOf(' ');

							if (posEspacioBlanco1 == -1) {

								br.close();

								System.out
										.println("Formato de fichero invalido");

								return;

							}

							nombreCiudadA = cadenaFichero.substring(0,
									posEspacioBlanco1);

							posEspacioBlanco2 = cadenaFichero.indexOf(' ',
									posEspacioBlanco1 + 1);

							if (posEspacioBlanco2 == -1) {

								br.close();
								return;

							}

							coordX = Float.parseFloat(cadenaFichero.substring(
									posEspacioBlanco1 + 1, posEspacioBlanco2));

							coordY = Float.parseFloat(cadenaFichero
									.substring(posEspacioBlanco2 + 1));

							mapaEspania.aniadirCiudad(new Ciudad(nombreCiudadA,
									coordX, coordY));

							Ciudad ciudadAniadida = mapaEspania
									.obtenerCiudad(nombreCiudadA);

							System.out.println("Aniadida ciudad "
									+ ciudadAniadida.getNombreCiudad() + " "
									+ ciudadAniadida.getCoordX() + " "
									+ ciudadAniadida.getCoordY());

						}
					}
				}

			} else {
				// Finalizar sin exito porque no se ha encontrado la seccion del
				// fichero que contiene las ciudades, es decir, no se ha
				// encontrado la cadena @Ciudades.
				br.close();
				return;
			}

			// Si no se han creado ciudades al llegar aqui el fichero no esta
			// bien construido.
			if (mapaEspania.numeroCiudadesMapa() == 0) {

				br.close();

				return;

			}

			// Conitnuar procesando las carreteras que unen las ciudades.
			while ((cadenaFichero = br.readLine()) != null) {

				cadenaFichero = cadenaFichero.trim();

				if (!cadenaFichero.equals("")) {

					posEspacioBlanco1 = cadenaFichero.indexOf(' ');

					if (posEspacioBlanco1 == -1) {

						br.close();

						System.out.println("Formato de fichero invalido");

						return;
					}

					nombreCiudadA = cadenaFichero.substring(0,
							posEspacioBlanco1);

					nombreCiudadB = cadenaFichero
							.substring(posEspacioBlanco1 + 1);

					mapaEspania.aniadirCarretera(nombreCiudadA, nombreCiudadB);

					numCarreterasProcesadas += 1;

				}
			}

			if (numCarreterasProcesadas == 0) {

				br.close();

				System.out.println("Formato de fichero invalido");

				return;

			}

			System.out.println("\nInspeccion de carreteras.\n");
			
			Iterator<Entry<String, Ciudad>> it = mapaEspania.obtenerCiudades()
					.entrySet().iterator();
			Iterator<Entry<String, Float>> it2 = null;
			Entry<String, Ciudad> entrada = null;
			Entry<String, Float> entrada2 = null;

			while (it.hasNext()) {

				entrada = it.next();

				System.out.println("Ciudad origen: " + entrada.getKey());

				if (entrada.getValue().numeroCiudadesVecinas() != 0) {

					it2 = entrada.getValue().obtenerCiudadesAdyacentes()
							.entrySet().iterator();

					while (it2.hasNext()) {

						entrada2 = it2.next();

						System.out.println("Ciudad destino: " + entrada2.getKey() + ", a una distancia de " +
								+ entrada2.getValue() + " km.");

					}
				}
				else{
				
					System.out.println("No esta conectada con otras ciudades.");
					
				}
				
				System.out.println("");
			}

		} catch (FileNotFoundException fnfe) {

			fnfe.printStackTrace();

		} catch (IOException ioe) {

			ioe.printStackTrace();

		} catch (NumberFormatException nfe) {

			nfe.printStackTrace();

		}
		
		System.out.println("\n"+mapaEspania.obtenerCamino(mapaEspania.dijkstra("Bilbao"), "Cordoba"));
		
		//System.out.println("\n"+mapaEspania.obtenerCamino(mapaEspania.dijkstra("Cordoba"), "Bilbao"));


	}
}
