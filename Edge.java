// package src;

public class Edge{
	public Node from;
	public Node to;
	public int capacity;
	public boolean original=false; //part of the original graph?
	public Edge symetric; //reference to the reverse edge so you can update residual capacity in less time
	public Edge(Node from, Node to, int capacity) {
		this.from = from;
		this.to = to;
		this.capacity = capacity;
	}


}
