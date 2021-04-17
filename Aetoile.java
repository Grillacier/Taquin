import java.util.ArrayList;
import java.util.HashSet;

public class Aetoile {
	private Configuration depart;
    private HashSet<String> vu;
    private FdPg<Configuration> file;
	private HashSet<String> terminer;
	
	public Aetoile (Configuration d) {
		this.depart = d;
        this.vu = new HashSet<>();
        this.file = new FdPg<Configuration>();
		this.terminer = new HashSet<>();
		
	}

	//permet de calculer le nombre d'éléments mal placé dans le tableau d'une cellule
	public int distanceHammingHeuristique(Configuration c) {
		Configuration finale = c.tableauFinal();
		int k = 0;
		for (int i = 0; i < c.getHauteur(); i++) {
			for (int j = 0; j < c.getLargeur(); j++) {
				if (c.getTableau()[i][j] != finale.getTableau()[i][j] && c.getTableau()[i][j] != 0) {
					k++;
				}
			}
		}
		return k; 
	}

	public int manhattanHeuristic(Configuration c) {
        int numero = 0;
        int val = 0;
        Configuration gagne = c.tableauFinal();
        for (int i = 0; i < c.getHauteur(); i++) {
            for (int j = 0; j < c.getLargeur(); j++) {
                if (c.getTableau()[i][j] != 0) {
                    numero = c.getTableau()[i][j];
                    val+=(Math.abs(i-gagne.getX()));
                    val+=(Math.abs(j-gagne.getY()));
                }
            }
        }
        return val;
    }

	/*
	//méthode qui permet de trouver la configuration la moins couteuse parmi les mouvements possibles de la cellule
	public Cellule fminValCellule (Cellule c) {
		ArrayList<Cellule> fval = new ArrayList<Cellule>();
		Cellule cellFmin = null; //initialisation
		//Cellule cellB = new Cellule (new int [c.tab.length][c.tab.length]);
		if (MouvementBas(c)) {
			int [][]tabB = constrTabBas(c.tab) ;
			Cellule cellB = new Cellule (tabB);
			cellB.x = c.x+1 ;
			cellB.g++;
			cellB.h = distanceHammingHeuristique(cellB);
			cellB.f = cellB.g + cellB.h;
			fval.add(cellB);
		}
		else if (MouvementHaut(c)) {
			int [][]tabH = constrTabHaut(c.tab);
			Cellule cellH = new Cellule (c.tab);
			cellH.x = c.x-1;
			cellH.g++;
			cellH.h = distanceHammingHeuristique(cellH);
			cellH.f = cellH.g + cellH.h;
			fval.add(cellH);
		}
		else if (MouvementGauche(c)) {
			int [][]tabG = constrTabGauche(c.tab) ;
			Cellule cellG = new Cellule (tabG);
			cellG.y = c.y-1;
			cellG.g++;
			cellG.h = distanceHammingHeuristique(cellG);
			cellG.f = cellG.g + cellG.h;
			fval.add(cellG);
		}
		else if (MouvementDroit(c)) {
			int [][]tabD = constrTabDroit(c.tab) ;
			Cellule cellD = new Cellule (tabD);
			cellD.y = c.y+1;
			cellD.g++;
			cellD.h = distanceHammingHeuristique(cellD);
			cellD.f = cellD.g + cellD.h;
			fval.add(cellD);
		}
		int fminVal = fval.get(0).f; //initialisation
		for (int i=1; i<fval.size(); i++) {
			if (fval.get(i).f<fminVal) {
				fminVal = fval.get(i).f;
			}
		}
		
		for (int i=0; i<fval.size(); i++) {
			if (fminVal == fval.get(i).f) {
				cellFmin = fval.get(i);
			}
		}
		
		return cellFmin;
	}
	*/
	
	public Configuration aetoile() {
		this.vu.add(this.depart.tableauEnString());
		this.file.Ajouter(depart, 0);
		//int distance = 0;
		while (!this.file.EstVide()) {
			Configuration min = this.file.ExtraireMin();
			if (min.jeuGagne()) {
                System.out.println("Configurations explorees : " + vu.size());
                return min;
            }
			min.successeurs();
            ArrayList<Configuration> s = min.getSuccesseur();
            for (int i = 0; i < s.size(); i++) {
                Configuration tmp = s.get(i);
				tmp.setDistance(tmp.distance(depart));
				System.out.println("distance de tmp : " + tmp.getDistance());
				int nouvDist = min.getDistance() + 1;
				int cle=min.getDistance()+manhattanHeuristic(tmp);
				System.out.println("nouvelle distance : " + nouvDist);
				if(file.hmap.containsValue(tmp)){
					if (tmp.getDistance() > nouvDist) {
						tmp.setDistance(nouvDist);
						this.vu.add(tmp.tableauEnString());
						this.file.MaJ(tmp, cle);
					}
				}if(terminer.contains(tmp.tableauEnString())){
					if (tmp.getDistance()> nouvDist) {
						tmp.setDistance(nouvDist);
						this.vu.add(tmp.tableauEnString());
						terminer.remove(tmp.tableauEnString());
						file.Ajouter(tmp, cle);
					}
				}else{
					tmp.setDistance(nouvDist);
					this.vu.add(tmp.tableauEnString());
					file.Ajouter(tmp, cle);
				}
            }
            //distance++;
		}
		return null;
	}
}

	
	

