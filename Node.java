// package src;

import java.util.ArrayList;

public class Node {
	public String label;
	public ArrayList<Edge> adjacentNodes; //storing edges going out of this node
	public boolean is_visited=false;
	public Node(String label) {
		this.label = label;
		this.adjacentNodes = new ArrayList<Edge>();
	}

	// add edge to the adjacentNodes
	public void add(Node neighborNode, int capacity) {
		Edge original_edge = new Edge(this, neighborNode, capacity);
		this.adjacentNodes.add(original_edge);
		original_edge.original=true; //original edge in the graph not a reverse edge
		//create reverse edges with start capacity=0
		if(! this.label.equals("KL") && ! this.label.equals("s"))
		original_edge.symetric = Graph.nodes[Graph.index_node(neighborNode.label)].add_reverse(this, original_edge);

	}

	public Edge add_reverse(Node neighborNode, Edge SymetricEdge) {
		Edge reverse = new Edge(this, neighborNode, 0);
		reverse.symetric = SymetricEdge;
		this.adjacentNodes.add(reverse);
		return reverse;
	}

}
