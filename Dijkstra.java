import java.util.*;

public class Dijkstra {
    private Configuration depart;
    private HashSet<String> vu;
    private FdPg<Configuration> file;

    /**
     * Contructeur de l'algorithme du parcours en largeur
     * @param d Configuration initiale
     */
    public Dijkstra(Configuration d){
        this.depart = d;
        this.vu = new HashSet<>();
        this.file = new FdPg<Configuration>();
    }

    /**
     * R&eacute;sout un taquin avec l'algorithme de Dijkstra
     * @return la configuration r&eacute;solue &agrave; partir d'une configuration quelconque
     */
    public Configuration dijkstra() {
        this.vu.add(this.depart.tableauEnString());
        this.file.Ajouter(depart, 0);
        int distance = 1;
        while (!this.file.EstVide()) {
            Configuration min = this.file.ExtraireMin();
            if (min.jeuGagne()) {
                System.out.println("Configurations explorees : " + vu.size());
                return min;
            }

            min.successeurs(); //Calcul des successeurs de la configuration minimale
            ArrayList<Configuration> s = min.getSuccesseur();
            for (int i = 0; i < s.size(); i++) {
                Configuration tmp = s.get(i);
                if (this.vu.add(tmp.tableauEnString())) {
                    this.file.Ajouter(tmp, distance);
                }
            }
            distance++;
        }
        return null;
    }
}
