import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


public class AjoutUtilisateur  extends JPanel  implements ActionListener {
	String type;

	JComboBox<String> droitCombo;
	JTextField nomField = new JTextField();
	Domaine domaineCombo;
	JTextField mailField = new JTextField();
	JTextField mdpField = new JPasswordField();
	JTextField mdpCField = new JPasswordField();
	JDatePickerImpl datePicker;
	JFormattedTextField numField;
	JTextField adresseRueField = new JTextField();
	JTextField adresseVilleField = new JTextField();
	JTextField adresseCodePostalField = new JTextField();
	
	JLabel domaineLabel = new JLabel("Domaine : ");
	JLabel naissanceLabel = new JLabel("Date de naissance : ");
	JLabel adresseLabel = new JLabel("Adresse  ");
	JLabel rueLabel = new JLabel("Rue : ");
	JLabel villeLabel = new JLabel("Ville : ");		
	JLabel postalLabel = new JLabel("Code Postal : ");	
	GridBagConstraints gbc = new GridBagConstraints();
	
	JButton validateButton = new JButton("Valider");
	
	public AjoutUtilisateur() {
		super(new GridBagLayout()); 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;	
		//gbc.gridwidth = 2;
		
		this.type = "entreprise";

		validateButton.addActionListener(this);
		
		droitCombo = new JComboBox<String>();
		droitCombo.addItem("entreprise");
		droitCombo.addItem("etudiant");
		droitCombo.addActionListener(this);
		
		domaineCombo = new Domaine();
			
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.jour", "Jour");
		p.put("text.mois", "Mois");
		p.put("text.annee", "Annee");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
	    NumberFormat intFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(intFormat);
		numberFormatter.setValueClass(Integer.class); 
		numberFormatter.setAllowsInvalid(false);
		numberFormatter.setMinimum(0);
		numField = new JFormattedTextField(numberFormatter);
		
		add(new JLabel("Vous êtes : "),gbc);
		gbc.gridx = 1;
		add(droitCombo,gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		add(new JLabel("* Nom : "),gbc);
		gbc.gridx = 1;
		add(nomField,gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		add(domaineLabel,gbc);
		gbc.gridx = 1;
		add(domaineCombo,gbc);
		if (type.equals("etudiant")) {			
			domaineLabel.hide(); domaineCombo.hide();
		}
		gbc.gridx = 0;
		gbc.gridy++;
		add(new JLabel("* Mail : "),gbc);
		gbc.gridx = 1;
		add(mailField,gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		add(new JLabel("* Mot de passe : "),gbc);
		gbc.gridx = 1;
		add(mdpField,gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		add(new JLabel("* Mot de passe : "),gbc);
		gbc.gridx = 1;
		add(mdpCField,gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		add(naissanceLabel,gbc);
		gbc.gridx = 1;
		add(datePicker,gbc);
		if (type.equals("entreprise")) {	
			naissanceLabel.hide();
			datePicker.hide();
		}	
		gbc.gridx = 0;
		gbc.gridy++;
		add(new JLabel("Téléphone : "),gbc);
		gbc.gridx = 1;
		add(numField,gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		add(adresseLabel,gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		add(rueLabel,gbc);
		gbc.gridx = 1;
		add(adresseRueField,gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		add(villeLabel,gbc);	
		gbc.gridx = 1;
		add(adresseVilleField,gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		add(postalLabel,gbc);
		gbc.gridx = 1;
		add(adresseCodePostalField,gbc);
		if (type.equals("etudiant")) {	
			adresseLabel.hide();
			rueLabel.hide();
			adresseRueField.hide();
			villeLabel.hide();
			adresseVilleField.hide();
			postalLabel.hide();
			adresseCodePostalField.hide();
		}
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		add(validateButton,gbc);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == droitCombo) {
			boolean visible = true;
			this.type = "entreprise";
			if (droitCombo.getSelectedItem().equals("etudiant")){
				visible = false;
				this.type = "etudiant";
			}	
			
			naissanceLabel.setVisible(!visible);
			datePicker.setVisible(!visible);
			
			domaineLabel.setVisible(visible);
			domaineCombo.setVisible(visible);
			adresseLabel.setVisible(visible);
			rueLabel.setVisible(visible);
			adresseRueField.setVisible(visible);
			villeLabel.setVisible(visible);		
			adresseVilleField.setVisible(visible);
			postalLabel.setVisible(visible);		
			adresseCodePostalField.setVisible(visible);
			
			revalidate();
			repaint();
		}	
		if (e.getSource() == validateButton){
			if (nomField.getText().equals(""))
				JOptionPane.showMessageDialog(this,"Le nom est obligatoire", "Erreur",JOptionPane.ERROR_MESSAGE);
			else if (mailField.getText().equals(""))
				JOptionPane.showMessageDialog(this,"Le mail est obligatoire", "Erreur",JOptionPane.ERROR_MESSAGE);
			else if (mdpField.getText().equals("") || mdpField.getText().equals(""))
				JOptionPane.showMessageDialog(this,"Le mot de passe est obligatoire", "Erreur",JOptionPane.ERROR_MESSAGE);
			else if (!mdpField.getText().equals(mdpCField.getText()))
				JOptionPane.showMessageDialog(this,"Le mot de passe n'est pas confirmé", "Erreur",JOptionPane.ERROR_MESSAGE);
			else {
				String req = "";
				if (droitCombo.getSelectedItem().equals("entreprise"))
					req = "INSERT INTO utilisateur (mail, mot_de_passe, droit, nom, telephone, adresseRue, adresseVille, adresseCodePostal, secteurActivite) "
						+"VALUES ('"+mailField.getText()+"','"+mdpField.getText()+"','Entreprise','"+nomField.getText()+"','"+numField.getText()+"" +
								"','"+adresseRueField.getText()+"','"+adresseVilleField.getText()+"','"+adresseCodePostalField.getText()+"','"+domaineCombo.getSelectedItem()+"')";
				else
					req = "INSERT INTO utilisateur (mail, mot_de_passe, droit, nom, telephone, date_naissance, secteurActivite) "
							+"VALUES ('"+mailField.getText()+"','"+mdpField.getText()+"','Etudiant','"+nomField.getText()+"','"+numField.getText()+"" +
									"','"+datePicker.getJFormattedTextField().getText()+"','"+domaineCombo.getSelectedItem()+"')";
				System.out.println(req);
				try {
					Application.statement_base.executeUpdate(req);
					JOptionPane.showMessageDialog(this,"Inscription réussie", "",JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(this,"Inscription échouée", "Erreur",JOptionPane.ERROR_MESSAGE);
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
			JFrame f = new JFrame ();
			f.add(new AjoutUtilisateur());
			f.setVisible(true);
			f.setSize(500,500);
		}catch(SQLException ee){
			System.err.println(ee.getMessage());
			System.exit(2);
		}			
	}
}
