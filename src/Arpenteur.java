import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Traverser;

import api.requestApi.sendInfoHeure;


public class Arpenteur {

	/** The db. */
	private static GraphDatabaseService db;

	final static File path = new File(Chemin.graphFichier + Chemin.fichier);

	/** The tx. */
	@SuppressWarnings("unused")
	private static Transaction tx = null;

	public Arpenteur() {
		GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
		db = dbFactory.newEmbeddedDatabase(path);
	}


	
	public int getEtudiantPresentDansNoeud(Map<String, Object> map) {
		return Integer.parseInt((String) map.get("EtudiantPresent"));
	}

	public String getLocalisation(Map<String, Object> map) {
		return (String) map.get("localisation");
	}

	public String getType(Map<String, Object> map) {
		return (String) map.get("Name");

	}

	public String getHeureDebut(Map<String, Object> map) {

		return (String) map.get("Heure_debut");

	}

	public String getHeureFin(Map<String, Object> map) {
		return (String) map.get("Heure_fin");
	}

	public  void  searchInData() {
		try (Transaction tx = db.beginTx()) {
			ArrayList<Noeud> listeNoeud = new ArrayList<Noeud>();

			String requeteAPI = "[";
			System.out.println("chemin = " + Chemin.graphFichier);
			// On récupère le noeud correspondant a la promotion rehercher
			ResourceIterator<Node> listesDesNoeudsDates = db.findNodes(TypeEvenement.DATE);
			while (listesDesNoeudsDates.hasNext()) {
				Node noeudDate = listesDesNoeudsDates.next();

				String date = (String) noeudDate.getProperty("Name");
				System.out.println("date : " + date);
				for (Relationship relationFluxEntrant : noeudDate.getRelationships(Relation.CONTIENT,
						Direction.OUTGOING)) {
					Node noeudEntrant = relationFluxEntrant.getOtherNode(noeudDate);
					Map<String, Object>   typeRecuperer = noeudEntrant.getAllProperties();
					Long idNoeud = noeudEntrant.getId();
					String type = getType(typeRecuperer);
					String heureDebut = getHeureDebut(typeRecuperer);
					String heureFin = getHeureFin(typeRecuperer);
					String localisation = getLocalisation(typeRecuperer);
					int nombreEtudiant  = getEtudiantPresentDansNoeud(typeRecuperer);
					Noeud noeudCourant = new Noeud(idNoeud,type,date,heureDebut,heureFin,"1",localisation,nombreEtudiant,Chemin.fichier);
					listeNoeud.add(noeudCourant);


				}
				//Pour chaque noeud adjacent au noeud de dat
				for (Relationship fluxEntrant : noeudDate.getRelationships(Relation.CONTIENT, Direction.OUTGOING)) {

					Node nEntrant = fluxEntrant.getOtherNode(noeudDate);
					/*
					 * On récupère les labels du noeud
					 */

				
					TraversalDescription pathDescriptor = db.traversalDescription().breadthFirst()
							.relationships(Relation.CONTIENT, Direction.OUTGOING)
							.evaluator(Evaluators.excludeStartPosition());
					Traverser traverser = pathDescriptor.traverse(nEntrant);
					for (Node cours : traverser.nodes()) {
						Map<String, Object>   typeRecuperer = cours.getAllProperties();
						Long idNoeud = cours.getId();

						if(!Noeud.existInList(idNoeud,listeNoeud))
						{
							
						
						String heureDebut = getHeureDebut(typeRecuperer);
						String heureFin = getHeureDebut(typeRecuperer);
						String type = getType(typeRecuperer);
						String localisation = getLocalisation(typeRecuperer);
						int nombreEtudiant  = getEtudiantPresentDansNoeud(typeRecuperer);

						Noeud noeudCourant = new Noeud(idNoeud,type,date,heureDebut,heureFin,"0",localisation,nombreEtudiant,Chemin.fichier);
						listeNoeud.add(noeudCourant);
						}
					}
					}
				
				for(Noeud n :listeNoeud ) {
					requeteAPI += n.toJSON() +  ",";
				}
				listeNoeud.clear();
				
			}
			
			requeteAPI = requeteAPI.substring(0, requeteAPI.length() -1)+ "]";
			System.out.println("JSON : " +requeteAPI);
			tx.success();
			//ON envoie le tout via l'API
			sendInfoHeure.sendFluxToApi(requeteAPI, true);
		}
		
	}
}
