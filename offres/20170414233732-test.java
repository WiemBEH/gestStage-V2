import java.lang.String;
import java.lang.*;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
public class test{
	public static void ABRConstruite(String nomFic){
		try{
		BufferedReader entree = new BufferedReader(new FileReader(new File(nomFic)));


    		while (true) {
      			String laLigne = null;
      			try {
        			// on lit une ligne du fichier
        			laLigne = entree.readLine();
      			} catch (IOException e) {
        		// si cela se passe mal, on s'arrête.
        		System.err.println("# Erreur pendant la lecture de \""+ nomFic +"\".");
        		System.exit(1);
      			}
      			if (laLigne == null) {
        			// si la fin du fichier est atteinte, on sort de la boucle
        			break;
      			}
      			// On affiche la ligne sur la sortie standard, qui s'affiche
      			// normalement dans le terminal qui a servi à lancer l'exécution du
      			// programme.
      			System.out.print(laLigne);
    		} 
    } catch (FileNotFoundException e) {
      // si le fichier n'est pas trouvé, on s'arrête.
      System.err.println("# Erreur : impossible de lire \""+ nomFic +"\".");
      System.exit(1);
    }
		 

	}
	public static void main(String g[]){
		ABRConstruite("text");
		String ali = "ali";
		String a = "a";
		String l = "l";
		String i = "i";
		String wli = "wli";
		String w = "w";
		System.out.println(ali.compareTo(wli));  // donne -22
		System.out.println(ali.compareTo(" li"));  // donne 65
		String rien = null;
	}
}
