import java.util.Arrays;
import java.util.Objects;

public class Plateau {
    public  Case[][] plateau; // double tableau
    public int hauteur, largeur; // taille du tableau
    private int x , y; // coordonnées de la case vide
    private final int compteur;
    private final Plateau precedent;


    public Plateau(int h, int l) {
        this.hauteur = h;
        this.largeur = l;
        this.plateau = tabNombreAleatoire(h, l);
        initialisationValXY ();
        this.compteur = 0;
        this.precedent = null;
    }

    public Plateau(int c,Plateau p) {
        this.hauteur = p.hauteur;
        this.largeur = p.largeur;
        this.compteur = c;
        this.precedent =p;
        for(int i = 0;i<this.hauteur;i++) {
            for(int j = 0;i<this.largeur;j++) {
                this.plateau[i][j]= p.plateau[i][j];
            }
        }
    }

    public Case[][] getPlateau () {
        return this.plateau;
    }

    public Plateau getPrecedent() {
        return this.precedent;
    }

    public int getCompteur() {
        return this.compteur;
    }


    /**
    * Copie le Plateau this
    * @return un nouveau Plateau identique au Plateau this
    */
    public Plateau copiePlateau() {
        Plateau copie = new Plateau(this.hauteur, this.largeur);
        for (int i=0; i<copie.plateau.length; i++) {
            for (int j=0; j<copie.plateau[i].length; j++) {
                copie.plateau[i][j] = this.plateau[i][j];
            }
        }
        return copie;
    }


    //permet d'initialiser les valeurs x et y de la case vide du plateau
    public void initialisationValXY () {
        for (int i=0; i<this.plateau.length; i++) {
            for (int j=0; j<this.plateau[i].length; j++) {
                if (this.plateau[i][j].getNumero()==0) {
                    this.x = i;
                    this.y = j;
                }
            }
        }
    }

    // méthode qui retourne la coordonnée X d'un numéro n dans le plateau
    public int xValue(int n) {
        int x = -1; //initialisation de x (-1 = c n'existe pas dans le plateau)
        for (int i=0; i<this.plateau.length; i++) {
            for (int j=0; j<this.plateau[i].length; j++) {
                if (this.plateau[i][j].getNumero()==n) {
                    x = i;
                }
            }
        }
        return x;
    }

    // méthode qui retourne la coordonnée Y d'un numéro n dans le plateau
    public int yValue(int n) {
        int y = -1; //initialisation de y (-1 = c n'existe pas dans le plateau)
        for (int i=0; i<this.plateau.length; i++) {
            for (int j=0; j<this.plateau[i].length; j++) {
                if (this.plateau[i][j].getNumero()==n) {
                    y = j;
                }
            }
        }
        return y;
    }


    public static void melangerTab(Case[][] tab) { //permet de melanger un tableau donner en argument changeant aleatoirement les elements du tableau
        //Cette foncion permet egalement d'eviter la repetition de nombres dans le tableau
        for (int i=0; i<tab.length; i++) {
            for (int j=0; j<tab[i].length; j++) {
                int i1 = (int)(Math.random()*tab.length-1); // choisi un indice i entre 0 et la taille du tableau -1
                int j1 = (int)(Math.random()*tab[i].length-1);
                Case temp = tab[i][j];
                tab[i][j] = tab[i1][j1];
                tab[i1][j1] = temp;
        //echange de maniere aleatoire les elements des indices
            }
        }
    }

    public  Case[][] tabNombreAleatoire (int ligne, int colonne){ //Cree un tableau avec des nombre aleatoire compris de 0 e (ligne*colonne)-1
        Case [][]tabTaquin = new Case [ligne][colonne];
        int x = 0;
        boolean val = false;
        // ajoute dans un premier temps dans le tableau tabTaquin des chiffres compris entre 0 et (ligne * colonne)-1
        for(int i=0; i < ligne; i++) {
            for (int j=0; j<colonne; j++) {
                tabTaquin[i][j] = new Case(x);
                x++;
            }
        }
        melangerTab(tabTaquin); //melange le tableau aleatoirement
        while (!estSoluble(tabTaquin)) {
            melangerTab(tabTaquin);
        }
        return tabTaquin;
    }


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
                System.out.print(this.plateau[i][j].getNumero() + " "); // affiche le num
                size = String.valueOf(this.plateau[i][j].getNumero()).length();
                if(size == 1){ // si la taille=1, ajouter un espace
                    System.out.print(" ");
                }
            }
            System.out.println();
            c++;
        }
    }


  /**
  * V&eacute;rifie si le plateau est en position gagnante
  * @return true si tab est en position gagnante, false sinon
  */
  public boolean jeuGagne(){ //fonction qui évalue si le jeu est termine (gagne) ou pas
    int k=1;
    for(int i=0; i<this.plateau.length; i++){
      for(int j=0; j<this.plateau[i].length; j++){
        if(i == (this.hauteur-1) && j == (this.largeur-1) && this.plateau[i][j].getNumero() == 0){
          return true;
        }

        if(this.plateau[i][j].getNumero()!=k){
          return false;
        }
        k++;
      }
    }
    return true;
  }


    public Plateau plateauGagnant() {
        Plateau gagne = copiePlateau();
        int k = 1;
        for (int i = 0; i < gagne.plateau.length; i++) {
            for (int j = 0; j < gagne.plateau[i].length; j++) {
                gagne.plateau[i][j] = new Case(k);
                k++;
            }
        }
        for (int i = 0; i < gagne.plateau.length; i++) {
            for (int j = 0; j < gagne.plateau[i].length; j++) {
                gagne.plateau[gagne.plateau.length-1][gagne.plateau[0].length-1] = new Case(0);

            }
        }
        return gagne;
    }


    public boolean MouvementBas() {
        if (this.x == this.hauteur-1) {
            return false;
        }
        return true;
    }

    public boolean MouvementHaut() {
        if (this.x == 0) {
            return false;
        }
        return true;
    }

    public boolean MouvementGauche() {
        if (this.y == 0 ) {
            return false;
        }
        return true;
    }

    public boolean MouvementDroit() {
        if (this.y == this.largeur-1) {
            return false;
        }
        return true;
    }

    /**
     * Cr&eacute;e un nouveau Plateau avec la Case vide d&eacute;plac&eacute;e vers le bas
     * @return un nouveau Plateau avec la Case vide en bas
     */
    public Plateau constrTabBas() {
        Plateau tabBas = copiePlateau();
        for (int i = 0; i < tabBas.hauteur; i++) {
            for (int j = 0; j < tabBas.plateau[i].length; j++) {
                if (i != tabBas.plateau.length-1 && tabBas.plateau[i][j].getNumero()==0) {
                    Case tmp = tabBas.plateau[i][j];
                    tabBas.plateau[i][j] = tabBas.plateau[i+1][j];
                    tabBas.plateau[i+1][j] = tmp;
                    tabBas.x += 1;
                    return tabBas;
                }
            }
        }
        return null;
    }

    /**
     * Cr&eacute;e un nouveau Plateau avec la Case vide d&eacute;plac&eacute;e vers le haut
     * @return un nouveau Plateau avec la Case vide en haut
     */
    public  Plateau constrTabHaut() {
        Plateau tabHaut = copiePlateau();
        for (int i = 0; i < tabHaut.hauteur; i++) {
            for (int j = 0; j < tabHaut.plateau[i].length; j++) {
                if (i != 0 && tabHaut.plateau[i][j].getNumero()==0) {
                    Case tmp = tabHaut.plateau[i][j];
                    tabHaut.plateau[i][j] = tabHaut.plateau[i-1][j];
                    tabHaut.plateau[i-1][j] = tmp;
                    tabHaut.x -= 1;
                    return tabHaut;
                }
            }
        }
        return null;
    }

    /**
     * Cr&eacute;e un nouveau Plateau avec la Case vide d&eacute;plac&eacute;e vers la gauche
     * @return un nouveau Plateau avec la Case vide a gauche
     */
    public Plateau constrTabGauche() {
        Plateau tabGauche = copiePlateau();
        for (int i = 0; i < tabGauche.hauteur; i++) {
            for (int j = 0; j < tabGauche.plateau[i].length; j++) {
                if (j != 0 && tabGauche.plateau[i][j].getNumero()==0) {
                    Case tmp = tabGauche.plateau[i][j];
                    tabGauche.plateau[i][j] = tabGauche.plateau[i][j-1];
                    tabGauche.plateau[i][j-1] = tmp;
                    tabGauche.y -= 1;
                    return tabGauche;
                }
            }
        }
        return null;
    }

    /**
     * Cr&eacute;e un nouveau Plateau avec la Case vide d&eacute;plac&eacute;e vers la droite
     * @return un nouveau Plateau avec la Case vide a droite
     */
    public Plateau constrTabDroit() {
        Plateau tabDroit = copiePlateau();
        for (int i=0; i<tabDroit.hauteur; i++) {
            for (int j=0; j<tabDroit.plateau[i].length; j++) {
                if (j != tabDroit.plateau[i].length-1 && tabDroit.plateau[i][j].getNumero()==0) {
                    Case tmp = tabDroit.plateau[i][j];
                    tabDroit.plateau[i][j] = tabDroit.plateau[i][j+1];
                    tabDroit.plateau[i][j+1] = tmp;
                    tabDroit.y += 1;
                    return tabDroit;
                }
            }
        }
        return null;
    }


    /**
     * Permet de d&eacute;placer la Case cible si le d&eacute;placement est valide
    */
  public void mouvement(String type){
    int x = -1;
    int y = -1;
    for(int i = 0;i<this.hauteur;i++){
      for(int j = 0;j<this.largeur;j++){
        if(this.plateau[i][j].getNumero() == 0){
          x = j;
          y = i;

        }
      }
    }

    if(type.equals("haut") && y>0){
      Case tmp = plateau[y][x];
      plateau[y][x] = plateau[y-1][x];
      plateau[y-1][x] =tmp;
      return;
    }
    if(type.equals("bas") && y<this.hauteur-1){
      Case tmp = plateau[y][x];
      plateau[y][x] = plateau[y+1][x];
      plateau[y+1][x] =tmp;
      return;
    }
    if(type.equals("droite") && x<this.largeur-1){
      Case tmp = plateau[y][x];
      plateau[y][x] = plateau[y][x+1];
      plateau[y][x+1] =tmp;
      return;
    }
    if(type.equals("gauche") && x>0){
      Case tmp = plateau[y][x];
      plateau[y][x] = plateau[y][x-1];
      plateau[y][x-1] =tmp;
      return;
    }
  }
  //  System.out.println("Impossible de deplacer la case de cette facon:  "+type);


  /**
  * Convertit un double tableau de Cases en un tableau d'entiers
  * @return un tableau d'entiers contenant les num&eacute;ros des Cases sans en changer l'ordre
  */
  public int[] conversion(Case[][]tab) {
    int[] tab2 = new int[tab.length*tab[0].length];
    int k = 0;
    for (int i = 0; i < tab.length; i++) {
      for (int j = 0; j < tab[i].length; j++) {
        tab2[k] = tab[i][j].getNumero();
        k++;
      }
    }
    return tab2;
  }


  /**
  * Tri un tableau d'entiers dans l'ordre croissant avec le 0 a la fin
  * @param p Tableau tableau de tableaux de Cases dont on va trier les cases
  * @return le nombre d'&eacute;changes et de d&eacute;placements du 0
  */
  private int[] tri(Case[][] p) {
    int[] tab = conversion(p); // plateau convertit en tableau d'entiers
    int[] res = new int[2];
    int nbEchanges = 0; // nombre de deplacements total des Cases
    int parite = 0; // nombre de deplacements du 0
    int tmp = 0;
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

  /**
  * V&eacute;rifie la solvabilit&eacute; du plateau
  * @param tab le plateau sur lequel on calcule la parit&eacute;
  * @return true si le nombre d'&eacute;changes et la parite sont tous les 2 pairs ou impairs, false sinon
  */
  public boolean estSoluble(Case [][]tab) {
    return tri(tab)[0] % 2 == tri(tab)[1] % 2;
  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plateau plateau1 = (Plateau) o;
        return hauteur == plateau1.hauteur &&
                largeur == plateau1.largeur &&
                x == plateau1.x &&
                y == plateau1.y &&
                compteur == plateau1.compteur &&
                Arrays.equals(plateau, plateau1.plateau);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(hauteur, largeur, x, y, compteur, precedent);
        result = 31 * result + Arrays.hashCode(plateau);
        return result;
    }
}
