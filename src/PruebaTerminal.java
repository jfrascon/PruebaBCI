import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PruebaTerminal {

	public static void main(String[] args) {

		BufferedReader br = null;
		String cadenaFichero = null;
		boolean inicioCiudades = false;
		
		System.out.println(args[0] + " " + args[1]);

		try {

			br = new BufferedReader(new FileReader(args[1]));

			// Posicionar el puntero del fichero sobre la caneda @Ciudades.
			while ((cadenaFichero = br.readLine()) != null && !inicioCiudades) {
				
				if(cadenaFichero.equals("@Ciudades")){
					
					inicioCiudades = true;
					
				}

			}
			
			// Continuar leyendo las ciudades
			if(inicioCiudades)

		} catch (FileNotFoundException fnfe) {

			fnfe.printStackTrace();

		} catch (IOException ioe) {

			ioe.printStackTrace();
		}

	}

}
