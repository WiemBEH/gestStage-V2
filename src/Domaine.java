import java.sql.SQLException;

import javax.swing.JComboBox;


public class Domaine extends JComboBox<String>{

	public Domaine() {
		try {
			Application.result_base = Application.statement_base.executeQuery("select * from domaine");
			while (Application.result_base.next()){
				addItem(Application.result_base.getString("domaine"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
