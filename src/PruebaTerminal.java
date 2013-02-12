import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PruebaTerminal {

	public static void main(String[] args) {

		BufferedReader br = null;
		String cadenaFichero = null;
		boolean inicioCiudades = false;
		boolean inicioCarreteras = false;
		String ciudadPrimera = null;
		String ciudadSegunda = null;
		float coordX = 0.0f;
		float coordY = 0.0f;

		Mapa mapaEspania = new Mapa();

		System.out.println(args[0] + " " + args[1]);

		try {

			br = new BufferedReader(new FileReader(args[1]));

			// Posicionar el puntero del fichero sobre la cadena @Ciudades.
			// Con este bucle evito las l’neas en blanco que pudiera tener el
			// fichero antes de la cadena "@Ciudades". Si la primera l’nea con
			// texto del fichero no es @Ciudades entonces el fichero tiene un
			// formato inv‡lido y acabo con la funcion main.
			while ((cadenaFichero = br.readLine()) != null && !inicioCiudades) {

				// Sin espacios en blanco. Una linea en blanco tras la funcion
				// trim() se queda en "" (cadena vac’a).
				cadenaFichero = cadenaFichero.trim();

				if (!cadenaFichero.equals("")) {

					if (cadenaFichero.equals("@Ciudades")) {

						inicioCiudades = true;

					} else {

						// Formato de fichero invalido.
						br.close();
						return;

					}
				}

			}

			// Si el fichero que debe contener las ciudades y carreteras est‡
			// vac’o el booleano inicioCiudades vale false. Indicar que la
			// funcion acabo sin exito.
			if (inicioCiudades) {

				// Leer las ciudades que se encuentras tras la cadena @Ciudades.
				while ((cadenaFichero = br.readLine()) != null
						&& !inicioCarreteras) {

					cadenaFichero = cadenaFichero.trim();
					// Si se trata de una linea vacia no hacer nada.
					if (!cadenaFichero.equals("")) {

						if (cadenaFichero.equals("@Carreteras")) {

							inicioCarreteras = true;

						} else {

							if (!cadenaFichero.equals("")) {

								// ciudadPrimera = cdena

							}

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

		} catch (FileNotFoundException fnfe) {

			fnfe.printStackTrace();

		} catch (IOException ioe) {

			ioe.printStackTrace();
		}

	}
}
