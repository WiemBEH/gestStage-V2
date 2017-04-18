import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.mysql.jdbc.Statement;


public class AfficheOffre extends JPanel implements ActionListener {
	boolean societe;
	String mail;
	String entreprise;
	String domaine;
	String libelle;
	String date_debut;
	String duree;
	String chemin_stockage;
	String descriptif;
	String etudiant;
	String id;
	JButton detail_button, postule_button;
	
	JLabel reponse_label;
	
	public AfficheOffre(boolean societe, String mail, String entreprise, String domaine, String libelle, String date_debut, String duree, String chemin_stockage, String descriptif, String etudiant, String id) {
		this.societe = societe;
		this.mail = mail;
		this.entreprise = entreprise;
		this.domaine = domaine;
		this.libelle = libelle;
		this.date_debut = date_debut;
		this.duree = duree;
		this.chemin_stockage = chemin_stockage;
		this.descriptif = descriptif;
		this.etudiant = etudiant;
		this.id = id;
		
		boolean candidatures = false;
		
		detail_button = new JButton("voir détail");
		postule_button = new JButton("Postuler");
		postule_button.addActionListener(this);
		detail_button.addActionListener(this);

		reponse_label = new JLabel("");
		String req = "";
		if (!societe) req = "SELECT * FROM candidature WHERE offre = '"+id+"' AND etudiant = '"+etudiant+"';";
		else req = "SELECT * FROM candidature WHERE offre = '"+id+"';";
		try {
			Statement st = (Statement) Application.connexion_base.createStatement();
			ResultSet re = st.executeQuery(req);
			if (re.next()){
				if (!societe) {
					System.out.println(re.getBoolean("reponse"));
					if (re.getBoolean("reponse") == true){
						reponse_label.setText("Acceptée");
						reponse_label.setForeground(Color.green);
					}
					else if (re.wasNull()) {
						reponse_label.setText("Pas de réponse encore");
						reponse_label.setForeground(Color.red);
					}
					else {
						reponse_label.setText("Refusée");
						reponse_label.setForeground(Color.orange);
					}
				}
				else 
					candidatures = true;
			}
			else {
					if (societe){						
						candidatures = false;
						reponse_label.setText("Pas de candidatures");
						reponse_label.setForeground(Color.orange);
					}
					else {
						reponse_label.setText("Vous n'avez pas postulé à cette offre");
						reponse_label.setForeground(Color.orange);
					}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(new JLabel("Entreprise : " + entreprise));
		add(new JLabel("Contact : " + mail));
		add(new JLabel("Domaine : " + domaine));
		add(new JLabel("Libellé : " + libelle));
		add(new JLabel("Début : " + date_debut));
		add(new JLabel("Durée : " + duree + " jours"));
		add(new JLabel("Decription : \n" + descriptif));
	 	add(new JLabel(reponse_label.getText()));
	 	add(detail_button);
	 	if (reponse_label.getText().equals("Vous n'avez pas postulé à cette offre"))
	 		add(postule_button);
	 	else if (societe && candidatures){
	 		postule_button.setText("Candidatures");
	 		add(postule_button);
	 	}
	 	setBorder(BorderFactory.createTitledBorder(""));
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == detail_button){
			String chemin_courant;
	    	try {
				chemin_courant = new java.io.File( "." ).getCanonicalPath();
			} catch (IOException e2) {
				chemin_courant = "/home/ali/Wiem/Projet 2Bis";
				System.out.println("fcds");
				e2.printStackTrace();
			}
			Desktop d = Desktop.getDesktop();
			System.out.println(chemin_courant+"/offres/"+chemin_stockage);
			try {
				d.open(new File(chemin_courant+"/offres/"+chemin_stockage));
			} catch (IOException e1) {
				System.out.println("fcdXSs");
				e1.printStackTrace();
			}
		}
		if (e.getSource() == postule_button && !societe){
			new Postule(this);
//			postule_button.hide();
		}
		if (e.getSource() == postule_button && societe){
			new Candidatures(this);
		}
		
		
	}	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new AfficheOffre (true, "aliabir_35@hotmail.com", null,null, null, null, null, "20170413001412-anglais6", null, null, null));
		f.pack();
		f.setVisible(true);
	}

}
