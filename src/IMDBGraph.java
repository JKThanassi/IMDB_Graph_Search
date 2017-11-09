import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Joseph Kaming-Thanassi
 * @version 11/8/2017
 */
public abstract class IMDBGraph implements Graph {
    public IMDBGraph(String actorsFilename, String actressesFilename) throws IOException {
    	//Load all of the data and then use internal methods to store it into the nodes
    	readData(actorsFilename);
    	readData(actressesFilename);
    }
    
    private void readData(String filename) throws IOException {
    	Scanner scanner = new Scanner(new File(filename), "ISO-8859-1");
    	
	    //This will be true until we reach the beginning of the actual actor list
	    //Maybe we could just literally count how many lines to skip
	    boolean isAtCopyright = true;
    	while (scanner.hasNextLine()) {
    		String line = scanner.nextLine();
    		String actor = "", movie = "";
    		//removes whitespace before and after a string
    		String[] piecesOfData = line.split("\t");
    		if (piecesOfData.length == 1) {
    			//only movie name
    		}
    		else if (piecesOfData.length == 2) {
    			//actor and movie name
    		}
    		line.trim();
    		//If there are two in the array, then there is a new actor
    		//If there is only one, then the actor continues
    		handleData(actor, movie);
    	}
    }
    
    protected abstract void handleData(String actor, String movie);
}
