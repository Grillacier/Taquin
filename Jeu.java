public class Jeu {
    private Configuration plateau;
    private Joueur joueur;

    public Jeu(Configuration p, Joueur j) {
        this.plateau = p;
        this.joueur = j;
    }

    public boolean oui(String o) {
        switch (o) {
            case "oui":
            case "Oui":
            case "OUI":
            case "o":
            case "O":
            case "yes":
            case "Yes":
            case "YES":
            case "y":
            case "Y":
                return true;
            default: return false;
        }
    }

    public boolean non(String n) {
        switch (n) {
            case "non":
            case "Non":
            case "NON":
            case "n":
            case "N":
            case "no":
            case "No":
            case "NO":
                return true;
            default: return false;
        }
    }

    public void jouer() {
        System.out.println("Bonjour " + joueur.getNom());
        joueur.setNom(joueur.demanderStr("Comment vous appelez-vous ?"));
        String mouv = "";
        String rob = "";
        long tempsDebut, tempsFin;

        while (!oui(rob) && !non(rob)) {
            rob = joueur.demanderStr("Êtes-vous un robot " + joueur.getNom() + " ?");
            if (oui(rob))
                joueur.setRobot(true);
            else if (non(rob))
                joueur.setRobot(false);
        }

        plateau.afficher();
        tempsDebut = System.nanoTime();
        while (!plateau.jeuGagne()) {
            if (joueur.getRobot()) {
                mouv = new ParcoursLargeur(plateau).parcoursEnLargeur().getChemin();
                for (int i = 0; i < mouv.length(); i++) {
                    plateau.mouvement(String.valueOf(mouv.charAt(i)));
                    plateau.afficher();
                }
            }
            else {
                mouv = joueur.demanderStr("Où voulez-vous déplacer le 0 ? (haut, bas, gauche, droite)");
                plateau.mouvement(mouv);
                plateau.afficher();
            }
        }
        tempsFin = System.nanoTime();
        System.out.println("Vous avez gagné en : " + ((tempsFin - tempsDebut)/1000000)/1000.0 + "s");
        System.out.println("Félicitations " + joueur.getNom() + " !");
        if (!joueur.getRobot())
            joueur.finir();
    }
}
