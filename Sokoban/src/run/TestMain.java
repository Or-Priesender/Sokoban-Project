/* MADE BY : OR PRIESENDER 203703582 AND IDO ABOUDI 204065429 */

package run;




import java.io.FileNotFoundException;
import java.io.IOException;

import invokers.CLI;
import policy.MySokobanPolicy;

public class TestMain {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
		try{
			/*things to consider:
			 * text level loader is limited to 100*100 sized stages because of the reader mark, can be changed.
			 * text level loader is reading the map alone, can be changed if needed.
			 * char level displayer also displays map alone, can be changed if needed.
			 * the CLI is currently familiar with up to 2 word commands.
			 *  
			 */
	
		CLI c = new CLI(new MySokobanPolicy());
		c.userInterface();
		
	
		} catch (IOException  e) {
			
			e.printStackTrace();
		} 
		
	
	}

}
