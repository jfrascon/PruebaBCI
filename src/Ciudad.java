/**
 * @author jfrascon
 * @version "%I%,%G% Clase que representa un nodo del grafo.
 */

public class Ciudad {

	private String nombreCiudad;
	private float coordX;
	private float coordY;

	/**
	 * 
	 * @param nombreCiudad
	 * @param coordX
	 * @param coordY
	 */
	Ciudad(String nombreCiudad, float coordX, float coordY) {

		this.nombreCiudad = nombreCiudad;
		this.coordX = coordX;
		this.coordY = coordY;
	}

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

}
