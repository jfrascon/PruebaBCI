import java.util.HashMap;
import java.util.Map;

/**
 * @author jfrascon
 * @version "%I%,%G% Clase que representa un nodo del grafo.
 */

public class Ciudad {

	private String nombreCiudad;
	private float coordX;
	private float coordY;
	private Map<String, Float> ciudadesVecinas;

	Ciudad(String nombreCiudad, float coordX, float coordY) {

		this.nombreCiudad = nombreCiudad;
		this.coordX = coordX;
		this.coordY = coordY;
		ciudadesVecinas = new HashMap<String, Float>();
	}

	Ciudad() {

		this.nombreCiudad = null;
		this.coordX = 0;
		this.coordY = 0;
		ciudadesVecinas = new HashMap<String, Float>();
	}

	/**
	 * 
	 * @param nombreCiudad
	 * @param coordX
	 * @param coordY
	 */

	/**
	 * 
	 * @return
	 */
	public String getNombreCiudad() {
		return nombreCiudad;
	}

	/**
	 * 
	 * @param nombreCiudad
	 */
	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	/**
	 * 
	 * @return
	 */
	public float getCoordY() {
		return coordY;
	}

	/**
	 * 
	 * @param coordY
	 */
	public void setCoordY(float coordY) {
		this.coordY = coordY;
	}

	/**
	 * 
	 * @return
	 */
	public float getCoordX() {
		return coordX;
	}

	/**
	 * 
	 * @param coordX
	 */
	public void setCoordX(float coordX) {
		this.coordX = coordX;
	}

	public void aniadirCiudadVecina(String ciudadVecina, float distancia) {

		Float distanciaExistente = ciudadesVecinas.put(ciudadVecina, new Float(distancia));
		
		if ( distanciaExistente != null){
			
			System.out.println("Ya existia una entrada anterior para la ciudad " + ciudadVecina + " con distancia " + distancia);
			
		}

	}
	
	public void eliminarCiudadVecina(String ciudadVecina){
		
		ciudadesVecinas.remove(ciudadVecina);
		
	}

	public int numeroCiudadesVecinas() {

		int numeroCiudadesVecinas = 0;

		if (ciudadesVecinas != null) {

			numeroCiudadesVecinas = ciudadesVecinas.size();
		}

		return numeroCiudadesVecinas;
	}

}
