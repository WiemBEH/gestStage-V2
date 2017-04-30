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


public class SuppressionDomaine extends JPanel  implements ActionListener {

	
	Vector<JButton> suppButtons =  new Vector<JButton>();
	Vector<JLabel> suppJLabel =  new Vector<JLabel>();
	String req;
	GridBagConstraints gbc;
	
	public SuppressionDomaine() {
		super(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		req = "SELECT * FROM domaine;";
		try {
			Application.result_base = Application.statement_base.executeQuery(req);
			while (Application.result_base.next()){
				gbc.gridx = 0;
				suppButtons.add(new JButton("supprimer"));
				suppButtons.lastElement().addActionListener(this);
				add(suppButtons.lastElement(), gbc);
				gbc.gridx = 1;
				suppJLabel.add(new JLabel(Application.result_base.getString("domaine")));				
				add(suppJLabel.lastElement(), gbc);				
				++gbc.gridy;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource().toString());

		for (int i=0; i<suppButtons.size(); i++){
			if (e.getSource() == suppButtons.get(i)){
				String req2 = "DELETE FROM domaine WHERE domaine = '"+suppJLabel.get(i).getText()+"';";
				System.out.println(req2);
				try {
					Application.statement_base.executeUpdate(req2);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(this,"Impossible de supprimer ce domaine", "Erreur",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(this,"Domaine supprimé ", "Erreur",JOptionPane.INFORMATION_MESSAGE);
				//la requete 
				remove(suppButtons.get(i));
				remove(suppJLabel.get(i));
				
				repaint();
				revalidate();
				
			}
		}		
		
	}

}
