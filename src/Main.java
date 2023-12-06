import java.util.Scanner;

import commandline.MorpionCMD;
import graphic.MorpionGraph;

public class Main {

    public static void main(String[] args) {

        int reponse = 0 ;
        Scanner scan = new Scanner(System.in);
        MorpionCMD morpy ;

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
           morpy = new MorpionGraph();
        } else {
            morpy = new MorpionCMD();
        }

        // Lancement du jeu
        morpy.startGame();
		
		// Boucle d'itération de la partie
		while (!morpy.partieFinie()) {
			morpy.tour();
		}
    }

}
