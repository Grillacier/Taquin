import java.util.ArrayList;

import game_test.Case;
import game_test.Configuration;
import game_test.Plateau;

import java.util.*;
import java.lang.*;

public class Configuration {
	private ArrayList<Configuration>successeurs = new ArrayList<Configuration>();
	private Plateau configuration;
	private int h; // heuristique
	
	public Configuration (Plateau plateau) {
		this.configuration = plateau;
		successeurs.add(this);
		creerSuccesseurs(this);
		this.h = manhattanHeuristic();
	}
	
	public Plateau getConfiguration () {
    	return this.configuration;
    }
	
	public ArrayList<Configuration> getSuccesseurs () {
    	return this.successeurs;
    }
	

	/**
	 * Rempli la liste des successeurs possibles
	 * @param c la Configuration a partir de laquelle on cherche toutes celles possibles
	 */
	public void creerSuccesseurs(Configuration c) {
		if (c.configuration.MouvementBas()) {
			Configuration suivant = new Configuration(configuration.constrTabBas());
			if (!successeurs.contains(suivant)) {
				successeurs.add(suivant);
				creerSuccesseurs(suivant);
			}
		}
		if (c.configuration.MouvementHaut()) {
			Configuration suivant = new Configuration(configuration.constrTabHaut());
			if (!successeurs.contains(suivant)) {
				successeurs.add(suivant);
				creerSuccesseurs(suivant);
			}
		}
		if (c.configuration.MouvementGauche()) {
			Configuration suivant = new Configuration(configuration.constrTabGauche());
			if (!successeurs.contains(suivant)) {
				successeurs.add(suivant);
				creerSuccesseurs(suivant);
			}
		}
		if (c.configuration.MouvementDroit()) {
			Configuration suivant = new Configuration(configuration.constrTabDroit());
			if (!successeurs.contains(suivant)) {
				successeurs.add(suivant);
				creerSuccesseurs(suivant);
			}
		}
	}
	
	public  Configuration configFinal() {
		Configuration configFinal = new Configuration(this.configuration.copiePlateau());
		int k = 1;
		for (int i=0; i<configFinal.configuration.getPlateau().length; i++) {
			for (int j=0; j<configFinal.configuration.getPlateau()[i].length; j++) {
				configFinal.configuration.getPlateau()[i][j] = new Case(k);
				k++;
			}
		}
		for (int i=0; i<configFinal.configuration.getPlateau().length; i++) {
			for (int j=0; j<configFinal.configuration.getPlateau()[i].length; j++) {
				configFinal.configuration.getPlateau()[configFinal.configuration.getPlateau().length-1][configFinal.configuration.getPlateau()[0].length-1] = new Case(0);
						
				}
			}
		
		return configFinal;
	}
	
	public int manhattanHeuristic() {
		int numero = 0;
		int val = 0;
		Configuration config = configFinal();
		for (int i=0; i<this.configuration.getPlateau().length; i++) {
			for (int j=0; j<this.configuration.getPlateau()[i].length; j++) {
				if (this.configuration.getPlateau()[i][j].getNumero() != 0) {
					numero = this.configuration.getPlateau()[i][j].getNumero();
					 val+=( Math.abs(i-config.configuration.xValue(numero)));
					 val+=(Math.abs(j-config.configuration.yValue(numero)));
				
				}
			}
		}
		
		return val;
	}

	//WIP
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Configuration that = (Configuration) o;
		return Objects.equals(successeurs, that.successeurs) &&
				Objects.equals(configuration, that.configuration);
	}

	//WIP
	@Override
	public int hashCode() {
		return Objects.hash(successeurs, configuration);
	}
}
