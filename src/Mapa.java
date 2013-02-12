import java.util.HashMap;
import java.util.Map;

public class Mapa {

	private Map<String, Ciudad> mapa = null;

	Mapa() {

		mapa = new HashMap<String, Ciudad>();
	}

	void aniadirCiudad(Ciudad ciudad) {

		mapa.put(ciudad.getNombreCiudad(), ciudad);

	}

	void aniadirCarretera(String nombreCiudadA, String nombreCiudadB,
			float distanciaKM) {

		Ciudad ciudadA = mapa.get(nombreCiudadA);
		Ciudad ciudadB = mapa.get(nombreCiudadB);

		// Para aniadir una carretera entre dos ciudades, ambas deben existir.
		if (ciudadA == null || ciudadB == null) {
			return;
		}

		// El grafo es bidireccional. La ciudad A esta unica con la ciudad B. Y
		// la ciudad B esta unida con la ciudad A.
		ciudadA.aniadirCiudadVecina(nombreCiudadB, distanciaKM);
		ciudadB.aniadirCiudadVecina(nombreCiudadA, distanciaKM);

		mapa.put(nombreCiudadA, ciudadA);
		mapa.put(nombreCiudadB, ciudadB);

	}

	void eliminarCiudad(String nombreCiudad) {

		mapa.remove(nombreCiudad);

	}

}
