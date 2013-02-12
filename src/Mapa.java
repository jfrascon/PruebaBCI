import java.util.HashMap;
import java.util.Map;

public class Mapa {

	private Map<String, Ciudad> ciudades = null;

	Mapa() {

		ciudades = new HashMap<String, Ciudad>();
	}

	public void aniadirCiudad(Ciudad ciudad) {

		ciudades.put(ciudad.getNombreCiudad(), ciudad);

	}

	public void aniadirCarretera(String nombreCiudadA, String nombreCiudadB) {

		Ciudad ciudadA = ciudades.get(nombreCiudadA);
		
		Ciudad ciudadB = ciudades.get(nombreCiudadB);

		// Para aniadir una carretera entre dos ciudades, ambas deben existir.
		if (ciudadA == null || ciudadB == null) {
		
			return;
		
		}

		// El grafo es bidireccional. La ciudad A esta unica con la ciudad B. Y
		// la ciudad B esta unida con la ciudad A.
		float distanciaKM = (float)Math.hypot(ciudadA.getCoordX() - ciudadB.getCoordX(), ciudadA.getCoordY() - ciudadB.getCoordY());
		
		ciudadA.aniadirCiudadVecina(nombreCiudadB, distanciaKM);
		
		ciudadB.aniadirCiudadVecina(nombreCiudadA, distanciaKM);

		ciudades.put(nombreCiudadA, ciudadA);
		
		ciudades.put(nombreCiudadB, ciudadB);

	}

	public void eliminarCiudad(String nombreCiudad) {

		ciudades.remove(nombreCiudad);

	}
	
	public int numeroCiudadesMapa(){
		
		return ciudades.size();
	}
	
	
	public Map<String, Ciudad> obtenerCiudades(){
		
		return ciudades;
	}
	
	
	public Ciudad obtenerCiudad(String nombreCiudad){
		
		return ciudades.get(nombreCiudad);
	}
	
	
}
