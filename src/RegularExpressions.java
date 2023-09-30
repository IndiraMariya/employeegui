
// TODO: Auto-generated Javadoc
/**
 * The Class RegularExpressions.
 */
public class RegularExpressions {
	

	/**
	 * Takes a string formatted as "<Last Name>, <First Name>" and returns
	 * a String in with "<First Name> <Last Name>". Note that there could be any
	 * number of spaces before or after each name in the input string.
	 * You *MUST* use regex grouping to implement this....
	 *
	 * @param in the input string 
	 * @return the swapped string
	 */
	String swapLastFirst(String in) {
		String pattern = "\\s*(\\w+)\\s*,\\s*(\\w+)\\s*";
		String result = in.replaceAll("\\s+{2,}", "");
		result = result.replaceAll(pattern, "$2 $1").replaceAll(",(\\w+)", "$1 ").replaceAll("(\\w+),", " $1");
		return result;
	}

	/**
	 * Pad tokens with spaces. Takes a string with a numeric expression,
	 * and pads all arithmetic tokens ('-', '+', '*', '/','(', and ')' ) 
	 * with spaces on either side. After replacement, any leading spaces at 
	 * the start of the string should be removed (ie, the first character of the 
	 * string cannot be a space). You MUST use regex grouping to implement the padding.
	 *
	 * @param in the numeric expression as an input string
	 * @return the formatted string with spaces added to either side of the numeric
	 *         operators.
	 */
	String padTokensWithSpaces(String in) {
		//TODO: Implement this method - it should be no more than 2-3 lines
		String pattern = "([\\+\\*-\\/\\(\\)])";
		String result = in.replaceAll(pattern, " $1 ").replaceAll("^\\s*", "");
	    result = result.replaceAll("\\.\\s+(?=[0-9a-z\\s])", ".").replaceAll("(?<=[0-9a-z\\s])\\s+\\.", ".");
		return result;
	}
	
	/**
	 * Identify token type. Returns an array of strings which identifies
	 * the type of token as an Operation, Integer, Double or Error. Each token 
	 * will be formated as "Type: token", where Type is one of the four options
	 * above.
	 *
	 * @param in the in
	 * @return the string[]
	 */
	String[] identifyTokenType(String in) {
	    String result = padTokensWithSpaces(in);
	    String[] tokens = result.split("\\s+");
	    for (int i = 0; i < tokens.length; i++) {
	        String token = tokens[i];
	        if (token.matches("\\d+")) {
	            tokens[i] = "Integer: " + token;
	        } else if (token.matches("-?\\d+(\\.\\d+)?")) {
	            tokens[i] = "Double: " + token;
	        } else if (token.matches("[\\+\\*-\\/\\(\\)]")){
	            tokens[i] = "Operation: " + token;
	        } else {
	        	tokens[i] = "Error: " + token;
			}
	    }
	    return tokens;
	} 
	
	/**
	 * Method to print the String[] of tokens to the console.
	 *
	 * @param tokens the tokens
	 */
	void printTokens(String[] tokens) {
		for (int i = 0; i < tokens.length; i++ ) {
			System.out.println(tokens[i]);
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegularExpressions regex = new RegularExpressions();
		String test = "Duck,Daffy";   // ==> Should go to [Daffy Duck]
		System.out.println(test+" becomes ["+regex.swapLastFirst(test)+"]");
		test = "   Duck , Daisy";     // ==> Should become [Daisy Duck]
		System.out.println(test+" becomes ["+regex.swapLastFirst(test)+"]");
		test = "   Duck ,Donald";     // ==> Should become [Donald Duck]
		System.out.println(test+" becomes ["+regex.swapLastFirst(test)+"]");
		test = "	       ,Beyonce		";// ==> Should become [Donald Duck]
		System.out.println(test+" becomes ["+regex.swapLastFirst(test)+"]");

		
		test = "100+59*(17/4)";       // ==> [100 + 59 *  ( 17 / 4 ) ]
		System.out.println(test+" becomes ["+regex.padTokensWithSpaces(test)+"]");
		test = "-57+217*(17/-4) ";    // ==> [- 57 + 217 *  ( 17 /  - 4 )  ]
		System.out.println(test+" becomes ["+regex.padTokensWithSpaces(test)+"]");
		String[] tokens = regex.identifyTokenType(test);
		System.out.println("Identifying token types in ["+test+"]:");
		regex.printTokens(tokens);
		/*  Tokens should match:
		 *  Operation: -
		 *  Integer: 57
		 *  Operation: +
		 *  Integer: 217
		 *  Operation: *
		 *  Operation: (
		 *  Integer: 17
		 *  Operation: /
		 *  Operation: -
		 *  Integer: 4
		 *  Operation: ) 
		 */
		test = "-5.7+0.217*(107/-4.0)*17";
		System.out.println(test+" becomes ["+regex.padTokensWithSpaces(test)+"]");
		tokens = regex.identifyTokenType(test);
		System.out.println("Identifying token types in ["+test+"]:");
		regex.printTokens(tokens);
		/*Tokens should match:
		 *Operation: -
		 *Double: 5.7
		 *Operation: +
		 *Double: 0.217
		 *Operation: *
		 *Operation: (
		 *Integer: 107
		 *Operation: /
		 *Operation: -
		 *Double: 4.0
		 *Operation: )
		 *Operation: *
		 *Integer: 17
		 */
	}

}
