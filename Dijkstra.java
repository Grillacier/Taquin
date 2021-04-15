public class Dijkstra {
    private Configuration sommet;
    private FdPg<Configuration> file;
    private int distance;

    public Dijkstra(Configuration sommet) {
        this.sommet = sommet;
        this.file = new FdPg<Configuration>();
        this.file.Ajouter(sommet, 0);
        this.distance = manhattanHeuristic();
    }


    //pour A*
    public int manhattanHeuristic() {
        int numero = 0;
        int val = 0;
        Configuration gagne = sommet.tableauGagnant();
        for (int i = 0; i < sommet.getTableau().length; i++) {
            for (int j = 0; j < sommet.getTableau()[i].length; j++) {
                if (sommet.getTableau()[i][j] != 0) {
                    numero = sommet.getTableau()[i][j];
                    //modifié dans Configuration donc ne marche plus
                    val+=(Math.abs(i-gagne.getX()));
                    val+=(Math.abs(j-gagne.getY()));
                }
            }
        }
        return val;
    }


    public FdPg<Configuration> dijkstra() {
        FdPg<Configuration> chemin = new FdPg<>();
        Configuration u = this.sommet;
        chemin.Ajouter(u, 0);
        /*
        while (!this.file.EstVide()) {
            u = this.file.ExtraireMin();
            if (u.equals(sommet.tableauGagnant()))
                return chemin;

            for (Configuration v : file) {
                if (chemin.IndiceDsF(v) != -1) {
                    if (file.IndiceDsF(v) > file.IndiceDsF(u) + distance) {
                        file.MaJ(v, file.IndiceDsF(u) + distance);
                        v.setPrecedent(u);
                        file.MaJ(v, file.IndiceDsF(v));
                    }
                }
                else {
                    if (file.IndiceDsF(v) < 0) {
                        file.MaJ(v, file.IndiceDsF(u) + distance);
                        v.setPrecedent(u);
                        file.MaJ(v, file.IndiceDsF(v));
                        chemin.Ajouter(u, file.IndiceDsF(u));
                    }
                }
            }
        }*/
        return chemin;
    }


    public void afficheChemin(FdPg<Configuration> chemin) {
        for (int i = 0; i < chemin.nb; i++) {
            chemin.T.get(i).val.afficher();
            System.out.println();
        }
    }
}
