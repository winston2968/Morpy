package test;

import java.util.Arrays;

public class ConversionTableauT {
	
	public static void main(String[] args) {
		char[][] tableau = new char[][] {{'.', 'X', 'O'}, {'.', '.', 'O'}, {'O', '.', 'X'}};
		
		/*
		// Remplissage du tableau de point
		for (int i = 0; i < tableau.length; i = i + 1) {
			for (int j = 0; j < tableau[i].length; j = j + 1) {
				tableau[i][j] = '.';
			}
		}
		*/
		
		System.out.println(afficheTableau(tableau));
		String message = convertToSend(tableau);
		System.out.println("After conversion : " + message);
		char[][] tableau2 = convertToPrint(message);
		System.out.println("Conversion...");
		System.out.println(afficheTableau(tableau2));
		tableau2[1][1] = 'O';
		System.out.println(afficheTableau(tableau2));
		
		
		
		
		
		
		
		
		/*
		// On affiche le tableau
		String retour  = afficheTableau(tableau);
		System.out.println(retour);
		// Récupération des valeurs
		String[] split1 = retour.split("\n");
		System.out.println(afficheListe(split1));
		String[][] split2 = new String[3][];
		for (int i = 0; i < split1.length ;i++) {
			split2[i] = split1[i].split(" ");
		}
		System.out.println(afficheTableau2(split2));
		System.out.println(split2[2][2]);
		*/
		
	}
	
	// Convertit le message reçu pour l'affichage
	public static char[][] convertToPrint(String chaine) {
		char[][] tableau = new char[3][3];
		String[] split1 = chaine.split("-");
		String[][] split2 = new String[3][3];
		for (int i = 0 ; i < split1.length ; i++) {
			split2[i] = split1[i].split(",");
		}
		for (int i = 0 ; i < split2.length ; i++) {
			for (int j = 0 ; j < split2[i].length ; j++) {
				tableau[i][j] = split2[i][j].charAt(0);
			}
		}
		return tableau ;
		
		
	}
	
	
	// Convertit le tableau pour l'envoi dans le socket
	public static String convertToSend(char[][] tableau) {
		String retour = "" ;
		for (int i = 0 ; i < tableau.length ; i++) {
			for (int j = 0 ; j < tableau.length ; j++) {
				if (j == tableau[i].length - 1) {
					retour += tableau[i][j];
				} else {
					retour += tableau[i][j] + "," ;
				}
			}
			if (i != tableau.length - 1) {
				retour += "-";
			}
		}
		return retour ;
	}
	
	// Affiche le tableau comme dans le jeu
	public static String afficheTableau(char[][] tableau) {
		
		String retour = "" ;
		
		for (int i = 0; i < tableau.length; i = i + 1) {
			for (int j = 0; j < tableau[i].length; j = j + 1) {
				retour += tableau[i][j] + " ";
			}
			retour += "\n";
		}
		return retour ;
	}
	

}
