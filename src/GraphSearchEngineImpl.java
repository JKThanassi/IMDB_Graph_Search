import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * @author Joseph Kaming-Thanassi
 * @version 11/8/2017
 */
public class GraphSearchEngineImpl implements GraphSearchEngine {
    private LinkedList<Node> _nodesToVisit;
    private ArrayList<Node> _visitedNodes, _backTrackList;
    private HashMap<Node,Node> _nodeVisitedBy; //key is a node and value is the node that was used to access the key node
    public GraphSearchEngineImpl(){
        _nodesToVisit = new LinkedList<Node>();
        _visitedNodes = new ArrayList<Node>();
        _backTrackList = new ArrayList<Node>();
        _nodeVisitedBy = new HashMap<Node, Node>();
    }

    /**
     * This function will find the shortest path from one node to another
     * @param s the start node.
     * @param t the target node.
     * @return A list of the elements in the shortest path from one node to another
     */
    public List<Node> findShortestPath(Node s, Node t) {
        //when they want to find a new path clear the lists
        _nodesToVisit.clear();
        _visitedNodes.clear();
        _backTrackList.clear();
        _nodeVisitedBy.clear();
        //if you are finding the shortest path from and to the same node just return the node
        if (s.equals(t)) {
            _backTrackList.add(s);
            return _backTrackList;
        }
        //add to nodes to vists and node visited by the starting value
        _nodesToVisit.addLast(s);
        _nodeVisitedBy.put(s, null);
        while(_nodesToVisit.size() > 0){
            final Node n = _nodesToVisit.removeFirst();
            _visitedNodes.add(n);

            for (Node neighbor : n.getNeighbors()){
                if (!_nodesToVisit.contains(neighbor) && !_visitedNodes.contains(neighbor)){
                    _nodesToVisit.addLast(neighbor);
                    _nodeVisitedBy.put(neighbor, n);

                    if (neighbor.equals(t)){
                        _backTrackList.add(neighbor);
                        return backTrack(neighbor);
                    }
                }
            }
        }
            return null;
    }

    /**
     * This function gives the list of nodes hopped through--on the shortest path--to get to the target node
     * The _nodeVisitedBy hashmap contains the node as a key and as a value the node used to access the key node
     * @param n the target node
     * @return A list of nodes hopped through to get to node n
     */
    private ArrayList<Node> backTrack(Node n){
        if(_nodeVisitedBy.get(n) != null){
            _backTrackList.add(0,_nodeVisitedBy.get(n));
            backTrack(_nodeVisitedBy.get(n));
        }
        return _backTrackList;
    }

}



