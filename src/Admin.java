import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Admin extends JFrame implements ActionListener {

	
	JComboBox<String> actionCombo;
	JButton actionButton;
	JPanel coprsPanel;
	
	public Admin() {
			
	actionCombo = new JComboBox<String>();
	actionCombo.addItem("Selectionner une action");
	actionCombo.addItem("Ajouter utilisateur");
	actionCombo.addItem("Ajouter domaine");
	actionCombo.addItem("Supprimer utilisateur");
	actionCombo.addItem("Supprimer domaine");
	actionCombo.addActionListener(this);
	
	coprsPanel = new JPanel();
	
	
	add(actionCombo,  BorderLayout.NORTH);

	add(coprsPanel,  BorderLayout.CENTER);	
	
	setTitle("Administrateur");
	setSize(500,500);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == actionCombo) {
			String s = (String) actionCombo.getSelectedItem();
			switch (s) {
				case "Ajouter utilisateur":
					this.remove(coprsPanel);
					coprsPanel = new AjoutUtilisateur();
					add(coprsPanel, BorderLayout.CENTER);
					revalidate();
					System.out.println("Ajouter utilisateur");
					break;
				case "Ajouter domaine":
					this.remove(coprsPanel);
					coprsPanel = new AjoutDomaine();
					add(coprsPanel, BorderLayout.CENTER);
					revalidate();
					System.out.println("Ajouter domaine");
					break;
				case "Supprimer utilisateur":
					this.remove(coprsPanel);
					coprsPanel = new SuppressionUtilisateur();
					add(coprsPanel, BorderLayout.CENTER);
					revalidate();
					System.out.println("Supprimer utilisateur");										
					break;				
				case "Supprimer domaine":
					this.remove(coprsPanel);
					coprsPanel = new SuppressionDomaine();
					add(coprsPanel, BorderLayout.CENTER);
					revalidate();
					System.out.println("Supprimer domaine");					
					break;							
			}
		}	
	}
	

	public static void main(String[] args) {
		
		try{
			Application.connectionBD();
			new Admin();
		}catch(SQLException ee){
			System.err.println(ee.getMessage());
			System.exit(2);
		}		
	}


}
