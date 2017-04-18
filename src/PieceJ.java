 
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;

import org.apache.commons.io.FileUtils;



public class PieceJ extends JPanel implements ActionListener {
	String chemin_courant;
	JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
    String nom_fichier = "";
    String utilisateur = "";
    public PieceJ(){
    	this("entrepise");
    }
    public PieceJ(String utilsateur) {
        super(new BorderLayout());
        this.utilisateur = utilisateur;
        
    	try {
			chemin_courant = new java.io.File( "." ).getCanonicalPath();
		} catch (IOException e) {
			chemin_courant = "/home/ali/Wiem/Projet 2Bis";
			e.printStackTrace();
		}

        log = new JTextArea();
  //    log.setMargin(new Insets(5,2,5,52));
        log.setBackground(Color.GRAY);
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
        

        fc = new JFileChooser();
        String txtOpenButton = "Ajouter en PJ l";
        if (this.utilisateur.equals("etudiant"))
        	txtOpenButton += "e CV";
       	else
       		txtOpenButton += "'offre";
        openButton = new JButton(txtOpenButton);
        openButton.addActionListener(this);
 
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
 
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }
 
    public void actionPerformed(ActionEvent e) { 
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(PieceJ.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
            	File file = fc.getSelectedFile();
                nom_fichier = timeStamp+'-'+file.getName(); 
                
                try {
                	String rep = "/offres/";
                	if (this.utilisateur.equals("etudiant"))
                		rep = "/cvs/";
					File dest = new File(chemin_courant+rep+nom_fichier);
                	FileUtils.copyFile(file, dest);
                } catch (IOException ec) {
                    ec.printStackTrace();
                }
                log.append(file.getName() +" ajouté ! ");   
            }
        } 
    }
 

    private static void createAndShowGUI() {
 //        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
//        frame.add(new PieceJ());
 
//        frame.pack();
//        frame.setVisible(true);
    } 
    public static void main(String arg[]) {
    	Date d = new Date();
    	String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
    	System.out.println(timeStamp);
    }
}