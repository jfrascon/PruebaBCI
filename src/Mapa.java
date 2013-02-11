import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Mapa {
	
	private Map<String, Ciudad> mapa = null;
	
	Mapa(String nombreFicheroMapa){
		
		mapa = new HashMap<String, Ciudad>();
		
		BufferedReader br = null;
		String cadenaFichero = null;
		
		try {
			
			br = new BufferedReader(new FileReader(nombreFicheroMapa));
			
			while((cadenaFichero = br.readLine()) != null){
				
				
				
				
				
			}
			
		} catch (FileNotFoundException fnfe) {
			
			fnfe.printStackTrace();
			
		} catch (IOException ioe) {
			
			ioe.printStackTrace();
		}
		

		
		
		
	}
	

	
	

}
