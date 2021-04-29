import java.util.ArrayList;
import java.util.HashSet;

public class Aetoile {
	private Configuration depart;
    private HashSet<String> ferme;
    private FdPg<Configuration> ouvert;
	//private HashSet<String> terminer;
	
	public Aetoile (Configuration d) {
		this.depart = d;
        this.ferme = new HashSet<>();
        this.ouvert = new FdPg<Configuration>();
		
	}


	public Configuration aetoile(){
		ouvert.Ajouter(this.depart, 0);
		ferme.add(this.depart.tableauEnString());
		while(!ouvert.EstVide()){
			Configuration min = ouvert.ExtraireMin();
			ferme.add(min.tableauEnString());
			if(min.jeuGagne()){
				return min;
			}
			int distanceMin = min.getChemin().length();
			int distanceTmp = distanceMin + 1;
			min.successeurs();
			ArrayList<Configuration> fils = min.getSuccesseur();
			for(Configuration tmp : fils){
				int cle = distanceTmp + tmp.manhattanHeuristique();
				int cleExiste = ouvert.IndiceDsF(tmp);
				if(cleExiste != -1){
					if(cleExiste > cle){
						ouvert.MaJ(tmp, cle);
					}
				}else if(ferme.contains(tmp.tableauEnString())){
					if(cleExiste > cle){
						ferme.remove(tmp.tableauEnString());
						ouvert.Ajouter(tmp, cle);
					}
				}else{
					ouvert.Ajouter(tmp, cle);
				}
			}
		}
		return null;
	}

/*
	// VERSION EFFICACE
	public Configuration aetoile2(){
		ouvert.Ajouter(this.depart, 0);
		ferme.add(this.depart.tableauEnString());
		while(!ouvert.EstVide()){
			Configuration min = ouvert.ExtraireMin();
			ferme.add(min.tableauEnString());
			if(min.jeuGagne()){
				return min;
			}
			int distanceMin = min.getChemin().length();
			int distanceTmp = distanceMin++;
			min.successeurs();
			ArrayList<Configuration> fils = min.getSuccesseur();
			for(Configuration tmp : fils){
				int cleMin = distanceMin + min.manhattanHeuristique();
				int cleTmp = distanceTmp + tmp.manhattanHeuristique();
				if(ouvert.IndiceDsF(tmp) != -1){
					if(cleTmp < cleMin){
						ouvert.MaJ(tmp, cleTmp);
						if(ferme.contains(tmp.tableauEnString())){
							ferme.remove(tmp.tableauEnString());
						}
					}
				}else if(!ferme.contains(tmp.tableauEnString())){
					ouvert.Ajouter(tmp, cleTmp);
				}
			}
		}
		return null;
	}
*/



/* deplacements de manhattan et hamming dans Confguration
	//permet de calculer le nombre de cases mal placees
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
    }*/

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

	/*Version 1
	public Configuration aetoile() {
		this.vu.add(this.depart.tableauEnString());
		this.file.Ajouter(depart, 0);
		int distance = 1;
		while (!this.file.EstVide()) {
			Configuration min = this.file.ExtraireMin();
			terminer.add(min.tableauEnString());
			if (min.jeuGagne()) {
                System.out.println("Configurations explorees : " + vu.size());
                return min;
            }
			this.vu.remove(min);
			this.terminer.add(min.tableauEnString());
			min.successeurs();
            ArrayList<Configuration> s = min.getSuccesseur();
            for (int i = 0; i < s.size(); i++) {
                Configuration tmp = s.get(i);
				tmp.setDistance(tmp.distance(depart));
				System.out.println("distance de tmp : " + tmp.getDistance());
				int nouvDist = min.getDistance() + 1;
				int cle=nouvDist+manhattanHeuristic(tmp);
				System.out.println("nouvelle distance : " + nouvDist);
				if(file.hmap.containsValue(tmp)){
					if (tmp.getDistance() > nouvDist) {
						tmp.setDistance(nouvDist);
						this.vu.add(tmp.tableauEnString());
						this.file.MaJ(tmp, cle);
					}
				}else if(terminer.contains(tmp.tableauEnString())){
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
            distance++;
		}
		return null;
	}
	*/

	/*Version de Louis
	public Configuration aetoile() {
        this.vu.add(this.depart.tableauEnString());
        this.file.Ajouter(depart, 0);
        //int distance = 0;
        while (!this.file.EstVide()) {
            Configuration min = this.file.ExtraireMin();
            this.vu.add(min.tableauEnString());
            if (min.jeuGagne()) {
                System.out.println("Configurations explorees : " + vu.size());
                return min;
            }

            min.successeurs();
            ArrayList<Configuration> configurationsAdj = min.getSuccesseur();
            for (Configuration tmp : configurationsAdj) {
                tmp.setDistance(tmp.distance(depart));
                int nouvDist = min.getDistance() + 1;
                int cle = min.getDistance() + manhattanHeuristic(tmp);

                if (file.hmap.containsValue(tmp)) {
                    if (tmp.getDistance() > nouvDist) {
                        tmp.setDistance(nouvDist);
                        this.vu.add(tmp.tableauEnString());
                        this.file.MaJ(tmp, cle);
                    }
                }
                if (this.vu.contains(tmp.tableauEnString())) {
                    if (tmp.getDistance() > nouvDist) {
                        tmp.setDistance(nouvDist);
                        this.vu.remove(tmp.tableauEnString());
                        this.vu.add(tmp.tableauEnString());
                        file.Ajouter(tmp, cle);
                    }
                } else {
                    tmp.setDistance(nouvDist);
                    this.vu.add(tmp.tableauEnString());
                    file.Ajouter(tmp, cle);
                }
            }
            //distance++;
        }
        return null;
    }*/
/* Ancienne version
	public Configuration aetoile() {
		this.file.Ajouter(depart, 0);
		this.vu.add(this.depart.tableauEnString());

		while (!this.file.EstVide()) {
			Configuration min = this.file.ExtraireMin();
			this.vu.add(min.tableauEnString());
			//this.terminer.add(min.tableauEnString());
			if (min.jeuGagne()) {
				System.out.println("Configurations explorees : " + vu.size());
				return min;
			}

			min.successeurs();
			ArrayList<Configuration> configurationsAdj = min.getSuccesseur();
			for (Configuration tmp : configurationsAdj) {
				tmp.setDistance(tmp.distance(depart));
				//int nouvDist = min.getDistance() + 1;
				int cle = min.getDistance() + distanceHammingHeuristique(tmp);
				tmp.setDistance(min.getDistance() + 1);

				if (file.hmap.containsValue(tmp)) {
					//if (tmp.getDistance() > nouvDist) {
						//this.vu.add(tmp.tableauEnString());
						this.file.MaJ(tmp, cle);
					//}
				}
				else if (this.vu.contains(tmp.tableauEnString())) {
					//if (tmp.getDistance() > nouvDist) {
						//this.vu.add(tmp.tableauEnString());
						this.vu.remove(tmp.tableauEnString());
						//this.file.Ajouter(tmp, cle);
					//}
				} else {
					this.vu.add(tmp.tableauEnString());
					this.file.Ajouter(tmp, cle);
				}
			}
		}
		return null;
	}*/
}

	
	

