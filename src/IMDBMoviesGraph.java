import java.io.IOException;
import java.util.Collection;

/**
 * @author Joseph Kaming-Thanassi
 * @version 11/8/2017
 * This class extends IMDBGraph and provides functionality specific to movie nodces
 */
public class IMDBMoviesGraph extends IMDBGraph {
    public IMDBMoviesGraph(String actorsFilename, String actressesFilename) throws IOException {
    	super(actorsFilename, actressesFilename);
    }

    /**
     * This function will get a node of the name that is passed in
     * @param name the name of the requested Node
     * @return the node containing the name passed in
     */
    public Node getNodeByName(String name) {
        return _movieMap.get(name);
    }

    /**
     * This function returns all movie nodes
     * @return a list of all movie nodes
     */
    public Collection<? extends Node> getNodes() {
        return _movieMap.values();
    }
}
