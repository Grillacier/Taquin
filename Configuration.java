import java.util.Arrays;
import java.util.Random;

public class Configuration {
	private int hauteur, largeur; // taille du tableau
	private int x , y; // coordonnées de la case vide
	private int[][] tableau;
	private String chemin;

	public Configuration(int h, int l){
		this.hauteur = h;
		this.largeur = l;
		this.tableau = new int[h][l];
		this.creationTableau();
		this.chemin = "";
	}

	// Créer un objet similaire à un autre
	public Configuration(Configuration c){
		this.hauteur = c.hauteur;
		this.largeur = c.largeur;
		this.tableau = new int[this.hauteur][this.largeur];
		this.copier(c);
	}

	// copie le tableau de la configuration en parametre
	public void copier(Configuration c){
		for (int i=0; i<this.hauteur; i++) {
			for (int j=0; j<this.largeur; j++) {
				this.tableau[i][j] = c.tableau[i][j];
			}
		}
		this.chemin = c.chemin;
		this.x = c.x;
		this.y = c.y;
	}

	// Permet de trouver le X et Y du tableau
	public void initialisationXY () {
		for (int i=0; i<this.hauteur; i++) {
			for (int j=0; j<this.largeur; j++) {
				if (this.tableau[i][j] == 0) {
					this.x = i;
					this.y = j;
				}
			}
		}
	}

	// Mélange le tableau
	public void melangerTableau(){
		for (int i=0; i<this.hauteur; i++) {
			for (int j=0; j<this.largeur; j++) {
				// i1 & j1 sont des coordonnées pour l'échange
				int i1 = chiffreAleatoire(0, this.hauteur-1);
				int j1 = chiffreAleatoire(0, this.largeur-1);
				// On échange 2 cases entre elles
				int temp = this.tableau[i][j];
				this.tableau[i][j] = this.tableau[i1][j1];
				this.tableau[i1][j1] = temp;
			}
		}
		initialisationXY();
	}

	// Permet de créer un nouveau tableau mélangé et soluble
	public void creationTableau(){
		int a = 0;
		for(int i=0; i<this.hauteur; i++) {
			for (int j=0; j<this.largeur; j++) {
				this.tableau[i][j] = a; // Des valeurs sont attribués au tableau pour ensuite le mélanger
				a++;
			}
		}
		melangerTableau();
		while (!estSoluble()) { // vérifie que le tableau soit soluble
			melangerTableau();
		}
	}

	// Renvoie un chiffre aleatoire entre [min, max]
	public int chiffreAleatoire(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

/*
	A SUPPRIMER SI INUTILE
	LA FONCTION "mouvement" DEVRAIT REMPLACER TOUT CA

	public boolean MouvementBas () {
		if (this.x==this.hauteur-1) {
			return false;
		}

		return true;
	}

	public boolean MouvementHaut() {
		if (this.x==0) {
			return false;
		}

		return true;
	}

	public boolean MouvementGauche() {
		if (this.y==0 ) {
			return false;
		}

		return true;
	}

	public boolean MouvementDroit() {
		if (this.y==this.largeur-1) {
			return false;
		}

		return true;
	}

	//construit un tableau avec les coordonnées x et y déplacée en bas
	public Configuration constrTabBas(){
		Configuration tabBas = copiePlateau();
		for (int i=0; i<tabBas.hauteur; i++) {
			for (int j=0; j<tabBas.plateau[i].length; j++) {
				if (i != tabBas.plateau.length-1 && tabBas.plateau[i][j].getNumero()==0) {
					Case tmp = tabBas.plateau[i][j];
					tabBas.plateau[i][j] = tabBas.plateau[i+1][j];
					tabBas.plateau[i+1][j] = tmp;
					return tabBas;
				}
			}
		}

		return null;
	}
	//construit un tableau avec les coordonnées x et y déplacée en haut
	public  Configuration constrTabHaut(){
		Configuration tabHaut = copiePlateau();
		for (int i=0; i<tabHaut.hauteur; i++) {
			for (int j=0; j<tabHaut.plateau[i].length; j++) {
				if (i != 0 && tabHaut.plateau[i][j].getNumero()==0) {
					Case tmp = tabHaut.plateau[i][j];
					tabHaut.plateau[i][j] = tabHaut.plateau[i-1][j];
					tabHaut.plateau[i-1][j] = tmp;
					return tabHaut;
				}
			}
		}

		return null;
	}

	//construit un tableau avec les coordonnées x et y déplacée à gauche
	public Configuration constrTabGauche(){
		Plateau tabGauche = copiePlateau();
		for (int i=0; i<tabGauche.hauteur; i++) {
			for (int j=0; j<tabGauche.plateau[i].length; j++) {
				if (j != 0 && tabGauche.plateau[i][j].getNumero()==0) {
					Case tmp = tabGauche.plateau[i][j];
					tabGauche.plateau[i][j] = tabGauche.plateau[i][j-1];
					tabGauche.plateau[i][j-1] = tmp;
					return tabGauche;
				}
			}
		}

		return null;
	}
	// construit un tableau avec les coordonnées x et y déplacée à droite
	public Configuration constrTabDroit(){
		Plateau tabDroit = copiePlateau();
		for (int i=0; i<tabDroit.hauteur; i++) {
			for (int j=0; j<tabDroit.plateau[i].length; j++) {
				if (j != tabDroit.plateau[i].length-1 && tabDroit.plateau[i][j].getNumero()==0) {
					Case tmp = tabDroit.plateau[i][j];
					tabDroit.plateau[i][j] = tabDroit.plateau[i][j+1];
					tabDroit.plateau[i][j+1] = tmp;
					return tabDroit;
				}
			}
		}

		return null;

	}
*/

	// Affiche la taquin de facon claire
	public void afficher(){
		char c = 'A';
		int size;
		System.out.print("   "); // espaces de la premiere ligne
		for(int i=0; i<this.largeur; i++) // affiche la premiere ligne
			System.out.print(i+1 + "  ");
		System.out.println();

		for(int i=0; i<this.hauteur; i++){
			System.out.print(c + "  "); // affiche la premiere colonne
			for(int j=0; j<this.largeur; j++){
				System.out.print(this.tableau[i][j] + " "); // affiche le num
				size = String.valueOf(this.tableau[i][j]).length();
				if(size == 1){ // si la taille=1, ajouter un espace
					System.out.print(" ");
				}
			}
			System.out.println();
			c++;
		}
		System.out.println("---------");
	}

	// Vérifie si le plateau est en position finale et donc si je le jeu est finit
	public boolean jeuGagne(){
		int k=1;
		for(int i=0; i<this.hauteur; i++){
			for(int j=0; j<this.largeur; j++){
				if(i == (this.hauteur-1) && j == (this.largeur-1) && this.tableau[i][j] == 0){
					return true;
				}else if(this.tableau[i][j] != k ){
					return false;
				}
				k++;
			}
		}
		return false;
	}

	// Permet de deplacer la case vide du taquin si le mouvement est autorise
	public void mouvement(String dir){
		if(dir.equals("haut") && this.x>0){
			chemin += "H";
			echangeCase(this.x-1, this.y);
		} else if(dir.equals("bas") && this.x<this.hauteur-1){
			chemin += "B";
			echangeCase(this.x+1, this.y);
		}else if(dir.equals("droite") && this.y<this.largeur-1){
			chemin += "D";
			echangeCase(this.x, this.y+1);
		}else if(dir.equals("gauche") && this.y>0){
			chemin += "G";
			echangeCase(this.x, this.y-1);
		}
	}

	// Echange une case (en parametre) avec la case vide
	public void echangeCase(int caseX, int caseY){
		int case0 = this.tableau[this.x][this.y];
		this.tableau[this.x][this.y] = this.tableau[caseX][caseY];
		this.tableau[caseX][caseY] = case0;
		this.x = caseX;
		this.y = caseY;
	}

	// Renvoie un string du taquin
	public String tableauEnString(){
		String s = "";
		for(int i=0; i<this.hauteur; i++){
			for(int j=0; j<this.largeur; j++){
				s += Integer.toString(this.tableau[i][j]);
			}
		}
		return s;
	}

	// Renvoie un tableau d'entier du taquin
	public int[] conversion() {
		int[] tab = new int[this.hauteur*this.largeur];
		int k = 0;
		for (int i=0; i<this.hauteur; i++) {
			for (int j=0; j<this.largeur; j++) {
				tab[k] = this.tableau[i][j];
				k++;
			}
		}
		return tab;
	}

	// Tri le tableau du taquin afin de compter le nombre d'echanges et de deplacements
	private int[] triEchangeParite() {
		int[] tab = conversion(); // plateau convertit en tableau d'entiers
		int[] res = new int[2];
		int nbEchanges = 0; // nombre de deplacements total des Cases
		int parite = 0; // nombre de deplacements du 0
		int tmp;
		for (int i = 0; i < tab.length; i++) {
			for (int j = 1; j < tab.length-i; j++) {
				if (tab[j-1] == 0) {
					parite++;
					nbEchanges++;
					tmp = tab[j];
					tab[j] = tab[j-1];
					tab[j-1] = tmp;
				}
				else if (tab[j] == 0) {
					continue;
				}
				else {
					if (tab[j-1] > tab[j]) {
						nbEchanges++;
						tmp = tab[j - 1];
						tab[j-1] = tab[j];
						tab[j] = tmp;
					}
				}
			}
		}
		res[0] = nbEchanges;
		res[1] = parite;
		return res;
	}

	// Verifie que le taquin est soluble : renvoie "true" si le nombre d'echanges et la parite sont tous deux pairs ou impairs, sinon, renvoie "false"
	public boolean estSoluble() {
		return triEchangeParite()[0] % 2 == triEchangeParite()[1] % 2;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHauteur() {
		return hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

	public int[][] getTableau() {
		return tableau;
	}

	public void setTableau(int[][] tableau) {
		this.tableau = tableau;
	}

	public String getChemin() {
		return chemin;
	}
}
