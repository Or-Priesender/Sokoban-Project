package shared;

import java.util.ArrayList;
import java.util.List;

public class SolutionDecoder {

	public static List<String> decompress(String compressed){
		List<String> result = new ArrayList<>();
		String[] s = compressed.split(",");

		for(int i=0;i<s.length;i++){
			if(i%2 == 0)
			{
				int amount = Integer.parseInt(s[i]);
				if(!s[i+1].equals("MoveBox")){
					for(int j=0;j<amount;j++){
						result.add(s[i+1]);
					}
				}
			}
		}
		return result;
	}
}
