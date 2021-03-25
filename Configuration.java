import java.util.ArrayList;
import java.util.Objects;

public class Configuration {
	private ArrayList<Configuration>successeurs = new ArrayList<Configuration>();
	private Plateau config;
	
	public Configuration (Plateau plateau) {
		this.config = plateau;
		successeurs.add(this);
		creerSuccesseurs(this);
	}

	/**
	 * Rempli la liste des successeurs possibles
	 * @param c la Configuration a partir de laquelle on cherche toutes celles possibles
	 */
	public void creerSuccesseurs(Configuration c) {
		if (c.config.MouvementBas()) {
			Configuration suivant = new Configuration(config.constrTabBas());
			if (!successeurs.contains(suivant)) {
				successeurs.add(suivant);
				creerSuccesseurs(suivant);
			}
		}
		if (c.config.MouvementHaut()) {
			Configuration suivant = new Configuration(config.constrTabHaut());
			if (!successeurs.contains(suivant)) {
				successeurs.add(suivant);
				creerSuccesseurs(suivant);
			}
		}
		if (c.config.MouvementGauche()) {
			Configuration suivant = new Configuration(config.constrTabGauche());
			if (!successeurs.contains(suivant)) {
				successeurs.add(suivant);
				creerSuccesseurs(suivant);
			}
		}
		if (c.config.MouvementDroit()) {
			Configuration suivant = new Configuration(config.constrTabDroit());
			if (!successeurs.contains(suivant)) {
				successeurs.add(suivant);
				creerSuccesseurs(suivant);
			}
		}
	}

	//WIP
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Configuration that = (Configuration) o;
		return Objects.equals(successeurs, that.successeurs) &&
				Objects.equals(config, that.config);
	}

	//WIP
	@Override
	public int hashCode() {
		return Objects.hash(successeurs, config);
	}
}
