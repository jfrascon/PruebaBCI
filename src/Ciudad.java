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
	private Map<String, Float> ciudadesAdyacentes;

	Ciudad(String nombreCiudad, float coordX, float coordY) {

		this.nombreCiudad = nombreCiudad;
		this.coordX = coordX;
		this.coordY = coordY;
		ciudadesAdyacentes = new HashMap<String, Float>();
	}

	Ciudad() {

		this.nombreCiudad = null;
		this.coordX = 0;
		this.coordY = 0;
		ciudadesAdyacentes = new HashMap<String, Float>();
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

	public void aniadirCiudadAdyacente(String nombreCiudadVecina,
			float distanciaKM) {

		ciudadesAdyacentes.put(nombreCiudadVecina, new Float(distanciaKM));

	}

	public void eliminarCiudadVecina(String ciudadVecina) {

		ciudadesAdyacentes.remove(ciudadVecina);

	}

	public int numeroCiudadesVecinas() {

		return ciudadesAdyacentes.size();

	}

	public Map<String, Float> obtenerCiudadesAdyacentes() {

		return ciudadesAdyacentes;

	}

}