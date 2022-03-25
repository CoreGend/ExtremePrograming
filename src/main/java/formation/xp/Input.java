package formation.xp;

import java.util.Scanner;
import java.util.Arrays;

public class Input {
	
	private Input() {}
	
	public static String BoardInput() {
		Scanner sin = new Scanner(System.in);
		boolean done = false;
		String res = null;
		String[] validInputs = {"z","q","s","d","w","a", "quit"};
		
		do {
			try {
				String[] in = sin.nextLine().toLowerCase().split(" ");
				if(in.length == 1) {					
					if (Arrays.asList(validInputs).contains(in[0])) {
						if(in[0].equals("a") || in[0].equals("q")) {
							res = "left";
						} else if (in[0].equals("z") || in[0].equals("w")) {
							res = "up";
						} else if (in[0].equals("s")) {
							res = "down";
						} else if (in[0].equals("d")){
							res = "right";
						} else if (in[0].equals("quit")) {
							res = "quit";
						}
						done = true;
					}
				} else {
					break;
				}
			} catch(Exception e) {
				// rien
			}
			
			if(!done) {
				System.err.println("Format incorrect, format attendu : 'z'");
			}
		} while (!done && sin.hasNextLine());
		
		return res;
	}
}
