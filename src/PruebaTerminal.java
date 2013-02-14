import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import modelo.BaseDatos;
import modelo.Mapa;

public class PruebaTerminal {

	public static void main(String[] args) {


		BufferedReader brConsola = null;
		String cadenaLeida = null;
		String nombreCiudadA = null;
		String nombreCiudadB = null;

		// Por defecto se usa el fichero como base de datos de ciudades y carreteras.
		boolean fichero = true;
		if (args.length != 0) {
			if (args[0].compareTo("0") == 0) {
				fichero = false;
			}
		}
		// Obtener el grafo de la aplicacion del fichero o de la base de datos relacional.
		BaseDatos bd = new BaseDatos(fichero);		
		Mapa mapaEspania = bd.obtenerMapa();

		try {
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
				brConsola.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
