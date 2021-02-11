
public class Plateau {
    public Case[][] plateau; // double tableau
    public int hauteur, largeur; // taille du tableau

    public Plateau(int h, int l){
        this.hauteur = h;
        this.largeur = l;
        this.plateau = new Case[this.hauteur][this.largeur];
    }

    public void afficher(){
        char c = 'A';
        int size;
        System.out.print("   "); // espaces de la premiere ligne
        for(int i=0; i<this.largeur; i++) // affiche la premiere ligne
            System.out.print(i + "  ");
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

    public boolean jeuGagner(){ //fonction qui évalue si le jeu est terminé (gagné) ou pas
        Case[][] ptrie=new Case[][]{
            {new Case(1), new Case(2), new Case(3), new Case(4)},
            {new Case(5), new Case(6), new Case(7), new Case(8)},
            {new Case(9), new Case(10), new Case(11), new Case(12)},   // Fonction rigide, utilisation possible uniquement si le tableau est de taille 4x4
            {new Case(13), new Case(14), new Case(15), new Case(0)}};
        for(int i=0; i<plateau.length; i++){
            for(int j=0; j<plateau[i].length; j++){
                if(plateau[i][j].getNumero()!=ptrie[i][j].getNumero()){
                    return false;
                }
            }
        }
        return true;
    }
    /** Permet de deplacer la Case cible si le deplacement est valide
      *
      *@param cibleh Coordonnée y (hauteur)
      *@param cible l Coordonnée x (largeur)
      */
    public void mouvement(int cibleh, int ciblel){
      Case cible = this.plateau[cibleh][ciblel];
      if(cibleh + 1 <hauteur){
        if(this.plateau[cibleh+1][ciblel].getNumero() == 0){   //Je suppose que le case vide est la case 0
          this.plateau[cibleh][ciblel] = this.plateau[cibleh+1][ciblel];
          this.plateau[cibleh+1][ciblel] = cible;
          return;
        }
      }
      if(cibleh - 1 >-1){
        if(this.plateau[cibleh-1][ciblel].getNumero() == 0){
          this.plateau[cibleh][ciblel] = this.plateau[cibleh-1][ciblel];
          this.plateau[cibleh-1][ciblel] = cible;
          return;
        }

      }
      if(ciblel + 1 <largeur){
        if(this.plateau[cibleh][ciblel+1].getNumero() == 0){
          this.plateau[cibleh][ciblel] = this.plateau[cibleh][ciblel+1];
          this.plateau[cibleh][ciblel+1] = cible;
          return;
        }

      }
      if(ciblel - 1 >-1){
        if(this.plateau[cibleh][ciblel-1].getNumero() == 0){
          this.plateau[cibleh][ciblel] = this.plateau[cibleh][ciblel-1];
          this.plateau[cibleh][ciblel-1] = cible;
          return;
        }

      }
      System.out.println("Impossible de deplacer la case cible x: "+ciblel+"   y: "+cibleh);
    }
}
