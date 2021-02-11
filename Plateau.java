
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

    public boolean jeuGagner(Case[][] tab){ //fonction qui évalue si le jeu est terminé (gagné) ou pas
        if(tab[tab.length-1][tab[0].length-1]!=0){
            return false;
        }

        int k=1;
        for(int i=0; i<tab.length-1; i++){
            for(int j=0; j<tab[i].length; j++){
                if(tab[i][j].getNumero()!=k){
                    return false;
                }
                k++;
            }
        }
        return true;
    }
}
