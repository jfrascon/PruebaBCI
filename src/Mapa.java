import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Mapa {

	private Map<String, Ciudad> ciudades = null;

	Mapa() {

		ciudades = new HashMap<String, Ciudad>();
	}

	public void aniadirCiudad(Ciudad ciudad) {
		// Si la clave 'ciudad.getNombreCiudad()' existe en el mapa
		// antes de la llamada a este metodo su valor asociado es machacado al
		// ejecutar el metodo put.
		ciudades.put(ciudad.getNombreCiudad(), ciudad);

	}

	public void aniadirCarretera(String nombreCiudadA, String nombreCiudadB) {

		// Una carretera comunica dos ciudades que existen en el mapa.
		// Si una de las dos ciudades no existe finaliza el metodo.
		if (nombreCiudadA == null || nombreCiudadB == null) {

			return;

		}

		Ciudad ciudadA = ciudades.get(nombreCiudadA);
		Ciudad ciudadB = ciudades.get(nombreCiudadB);

		if (ciudadA == null || ciudadB == null) {

			return;
		}

		// Distancia entre dos ciudades. Teorema de Pitagoras.
		float deltaX = ciudadA.getCoordX() - ciudadB.getCoordX();
		float deltaY = ciudadA.getCoordY() - ciudadB.getCoordY();
		float distanciaKM = (float) Math.hypot(deltaX, deltaY);

		// El grafo es bidireccional. La ciudad A esta unida con la ciudad B. Y
		// la ciudad B esta unida con la ciudad A.
		ciudadA.aniadirCiudadAdyacente(nombreCiudadB, distanciaKM);
		ciudadB.aniadirCiudadAdyacente(nombreCiudadA, distanciaKM);

		ciudades.put(nombreCiudadA, ciudadA);
		ciudades.put(nombreCiudadB, ciudadB);

	}

	public void eliminarCiudad(String nombreCiudad) {

		ciudades.remove(nombreCiudad);

	}

	public int numeroCiudadesMapa() {

		return ciudades.size();
	}

	public Map<String, Ciudad> obtenerCiudades() {

		return ciudades;
	}

	public Ciudad obtenerCiudad(String nombreCiudad) {

		return ciudades.get(nombreCiudad);
	}

	public String obtenerCamino(Map<String, String> ciudadesDijkstra,
			String nombreCiudadDestino) {

		if (nombreCiudadDestino == null
				|| !ciudadesDijkstra.containsKey(nombreCiudadDestino)) {

			return "No hay camino hasta el destino";

		}

		String camino = nombreCiudadDestino;
		String nombreCiudadPadreDistanciaDesdeCiudadOrigen[] = ciudadesDijkstra
				.get(nombreCiudadDestino).split(" ");
		String nombreCiudadPadre = nombreCiudadPadreDistanciaDesdeCiudadOrigen[0];
		float distanciaDesdeCiudadOrigen = Float
				.parseFloat(nombreCiudadPadreDistanciaDesdeCiudadOrigen[1]);

		while (nombreCiudadPadre.compareTo("?") != 0) {

			camino = nombreCiudadPadre + "->" + camino;
			nombreCiudadPadre = (String) ciudadesDijkstra
					.get(nombreCiudadPadre).split(" ")[0];
		}

		return camino + " ( " + distanciaDesdeCiudadOrigen + " km ) ";

	}

	public String algoritmoAEstrella(String nombreCiudadOrigen,
			String nombreCiudadDestinto) {

		if (nombreCiudadOrigen == null || nombreCiudadDestinto == null
				|| !ciudades.containsKey(nombreCiudadOrigen)
				|| !ciudades.containsKey(nombreCiudadDestinto)) {

			return "El origen y/o el destino no se encuentran en el mapa.";
		}

		if (nombreCiudadOrigen.equals(nombreCiudadDestinto)) {

			return "Se encuentra en su destinto.";

		}

		// Lista abierta.
		Map<String, Float> ciudadesPendientes = new HashMap<String, Float>();

		// Lista cerrada.
		Map<String, Float> ciudadesVisitadas = new HashMap<String, Float>();

		Map<String, Float> ciudadesVecinas = new HashMap<String, Float>();

		Iterator<Entry<String, Float>> it = null;
		Entry<String, Float> ciudadApuntada = null;

		// g
		float g_distanciaAcumuladaTotal = 0.0f;
		// h
		float distanciaAcumulada = 0.0f;

		float costeCiudad = 0.0f;

		float costeMinimo = 0.0f;

		float deltaX = 0.0f;

		float deltaY = 0.0f;

		float coordXCiudadDestino = ciudades.get(nombreCiudadDestinto)
				.getCoordX();

		float coordYCiudadDestino = ciudades.get(nombreCiudadDestinto)
				.getCoordY();

		String nombreCiudadEscogida = "";

		// Aniadir la ciudad origen a la lista abierta.
		ciudadesPendientes.put(nombreCiudadOrigen, g_distanciaAcumuladaTotal);

		// Recorrer la lista abierta mientras posea ciudades en busca de aquella
		// que posea el menor coste.
		while (!ciudadesPendientes.isEmpty()) {

			it = ciudadesPendientes.entrySet().iterator();

			costeMinimo = Float.MAX_VALUE;

			// Buscar en la lista abierta la ciudad de menor corte.
			while (it.hasNext()) {

				ciudadApuntada = it.next();

				deltaX = ciudades.get(ciudadApuntada.getKey()).getCoordX()
						- coordXCiudadDestino;

				deltaY = ciudades.get(ciudadApuntada.getKey()).getCoordY()
						- coordYCiudadDestino;

				costeCiudad = ciudadApuntada.getValue()
						+ (float) Math.hypot(deltaX, deltaY);

				if (costeCiudad < costeMinimo) {

					costeMinimo = costeCiudad;

					nombreCiudadEscogida = ciudadApuntada.getKey();
				}
			}

			if (nombreCiudadEscogida.equals(nombreCiudadDestinto)) {

				// AQUI ACABA LA FUNCION.
				return "";
			} else {

				g_distanciaAcumuladaTotal = ciudadApuntada.getValue();

				// Eliminar la ciudad apuntada de la lista abierta.
				ciudadesPendientes.remove(nombreCiudadEscogida);

				// Aniadir la ciudad apuntada de la lista cerrada, junto con la
				// distancia
				// que existe entre la ciudad de origen y ella,
				ciudadesVisitadas.put(nombreCiudadEscogida,
						g_distanciaAcumuladaTotal);

				// Las ciudades vecinas de la ciudad escogida se deben aniadir a
				// la lista abierta excepto aquellas que se hayan en la lista
				// cerrada.
				ciudadesVecinas = ciudades.get(nombreCiudadEscogida)
						.obtenerCiudadesAdyacentes();

				it = ciudadesVecinas.entrySet().iterator();

				while (it.hasNext()) {

					ciudadApuntada = it.next();

					// Comprobar si la ciudad vecina apuntada se haya en la
					// lista cerrada.
					// En caso afirmativo no hacer nada.
					// En caso negativo continuar con las comprobaciones.
					if (!ciudadesVisitadas.containsKey(ciudadApuntada.getKey())) {

						// Distancia desde la ciudad origen hasta la ciudad
						// vecina apuntada.
						distanciaAcumulada = g_distanciaAcumuladaTotal
								+ ciudadApuntada.getValue();

						// Comprobar si la lista abierta cotiene la ciudad
						// vecina apuntada.
						// En caso afirmativo continuar con las comprobaciones.
						// En caso negativo, aniadir la ciudad vecina apuntada a
						// la lista abierta.
						if (ciudadesPendientes.containsKey(ciudadApuntada
								.getKey())) {

							// La ciudad vecina apuntada se haya en la lista
							// abierta.
							// Comprobar si la distancia desde la ciudad origen
							// hasta la ciudad vecina apuntada ahora es menor
							// que la distancia que se tiene apuntada en lista
							// abierta.
							// En caso afirmativo registrar la nueva distancia.
							// En caso negativo ignorar el resultado.
							if (distanciaAcumulada < ciudadesPendientes
									.get(ciudadApuntada.getKey())) {

								ciudadesPendientes.put(ciudadApuntada.getKey(),
										distanciaAcumulada);

							}

						} else {

							ciudadesPendientes.put(ciudadApuntada.getKey(),
									distanciaAcumulada);

						}

					}

				}

			}

		}

		return "No existe ruta hasta el destino";

	}

	public Map<String, String> dijkstra(String nombreCiudadOrigen) {

		// Gestion de errores.
		if (nombreCiudadOrigen == null
				|| !ciudades.containsKey(nombreCiudadOrigen)) {

			return null;
		}

		int numeroCiudadesVisitadas = 0;
		String nombreCiudadPadreDistanciaDesdeCiudadOrigen[] = null;
		String nombreCiudadPadre = "";
		String nombreCiudadSeleccionada = "";
		float distanciaDesdeCiudadOrigen = 0.0f;
		float distanciaDesdeCiudadOrigenAnterior = 0.0f;
		float distanciaDesdeCiudadOrigenMinima = 0.0f;

		boolean b = false;

		// Clave: nombreCiudadPadre-nombreCiudadHijo
		Map<String, String> ciudadesDijkstra = new HashMap<String, String>();
		Map<String, String> ciudadesVisitadas = new HashMap<String, String>();
		// SS -> String String
		Iterator<Entry<String, String>> itSS = null;
		Entry<String, String> entrySS = null;

		// SC -> String Ciudad
		Iterator<Entry<String, Ciudad>> itSC = ciudades.entrySet().iterator();
		Entry<String, Ciudad> entrySC = null;

		Map<String, Float> ciudadesAdyacentes = null;
		// SFCA -> String Float Ciudades Adyacentes.
		Iterator<Entry<String, Float>> itSFCA = null;
		Entry<String, Float> entrySFCA = null;

		// Marcar todos las ciudades como no visitados al principio del
		// algoritmo.
		while (itSC.hasNext()) {

			entrySC = itSC.next();
			ciudadesDijkstra.put(entrySC.getKey(), "? -1");
		}

		// ----- ----- ----- ----- ----- ----- ----- ----- ----- -----
		System.out.println("\n");
		itSS = ciudadesDijkstra.entrySet().iterator();
		while (itSS.hasNext()) {
			entrySS = itSS.next();
			nombreCiudadPadreDistanciaDesdeCiudadOrigen = entrySS.getValue()
					.split(" ");

			b = ciudadesVisitadas.containsKey(entrySS.getKey());

			if (b) {
				System.out
						.println("* "
								+ entrySS.getKey()
								+ " "
								+ nombreCiudadPadreDistanciaDesdeCiudadOrigen[0]
								+ " "
								+ Float.parseFloat(nombreCiudadPadreDistanciaDesdeCiudadOrigen[1]));
			} else {

				System.out
						.println("  "
								+ entrySS.getKey()
								+ " "
								+ nombreCiudadPadreDistanciaDesdeCiudadOrigen[0]
								+ " "
								+ Float.parseFloat(nombreCiudadPadreDistanciaDesdeCiudadOrigen[1]));

			}
		}
		// ----- ----- ----- ----- ----- ----- ----- ----- ----- -----

		// La ciudad origen no tiene ciudad predecesora y dista 0 km de
		// ella misma.
		// En la clave nombreCiudadPadre vale ?
		ciudadesDijkstra
				.put(nombreCiudadOrigen, "? " + new Float(0).toString());
		// Registrar la ciudad origen como visitada.
		ciudadesVisitadas.put(nombreCiudadOrigen, "");
		numeroCiudadesVisitadas = 1;

		// ----- ----- ----- ----- ----- ----- ----- ----- ----- -----
		System.out.println("\n");
		itSS = ciudadesDijkstra.entrySet().iterator();
		while (itSS.hasNext()) {
			entrySS = itSS.next();
			nombreCiudadPadreDistanciaDesdeCiudadOrigen = entrySS.getValue()
					.split(" ");

			b = ciudadesVisitadas.containsKey(entrySS.getKey());

			if (b) {
				System.out
						.println("* "
								+ entrySS.getKey()
								+ " "
								+ nombreCiudadPadreDistanciaDesdeCiudadOrigen[0]
								+ " "
								+ Float.parseFloat(nombreCiudadPadreDistanciaDesdeCiudadOrigen[1]));
			} else {

				System.out
						.println("  "
								+ entrySS.getKey()
								+ " "
								+ nombreCiudadPadreDistanciaDesdeCiudadOrigen[0]
								+ " "
								+ Float.parseFloat(nombreCiudadPadreDistanciaDesdeCiudadOrigen[1]));

			}
		}
		// ----- ----- ----- ----- ----- ----- ----- ----- ----- -----

		// Obtener las ciudades adyacentes a la ciudad origen.
		// Cada ciudad es asociada con la ciudad padre y con la
		// distancia desde la ciudad origen hasta ella.
		ciudadesAdyacentes = ciudades.get(nombreCiudadOrigen)
				.obtenerCiudadesAdyacentes();
		itSFCA = ciudadesAdyacentes.entrySet().iterator();

		while (itSFCA.hasNext()) {

			entrySFCA = itSFCA.next();

			ciudadesDijkstra.put(entrySFCA.getKey(), nombreCiudadOrigen + " "
					+ entrySFCA.getValue().toString());
		}

		// ----- ----- ----- ----- ----- ----- ----- ----- ----- -----
		System.out.println("\n");
		itSS = ciudadesDijkstra.entrySet().iterator();
		while (itSS.hasNext()) {
			entrySS = itSS.next();
			nombreCiudadPadreDistanciaDesdeCiudadOrigen = entrySS.getValue()
					.split(" ");

			b = ciudadesVisitadas.containsKey(entrySS.getKey());

			if (b) {
				System.out
						.println("* "
								+ entrySS.getKey()
								+ " "
								+ nombreCiudadPadreDistanciaDesdeCiudadOrigen[0]
								+ " "
								+ Float.parseFloat(nombreCiudadPadreDistanciaDesdeCiudadOrigen[1]));
			} else {

				System.out
						.println("  "
								+ entrySS.getKey()
								+ " "
								+ nombreCiudadPadreDistanciaDesdeCiudadOrigen[0]
								+ " "
								+ Float.parseFloat(nombreCiudadPadreDistanciaDesdeCiudadOrigen[1]));

			}
		}
		// ----- ----- ----- ----- ----- ----- ----- ----- ----- -----

		while (numeroCiudadesVisitadas < ciudades.size() - 1) {
			// Seleccionar en 'ciudadesDijkstra' la ciudad descubierta no
			// visitada
			// mas cercana a la ciuadad origen.
			distanciaDesdeCiudadOrigenMinima = Float.POSITIVE_INFINITY;
			itSS = ciudadesDijkstra.entrySet().iterator();
			while (itSS.hasNext()) {
				entrySS = itSS.next();
				nombreCiudadPadreDistanciaDesdeCiudadOrigen = entrySS
						.getValue().split(" ");
				distanciaDesdeCiudadOrigen = Float
						.parseFloat(nombreCiudadPadreDistanciaDesdeCiudadOrigen[1]);

				// distanciaDesdeCiudadOrigen != -1 significa ciudad descubierta
				if (distanciaDesdeCiudadOrigen != -1
						&& !ciudadesVisitadas.containsKey(entrySS.getKey())
						&& distanciaDesdeCiudadOrigen < distanciaDesdeCiudadOrigenMinima) {

					distanciaDesdeCiudadOrigenMinima = distanciaDesdeCiudadOrigen;
					nombreCiudadSeleccionada = entrySS.getKey();
				}

			}
			
			System.out.println();

			// Marcar la ciudad adyacente seleccionada como visitada.
			ciudadesVisitadas.put(nombreCiudadSeleccionada, "");
			numeroCiudadesVisitadas += 1;

			// ----- ----- ----- ----- ----- ----- ----- ----- ----- -----
			System.out.println("\n");
			itSS = ciudadesDijkstra.entrySet().iterator();
			while (itSS.hasNext()) {
				entrySS = itSS.next();
				nombreCiudadPadreDistanciaDesdeCiudadOrigen = entrySS
						.getValue().split(" ");

				b = ciudadesVisitadas.containsKey(entrySS.getKey());

				if (b) {
					System.out
							.println("* "
									+ entrySS.getKey()
									+ " "
									+ nombreCiudadPadreDistanciaDesdeCiudadOrigen[0]
									+ " "
									+ Float.parseFloat(nombreCiudadPadreDistanciaDesdeCiudadOrigen[1]));
				} else {

					System.out
							.println("  "
									+ entrySS.getKey()
									+ " "
									+ nombreCiudadPadreDistanciaDesdeCiudadOrigen[0]
									+ " "
									+ Float.parseFloat(nombreCiudadPadreDistanciaDesdeCiudadOrigen[1]));

				}
			}
			// ----- ----- ----- ----- ----- ----- ----- ----- ----- -----

			// Para cada ciudad adyacente no visitada de la ciudad seleccionada
			// decidir si resulta mejor el camino calculado antes o si es
			// mejor usar el camino que lleva a la ciudad actual seleccionada y
			// a continuacion usar el arco que las une.
			distanciaDesdeCiudadOrigen = distanciaDesdeCiudadOrigenMinima;
			ciudadesAdyacentes = ciudades.get(nombreCiudadSeleccionada)
					.obtenerCiudadesAdyacentes();
			itSFCA = ciudadesAdyacentes.entrySet().iterator();
			while (itSFCA.hasNext()) {
				entrySFCA = itSFCA.next();
				// Ciudad adyacente no visitada
				if (!ciudadesVisitadas.containsKey(entrySFCA.getKey())) {

					distanciaDesdeCiudadOrigen += entrySFCA.getValue()
							.floatValue();
					distanciaDesdeCiudadOrigenAnterior = Float
							.parseFloat(ciudadesDijkstra
									.get(entrySFCA.getKey()).split(" ")[1]);

					// Si distanciaDesdeCiudadOrigenAnterior != -1 entonces
					// la ciudad adyacente que se esta procesando ya fue
					// considerada como ciudad adyacente en un
					// paso anterior del algoritmo.
					if (distanciaDesdeCiudadOrigenAnterior != -1) {

						if (distanciaDesdeCiudadOrigen < distanciaDesdeCiudadOrigenAnterior) {

							ciudadesDijkstra.put(
									entrySFCA.getKey(),
									nombreCiudadSeleccionada
											+ " "
											+ new Float(
													distanciaDesdeCiudadOrigen)
													.toString());

						}
					} else {

						ciudadesDijkstra.put(
								entrySFCA.getKey(),
								nombreCiudadSeleccionada
										+ " "
										+ new Float(distanciaDesdeCiudadOrigen)
												.toString());
					}
				}
			}

			// ----- ----- ----- ----- ----- ----- ----- ----- ----- -----
			System.out.println("\n");
			itSS = ciudadesDijkstra.entrySet().iterator();
			while (itSS.hasNext()) {
				entrySS = itSS.next();
				nombreCiudadPadreDistanciaDesdeCiudadOrigen = entrySS
						.getValue().split(" ");

				b = ciudadesVisitadas.containsKey(entrySS.getKey());

				if (b) {
					System.out
							.println("* "
									+ entrySS.getKey()
									+ " "
									+ nombreCiudadPadreDistanciaDesdeCiudadOrigen[0]
									+ " "
									+ Float.parseFloat(nombreCiudadPadreDistanciaDesdeCiudadOrigen[1]));
				} else {

					System.out
							.println("  "
									+ entrySS.getKey()
									+ " "
									+ nombreCiudadPadreDistanciaDesdeCiudadOrigen[0]
									+ " "
									+ Float.parseFloat(nombreCiudadPadreDistanciaDesdeCiudadOrigen[1]));

				}
			}
			// ----- ----- ----- ----- ----- ----- ----- ----- ----- -----

		}

		return ciudadesDijkstra;
	}

}
