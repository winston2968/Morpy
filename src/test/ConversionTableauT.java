package test;

public class ConversionTableauT {
	
	public static void main(String[] args) {
		char[][] tableau = new char[3][3];
		
		for (int i = 0; i < tableau.length; i = i + 1) {
			for (int j = 0; j < tableau[i].length; j = j + 1) {
				tableau[i][j] = '.';
			}
		}
		
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
		
		
	}
	public static String afficheListe(String[] liste) {
		String retour = "";
		for (int i = 0 ; i < liste.length ;i++) {
			retour += liste[i] + ",";
		}
		return retour ;
	}
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
	
public static String afficheTableau2(String[][] tableau) {
		
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
