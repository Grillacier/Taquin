
public class TaquinLanceur {

    public static void main(String[] args) {
        new Jeu();

        // Test tableau taille 3x3
        /*Configuration c = new Configuration(3,3);
        c.nouveauTableau();
        int[][] t = {{1,2,3},
                     {4,5,0},
                     {7,8,6}};
        c.setTableau(t);
        c.setX(1);
        c.setY(2);
        c.afficher();
        System.out.println("### Algo ###");
        ParcoursLargeur pl = new ParcoursLargeur(c);
        pl.parcours();*/

        // Test tableau taille 2x2
        /*Configuration d = new Configuration(2,2);
        d.nouveauTableau();
        int[][] t2 = {{0,1},
                      {3,2}};
        d.setTableau(t2);
        d.setX(0);
        d.setY(0);
        d.afficher();
        System.out.println("### Algo ###");
        ParcoursLargeur pl2 = new ParcoursLargeur(d);
        pl2.parcours();*/

        // Nouveau test (temporaire)
        Configuration c = new Configuration(3,3);
        c.nouveauTableau();
        /*int[][] t = {{1,2,3},
                    {4,5,0},
                    {7,8,6}};
        c.setX(1);
        c.setY(2);*/
        int[][] t = {{5,2,3},
                     {1,7,0},
                     {8,4,6}};
        c.setX(1);
        c.setY(2);
        /*int[][] t = {{4,2,3},
                     {5,1,7},
                     {8,6,0}};
        c.setX(2);
        c.setY(2);*/
        /*int[][] t = {{5,2,0},
                {1,7,3},
                {8,4,6}};
        c.setX(0);
        c.setY(2);*/
        c.setTableau(t);
        c.afficher();

        // Test 2x2
        /*Configuration c = new Configuration(3,3);
        c.nouveauTableau();
        c.afficher();*/

        System.out.println("x="+c.getX()+"  y="+c.getY());
        System.out.println("### Algo ###");
        long timeStart;
        long timeStop;
        ParcoursLargeur pl = new ParcoursLargeur(c);
        timeStart = System.nanoTime();
        Configuration resolu = pl.parcours();
        timeStop = System.nanoTime();
        resolu.afficher();
        System.out.println("Chemin : " + resolu.getChemin());
        System.out.println("Longueur du chemin : " + resolu.getChemin().length());
        System.out.println("Résolu en : " + ((timeStop-timeStart)/1000000)/1000.0 + "s");
    }
}
