import java.lang.reflect.Type;
import java.util.*;
import javax.swing.*;
import java.util.Scanner;

public class TaquinLanceur {

	public static boolean launcher(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Souhaitez vous lancer le taquin en mode (t)erminal, en mode (g)raphique ou bien (q)uitter?");

		String command = scan.next();
		if(command.equals("t")){
			Jeu jeu = new Jeu();
			return true;
		}
		if(command.equals("g")){
			Vue a=new Vue();
			return true;
		}
		if(command.equals("q")){
			return false;

		}
		System.out.println("Commande inconnue");
		return true;
	}

    public static void main(String[] args) {
		boolean run = true;
		while(run){
			run = launcher();
		}
		System.out.println("Fermeture");

    }

}
