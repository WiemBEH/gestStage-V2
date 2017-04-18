import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class Utilisateur extends JFrame implements ActionListener{
  	private String mail;
  	private String nom;
  	private String telephone;
	
	public Utilisateur(String mail, String nom, String tel) {
		this.mail = mail;
		this.nom = nom;
		this.telephone = tel;
	}
	
	String getMail(){
		return this.mail;
	}

	String getNom(){
		return this.nom;
	}
	
	String getTelephone(){
		return this.telephone;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}	
}
