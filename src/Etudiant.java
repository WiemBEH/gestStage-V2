import java.awt.BorderLayout;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;



public class Etudiant extends Utilisateur {
	Domaine domaines;
	JButton recherche_button;
	GridBagConstraints gbc;
	JPanel resultat_recherche;
	JScrollPane JCdomaines;
	
	JCheckBox postule_check;
	
	public Etudiant(String mail, String nom, String telephone) {
		super(mail, nom, telephone);
		
		postule_check = new JCheckBox("postulées");
		postule_check.setSelected(true);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;	
		  
		JPanel recherche_panel = new JPanel(new GridBagLayout());
	  	
		domaines = new Domaine();
		domaines.insertItemAt("TOUT", 0);
		domaines.setSelectedItem("TOUT");
		recherche_panel.add(new JLabel("Domaine de l'offre "), gbc);
		gbc.gridx = 1;	
		gbc.gridy = 0;	
		recherche_panel.add(domaines, gbc);

		gbc.gridx = 0;	
		gbc.gridy = 2;		
		recherche_panel.add(postule_check, gbc);		
		
		gbc.gridx = 0;	
		gbc.gridy = 3;	
		gbc.gridwidth = 2;
		recherche_button = new JButton("Rechercher");
		recherche_button.addActionListener(this);
		recherche_panel.add(recherche_button, gbc);
		
		recherche_panel.setBorder(BorderFactory.createTitledBorder("Recherche de stage"));
		
		add(recherche_panel, BorderLayout.NORTH);
		
		JCdomaines = new JScrollPane();
		add(JCdomaines, BorderLayout.CENTER);
		
		setTitle("Etudiant : "+mail);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//pack();
		setSize(500,500);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == recherche_button){
			System.out.println("fd");
				resultat_recherche = new JPanel(new GridBagLayout());
				String req = "SELECT * FROM offre_stage";
				if (!domaines.getSelectedItem().equals("TOUT"))
					req += " WHERE domaine = '"+domaines.getSelectedItem()+"' AND ";
				else
					req += " WHERE ";
				if (postule_check.isSelected())
					req += "id IN (SELECT offre FROM candidature)";
				else
					req += "id NOT IN (SELECT offre FROM candidature)";
				req += ";";
				try {
					Application.result_base = Application.statement_base.executeQuery(req);
					while(Application.result_base.next()){
						gbc.gridy++;
						resultat_recherche.add(new AfficheOffre(false,
							Application.result_base.getString("entreprise"),null,
							Application.result_base.getString("domaine"),
							Application.result_base.getString("libelle"),
							Application.result_base.getString("date_debut"),
							Application.result_base.getString("duree"),
							Application.result_base.getString("chemin_stockage"),
							Application.result_base.getString("descriptif"), 
							this.getMail(),
							Application.result_base.getString("id")),gbc);
							
					}
					remove(JCdomaines);
					JCdomaines = new JScrollPane(resultat_recherche);
					add(JCdomaines, BorderLayout.CENTER);
					revalidate();
					//repaint();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			Application.connectionBD();
		}catch(SQLException ee){
			System.err.println(ee.getMessage());
			System.exit(2);
		}
		
		try {
			Etudiant frame = new  Etudiant("aliabir_35@hotmail.com", null, null);
			 //frame.pack();
			//frame.setSize(500,100);
			frame.setVisible(true);
		} catch(Exception e){
			 JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

}
