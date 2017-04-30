import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Candidat  extends JPanel implements ActionListener {

	String chamein_cv;
	String mail_etudiant;
	String motivation_etudiant;
	Boolean remarque;
	int offre;
	
	JButton cv_button, accepter_button, refuser_button;
	JLabel  mail_etudiant_label, remarque_label, motivation_label;
	
	
	public Candidat(int offre,String chamein_cv,String mail_etudiant,String motivation_etudiant,Boolean remarque) {
		System.out.println("+++++++++++++++++++1");
		this.offre = offre;
		this.chamein_cv = chamein_cv;
		this.mail_etudiant = mail_etudiant;
		this.motivation_etudiant = motivation_etudiant;
		this.remarque = remarque;
		
		cv_button = new JButton("CV");
		accepter_button = new JButton("Accepter") ;
		refuser_button = new JButton("Refuser");
		refuser_button.addActionListener(this);
		accepter_button.addActionListener(this);
		cv_button.addActionListener(this);
		System.out.println("+++++++++++++++++++2");
		mail_etudiant_label = new JLabel("Mail : \n :"+mail_etudiant);
		System.out.println("+++++++++++++++++++3");
		remarque_label = new JLabel();
		if (remarque == null)
			remarque_label.setText("Candidature en attente");
		else if (remarque)
			remarque_label.setText("Candidature acceptée");
		else
			remarque_label.setText("Candidature refusée");
		System.out.println("+++++++++++++++++++4");
		motivation_label = new JLabel("Motivations : \n"+motivation_etudiant);	
		System.out.println("+++++++++++++++++++5");

		add(mail_etudiant_label);
		add(motivation_label);
		add(cv_button);
		
		add(accepter_button);
		add(refuser_button);
		add(remarque_label);
		
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));		
	 	setBorder(BorderFactory.createTitledBorder(""));
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cv_button){
			String chemin_courant;
	    	try {
				chemin_courant = new java.io.File( "." ).getCanonicalPath();
			} catch (IOException e2) {
				chemin_courant = "/home/ali/Wiem/Projet 2Bis";
				e2.printStackTrace();
			}
			Desktop d = Desktop.getDesktop();
			System.out.println(chemin_courant+"/cvs/"+chamein_cv);
			try {
				d.open(new File(chemin_courant+"/cvs/"+chamein_cv));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == accepter_button)
			changer_reponse(true);
		if (e.getSource() == refuser_button)
			changer_reponse(false);
		
	}
	
	void changer_reponse(boolean rep){
		int rep_i = 0;
		if (rep) rep_i = 1;
		try {
			String req = "UPDATE candidature SET "
					+"reponse = '"+rep_i+"' WHERE offre = '"+offre+"' AND etudiant = '"+mail_etudiant+"';";
			System.out.println(req);
			Application.statement_base.executeUpdate(req);
			JOptionPane.showMessageDialog(this,"Réponse effectuée", "",JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		if (rep)
			remarque_label.setText("Candidature acceptée");
		else
			remarque_label.setText("Candidature refusée");
		revalidate();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
}
