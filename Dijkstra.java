public class Dijkstra {
    //private FilePriorite F; //a implementer
    private Plateau parent; //avant-dernier sommet
    private Plateau[] pi; //sommet precedent
    private Plateau[] parcouru; //sommets visites
    private int[] d; //priorite d'un element

    public Plateau getParent() {
        return parent;
    }

    public void setParent(Plateau parent) {
        this.parent = parent;
    }

    //a modifier avec les files de priorite
    //poids d'un arc
    private int w(int u, int v) {
        if (u == v)
            return 0;
        else
            return 1;
    }
    
    public Plateau[] dijkstraOTF(Plateau s, Plateau resolu) {
        Plateau[] graphe = new Plateau[100]; //on cree le graphe au fur et a mesure
        this.pi = new Plateau[s.plateau.length];
        this.parcouru = new Plateau[s.plateau.length];
        this.d = new int[s.plateau.length];

        graphe[0] = s;
        d[0] = 0;

        for (int t = 1; t < d.length; t++) {
            d[t] = 1;
        }

        for (int x = 0; x < pi.length; x++) {
            pi[x] = null;
            parcouru[x] = null;
        }

        Plateau min = null;
        int fin = 0;
        Plateau u = graphe[0];

        while (graphe[fin] != resolu) { //tant qu'on n'atteint pas le sommet objectif
            u = graphe[fin];
            if (u == resolu) {
                setParent(pi[fin]);
                pi[fin] = graphe[fin];
                return pi;
            }

            for (int i = 0; i < graphe.length; i++) {
                if (parcouru[i] == null)
                    min = graphe[i];

                if (parcouru[i] == null && d[i] > d[fin] + w(fin, i)) {
                    d[i] = d[fin] + w(fin, i);
                    pi[i] = min;
                    parcouru[i] = min; //mettre a jour la file
                }

                else {
                    d[i] = d[fin] + w(fin, i);
                    pi[i] = graphe[fin];
                    //ajouter dans la file
                }
            }
            graphe[fin] = null;
            fin++;
        }

        return parcouru;
    }
}
