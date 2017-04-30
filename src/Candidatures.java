import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.mysql.jdbc.Statement;

public class Candidatures extends JFrame implements ActionListener{
	AfficheOffre offre;

	public Candidatures(AfficheOffre offre) {
		this.offre = offre;
		offre.remove(offre.postule_button);
		
//		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//	    setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
	    setLayout(new BorderLayout());    
		
	    add(offre, BorderLayout.NORTH);
		JPanel scrol = new JPanel(new GridBagLayout());
		
		String req = "SELECT * FROM candidature WHERE offre = '"+offre.id+"';";
		try {
			System.out.println("------------------------");
			Statement st = (Statement) Application.connexion_base.createStatement();
			ResultSet re = st.executeQuery(req);
			while (re.next()){
				System.out.println("+++++++++++++++++++");
				Boolean reponse;
				if (re.getBoolean("reponse") == true)
					reponse = true;
				else if (re.wasNull())
					reponse = null;
				else 
					reponse = false;
				scrol.add(new Candidat (re.getInt("offre"), re.getString("chemin_cv"), re.getString("etudiant"), re.getString("motivation"), reponse));
			}		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		add(new JScrollPane(scrol),BorderLayout.CENTER);
		
		setSize(500,500);
		setTitle("Candidatures : ");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
