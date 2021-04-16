import java.util.*;
public class Dijkstra {
    private Configuration depart;
    private FdPg<Configuration> file;
    private HashSet<String> vu;
   // private int distance;

    public Dijkstra(Configuration d){
        this.depart=d;
        this.file = new FdPg<Configuration>();
        this.vu = new HashSet<>();
    }


    //pour A*
   /* public int manhattanHeuristic() {
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
    }*/


    public FdPg<Configuration> dijkstra() {
        FdPg<Configuration> chemin = new FdPg<>();
        chemin.Ajouter(depart, 0);
    	this.file.Ajouter(depart, 0);
        System.out.println(file.toString());
        file.ExtraireMin().afficher();
        while (!this.file.EstVide()) {
            Configuration min = this.file.ExtraireMin();
            //vu.add(min.tableauEnString());
        	if (min.jeuGagne()) {
        		return chemin;
        	}
            ArrayList<Configuration> s=min.getSuccesseur();
        	for (int i=0; i<s.size(); i++) {
        		//System.out.println(s.get(i));
        	
        		//s.get(i).afficher();
        		s.get(i).setDistance(min.getDistance()+1);
        		//System.out.println(s.get(i).getDistance());
        		//vu.add(s.get(i).tableauEnString());
                System.out.println(s.get(i).estPresent(s.get(i), file) );
        		if (!s.get(i).estPresent(s.get(i), file) && this.vu.add(s.get(i).tableauEnString())){
        			this.file.Ajouter(s.get(i), s.get(i).getDistance());
                    chemin.Ajouter(s.get(i), s.get(i).getDistance());

        		}
        	}
        }
        return chemin;
     }
    
           /* u = this.file.ExtraireMin();
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
       // return chemin;
    


    public void afficheChemin(FdPg<Configuration> chemin) {
        for (int i = 0; i < chemin.nb; i++) {
            chemin.T.get(i).val.afficher();
            System.out.println();
        }
    }
}
