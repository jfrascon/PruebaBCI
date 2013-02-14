import vista.Menu;
import modelo.BaseDatos;
import modelo.Mapa;

public class PruebaTerminal {

	public static void main(String[] args) {

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
		Menu menuConsola = new Menu();
		String[] cadenasLeidas = null;
		do {
			cadenasLeidas = menuConsola.muestraOpciones();
			if (cadenasLeidas[0].compareTo("1") == 0) {
				System.out.println("CAMINO: " + mapaEspania.obtenerCamino(mapaEspania.dijkstra(cadenasLeidas[1]), cadenasLeidas[2]) + ".\n");
			}
		} while (cadenasLeidas[0].compareTo("2") != 0);
	}
}
