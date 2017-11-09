
import java.io.IOException;
import java.util.Collection;

/**
 * @author Joseph Kaming-Thanassi
 * @version 11/8/2017
 */
public class IMDBActorsGraph extends IMDBGraph{
    private class ActorNode implements Node{
        private String _name;
        private ArrayList<Node> _movieList;
        public MovieNode(String title){
            _title = title;
            _actorList = new Arraylist<Node>();
        }
        
        public Collection<? extends Node> getNeighbors(){
            return _movieList;
        }

        public String getName(){
            return _name;
        }
    }

    public IMDBActorsGraph(String actorsFilename, String actressesFilename) throws IOException {
        super(actorsFilename, actressesFilename);
    }

    public Node getNodeByName(String name) {
        return null;
    }


    public Collection<? extends Node> getNodes() {
        return null;
    }
}
