import java.io.IOException;
import java.util.Collection;

/**
 * @author Joseph Kaming-Thanassi
 * @version 11/8/2017
 */
public class IMDBMoviesGraph extends IMDBGraph {
    public IMDBMoviesGraph(String actorsFilename, String actressesFilename) throws IOException {
    	super(actorsFilename, actressesFilename);
    }

    public Node getNodeByName(String name) {
        return movieMap.get(name);
    }

    public Collection<? extends Node> getNodes() {
        return movieMap.values();
    }
}
