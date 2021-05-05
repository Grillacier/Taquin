import java.util.ArrayList;
import java.util.HashSet;

public class Aetoile {
	private Configuration depart;
    private HashSet<String> ferme;
    private FdPg<Configuration> ouvert;

	/**
	 * Contructeur de l'algorithme du parcours en largeur
	 * @param d Configuration initiale
	 */
	public Aetoile (Configuration d) {
		this.depart = d;
        this.ferme = new HashSet<>();
        this.ouvert = new FdPg<Configuration>();
		
	}

	/**
	 * R&eacute;sout un taquin avec l'algorithme A*
	 * @return la configuration r&eacute;solue &agrave; partir d'une configuration quelconque
	 */
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
}

	
	

