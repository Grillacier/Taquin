import java.util.Arrays;
import java.util.*;
import java.util.Random;

public class Configuration {
	private int hauteur, largeur; // taille du tableau
	private int x , y; // coordonnées de la case vide
	private int[][] tableau;
	private String chemin;
	private ArrayList<Configuration> parent;
	private int distance; // poids d'une configuration
	private ArrayList<Configuration> successeur;
	private String[] deplacements;

	public Configuration(int h, int l){
		this.hauteur = h;
		this.largeur = l;
		this.tableau = new int[h][l];
		this.creationTableau();
		this.chemin = "";
		this.parent = new ArrayList<>();
		this.successeur = new ArrayList<>();
		this.deplacements = new String[]{"haut", "bas", "gauche", "droite"};
		this.distance = distance(this);
	}

	// Creer un objet similaire à un autre
	public Configuration(Configuration c){
		this.hauteur = c.hauteur;
		this.largeur = c.largeur;
		this.tableau = new int[this.hauteur][this.largeur];
		this.copier(c);
		this.distance = distance(c);
        this.successeur = new ArrayList<>();
		this.deplacements = new String[]{"haut", "bas", "gauche", "droite"};
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

	// Permet de creer un nouveau tableau melange et soluble
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

	//construit un tableau en configuration gagnante
	public Configuration tableauFinal() {
		Configuration g = new Configuration(this.hauteur, this.largeur);
		int[][] tab = new int[this.hauteur][this.largeur];
		int k = 1;
		for (int i = 0; i < hauteur; i++) {
			for (int j = 0; j < largeur; j++) {
				tab[i][j] = k;
				k++;
			}
		}
		tab[tab.length-1][tab[0].length-1] = 0;
		g.setTableau(tab);
		return g;
	}


	/*A SUPPRIMER SI INUTILE
	LA FONCTION "mouvement" DEVRAIT REMPLACER TOUT CA*/

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
		Configuration tabBas = new Configuration(this);
		for (int i=0; i<tabBas.hauteur; i++) {
			for (int j=0; j<tabBas.tableau[i].length; j++) {
				if (i != tabBas.tableau.length-1 && tabBas.tableau[i][j]==0) {
					int tmp = tabBas.tableau[i][j];
					tabBas.tableau[i][j] = tabBas.tableau[i+1][j];
					tabBas.tableau[i+1][j] = tmp;
					return tabBas;
				}
			}
		}

		return null;
	}
	//construit un tableau avec les coordonnées x et y déplacée en haut
	public  Configuration constrTabHaut(){
		Configuration tabHaut = new Configuration(this);
		for (int i=0; i<tabHaut.hauteur; i++) {
			for (int j=0; j<tabHaut.tableau[i].length; j++) {
				if (i != 0 && tabHaut.tableau[i][j]==0) {
					int tmp = tabHaut.tableau[i][j];
					tabHaut.tableau[i][j] = tabHaut.tableau[i-1][j];
					tabHaut.tableau[i-1][j] = tmp;
					return tabHaut;
				}
			}
		}

		return null;
	}

	//construit un tableau avec les coordonnées x et y déplacée à gauche
	public Configuration constrTabGauche(){
		Configuration tabGauche = new Configuration(this);
		for (int i=0; i<tabGauche.hauteur; i++) {
			for (int j=0; j<tabGauche.tableau[i].length; j++) {
				if (j != 0 && tabGauche.tableau[i][j]==0) {
					int tmp = tabGauche.tableau[i][j];
					tabGauche.tableau[i][j] = tabGauche.tableau[i][j-1];
					tabGauche.tableau[i][j-1] = tmp;
					return tabGauche;
				}
			}
		}

		return null;
	}
	// construit un tableau avec les coordonnées x et y déplacée à droite
	public Configuration constrTabDroit(){
		Configuration tabDroit = new Configuration(this);
		for (int i=0; i<tabDroit.hauteur; i++) {
			for (int j=0; j<tabDroit.tableau[i].length; j++) {
				if (j != tabDroit.tableau[i].length-1 && tabDroit.tableau[i][j]==0) {
					int tmp = tabDroit.tableau[i][j];
					tabDroit.tableau[i][j] = tabDroit.tableau[i][j+1];
					tabDroit.tableau[i][j+1] = tmp;
					return tabDroit;
				}
			}
		}

		return null;

	}

	public int xValue(int n) {
        int x = -1; //initialisation de x (-1 = c n'existe pas dans le plateau)
        for (int i=0; i<this.tableau.length; i++) {
            for (int j=0; j<this.tableau[i].length; j++) {
                if (this.tableau[i][j]==n) {
                    x = i;
                }
            }
        }
        return x;
    }
    // méthode qui retourne la coordonnée Y d'un numéro n dans le plateau
    public int yValue(int n) {
        int y = -1; //initialisation de y (-1 = c n'existe pas dans le plateau)
        for (int i=0; i<this.tableau.length; i++) {
            for (int j=0; j<this.tableau[i].length; j++) {
                if (this.tableau[i][j]==n) {
                    y = j;
                }
            }
        }
        return y;
    }
    	// méthode calculant le nombre de deplacement
	 public int distance(Configuration initial) {
	        int numero = 0;
	        int val = 0;
	        for (int i = 0; i < initial.tableau.length; i++) {
	            for (int j = 0; j < initial.tableau[i].length; j++) {
	                if (initial.tableau[i][j] != 0) {
	                    numero = initial.tableau[i][j];
	                    val+=(Math.abs(i-this.xValue(numero)));
	                    val+=(Math.abs(j-this.yValue(numero)));
	                }
	            }
	        }
	        return val;
	    }


	// Affiche la taquin de facon claire
	public void afficherB(){
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
	public void afficher(){
		int size;
		for(int i=0; i<this.largeur; i++){
			System.out.print(" ----");
		}
		System.out.println();
		for(int i=0; i<this.largeur; i++){
			for(int j=0; j<this.hauteur; j++){
				size = String.valueOf(this.tableau[i][j]).length();
				if(size == 1){ // si la taille=1, ajouter un espace
					System.out.print("| " + this.tableau[i][j] + "  ");
				}else{
					System.out.print("| " + this.tableau[i][j] + " ");
				}
			}
			System.out.println("|");
			for(int k=0; k<this.largeur; k++){
				System.out.print(" ----");
			}
			System.out.println();
		}
	}

	// Verifie si le plateau est en position finale et donc si je le jeu est finit
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
	public boolean mouvement(String dir){
		if(dir.equals("haut") && this.x>0){
			chemin += "H";
			echangeCase(this.x-1, this.y);
			return true;
		} else if(dir.equals("bas") && this.x<this.hauteur-1){
			chemin += "B";
			echangeCase(this.x+1, this.y);
			return true;
		}else if(dir.equals("droite") && this.y<this.largeur-1){
			chemin += "D";
			echangeCase(this.x, this.y+1);
			return true;
		}else if(dir.equals("gauche") && this.y>0){
			chemin += "G";
			echangeCase(this.x, this.y-1);
			return true;
		}
		return false;
	}

	/*public /*ArrayList<Configuration> void successeurs(){
		ArrayList<Configuration> successeurs = new ArrayList<Configuration>();
		if(this.x>0){
			Configuration haut = new Configuration (this);
			chemin += "H";
			echangeCase(haut.x-1, haut.y);
			successeurs.add(haut);

		} if(this.x<this.hauteur-1){
			Configuration bas = new Configuration (this);
			chemin += "B";
			echangeCase(bas.x+1, bas.y);
			successeurs.add(bas);

		}if(this.y<this.largeur-1){
			Configuration droit = new Configuration (this);
			chemin += "D";
			echangeCase(droit.x, droit.y+1);
			successeurs.add(droit);

		}if(this.y>0){
			Configuration gauche = new Configuration (this);
			chemin += "G";
			echangeCase(gauche.x, gauche.y-1);
			successeurs.add(gauche);
		}

		for (int i=0; i<successeurs.size(); i++) {
			successeurs.get(i).afficher();
			System.out.println();
		}
	}*/

	// renvoie la liste des successeurs du tableau
	public void successeurs(){
		for(int i=0; i<deplacements.length; i++){
			Configuration tmp = new Configuration(this);
			if(tmp.mouvement(deplacements[i])){
				successeur.add(tmp);
			}
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

	    public ArrayList<Configuration> getParent() {
	        return this.parent;
	    }

	    public int getDistance() {
	    	return this.distance;
	    }

		public void setDistance(int d) {
			this.distance = d;
		}

		public ArrayList<Configuration> getSuccesseur() {
			return this.successeur;
		}

		public String[] getDeplacements() {
			return deplacements;
		}

}
