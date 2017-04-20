import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class SuppressionUtilisateur extends JPanel  implements ActionListener{
	
	Vector<JButton> suppButtons =  new Vector<JButton>();
	Vector<JLabel> suppJLabel =  new Vector<JLabel>();
	Vector<String> mails =  new Vector<String>();
	String req;
	GridBagConstraints gbc;
	
	public SuppressionUtilisateur() {
		super(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		req = "SELECT * FROM utilisateur;";
		
		String affichage = "";
		try {
			Application.result_base = Application.statement_base.executeQuery(req);
			while (Application.result_base.next()){
				if (Application.result_base.getString(3).equals("Administrateur"))
					continue;
				gbc.gridx = 0;
				suppButtons.add(new JButton("supprimer"));
				suppButtons.lastElement().addActionListener(this);
				add(suppButtons.lastElement(), gbc);
				gbc.gridx = 1;
//				for (int i = 0; i < Application.result_base.getMetaData().getColumnCount(); i++){
					affichage += Application.result_base.getString(3)+" : "+Application.result_base.getString(4)+" ( "+Application.result_base.getString(1)+")";
//				}
				suppJLabel.add(new JLabel(affichage));	
				mails.add(Application.result_base.getString(1));
				add(suppJLabel.lastElement(), gbc);				
				++gbc.gridy;
				affichage = "";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource().toString());

		for (int i=0; i<suppButtons.size(); i++){
			if (e.getSource() == suppButtons.get(i)){
				String req4 = "DELETE FROM candidature WHERE etudiant = '"+mails.get(i)+"';";
				String req3 = "DELETE FROM offre_stage WHERE entreprise = '"+mails.get(i)+"';";
				String req2 = "DELETE FROM utilisateur WHERE mail = '"+mails.get(i)+"';";
				System.out.println(req2);
				try {
					Application.statement_base.executeUpdate(req4);
					Application.statement_base.executeUpdate(req3);
					Application.statement_base.executeUpdate(req2);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(this,"Impossible de supprimer l'utilisateur", "Erreur",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(this,"Utilisateur supprimé ", "Erreur",JOptionPane.ERROR_MESSAGE);
				//la requete 
				remove(suppButtons.get(i));
				remove(suppJLabel.get(i));
				
				repaint();
				revalidate();
				
			}
		}	
		
	}

}
