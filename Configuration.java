package game_test;

import java.util.ArrayList;

public class Configuration {
	private ArrayList<Configuration>successeurs;
	private Plateau configuration;
	
	public Configuration (Plateau plateau) {
		this.configuration = plateau;
	}
	
	public void creationConfig() {
		if (this.configuration.MouvementBas()) {
			//new Configuration ()
		}
	}
}
