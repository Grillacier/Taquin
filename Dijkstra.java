public class Dijkstra {
    //private FilePriorite G; //a implementer
    private int parent; //avant-dernier sommet

    public int getParent() {
        return parent;
    }

    //a modifier quand avec les files de priorite
    //poids d'un arc
    private int w(int u, int v) {
        if (u == 0 || v == 0)
            return 1;
        else
            return 0;
    }

    public int dijkstra(Plateau p, int s) {
        int[] graphe = p.conversion(p.plateau);
        int[] pi = new int[graphe.length]; //sommet precedent
        int[] d = new int[graphe.length]; //priorite = 0 si l'element est le sommet d'origine, 1 sinon
        int[] parcouru = new int[graphe.length]; //sommets parcourus

        for (int u = 0; u < graphe.length; u++) {
            pi[u] = 0;
            parcouru[u] = -1;
            if (u == s)
                d[u] = 0;
            else
                d[u] = 1;
        }

        int min = graphe[0];
        int fin = 0;

        while (fin < graphe.length) {
            for (int i = 0; i < graphe.length; i++) {
                if (parcouru[i] == -1 && min > graphe[i])
                    min = graphe[i];
            }

            for (int j = 0; j < graphe.length; j++) {
                if (parcouru[j] == -1 && d[j] > d[min] + w(min, j)) {
                    d[j] = d[min] + w(min, j);
                    pi[j] = min;
                    parcouru[j] = min;
                }
            }
            graphe[min] = 999;
            fin++;
        }

        int dFinal = d[0];
        for (int v = 0; v < d.length; v++) {
           if (d[v] < dFinal)
               dFinal = d[v];
        }

        this.parent = pi[dFinal];
        return dFinal;
    }
}
