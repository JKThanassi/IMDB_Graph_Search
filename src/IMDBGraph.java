import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Joseph Kaming-Thanassi
 * @version 11/8/2017
 */
public abstract class IMDBGraph implements Graph {
	
	protected class ActorNode implements Node {

		String name;
		ArrayList<MovieNode> movies;
		
		protected ActorNode(String name) {
			this.name = name;
			movies = new ArrayList<MovieNode>();
		}
		
		private void addMovie(MovieNode movieNode) {
			movies.add(movieNode);
		}
		
		public String getName() {
			return name;
		}

		public Collection<? extends Node> getNeighbors() {
			return movies;
		}
		
	}
	
	protected class MovieNode implements Node {

		String name;
		ArrayList<ActorNode> cast;
		
		protected MovieNode(String name) {
			this.name = name;
			cast = new ArrayList<ActorNode>();
		}
		
		private void addPersonel(ActorNode actorNode) {
			cast.add(actorNode);
		}
		
		public String getName() {
			return name;
		}

		public Collection<? extends Node> getNeighbors() {
			return cast;
		}
		
	}
	
	protected HashMap<String, ActorNode> actorMap;
	protected HashMap<String, MovieNode> movieMap;
	
	
    public IMDBGraph(String actorsFilename, String actressesFilename) throws IOException {
    	//Load all of the data and then use internal methods to store it into the nodes
    	actorMap = new HashMap<String, ActorNode>();
    	movieMap = new HashMap<String, MovieNode>();
    	readData(actorsFilename);
    	readData(actressesFilename);
    }
    
    private void readData(String filename) throws IOException {
    	Scanner scanner = new Scanner(new File(filename), "ISO-8859-1");
    	
	    //This will be true until we reach the beginning of the actual actor list
	    //Maybe we could just literally count how many lines to skip
	    boolean isAtCopyright = true;
	    String lastActor = "";
    	while (scanner.hasNextLine()) {
    		String line = scanner.nextLine();
    		if (isAtCopyright) {
    			if (line.contains("THE ACTORS LIST") || line.contains("THE ACTRESSES LIST")) {
    				scanner.nextLine();
    				scanner.nextLine();
    				scanner.nextLine();
    				scanner.nextLine();
    				isAtCopyright = false;
    			}
    		}
    		else if (!line.contains("\"") && !line.contains("(TV)")) {
    			int firstTabIndex = line.indexOf("\t");
    			int lastTabIndex = line.lastIndexOf("\t");
    			int endIndex = line.indexOf(")");
	    		String movie = "";
	    		
	    		//If the actor is different, change it
    			if (firstTabIndex != 0)
    				lastActor = line.substring(0, firstTabIndex);
    			
	    		movie = line.substring(lastTabIndex + 1, endIndex + 1);
	    		//If there are two in the array, then there is a new actor
	    		//If there is only one, then the actor continues
	    		handleData(lastActor, movie);
    		}
    	}
    }
    
    private void handleData(String actor, String movie) {
    	ActorNode actorNode;
    	MovieNode movieNode;
    	
    	if (!actorMap.containsKey(actor)) {
    		actorNode = new ActorNode(actor);
    		actorMap.put(actor, actorNode);
    	}
    	else
    		actorNode = actorMap.get(actor);
    	
    	if (!movieMap.containsKey(movie)) {
    		movieNode = new MovieNode(movie);
    		movieMap.put(movie, movieNode);
    	}
    	else
    		movieNode = movieMap.get(movie);
    	
    	actorNode.addMovie(movieNode);
    	movieNode.addPersonel(actorNode);
    }
}
