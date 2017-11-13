
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

/**
 * @author Joseph Kaming-Thanassi
 * @version 11/8/2017
 */
public class IMDBActorsGraph extends IMDBGraph{
    public IMDBActorsGraph(String actorsFilename, String actressesFilename) throws IOException {
    	super(actorsFilename, actressesFilename);
    }

    public Node getNodeByName(String name) {
        return actorMap.get(name);
    }


    public Collection<? extends Node> getNodes() {
    	return actorMap.values();
    }
}
