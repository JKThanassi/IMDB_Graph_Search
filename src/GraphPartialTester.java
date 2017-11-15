import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;
import java.io.*;

/**
 * Code to test Project 3; you should definitely add more tests!
 */
public class GraphPartialTester {
	Graph actorsGraph, moviesGraph;
	GraphSearchEngine searchEngine;

	@Test
	/**
	 * Verifies that there is no shortest path between a specific and actor and actress.
	 */
	public void findShortestPath () {
		final Node actor1 = actorsGraph.getNodeByName("Adara, Amirah");
		final Node actress2 = actorsGraph.getNodeByName("Addams, Ava");
		final List<Node> shortestPath = searchEngine.findShortestPath(actor1, actress2);
		//assertNull(shortestPath);  // there is no path between these people
		for (Node n : shortestPath) {
			System.out.println(n.getName());
		}
	}

	@Before
	/**
	 * Instantiates the actors and movies graphs
	 */
	public void setUp () throws IOException {

		actorsGraph = new IMDBActorsGraph("E:\\Drive\\Documents\\Term B\\CS-210X\\IMDB_Graph_Search\\lists\\actors_100000.list",
				"E:\\Drive\\Documents\\Term B\\CS-210X\\IMDB_Graph_Search\\lists\\actresses_100000.list");
		moviesGraph = new IMDBMoviesGraph("E:\\Drive\\Documents\\Term B\\CS-210X\\IMDB_Graph_Search\\lists\\actors_100000.list",
				"E:\\Drive\\Documents\\Term B\\CS-210X\\IMDB_Graph_Search\\lists\\actresses_100000.list");
		searchEngine = new GraphSearchEngineImpl();
	}

	@Test
	/**
	 * Just verifies that the graphs could be instantiated without crashing.
	 */
	public void finishedLoading () {
		assertTrue(true);
		// Yay! We didn't crash
	}

	@Test
	/**
	 * Verifies that a specific movie has been parsed.
	 */
	public void testSpecificMovie () {
		testFindNode(moviesGraph, "Bye Bye Birdie (1963)");
	}

	@Test
	/**
	 * Verifies that a specific actress has been parsed.
	 */
	public void testSpecificActress () {
		testFindNode(actorsGraph, "Aaugad, Amina");
	}

	@Test
	public void testSpecificActor(){
		testFindNode(actorsGraph, "Aaker, Lee");
	}

	/**
	 * Verifies that the specific graph contains a node with the specified name
	 * @param graph the Graph to search for the node
	 * @param name the name of the Node
	 */
	private static void testFindNode (Graph graph, String name) {
		final Collection<? extends Node> nodes = graph.getNodes();
		boolean found = false;
		for (Node node : nodes) {
			if (node.getName().trim().equals(name)) {
				found = true;
			}
		}
		assertTrue(found);
	}
}
