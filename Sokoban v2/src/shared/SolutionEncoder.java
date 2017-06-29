package shared;

import java.util.List;

/**
 * A util for compressing a solution string to a much shorter string. can be de-compressed by the SolutionDecoder class.
 * @author Or Priesender
 *
 */
public class SolutionEncoder {

	List<String> fullSolution;
	
	
	public static String compress(List<String> solution){
		StringBuilder sb = new StringBuilder();
		int counter = 1;
		
		for(int i=1;i<solution.size();i++){
			String current = solution.get(i);
			if(current.equals(solution.get(i-1)))
				counter++;
			else {
				sb.append(counter);
				sb.append(",");
				sb.append(solution.get(i-1));
				sb.append(",");
				counter = 1;
			}
			
		}
		sb.append(counter);
		sb.append(",");
		sb.append(solution.get(solution.size()-1));
		
		return sb.toString();
	}
	
	
}
