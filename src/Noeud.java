import java.util.ArrayList;

import com.google.gson.Gson;

public class Noeud {
	private long id;
	private String type;
	private String date;
	private String fichier;
	private String heureDebut;
	private String heureFin;
	private String premierCours;
	private String localisation;
	private int nombreEtudiant;
	private String mdp;
	
	
	public Noeud(long id,String type,String date,String heureDebut,String heureFin,String premierCours,
			String localisation,int nombreEtudiant,String fichier)
	{
		this.id = id;
		this.type = type;
		this.date = date;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.premierCours = premierCours;
		this.localisation= localisation;
		this.nombreEtudiant = nombreEtudiant;
		this.fichier = fichier;
		this.mdp = MotDePasseBDD.mdp;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}
	public String getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}
	public String isPremierCours() {
		return premierCours;
	}
	public void setPremierCours(String premierCours) {
		this.premierCours = premierCours;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	
	public static final boolean existInList(long id,ArrayList<Noeud> listeNoeud)
	{
		for(Noeud n : listeNoeud)
		{
			if (n.getId() == id)
				return true;
		}
		return false;
	}
	
	public int getNombreEtudiant() {
		return nombreEtudiant;
	}
	public void setNombreEtudiant(int nombreEtudiant) {
		this.nombreEtudiant = nombreEtudiant;
	}
	public String toString()
	{
		String retour =  "date du :" + date + "premierCours ?  :" + premierCours + "\n";
		retour += "heureDebut : " + heureDebut + " heureFin  "+ heureFin+ "\n";
		retour += "type = " + type + " nombre etudiant  " + nombreEtudiant;
		return retour;
	}
	
	
	public String toJSON()
	{
		Gson g = new Gson();
		String sortie = g.toJson(this);
		return sortie;
	}
	public String getFichier() {
		return fichier;
	}
	public void setFichier(String fichier) {
		this.fichier = fichier;
	}
}
