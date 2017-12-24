import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	

	   public static void main (String[] args){
		   boolean envoieToApi = true;
		   File[] liste = Fichier.findFilesRecursively();
		   ArrayList<String> listeFichier = Fichier.findFilesString();

		   System.out.println("taille : " + listeFichier.size());
		   Arpenteur arpenteur = new Arpenteur(listeFichier,0,1);
		   
		   arpenteur.run(envoieToApi);
		   }

}
