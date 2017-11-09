
import java.io.IOException;
import java.util.Collection;

/**
 * @author Joseph Kaming-Thanassi
 * @version 11/8/2017
 */
public class IMDBActorsGraph extends IMDBGraph{

    /**
     * This class implements the Node Interface and tailors it for actors
     */
    private class ActorNode implements Node{
        private String _name;
        private ArrayList<Node> _movieList;
        /**
         * @param name the name of the actor
         */
        public ActorNode(String name){
            _title = title;
            _actorList = new Arraylist<Node>();
        }
        /**
         * Gets the neighbors of the current node
         * @return this method returns the movies that the actor is in
         */
        public Collection<? extends Node> getNeighbors(){
            return _movieList;
        }
        /**
         * Getter for the name of the node
         * @return the name of the node
         */
        public String getName(){
            return _name;
        }
    }

    public IMDBActorsGraph(String actorsFilename, String actressesFilename) throws IOException {
        super(actorsFilename, actressesFilename);
    }

    protected void handleData(String actor, String movie) {
    	
    }

    public Node getNodeByName(String name) {
        return null;
    }


    public Collection<? extends Node> getNodes() {
        return null;
    }
}
