import java.util.Date;

import org.neo4j.graphdb.Label;



public enum TypeEvenement implements Label {
	FORMATION(null),
	PROMOTION(null),
	DATE(null),
	CAMPUS(null),
	AMPHITHEATRE("CM"),
	MAJ("Mise a Niveau"),
	TD("TD"),
	SORTIE("SORTIE"),
	CC("Controle"),
	PROJET("Projet"),
	TP("TP");
	
private String name;
	
	
TypeEvenement(String n)
	{
		setName(n);
	}



	


	
	public static final TypeEvenement createTypeEvenement(String type)
	{
		if(type.equals("CM"))
		{
			TypeEvenement typeCM = TypeEvenement.AMPHITHEATRE;
			return typeCM;
		}
		else if(type.equals("TD"))
		{
			TypeEvenement typeTD = TypeEvenement.TD;
			return typeTD;
		}
		
		else if(type.equals("TP"))
		{
			TypeEvenement typeTP = TypeEvenement.TP;
			return typeTP;
		}
		else if(type.equals("MAJ"))
		{
			TypeEvenement typeMAJ = TypeEvenement.MAJ;
			return typeMAJ;
		}
		else if(type.equals("CC"))
		{
			TypeEvenement typeControle = TypeEvenement.CC;
			return typeControle;
		}
		else if (type.equals("Controle"))
		{
			TypeEvenement typeControle = TypeEvenement.CC;
			return typeControle;
		}
		else if(type.equals("PROJET"))
		{
			TypeEvenement typeProjet = TypeEvenement.PROJET;
			return typeProjet;
		}
		else if(type.equals("Mise a Niveau"))
		{
			TypeEvenement typeMAN = TypeEvenement.MAJ;
			return typeMAN;
		}
		else
		{
			System.err.println("Erreur type :" + type + " non connue");
			return null;
		}
	}
	
	public static final TypeEvenement createTypeDifferents(String typeDifferents)
	{

		if(typeDifferents.equals("TiP"))
		{
			TypeEvenement typeTP = TypeEvenement.TP;
			return typeTP;
		}
		if(typeDifferents.equals("MAT1"))
		{
			TypeEvenement typeTP = TypeEvenement.TP;
			return typeTP;
		}
		if(typeDifferents.equals("PSVCM"))
		{
			TypeEvenement typeTP = TypeEvenement.AMPHITHEATRE;
			return typeTP;
		}
		if(typeDifferents.equals("TiD"))
		{
			TypeEvenement typeTP = TypeEvenement.TD;
			return typeTP;
		}
		if(typeDifferents.equals("TID"))
		{
			TypeEvenement typeTP = TypeEvenement.TD;
			return typeTP;
		}
		return null;
		
	}
	
	public static boolean typeDifferent(String type)
	{
		if(type.equals("TiP"))
		{
			return true;
		}
		if(type.equals("MAT1"))
		{
			return true;
		}
		if(type.equals("PSVCM"))
		{
			return true;
		}
		if(type.equals("TiD"))
			return true;
		if(type.equals("TID"))
			return true;
		return false;
	}



	public static final TypeEvenement createTypeEvenement(Date d)
	{
		TypeEvenement typeDate = TypeEvenement.DATE;
		typeDate.setName(d.toString());
		return typeDate;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString()
	{
		return name;
	}

}
