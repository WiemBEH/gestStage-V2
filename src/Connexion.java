import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;
	  
public class Connexion extends JFrame implements ActionListener{
	
	  JButton connexion_button;
	  JButton inscription_button;
	  JPanel panel;
	  JLabel identifiant_label,mdp_label;
	  final JTextField  identifiant_field,mdp_field;
	  
	  Connexion() {
		  identifiant_label = new JLabel("Identifiant: ");
		  identifiant_field = new JTextField(15);
	 
		  mdp_label = new JLabel("Password:");
		  mdp_field = new JPasswordField(15);
	  
		  connexion_button=new JButton("connexion");
		  inscription_button=new JButton("inscription");
		  
		  panel = new JPanel(new GridLayout(3,1));
		  panel.add(identifiant_label);
		  panel.add(identifiant_field);
		  panel.add(mdp_label);
		  panel.add(mdp_field);
		  panel.add(connexion_button);
		  panel.add(inscription_button);
		  add(panel,BorderLayout.CENTER);
		  connexion_button.addActionListener(this);
		  inscription_button.addActionListener(this);
		  setTitle("Page de connexion");
		  setSize(300,100);
		  setVisible(true);
	  }
	  
	  public void actionPerformed(ActionEvent ae) {
		  if (ae.getActionCommand().equals("connexion"))
			  connecter();
		  else if (ae.getActionCommand().equals("inscription"))
			  inscrire();
	  }
	  void inscrire (){
			JFrame f = new JFrame ();
			f.add(new AjoutUtilisateur());
			f.setVisible(true);
			f.setSize(500,500);
	  }
	  void connecter (){
			try {
				String identifiant = identifiant_field.getText();
				Application.result_base = Application.statement_base.executeQuery("select * from utilisateur where mail='"+identifiant+"'");
				if(Application.result_base.next()){
					if (!Application.result_base.getString("mot_de_passe").equals(mdp_field.getText()))
						JOptionPane.showMessageDialog(this,"Mot de passe incorrecte", "Erreur",JOptionPane.ERROR_MESSAGE);		
					else {
						String droit = Application.result_base.getString("droit");
					  	String nom = Application.result_base.getString("nom");
					  	Date date_naissance = Application.result_base.getDate("date_naissance");
					  	String telephone = Application.result_base.getString("telephone");
					  	String adresseRue = Application.result_base.getString("adresseRue");
					  	String adresseVille = Application.result_base.getString("adresseVille");
					  	String adresseCodePostal = Application.result_base.getString("adresseCodePostal");
					  	String secteurActivite = Application.result_base.getString("secteurActivite");	
						
						JFrame PageSuivante = null;
						if (droit.equals("Entreprise")) {
							PageSuivante = new Entreprise(identifiant,nom,telephone,adresseRue,adresseVille,adresseCodePostal,secteurActivite);
							System.out.println("Entreprise");
						}
						else if (droit.equals("Etudiant")) {
							PageSuivante = new Etudiant(identifiant, nom, telephone);
							System.out.println("Etudiant");
						}
							
						else if (droit.equals("Administrateur")) {
							PageSuivante = new Admin();
							System.out.println("Administrateur");
						}
						PageSuivante.setSize(500,500);
						this.setVisible(false);
				  		PageSuivante.setVisible(true);
					}
				}
				else
					JOptionPane.showMessageDialog(this,"Identifiant incorrecte", "Erreur",JOptionPane.ERROR_MESSAGE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		  
		 
	 }
	 public static void main(String arg[]) {
		 try {
			 Connexion frame=new  Connexion();
			 //frame.setSize(300,100);
			 //frame.setVisible(true);
		 } catch(Exception e){
			 JOptionPane.showMessageDialog(null, e.getMessage());}
	   	}
}
