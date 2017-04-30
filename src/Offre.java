import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import javax.swing.text.NumberFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


public class Offre extends JFrame implements ActionListener {
	
	  private String mail_entreprise;
	
	  JButton validation_button, retour_button;
	  JPanel panel;
	  JLabel libelle_label, debut_label, description_label, duree_label;
	  final JTextField  libelle_field;
	  JTextArea description_field;
	  SpinnerDateModel debut_field;
	  JDatePickerImpl datePicker;
	  JFormattedTextField duree_field;
	  PieceJ PJ;
	
	  Domaine domaines;
	  
	public Offre(String mail_entreprise) {
		  this.mail_entreprise = mail_entreprise;		  
		  
		  domaines = new Domaine();
		  
		  validation_button = new JButton("Sauvegarder l'offre");
		  validation_button.addActionListener(this);
		  retour_button = new JButton("Sauvegarder l'offre");
		  retour_button.addActionListener(this);
		  
		  libelle_label = new JLabel("Libellé de l'offre ");
		  libelle_field = new JTextField(15);
	 
		  debut_label = new JLabel("Date de début du stage ");
		  
		  UtilDateModel model = new UtilDateModel();
		  Properties p = new Properties();
		  p.put("text.jour", "Jour");
		  p.put("text.mois", "Mois");
		  p.put("text.annee", "Annee");
		  JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		  datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		   
		  duree_label = new JLabel("Duree ");
		  NumberFormat intFormat = NumberFormat.getIntegerInstance();
		  NumberFormatter numberFormatter = new NumberFormatter(intFormat);
		  numberFormatter.setValueClass(Integer.class); 
		  numberFormatter.setAllowsInvalid(false);
		  numberFormatter.setMinimum(0);
		  duree_field = new JFormattedTextField(numberFormatter);
		  
		  description_label = new JLabel("Descriptif de l'offre ");
		  description_field = new JTextArea();
		  description_field.setPreferredSize(new Dimension(100, 100));
		  JScrollPane scrollPane = new JScrollPane(description_field);
		   
		  panel = new JPanel(new GridBagLayout()); //new GridLayout(4,4)
		  GridBagConstraints gbc = new GridBagConstraints();
		  gbc.fill = GridBagConstraints.HORIZONTAL;
		  gbc.gridx = 0;
		  gbc.gridy = 0;	
		  gbc.gridwidth = 2;
		  panel.add(retour_button, gbc);
		  retour_button.hide();
		  gbc.gridwidth = 1;
		  gbc.gridy = 1;		  
		  panel.add(new JLabel("Domaine de l'offre "), gbc);
		  gbc.gridx = 1;
		  gbc.gridy = 1;
		  panel.add(domaines, gbc);
		  gbc.gridx = 0;
		  gbc.gridy = 2;
//		  panel.getContentPane().setLayout(new BoxLayout(panel.getContentPane(), BoxLayout.Y_AXIS));
		  panel.add(libelle_label, gbc);
		  gbc.gridx = 1;
		  panel.add(libelle_field, gbc);
		  gbc.gridx = 0;
		  gbc.gridy = 3;
		  panel.add(debut_label, gbc);
//		  panel.add(debut_field);
		  gbc.gridx = 1;
		  panel.add(datePicker, gbc);
		  gbc.gridx = 0;
		  gbc.gridy = 4;
		  panel.add(duree_label, gbc);
		  gbc.gridx = 1;
		  panel.add(duree_field, gbc);
		  gbc.gridx = 0;
		  gbc.gridy = 5;
		  gbc.gridwidth = 2;
		  PJ = new PieceJ(); 
		  panel.add(PJ, gbc);
		  gbc.gridy = 6;
		  panel.add(description_label, gbc);
		  gbc.gridy = 7;
		  //gbc.gridheight = 5;
		  panel.add(scrollPane, gbc);
		  gbc.gridy = 8;
		  gbc.gridheight = 1;
		  panel.add(validation_button, gbc);
		  add(panel,BorderLayout.CENTER);
		  setTitle("Saisir Offre");		
		  //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == retour_button){
			this.setVisible(false);
			(new Entreprise(this.mail_entreprise, null, null, null, null, null, null)).setVisible(true);
		}		
		if (e.getSource() == validation_button){
			if (libelle_field.getText().isEmpty())
				JOptionPane.showMessageDialog(this,"Le libellé de l'offre est obligatoire", "Erreur",JOptionPane.ERROR_MESSAGE);
			else if (datePicker.getJFormattedTextField().getText().isEmpty())
				JOptionPane.showMessageDialog(this,"La date du début de stage est obligatoire", "Erreur",JOptionPane.ERROR_MESSAGE);
			else if (duree_field.getText().equals(""))
				JOptionPane.showMessageDialog(this,"La durée du stage est obligatoire", "Erreur",JOptionPane.ERROR_MESSAGE);
			else if (PJ.nom_fichier.isEmpty())
				JOptionPane.showMessageDialog(this,"La pièce jointe de l'offre est obligatoire", "Erreur",JOptionPane.ERROR_MESSAGE);
			else if (description_field.getText().isEmpty())
				JOptionPane.showMessageDialog(this,"La description de l'offre est obligatoire", "Erreur",JOptionPane.ERROR_MESSAGE);
			else {
				System.out.println("INSERT INTO offre_stage (entreprise, domaine, libelle, date_debut, duree, chemin_stockage, descriptif) "
						+"VALUES ('"+mail_entreprise+"','"+domaines.getSelectedItem()+"','"+libelle_field.getText()+"','"+datePicker.getJFormattedTextField().getText()+"','"+duree_field.getText()+"','"+PJ.nom_fichier+"','"+description_field.getText()+"')");
				
				try {
					String req = "INSERT INTO offre_stage (entreprise, domaine, libelle, date_debut, duree, chemin_stockage, descriptif) "
							+"VALUES ('"+mail_entreprise+"','"+domaines.getSelectedItem()+"','"+libelle_field.getText()+"','"+ datePicker.getJFormattedTextField().getText()+"','"+duree_field.getText()+"','"+PJ.nom_fichier+"','"+description_field.getText()+"')";
					Application.statement_base.executeUpdate(req);
					JOptionPane.showMessageDialog(this,"Offre sauvegardée", "",JOptionPane.INFORMATION_MESSAGE);
					this.setVisible(false);
					(new Entreprise(this.mail_entreprise, null, null, null, null, null, null)).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
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
			Offre frame = new  Offre("aliabir_35@hotmail.com");
			 frame.pack();
			//frame.setSize(500,100);
			frame.setVisible(true);
		} catch(Exception e){
			 JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
}
