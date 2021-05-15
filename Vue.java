import java.util.*;
import java.io.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.io.*;
import javax.imageio.*;
import java.awt.event.KeyEvent;

public class Vue extends JFrame{
    private Joueur joueur;
    private JPanel accueilHaut, accueilBas, reglesDuJeu, pageDeJeu, panelJeu;
    private JLabel height, algo;
    private JButton jouer, charger, regles, validerPseudo, retourAccueil, pcel, aEtoile, dijk, generer;
    private JSlider taille;
    private JTextField pseudo;

    public Vue(){
        joueur = new Joueur();
        joueur.lire();
        this.setTitle("Taquin 4");
        this.setSize(1250, 800);
        this.setResizable(false);
        getContentPane().setLayout(new GridLayout(2,1));

        jouer = new JButton("Jouer");
        charger = new JButton("Charger");
        regles = new JButton("Règles");
        validerPseudo = new JButton("Valider");
        retourAccueil = new JButton("Retour à l'accueil");

        panelAcceuil();

        /* Action Listener */

        regles.addActionListener((event)->{
            getContentPane().setLayout(new GridLayout(1,1));
            System.out.println("help");
            getContentPane().remove(accueilBas);
            getContentPane().remove(accueilHaut);
            getContentPane().add(panelRegles());
            getContentPane().validate();
            getContentPane().repaint();

        });

        charger.addActionListener(
                (ActionEvent e)->{
                    System.out.println("charge");

                });

        jouer.addActionListener(
                (ActionEvent e)->{
                	panelJeu();
                	this.revalidate();
                    this.repaint();
                    System.out.println("jouer");
                });

        validerPseudo.addActionListener((event) ->{
            joueur.setNom(pseudo.getText());
            joueur.sauvegarder();
            System.out.println("Valider pressed");
        });

        retourAccueil.addActionListener((event)->{
            getContentPane().setLayout(new GridLayout(2,1));
            getContentPane().remove(reglesDuJeu);
            panelAcceuil();
            getContentPane().validate();
            getContentPane().repaint();


        });


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public void panelAcceuil(){
        JPanel accueil = new JPanel();

        accueilHaut = new JPanel();
        accueilBas = new JPanel();

        accueilHaut.setBackground(Color.WHITE);
        accueilBas.setBackground(Color.GRAY);

        accueilHaut.setLayout(null);
        accueilBas.setLayout(null);

        getContentPane().add(accueilHaut);
        getContentPane().add(accueilBas);

        // Panneau HAUT
        JLabel titre = new JLabel();
        titre.setText("Bienvenue sur le jeu du Taquin !");
        titre.setFont(new Font("New Times Roman", Font.BOLD, 40));
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        titre.setVerticalAlignment(SwingConstants.TOP);
        titre.setBounds(150,20,1000,50);
        accueilHaut.add(titre);

        JLabel id=new JLabel("Votre pseudo :");
        id.setBounds(450,100,300,50);
        accueilHaut.add(id);

        pseudo = new JTextField(joueur.getNom());
        pseudo.setBounds(480,150,300,50);
        accueilHaut.add(pseudo);

        accueilHaut.add(validerPseudo);
        validerPseudo.setBounds(790,150,100,50);

        //Panneau BAS
        accueilBas.add(jouer);
        accueilBas.add(charger);
        accueilBas.add(regles);
        jouer.setBounds(150,100,300,90);
        charger.setBounds(480,100,300,90);
        regles.setBounds(810,100,300,90);
    }


  /*  public JPanel getAcceuilPanelHaut(){

      // JPANEL
      JPanel panneauHaut = new JPanel();
      panneauHaut.setBackground(Color.WHITE);
      panneauHaut.setLayout(null);

      // Panneau HAUT
      JLabel titre = new JLabel();
      titre.setText("Bienvenue sur le jeu du Taquin !");
      titre.setFont(new Font("New Times Roman", Font.BOLD, 40));
      titre.setHorizontalAlignment(SwingConstants.CENTER);
      titre.setVerticalAlignment(SwingConstants.TOP);
      titre.setBounds(150,20,1000,50);
      panneauHaut.add(titre);

      JLabel id=new JLabel("Votre pseudo :");
      id.setBounds(450,100,300,50);
      panneauHaut.add(id);

      JTextField pseudo = new JTextField("");
      pseudo.setBounds(480,150,300,50);
      //pseudo.setBackground(Color.YELLOW);
      //pseudo.setForeground(Color.GRAY);
      panneauHaut.add(pseudo);

      JButton v=new JButton("Valider");
      panneauHaut.add(v);
      v.setBounds(790,150,100,50);
      return panneauHaut;
   }

   public JPanel getAccueilPanelBas(){
     // JPANEL
     JPanel panneauBas = new JPanel();
     panneauBas.setBackground(Color.GRAY);
     panneauBas.setLayout(null);

     //Panneau BAS
     JButton j=new JButton("Jouer/Play");
     JButton c=new JButton("Charger/Load");
     JButton r=new JButton("Règles du jeu");
     panneauBas.add(j);
     panneauBas.add(c);
     panneauBas.add(r);
     j.setBounds(150,100,300,90);
     c.setBounds(480,100,300,90);
     r.setBounds(810,100,300,90);

     r.addActionListener(
         (ActionEvent e)->{
             getContentPane().setLayout(new GridLayout(1,1));
             System.out.println("help");
             getContentPane().remove(panneauBas);
             getContentPane().remove(panneauHaut);
             getContentPane().add(getHelpPanel());
             getContentPane().validate();
             getContentPane().repaint();

     });
     c.addActionListener(
         (ActionEvent e)->{
           System.out.println("charge");

     });
     j.addActionListener(
         (ActionEvent e)->{
                 System.out.println("jouer");
     });
      return panneauBas;
   } */

    public JPanel panelRegles(){
        reglesDuJeu = new JPanel();
        reglesDuJeu.setLayout(null);
        reglesDuJeu.add(retourAccueil);
        retourAccueil.setBounds(100,500,1050,150);
        JLabel texte=new JLabel("<html>Résolution manuelle :<br>Afin de résoudre le célèbre jeu du taquin, il faut déplacer les cases verticalement et horizontalement sur la case vide.<br>Une fois avoir déplacer les cases nécéssaires et qu'elles sont dans l'ordre croissant, vous avez gagnez !<br><br>Résolution automatique :<br>Sélectionner la taille de la configuation de taquin souhaité.<br>Choisir l'algorithme de votre choix.<br>Cliquez sur Lancer !<br></html>");
        JLabel continuation=new JLabel("Profitez bien du jeu !!!");
        texte.setFont(new Font("New Times Roman", Font.BOLD, 15));
        texte.setBounds(50,50,1150,400);
        continuation.setBounds(500,150,1150,400);
        continuation.setFont(new Font("New Times Roman", Font.BOLD, 17));
        reglesDuJeu.add(texte);
        reglesDuJeu.add(continuation);
        return reglesDuJeu;



    }
    
    public void panelJeu() {
    	
    	this.getContentPane().removeAll();
    	this.pageDeJeu = new JPanel();
    	this.setLayout(new GridLayout(1,1));
    	this.pageDeJeu.setBounds(0,0,1250, 800);
		this.pageDeJeu.setBackground(new Color(233,233,233));
		this.pageDeJeu.setLayout(null);
		this.add(pageDeJeu);
		this.taille = new JSlider(2,6,3);
		this.taille.setBounds(0, 180, 110, 400);
		this.taille.setMajorTickSpacing(1);
		this.taille.setPaintTicks(true);
		this.taille.setPaintLabels(true);
		this.taille.setOrientation(SwingConstants.VERTICAL);
		
		this.panelJeu = new JPanel();
		this.panelJeu.setLayout(null);
		this.panelJeu.setBounds(200, 180, 800, 500);
		this.panelJeu.setBackground(new Color(139,69,19));
		this.height = new JLabel("Taille");
		this.height.setBounds(0, 0, 70, 20);
		
		this.algo = new JLabel("ALGORITHME");
		this.algo.setBounds(425, 10, 400, 50);
		this.algo.setFont(new Font("Serif",Font.PLAIN,50));
		
		this.aEtoile = new JButton("A*");
		this.aEtoile.setBounds(248, 94, 200, 50);
		this.aEtoile.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.dijk = new JButton("Dijkstra");
		this.dijk.setBounds(508, 94, 200, 50);
		this.dijk.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.pcel = new JButton("Pcel"); //parcours en largeur
		this.pcel.setBounds(770, 94, 200, 50);
		this.pcel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		this.generer = new JButton ("generer");
		this.generer.setBounds(10, 600, 91, 25);
		this.generer.setFocusable(false);
		this.generer.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		this.pageDeJeu.add(generer);
		this.pageDeJeu.add(this.panelJeu);
		this.pageDeJeu.add(this.taille);
		this.pageDeJeu.add(this.dijk);
		this.pageDeJeu.add(this.aEtoile);
		this.pageDeJeu.add(this.pcel);
		this.pageDeJeu.add(this.algo);
		taquinGenerator(150, 8, 3, 158, 158); // taquin par défaut 3*3;
		
		this.generer.addActionListener((event) -> {
				if (this.taille.getValue()==3) {
					this.panelJeu.removeAll();
					taquinGenerator(150, 8, 3, 158, 158);
					this.panelJeu.validate();
					this.panelJeu.repaint();
				}
				
				if (this.taille.getValue()==2) {
					this.panelJeu.removeAll();
					taquinGenerator(150, 8, 2, 240, 240);
					this.panelJeu.validate();
					this.panelJeu.repaint();
				}
				
				if (this.taille.getValue()==4) {
					this.panelJeu.removeAll();
					taquinGenerator(155, 14, 4, 115, 115);
					this.panelJeu.validate();
					this.panelJeu.repaint();
				}
				
				if(this.taille.getValue()==5) {
					this.panelJeu.removeAll();
					taquinGenerator(155, 10, 5, 92, 92);
					this.panelJeu.validate();
					this.panelJeu.repaint();
				}
				
				if(this.taille.getValue()==6) {
					this.panelJeu.removeAll();
					taquinGenerator(155, 10, 6, 76, 76);
					this.panelJeu.validate();
					this.panelJeu.repaint();
				}
				
				
				});
				
				/*if(this.hauteur.getValue()==3) {
					this.panelJeu.removeAll();
					Configuration c3 = new Configuration (this.hauteur.getValue(),this.largeur.getValue());
					this.panelJeu.setBounds(140, 210, 430, 420);
			int a = 11;
			int c = 5;
			for (int i=0; i<c3.getHauteur(); i++) {
				for (int j=0; j<c3.getLargeur(); j++) {
				String b = Integer.toString(c3.getTableau()[i][j]);
				JButton b1 = new JButton (b);
				Bouton b2 = new Bouton (i,j,b1);
				b2.getButton().setSize(133, 133);
				b2.getButton().setLocation(0+a, 0+c);
				b2.getButton().setBackground(new Color(252,242,216));
				a+=138;
				 if (j==this.largeur.getValue()-1) {
						a = 11;
						c+=138;
					}
				 if (b2.getButton().getText().equals("0")) {
					 b2.getButton().setBackground(new Color(139,69,19));
					 b2.getButton().setText(" ");
				 }
				 jButtonAction(b2,c3);
				
				this.panelJeu.add(b2.getButton());
				}
				
			}
			}
				this.panelJeu.revalidate();
	            this.panelJeu.repaint();
	            
	            if (this.hauteur.getValue()==4) {
	            	this.panelJeu.removeAll();
	            	Configuration c4 = new Configuration (this.hauteur.getValue(),this.largeur.getValue());
	            	this.panelJeu.setBounds(100, 110, 540, 540);
	            	int a = 13;
	        		int c = 14;
	        		for (int i=0; i<c4.getHauteur(); i++) {
	        			for (int j=0; j<c4.getLargeur(); j++) {
	        			String b = Integer.toString(c4.getTableau()[i][j]);
	        			JButton b1 = new JButton (b);
	        			Bouton b2 = new Bouton (i,j,b1);
	        			b2.getButton().setSize(125, 125);
	        			b2.getButton().setLocation(0+a, 0+c);
	        			b2.getButton().setBackground(new Color(252,242,216));
	        			a+=130;
	        			 if (j==3) {
	        					a = 13;
	        					c+=130;
	        				}
	        			 if (b2.getButton().getText().equals("0")) {
	        				 b2.getButton().setBackground(new Color(139,69,19));
	        				 b2.getButton().setText(" ");
	        			 }
	        			
	        			this.panelJeu.add(b2.getButton());
	        			}
	        			
	        		}
	            }

				this.panelJeu.revalidate();
	            this.panelJeu.repaint();
	            
	            if(this.hauteur.getValue()==5) {
	            	this.panelJeu.removeAll();
	            	Configuration c5 = new Configuration (this.hauteur.getValue(),this.largeur.getValue());
	            	this.panelJeu.setBounds(100, 110, 540, 540);
	            	int a = 10;
	        		int c = 10;
	        		for (int i=0; i<c5.getHauteur(); i++) {
	        			for (int j=0; j<c5.getLargeur(); j++) {
	        			String b = Integer.toString(c5.getTableau()[i][j]);
	        			JButton b1 = new JButton (b);
	        			Bouton b2 = new Bouton (i,j,b1);
	        			b2.getButton().setSize(100, 100);
	        			b2.getButton().setLocation(0+a, 0+c);
	        			b2.getButton().setBackground(new Color(252,242,216));
	        			a+=105;
	        			 if (j==4) {
	        					a = 10;
	        					c+=105;
	        				}
	        			 if (b2.getButton().getText().equals("0")) {
	        				 b2.getButton().setBackground(new Color(139,69,19));
	        				 b2.getButton().setText(" ");
	        			 }
	        			
	        			this.panelJeu.add(b2.getButton());
	        			}
	        	}
	            }
	            this.panelJeu.revalidate();
	            this.panelJeu.repaint();
		
			}});
		int a = 11;
		int c = 5;
		Configuration cDefault = new Configuration(3,3);
		for (int i=0; i<cDefault.getHauteur(); i++) {
			for (int j=0; j<cDefault.getLargeur(); j++) {
			String b = Integer.toString(cDefault.getTableau()[i][j]);
			JButton b1 = new JButton (b);
			Bouton b2 = new Bouton (i,j,b1);
			b2.getButton().setSize(133, 133);
			b2.getButton().setLocation(0+a, 0+c);
			b2.getButton().setBackground(new Color(252,242,216));
			a+=138;
			 if (j==this.largeur.getValue()-1) {
					a = 11;
					c+=138;
				}
			 if (b2.getButton().getText().equals("0")) {
				 b2.getButton().setBackground(new Color(139,69,19));
				 b2.getButton().setText(" ");
			 }
			
			this.panelJeu.add(b2.getButton());
			}
			
		}
		
	}*/
	
	/*public void jButtonAction(Configuration c) {
	
			for (int i=0; i<c.getHauteur(); i++) {
				for (int j=0; j<c.getLargeur(); j++) {
						jb.getButton().addActionListener((event) -> {
							if (c.getTableau()[jb.getX()][jb.getY()]==2) {
								JButton h = new JButton();
								Bouton x = new Bouton(0,0,h);
								jb.setButton(h);
							}
							//c.mouvement(c.positionCaseVide(jb.getX(), jb.getY(), c.getTableau()));
							
							//c.echangeCase(iValue-1, jValue);
						});
					
					}
						
				}*/
			
          
	}
    // méthode qui affiche le taquin par défaut ici 3*3 dans l'interface graphique
    public void taquinGenerator(int xPos, int yPos, int taille, int buttonWidth, int buttonHeight) {
    	int xTemp = xPos;
		Configuration configDefault = new Configuration(taille,taille);
		for (int i=0; i<configDefault.getHauteur(); i++) {
			for (int j=0; j<configDefault.getLargeur(); j++) {
			String b = Integer.toString(configDefault.getTableau()[i][j]);
			JButton b1 = new JButton (b);
			b1.setSize(buttonWidth, buttonHeight);
			b1.setLocation(0+xPos, 0+yPos);
			b1.setBackground(new Color(252,242,216));
			xPos+=buttonWidth+5;
			 if (j==taille-1) {
					xPos = xTemp;
					yPos+=buttonHeight+5;
				}
			 /*
			  * je fais en sorte que la case avec un 0 soit vide en étant transparent
			  * */
			 if (b1.getText().equals("0")) {
				 b1.setBackground(new Color(139,69,19));
				 b1.setText(" ");
			 }
			
			this.panelJeu.add(b1);
			}
    }}
}