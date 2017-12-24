import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	

	   public static void main (String[] args){
		   boolean envoieToApi = true;
		   File[] liste = Fichier.findFilesRecursively();
		   ArrayList<String> listeFichier = Fichier.findFilesString();

		   System.out.println("taille : " + listeFichier.size());
		   Arpenteur arpenteur = new Arpenteur(listeFichier,30,35);
		   
		   arpenteur.run(envoieToApi);
		   }

}


/*

0,5 check
5,10
10,15
15,20
20,25
25,30
30,35



*/