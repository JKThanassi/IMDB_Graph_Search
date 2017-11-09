import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Joseph Kaming-Thanassi
 * @version 11/8/2017
 */
public class IMDBMoviesGraph extends IMDBGraph {
    private class MovieNode implements Node{
        private String _title;
        private ArrayList<Node> _actorList;
        public MovieNode(String title){
            _title = title;
            _actorList = new Arraylist<Node>();
        }
        
        public Collection<? extends Node> getNeighbors(){
            return _actorList;
        }

        public String getName(){
            return _title;
        }
    }

    public IMDBMoviesGraph(String actorsFilename, String actressesFilename) throws IOException {
        super(actorsFilename, actressesFilename);
    }

    public Node getNodeByName(String name) {
        return null;
    }

    public Collection<? extends Node> getNodes() {
        return null;
    }
}
