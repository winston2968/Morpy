package commandline;

public class MainCommandLine {
	
	public static void main(String[] args) {
		Morpion morpy = new Morpion();
		
		morpy.startGame();
		
		
		while (morpy.partieFinie() == false) {
			morpy.tour();
		}
		
	}

}
