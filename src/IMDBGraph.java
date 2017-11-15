import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Joseph Kaming-Thanassi
 * @version 11/8/2017
 * This class contains common functionality of the actorGraph and movieGraph classes.
 */
public abstract class IMDBGraph implements Graph {

	/**
	 * This class implements the Node interface and adds specific functionality tailored specifically to Actors
	 */
	protected class ActorNode implements Node {

		String name;
		ArrayList<MovieNode> movies;
		
		protected ActorNode(String name) {
			this.name = name;
			movies = new ArrayList<MovieNode>();
		}

		/**
		 * This function adds movies to the actor node
		 * @param movieNode the movie that the actor appeared in
		 */
		private void addMovie(MovieNode movieNode) {
			movies.add(movieNode);
		}

		/**
		 * This is a getter for the name of the actor
		 * @return the name of the actor
		 */
		public String getName() {
			return name;
		}

		/**
		 * This function returns all of the movies that an actor has been in
		 * @return a list of movie nodes
		 */
		public Collection<? extends Node> getNeighbors() {
			return movies;
		}
		
	}

	/**
	 * This class implements the Node interface and adds specific functionality tailored specifically to movies
	 */
	protected class MovieNode implements Node {

		String name;
		ArrayList<ActorNode> cast;
		
		protected MovieNode(String name) {
			this.name = name;
			cast = new ArrayList<ActorNode>();
		}

		/**
		 * This fucntion adds a actor that appeared in a movie to the specific movie node
		 * @param actorNode the actor that appeared in the movie represented by this node
		 */
		private void addPersonel(ActorNode actorNode) {
			cast.add(actorNode);
		}

		/**
		 * a getter for the name of the movie
		 * @return the name of the movie
		 */
		public String getName() {
			return name;
		}

		/**
		 * This function returns all of the actors that are in a given movie
		 * @return a list of cast members
		 */
		public Collection<? extends Node> getNeighbors() {
			return cast;
		}
		
	}
	
	protected HashMap<String, ActorNode> _actorMap;
	protected HashMap<String, MovieNode> _movieMap;
	
	
    public IMDBGraph(String actorsFilename, String actressesFilename) throws IOException {
    	//Load all of the data and then use internal methods to store it into the nodes
    	_actorMap = new HashMap<String, ActorNode>();
    	_movieMap = new HashMap<String, MovieNode>();
    	readData(actorsFilename);
    	readData(actressesFilename);
    }

	/**
	 * This function is responsible for the parsing of the IMDB database files
	 * @param filename the path to the IMDB database on your machine
	 * @throws IOException
	 */
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
					//System.out.println(scanner.nextLine());
    				isAtCopyright = false;
    			}
    		}
    		else if (!line.contains("\"") && !line.contains("(TV)")) {
    			int firstTabIndex = line.indexOf("\t");
    			int lastTabIndex = line.lastIndexOf("\t");
    			int endIndex = line.indexOf(")");
	    		String movie = "";
	    		
	    		//If the actor is different, change it
    			if (firstTabIndex > 0)
					lastActor = line.substring(0, firstTabIndex);
				if (lastTabIndex > 0 && endIndex > 0 && endIndex > lastTabIndex)
					movie = line.substring(lastTabIndex + 1, endIndex + 1);
	    		//If there are two in the array, then there is a new actor
	    		//If there is only one, then the actor continues
				if (movie != "")
	    			handleData(lastActor, movie);
    		}
    	}
    }

	/**
	 * This function is responsible for taking the data parsed by readData and sorting it into the correct nodes
	 * @param actor The name of the actor
	 * @param movie The name of the movie
	 */
	private void handleData(String actor, String movie) {
    	ActorNode actorNode;
    	MovieNode movieNode;
    	
    	if (!_actorMap.containsKey(actor)) {
    		actorNode = new ActorNode(actor);
    		_actorMap.put(actor, actorNode);
    	}
    	else
    		actorNode = _actorMap.get(actor);
    	
    	if (!_movieMap.containsKey(movie)) {
    		movieNode = new MovieNode(movie);
    		_movieMap.put(movie, movieNode);
    	}
    	else
    		movieNode = _movieMap.get(movie);
    	
    	actorNode.addMovie(movieNode);
    	movieNode.addPersonel(actorNode);
    }
}
