import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map.Entry;

public class PruebaTerminalBD {

	// Driver para tener acceso a la base de datos.
	static final String jdbcDriver = "com.mysql.jdbc.Driver";
	static final String urlBD = "jdbc:mysql://localhost:3306/pruebabiicode";
	static final String usuario = "root";
	static final String clave = "";

	public static void main(String[] args) {

		Connection conexion = null;
		Statement stmt = null;
		ResultSet rs = null;
		Mapa mapaEspania = null;
		try {
			// Cargar el driver.
			Class.forName(jdbcDriver);
			// Conexion a la base de datos.
			conexion = DriverManager.getConnection(urlBD, usuario, clave);
			stmt = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT nombreCiudad, coordX, coordY FROM Ciudades");

			mapaEspania = new Mapa();

			while (rs.next()) {
				mapaEspania.aniadirCiudad(new Ciudad(rs.getString("nombreCiudad"), rs.getFloat("coordX"), rs.getFloat("coordY")));
				Ciudad ciudadAniadida = mapaEspania.obtenerCiudad(rs.getString("nombreCiudad"));
				System.out.println("Ciudad: " + ciudadAniadida.getNombreCiudad() + " " + ciudadAniadida.getCoordX() + " " + ciudadAniadida.getCoordY());
			}

			rs = stmt.executeQuery("SELECT nombreCiudadA, nombreCiudadb FROM Carreteras");
			while (rs.next()) {
				mapaEspania.aniadirCarretera(rs.getString("nombreCiudadA"), rs.getString("nombreCiudadB"));
			}
			rs.close();

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

		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

		// Menu de consola.
		BufferedReader brConsola = new BufferedReader(new InputStreamReader(System.in));
		String cadenaLeida = null;
		String nombreCiudadA = null;
		String nombreCiudadB = null;
		try {
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
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				brConsola.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
