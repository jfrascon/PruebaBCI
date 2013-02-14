package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase que representa el menu que se le muestra al usuario para que este indique que desea hacer con la aplicacion.
 * 
 * @author jfrascon
 * @version "%I%, %G%
 * 
 */
public class Menu {

	private BufferedReader brConsola;

	/**
	 * Constructor de la aplicacion.
	 */
	public Menu() {

		brConsola = new BufferedReader(new InputStreamReader(System.in));
	}

	/**
	 * Este metodo se encarga de mostrar un menu al usuario con las opciones disponibles en la aplicacion. Lee la
	 * decision tomada por el usuario y los datos adjuntos en caso de que los hubiera y pasa toda esa informacion al
	 * bucle de control para que se realice la accion adecuada.
	 * 
	 * @return Tres cadenas de texto. La primera cadena de texto es la opcion escogida por el usuario en el menu de la
	 * aplicacion. Si el usuario quiere calcular la ruta mas corta entre dos ciudades, en la segunda cadena de texto
	 * aparece el nombre de la ciudad origen y en la tercera cadena de texto aparece el nombre de la ciudad destino. En
	 * cualquier otro caso las dos cadenas finales no tienen datos utiles.
	 */
	public String[] muestraOpciones() {

		String[] cadenasLeidas = new String[3];
		System.out.println("Que desea hacer?");
		System.out.println("1.- Calcular el camino mas corto entre dos ciudades.");
		System.out.println("2.- Finalizar la aplicacion.");
		System.out.print("Introduzca la opcion que desee: ");
		try {
			cadenasLeidas[0] = brConsola.readLine().trim();
			System.out.print("\n");
			if (cadenasLeidas[0].compareTo("1") == 0) {
				System.out.print("Introduzca la ciudad origen: ");
				cadenasLeidas[1] = brConsola.readLine().trim();
				System.out.print("");
				System.out.print("Introduzca la ciudad destino: ");
				cadenasLeidas[2] = brConsola.readLine().trim();
				System.out.println("");
			} else if (cadenasLeidas[0].compareTo("2") == 0) {
				throw new IOException();
			} else {
				System.out.println("Opcion no reconocida.\n");
			}
		} catch (IOException ioe) {
			cadenasLeidas[0] = "2";
			System.out.println("Gracias por utilizar la aplicacion.\n");
			try {
				if (brConsola != null) {
					brConsola.close();
				}
			} catch (IOException ioe2) {
			}
		}
		return cadenasLeidas;
	}
}
