import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import commandline.MorpionCMD;
import graphic.MorpionGraph;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		int reponse = 0;
		Scanner scan = new Scanner(System.in);
		MorpionCMD morpy;
		MorpionGraph morpi;

		// Choix du joueur du mode de jeu
		while (reponse != 1 && reponse != 2) {
			System.out.println("""
					Morpy:/$ Choisissez votre mode de jeu :
					        1 --> Graphique
					        2 --> CMD
					        """);
			System.out.print("Morpy:/$ ");
			reponse = scan.nextInt();
		}

		// Création de la bonne instance de morpion
		if (reponse == 1) {
			morpi = new MorpionGraph();

			// Lancement du jeu
			morpi.startGame();

			while (!morpi.connexiondone) {
				TimeUnit.SECONDS.sleep(1);
			}
			// Boucle d'itération de la partie
			morpi.plateau();
			
			
			while (!morpi.partieFinie()) {
				morpi.tour();
			}

		} else {
			morpy = new MorpionCMD();

			// Lancement du jeu
			morpy.startGame();

			// Boucle d'itération de la partie
			while (!morpy.partieFinie()) {
				morpy.tour();
			}
		}

	}

}
