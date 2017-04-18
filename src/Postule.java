import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Postule extends JFrame implements ActionListener{
	AfficheOffre offre;
	PieceJ PJ;
	JLabel motivation_label;
	JTextArea motivation_field;
	JButton validation_button;
	GridBagConstraints gbc;
	public Postule(AfficheOffre offre) {
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;	
  
		setLayout(new GridBagLayout());
		
		this.offre = offre;
		offre.remove(offre.postule_button);
		
		PJ = new PieceJ("etudiant"); 
		
		motivation_label = new JLabel("Motivations :");
		motivation_field = new JTextArea();
		motivation_field.setPreferredSize(new Dimension(100, 100));
		JScrollPane scrollPane = new JScrollPane(motivation_field);
		
		validation_button = new JButton("Finaliser la candidature");
		validation_button.addActionListener(this);
				
		add(offre, gbc);
		gbc.gridy = 1;
		add(PJ, gbc);
		gbc.gridy = 2;
		add(motivation_label, gbc);
		gbc.gridy = 3;
		add(scrollPane, gbc);
		gbc.gridy = 4;
		add(validation_button, gbc);
		
		setSize(500,500);
		setTitle("Postuler : ");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == validation_button){
			if (PJ.nom_fichier.isEmpty())
				JOptionPane.showMessageDialog(this,"Le CV est obligatoire", "Erreur",JOptionPane.ERROR_MESSAGE);
			else if (motivation_field.getText().isEmpty())
				JOptionPane.showMessageDialog(this,"Le message de motivation est obligatoire", "Erreur",JOptionPane.ERROR_MESSAGE);
			else {				
				try {
					String req = "INSERT INTO candidature (offre, etudiant, chemin_cv, motivation) "
							+"VALUES ('"+offre.id+"','"+offre.etudiant+"','"+PJ.nom_fichier+"','"+motivation_field.getText()+"')";
					System.out.println(req);
					Application.statement_base.executeUpdate(req);
					JOptionPane.showMessageDialog(this,"Candidature envoyée", "",JOptionPane.INFORMATION_MESSAGE);
					this.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}			
		}
	}

}
