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

	/** Constructeur de la Configuration
	*
	*@param h
	*      Hauteur de la nouvelle configuration
	 *
	 *@param l
	 *    Largeur de la nouvelle configuration
	 */
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

	/** Constructeur de configuration qui copie une configuration deja existante
	*
	*@param c
	*   Configuration a copier
	*
	*/

	public Configuration(Configuration c){
		this.hauteur = c.hauteur;
		this.largeur = c.largeur;
		this.tableau = new int[this.hauteur][this.largeur];
		this.copier(c);
		this.distance = distance(c);
        this.successeur = new ArrayList<>();
		this.deplacements = new String[]{"haut", "bas", "gauche", "droite"};
	}



	/** Copie la configuration entré et modifie avant que la configuration courante soit pareil
	*
	*@param c
	*     Configuration utilisé pour modele
	 *
	 */
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


	/** Initialise la configuration en placant de facon ordonné les valeurs
	*
	*/
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

	/** Melange le tableau afin d'obtenir une configuration aleatoire
	*
	*/
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

	/** Melange le tableau afin d'obtenir une configuration aleatoire et soluble
	*
	*/
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

	/** Renvoie un chiffre aleatoire dans un intervale bornée
	*@param min
	*   Valeur minimal de l'intervale
	*@param max
	*   Valeur maximal de l'intervale
	*@return
	*    Valeur aleatoire retournée
	*
	*/
	public int chiffreAleatoire(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	/** Modifie la coniguration pour la rendre gagante
	*
	*/
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
	/** Recherche une valeur dans la configuration et renvoie sa coordonnée X
	*@param n
	*    Valeur recherché
	*@return
	*   Coordonnée X de l'element recherché
	*/
	public int xValue(int n) {
        int x = -1; //initialisation de x (-1 = c n'existe pas dans le plateau)
        for (int i=0; i<this.tableau.length; i++) {
            for (int j=0; j<this.tableau[i].length; j++) {
                if (this.tableau[i][j]==n) {
                    x = i;
                    break;
                }
            }
        }
        return x;
    }
		/** Recherche une valeur dans la configuration et renvoie sa coordonnée Y
		*@param n
		*    Valeur recherché
		*@return
		*   Coordonnée Y de l'element recherché
		*/
    public int yValue(int n) {
        int y = -1; //initialisation de y (-1 = c n'existe pas dans le plateau)
        for (int i=0; i<this.tableau.length; i++) {
            for (int j=0; j<this.tableau[i].length; j++) {
                if (this.tableau[i][j]==n) {
                    y = j;
                    break;
                }
            }
        }
        return y;
    }

		/**Renvoie la distance entre la confiuration actuel et celle entré en parametre
		*
		*@param initial
		*   Configuration initial a comparé avec la configuration courante
		*@return
		*   Distance entre la configuration entré en parametre et la courante
		*/
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

	/*
	public int distance(Configuration c) {
		//Configuration finale = c.tableauFinal();
		int k = 0;
		for (int i = 0; i < hauteur; i++) {
			for (int j = 0; j < largeur; j++) {
				if (c.getTableau()[i][j] != this.getTableau()[i][j] && c.getTableau()[i][j] != 0) {
					k++;
				}
			}
		}
		return k;
	}
	*/


	/** Affiche la taquin de facon claire
	*
	*/
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

	/**Verifie si le plateau est en position finale et donc si je le jeu est finit
	*@return
	*   Return true si la configuration est resolu, false sinon
	*/
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

	/** Permet de deplacer la case vide du taquin en entrant la direction si le mouvement est autorisé
	*
	*@param dir
	*   Direction demandé, "haut" , "bas", "droite" ou "gauche"
	*@return
	*   Renvoie true si le deplacement est possible et appliqué, sinon false
	*/
    public boolean mouvement(String dir){
        if(haut(dir) && this.x>0){
            chemin += "H";
            echangeCase(this.x-1, this.y);
            return true;
        } else if(bas(dir) && this.x<this.hauteur-1){
            chemin += "B";
            echangeCase(this.x+1, this.y);
            return true;
        }else if(droite(dir) && this.y<this.largeur-1){
            chemin += "D";
            echangeCase(this.x, this.y+1);
            return true;
        }else if(gauche(dir) && this.y>0){
            chemin += "G";
            echangeCase(this.x, this.y-1);
            return true;
        }
        return false;
    }


		/** Renvoie la liste des successeurs du tableau
		*/
	public void successeurs(){
		for(int i=0; i<deplacements.length; i++){
			Configuration tmp = new Configuration(this);
			if(tmp.mouvement(deplacements[i])){
				successeur.add(tmp);
			}
		}
	}

	/** Echange une case en parametre avec la case vide
	*@param caseX
	*   Coordonnée X de la case à echanger
	*@param caseY
	*    Coordonnée Y de la case à echanger
	*/
	public void echangeCase(int caseX, int caseY){
		int case0 = this.tableau[this.x][this.y];
		this.tableau[this.x][this.y] = this.tableau[caseX][caseY];
		this.tableau[caseX][caseY] = case0;
		this.x = caseX;
		this.y = caseY;
	}

	/** Renvoie la configuration sous forme de String
	*
	*@return Configuration renvoyée
	*/
	public String tableauEnString(){
		String s = "";
		for(int i=0; i<this.hauteur; i++){
			for(int j=0; j<this.largeur; j++){
				s += Integer.toString(this.tableau[i][j]);
			}
		}
		return s;
	}

	// Heuristique de manhattan
	public int manhattanHeuristique(){
		Configuration fin = this.tableauFinal();
		int val = 0;
		for(int i=0; i<this.hauteur; i++){
			for(int j=0; j<this.largeur; j++){
				int tile = this.tableau[i][j];
				if(tile != 0){
					for(int a=0; a<this.hauteur; a++){
						for(int b=0; b<this.largeur; b++){
							if(fin.tableau[a][b] == tile){
								val += Math.abs(a-i) + Math.abs(b-j);
							}
						}
					}
				}
			}
		}
		return val;
	}

	/** Renvoie la configuration sous forme d'un tableau int
	*
	*@return Configuration renvoyée
	*/
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

	/** Tri le tableau du taquin afin de compter le nombre d'echanges et de deplacements
	*
	*@return
	*    Nombre d'echanges et deplacement [a confirmer]
	*/
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

	/** Verifie que la configuration est soluble
	*
	*@return
	*     renvoie true si la configuration est soluble, sinon non
	*
	*/
	public boolean estSoluble() {
		return triEchangeParite()[0] % 2 == triEchangeParite()[1] % 2;
	}

 /** Verifie que la chaine est bien accepté pour "Haut"
 *
 *@return
 *   renvoie true si la chaine est accepté, false sinon
 */
	public boolean haut(String h) {
        switch (h) {
            case "h":
            case "H":
            case "haut":
            case "Haut":
            case "HAUT":
                return true;
            default:
                return false;
        }
    }
		/** Verifie que la chaine est bien accepté pour "Bas"
	  *
	  *@return
	  *   renvoie true si la chaine est accepté, false sinon
	  */
    public boolean bas(String b) {
        switch (b) {
            case "b":
            case "B":
            case "bas":
            case "Bas":
            case "BAS":
                return true;
            default:
                return false;
        }
    }
		/** Verifie que la chaine est bien accepté pour "Gauche"
	  *
	  *@return
	  *   renvoie true si la chaine est accepté, false sinon
	  */
    public boolean gauche(String g) {
        switch (g) {
            case "g":
            case "G":
            case "gauche":
            case "Gauche":
            case "GAUCHE":
                return true;
            default:
                return false;
        }
    }
		/** Verifie que la chaine est bien accepté pour "Droite"
 	 *
 	 *@return
 	 *   renvoie true si la chaine est accepté, false sinon
 	 */
    public boolean droite(String d) {
        switch (d) {
            case "d":
            case "D":
            case "droite":
            case "Droite":
            case "DROITE":
                return true;
            default:
                return false;
        }
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

	@Override
	public boolean equals(Object o) {
		if (this == o)return true;
		if (o == null || getClass() != o.getClass()) return false;
		Configuration that = (Configuration) o;
		return Arrays.deepEquals(tableau, that.tableau);
	}

	@Override
	public int hashCode() {
		return Arrays.deepHashCode(tableau);
	}


}
