
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

/**
 * @author Joseph Kaming-Thanassi
 * @version 11/8/2017
 * This class extends IMDBGraph and provides functionality specific to actor nodces
 */
public class IMDBActorsGraph extends IMDBGraph{
    public IMDBActorsGraph(String actorsFilename, String actressesFilename) throws IOException {
    	super(actorsFilename, actressesFilename);
    }

    /**
     * @param name the name of the requested Node
     * @return The node with the corresponding name from the actormap
     */
    public Node getNodeByName(String name) {
        return _actorMap.get(name);
    }

    /**
     * This Function gets all actor nodes fom the actor map
     * @return list of movie nodes
     */
    public Collection<? extends Node> getNodes() {
    	return _actorMap.values();
    }
}
