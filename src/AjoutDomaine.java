import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AjoutDomaine extends JPanel  implements ActionListener {

	JTextField  domaine_field;
	JButton ajoutDomaine;
	
	GridBagConstraints gbc;
	
	public AjoutDomaine() {
		super(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		domaine_field = new JTextField();
		domaine_field.setPreferredSize( new Dimension( 300, 24 ) );
		
		ajoutDomaine = new JButton("Ajouter");
		ajoutDomaine.addActionListener(this);
		
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(domaine_field,gbc);
		gbc.gridy = 1;
		add(ajoutDomaine,gbc);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ajoutDomaine && !domaine_field.getText().equals("")){
			String req = "INSERT INTO domaine VALUES ('"+domaine_field.getText()+"');";
			System.out.println(req);
			try {
				Application.statement_base.executeUpdate(req);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this,"Impossible d'ajouter ce domaine", "Erreur",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this,"Domaine ajouté ", "Erreur",JOptionPane.ERROR_MESSAGE);

		}
	}
	public static void main(String[] args) {
		try{
			Application.connectionBD();
			new AjoutDomaine();
		}catch(SQLException ee){
			System.err.println(ee.getMessage());
			System.exit(2);
		}
	}

}
