import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Application {
	public static Connection connexion_base;
	public static Statement statement_base ;
	public static ResultSet result_base;

	public static void connectionBD() throws SQLException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			System.out.println("PB de driver");
			System.exit(2);
		}
		connexion_base = DriverManager.getConnection("jdbc:mysql://localhost/offres_stage","root","18011990");
		statement_base = connexion_base.createStatement();

	}
	
	public static void main(String[] args) {
		try{
			connectionBD();
		}catch(SQLException ee){
			System.err.println(ee.getMessage());
			System.exit(2);
		}
		
		try {
			 Connexion frame = new  Connexion();
		} catch(Exception e){
			 JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
