package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * Clase que representa la base de datos que contiene la informacion con la que se contruye el grafo de la aplicacion.
 * La base de datos puede ser un fichero de texto o bien una base de datos convencional como MySQL, etc. El usuario
 * puede especificar la fuente de donde se extrae la informacion.
 * 
 * @author jfrascon
 * @version "%I%, %G%
 * 
 */
public class BaseDatos {

	boolean fichero;

	public BaseDatos(boolean fichero) {
		this.fichero = fichero;
	}

	public Mapa obtenerMapa() {

		BufferedReader br = null;
		String cadenaLeida = "";
		String cadenaBuscar = "@urlBD";

		Mapa mapa = null;

		try {
			// La ruta es relativa al directorio bin de la aplicacion.
			br = new BufferedReader(new FileReader("../recursos/configuracion_base_datos.txt"));
			if (fichero) {

				cadenaBuscar = "@Fichero";
			}
			// Posicionarse sobre la cadena del fichero 'configuracion_base_datos.txt' que esta a continuacion de
			// cadenaBuscar.
			while ((cadenaLeida = br.readLine()) != null && cadenaLeida.trim().compareTo(cadenaBuscar) != 0) {
			}
			// Si cadenaLeida = null entonces el fichero no contenia la cadena cadenaBuscar. El fichero tiene
			// un formato invalido.
			if (cadenaLeida == null) {
				// Se lanza excepcion y se captura mas abajo. Y luego se ejecuta el bloque finally.
				System.out.println("Formato de fichero invalido.");
				throw new IOException();
			}

			if (fichero) {
				// Comprobar que la linea despues de @Fichero tiene contenido.
				String nombreFichero = br.readLine();
				if (nombreFichero == null || (nombreFichero = nombreFichero.trim()).compareTo("") == 0) {
					System.out.println("Formato de fichero invalido.");
					throw new IOException();
				}
				mapa = obtenerMapaFichero(nombreFichero);
			} else {

				String urlBD = br.readLine();
				if (urlBD == null || (urlBD = urlBD.trim()).compareTo("") == 0) {
					System.out.println("Formato de fichero invalido.");
					throw new IOException();
				}
				cadenaBuscar = "@usuario";
				while ((cadenaLeida = br.readLine()) != null && cadenaLeida.trim().compareTo(cadenaBuscar) != 0) {
				}
				if (cadenaLeida == null) {
					// Se lanza excepcion y se captura mas abajo. Y luego se ejecuta el bloque finally.
					System.out.println("Formato de fichero invalido.");
					throw new IOException();
				}
				String usuario = br.readLine();
				if (usuario == null || (usuario = usuario.trim()).compareTo("") == 0) {
					System.out.println("Formato de fichero invalido.");
					throw new IOException();
				}
				cadenaBuscar = "@clave";
				while ((cadenaLeida = br.readLine()) != null && cadenaLeida.trim().compareTo(cadenaBuscar) != 0) {
				}
				// Si cadenaLeida = null entonces el fichero no contenia la cadena cadenaBuscar. El fichero tiene
				// un formato invalido.
				if (cadenaLeida == null) {
					// Se lanza excepcion y se captura mas abajo. Y luego se ejecuta el bloque finally.
					System.out.println("Formato de fichero invalido.");
					throw new IOException();
				}
				String clave = br.readLine();
				if (clave == null) {
					System.out.println("Formato de fichero invalido.");
					throw new IOException();
				}
				mapa = obtenerMapaBD(urlBD, usuario, clave.trim());
			}

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return mapa;
	}

	private Mapa obtenerMapaFichero(String nombreFichero) {

		boolean inicioCarreteras = false;
		int numCarreterasProcesadas = 0;
		String cadenaLeida = null;
		String[] cadenasExtraidas = null;
		BufferedReader brFichero = null;

		Mapa mapa = new Mapa();
		
		System.out.println("\nUSANDO FICHERO COMO BASE DE DATOS.\n");

		try {
			brFichero = new BufferedReader(new FileReader(nombreFichero));

			// Posicionar el puntero del fichero sobre la cadena @Ciudades. Con este bucle evito las l’neas en blanco
			// que pudiera tener el fichero antes de la cadena "@Ciudades". Si la primera l’nea con texto del fichero no
			// es @Ciudades entonces el fichero tiene un formato inv‡lido y retorno un null.
			// Posicionarse sobre la cadena del fichero 'configuracion_base_datos.txt' que esta a continuacion de
			// cadenaBuscar.

			while ((cadenaLeida = brFichero.readLine()) != null && cadenaLeida.trim().compareTo("@Ciudades") != 0) {
			}
			if (cadenaLeida == null) {
				// Se lanza excepcion y se captura mas abajo. Y luego se ejecuta el bloque finally.
				System.out.println("Formato de fichero invalido.");
				throw new IOException();
			}

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
							System.out.println("Formato de fichero invalido");
							throw new IOException();
						}
						mapa.aniadirCiudad(new Ciudad(cadenasExtraidas[0], Float.parseFloat(cadenasExtraidas[1]), Float.parseFloat(cadenasExtraidas[2])));
						Ciudad ciudadAniadida = mapa.obtenerCiudad(cadenasExtraidas[0]);
						System.out.println("Ciudad: " + ciudadAniadida.getNombreCiudad() + " " + ciudadAniadida.getCoordX() + " " + ciudadAniadida.getCoordY());
					}
				}
			}
			// Si no se han creado ciudades al llegar aqui el fichero no esta bien construido.
			if (mapa.numeroCiudadesMapa() == 0) {
				System.out.println("Formato de fichero invalido");
				throw new IOException();
			}
			// Conitnuar procesando las carreteras que unen las ciudades.
			while ((cadenaLeida = brFichero.readLine()) != null) {
				cadenaLeida = cadenaLeida.trim();
				if (cadenaLeida.compareTo("") != 0) {
					cadenasExtraidas = cadenaLeida.split(" ");
					if (cadenasExtraidas.length < 2) {
						System.out.println("Formato de fichero invalido");
						throw new IOException();
					}
					mapa.aniadirCarretera(cadenasExtraidas[0], cadenasExtraidas[1]);
					numCarreterasProcesadas += 1;
				}
			}
			// Si al salir del bucle el numero de carreteras procesadas es nulo, entonces tras la etiqueta
			// @Carreteras no habia nada y por tanto el fichero es erroneo.
			if (numCarreterasProcesadas == 0) {
				System.out.println("Formato de fichero invalido");
				throw new IOException();
			}

			// ----- ----- ----- ----- ----- CODIGO DEPURACION ----- ----- ----- ----- -----
			System.out.println("\nInspeccion de carreteras.\n");
			Iterator<Entry<String, Ciudad>> itSC = mapa.obtenerCiudades().entrySet().iterator();
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

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (brFichero != null) {
				try {
					brFichero.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}

		return mapa;
	}

	private Mapa obtenerMapaBD(String urlBD, String usuario, String clave) {
		// String urlBD = "jdbc:mysql://localhost:3306/pruebabiicode";
		// String usuario = "root";
		// String clave = "";
		String jdbcDriver = "com.mysql.jdbc.Driver";
		Connection conexion = null;
		Statement stmt = null;
		ResultSet rs = null;
		Mapa mapa = new Mapa();
		
		System.out.println("\nUSANDO BASE DE DATOS RELACIONAL.");
		System.out.println(urlBD + " " + usuario + " " + clave + "\n");
		
		try {
			// Cargar el driver.
			Class.forName(jdbcDriver);
			// Conexion a la base de datos.
			conexion = DriverManager.getConnection(urlBD, usuario, clave);
			stmt = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT nombreCiudad, coordX, coordY FROM Ciudades");
			while (rs.next()) {
				mapa.aniadirCiudad(new Ciudad(rs.getString("nombreCiudad"), rs.getFloat("coordX"), rs.getFloat("coordY")));
				Ciudad ciudadAniadida = mapa.obtenerCiudad(rs.getString("nombreCiudad"));
				System.out.println("Ciudad: " + ciudadAniadida.getNombreCiudad() + " " + ciudadAniadida.getCoordX() + " " + ciudadAniadida.getCoordY());
			}
			rs = stmt.executeQuery("SELECT nombreCiudadA, nombreCiudadb FROM Carreteras");
			while (rs.next()) {
				mapa.aniadirCarretera(rs.getString("nombreCiudadA"), rs.getString("nombreCiudadB"));
			}
			rs.close();
			// ----- ----- ----- ----- ----- CODIGO DEPURACION ----- ----- ----- ----- -----
			System.out.println("\nInspeccion de carreteras.\n");
			Iterator<Entry<String, Ciudad>> itSC = mapa.obtenerCiudades().entrySet().iterator();
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
		return mapa;
	}
}
