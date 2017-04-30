import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class Entreprise extends Utilisateur {
  	private String adresseRue;
  	private String adresseVille;
  	private String adresseCodePostal;
  	private String secteurActivite;
				
	JButton faireOffre_button;
	JButton listeOffres_button;
	JPanel panel;
	
	JScrollPane JCdomaines;
	JPanel resultat_offre = new JPanel(new GridBagLayout());
	
	GridBagConstraints gbc;
	
	Entreprise(String mail, String nom, String telephone, String adresseRue, String adresseVille, String adresseCodePostal, String secteurActivite){	  
		super(mail, nom, telephone);
		setSize(300,500);
		this.adresseRue = adresseRue;
	  	this.adresseVille = adresseVille;
	  	this.adresseCodePostal = adresseCodePostal;
	  	this.secteurActivite = secteurActivite;
	  	
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
	  	
		faireOffre_button=new JButton("faire une offre de stage");
		listeOffres_button=new JButton("consulter vos offres de stage");
		faireOffre_button.addActionListener(this);
		listeOffres_button.addActionListener(this);
		
		panel = new JPanel();
//		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		panel.add(faireOffre_button);
		panel.add(listeOffres_button);
		add(panel,BorderLayout.NORTH);

		
		JCdomaines = new JScrollPane(resultat_offre);
		add(JCdomaines, BorderLayout.CENTER);
		
		setTitle("Entreprise : "+mail);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
	}
	  
	  public void actionPerformed(ActionEvent ae) {
			JFrame PageSuivante = null;
			  if (ae.getActionCommand().equals("faire une offre de stage")){
				  System.out.println("offre");
				  PageSuivante = new Offre(this.getMail());	
					PageSuivante.setSize(500,500);
					//this.setVisible(false);
			  		PageSuivante.setVisible(true);
			  }
			  if (ae.getActionCommand().equals("consulter vos offres de stage")){
				  
				  System.out.println("consultation");
					String req = "SELECT * FROM offre_stage WHERE entreprise = '"+this.getMail()+"';";
					try {
						Application.result_base = Application.statement_base.executeQuery(req);
						gbc.gridy = 0;
						while(Application.result_base.next()){
							System.out.println(gbc.gridy);
							resultat_offre.add(new AfficheOffre(true,
								Application.result_base.getString("entreprise"),null,
								Application.result_base.getString("domaine"),
								Application.result_base.getString("libelle"),
								Application.result_base.getString("date_debut"),
								Application.result_base.getString("duree"),
								Application.result_base.getString("chemin_stockage"),
								Application.result_base.getString("descriptif"), 
								this.getMail(),
								Application.result_base.getString("id")), gbc);
								++gbc.gridy; 
								
						}
						//remove(JCdomaines);
						//JCdomaines = new JScrollPane(resultat_offre);
						//add(JCdomaines, BorderLayout.CENTER);
						revalidate();
						//repaint();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			  }

	  }

	 public static void main(String arg[]) {
		try{
			Application.connectionBD();
		}catch(SQLException ee){
			System.err.println(ee.getMessage());
			System.exit(2);
		}
		 try {
			 Entreprise frame = new  Entreprise("aliabir_35@hotmail.com", "ali", "0", null, null, null, null);
			 //frame.setSize(300,100);
			 frame.setVisible(true);
		 } catch(Exception e){
			 JOptionPane.showMessageDialog(null, e.getMessage());}
	   	}
}
