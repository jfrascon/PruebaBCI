/**
 * @author jfrascon
 * @version "%I%,%G% Clase que representa un arco del grafo.
 */

public class Carretera {

	private String nombreCarretera;
	private Ciudad ciudadExtremo1;
	private Ciudad ciudadExtremo2;
	private float peso;

	/**
	 * @param ciudadExtremo1
	 * @param ciudadExtremo2
	 * @param peso
	 */
	Carretera(Ciudad ciudadExtremo1, Ciudad ciudadExtremo2, float peso) {

		this.ciudadExtremo1 = ciudadExtremo1;
		this.ciudadExtremo2 = ciudadExtremo2;
		this.peso = peso;

	}

	/**
	 * @return El nombre de la carretera.
	 */
	public String getNombreCarretera() {
		return nombreCarretera;
	}

	/**
	 * @param nombreCarretera
	 */

	public void setNombreCarretera(String nombreCarretera) {
		this.nombreCarretera = nombreCarretera;
	}

	/**
	 * 
	 * @return
	 */
	public Ciudad getCiudadExtremo1() {
		return ciudadExtremo1;
	}

	/**
	 * 
	 * @param ciudadExtremo1
	 */
	public void setCiudadExtremo1(Ciudad ciudadExtremo1) {
		this.ciudadExtremo1 = ciudadExtremo1;
	}

	/**
	 * 
	 * @return
	 */
	public Ciudad getCiudadExtremo2() {
		return ciudadExtremo2;
	}

	/**
	 * 
	 * @param ciudadExtremo2
	 */
	public void setCiudadExtremo2(Ciudad ciudadExtremo2) {
		this.ciudadExtremo2 = ciudadExtremo2;
	}

	/**
	 * 
	 * @return
	 */
	public float getPeso() {
		return peso;
	}

	/**
	 * 
	 * @param peso
	 */
	public void setPeso(float peso) {
		this.peso = peso;
	}

}
