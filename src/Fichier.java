import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Fichier {

	
	public static final  File[] findFilesRecursively() {
		File repertoire = new File(Chemin.graphFichier);
		
		File[] files=repertoire.listFiles();
	    return files;
	}
	
	public static final ArrayList<String> findFilesString()
	{
		File[] fichiers =  findFilesRecursively();
		ArrayList<String> listeFichier = new ArrayList<String>();
		for(File f : fichiers )
		{
			listeFichier.add(f.getAbsolutePath());
		}
		Collections.sort(listeFichier);	
		return listeFichier;

	}
}
