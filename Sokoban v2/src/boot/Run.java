/* MADE BY : OR PRIESENDER 203703582 AND IDO ABOUDI 204065429 */

package boot;




import java.io.FileNotFoundException;
import java.io.IOException;

import controller.CLI;
import controller.SokobanController;
import model.data.MyModel;
import model.policy.MySokobanPolicy;
import view.MyView;

public class Run {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
		
			/*things to consider:
			 * text level loader is limited to 100*100 sized stages because of the reader mark, can be changed.
			 * text level loader is reading the map alone, can be changed if needed.
			 * char level displayer also displays map alone, can be changed if needed.
			 * the CLI is currently familiar with up to 2 word commands.
			 * level only handles the map, in the future : add other data members in txt loader and saver, and in move commands.	
			 */
		
			MyView view = new MyView();
			MyModel model = new MyModel();
			SokobanController c = new SokobanController(view,model);
			view.addObserver(c);
			model.addObserver(c);
			
		/*

		try{
	
		CLI c = new CLI(new MySokobanPolicy());
		c.userInterface();
		
	
			
			
		} catch (IOException  e) {
			
			e.printStackTrace();
		} 
	*/	
	
	}

}
