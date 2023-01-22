// package src;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Graph {
	static int cities_num;
	static int ver_num; // length of the nodes array
	static Node[] nodes; // all nodes of the graphs stored like this: imaginary source s, r0, ..,r5, c0,
							// c1, ...., KL

	public Graph(int cnum) {
		cities_num = cnum;
		ver_num = cities_num + 8;
		nodes = new Node[ver_num];
		// create imaginary source
		nodes[0] = new Node("s");
		// create 6 regions nodes
		for (int i = 0; i < 6; i++) {
			nodes[i + 1] = new Node("r" + i);
		}
		// create cities nodes
		for (int i = 0; i < cities_num; i++) {
			nodes[i + 7] = new Node("c" + i);
		}
		// create king land
		nodes[cities_num + 7] = new Node("KL");

	}

	// return the index of the node that has this label, so you can access the node
	static int index_node(String label) {
		Character check = label.charAt(0);
		int index_of_neighborNode = 0; // default imaginary source s
		// region
		if (check.equals('r')) {
			int i = label.charAt(1) - '0';
			index_of_neighborNode = i + 1;

		}
		// city
		else if (check.equals('c')) {
			String str= label.substring(1, label.length()) ;
			int i=Integer.valueOf(str);
			index_of_neighborNode = i + 7;
		}
		// King land
		else {
			index_of_neighborNode = cities_num + 7;
		}
		return index_of_neighborNode;
	}

	// find new possible augmenting path
	private boolean bfs(Edge parent[]) {

		int visited[] = new int[ver_num]; // start initial value of zero for all nodes
		Node s = nodes[0]; // imaginary source
		LinkedList<Node> queue = new LinkedList<Node>();
		// add the imaginary source to the queue
		queue.add(s);
		visited[0] = 1;
		parent[0] = null;
		// while there are nodes to visit
		while (!queue.isEmpty()) {
			Node u = queue.poll();
			// traverse through edges going out of u
			for (Edge e : u.adjacentNodes) {

				int idx = index_node(e.to.label);// index of the node we are going to
				if (visited[idx] == 0 && e.capacity > 0) { // if it not visited and capacity>0
					if (e.to.label.equals("KL")) { // reached king land DONE
						parent[idx] = e;
						return true;
					}

					queue.add(e.to); // to see in the upcoming iterations
					parent[idx] = e; // store that edge that led to this node in the corresponding index
					visited[idx] = 1; // mark as visited

				}
			}
		}
		return false;
	}

	// which nodes are reachable by the source in the residual graph
	private static void bfs_reachable() {

		LinkedList<Node> queue = new LinkedList<Node>();
		nodes[0].is_visited = true; // source
		queue.add(nodes[0]);
		// while there are nodes to visit
		while (!queue.isEmpty()) {
			Node u = queue.poll();
			// traverse through edges going out of u
			for (Edge e : u.adjacentNodes) {
				if (e.to.is_visited == false && e.capacity > 0) {
					queue.add(e.to); // to see in the upcoming iterations
					e.to.is_visited = true; // mark as visited

				}
			}
		}
	}

	public int ford() {
		Node u, v;
		Node s = nodes[0]; // imaginary source
		Node t = nodes[ver_num - 1]; // king land
		Edge[] parent = new Edge[ver_num];
		int max_flow = 0;
		// as long as we have new possible augmenting paths
		while (bfs(parent)) {
			// set path flow to infinity
			int path_flow = Integer.MAX_VALUE;
			// update path flow
			for (v = t; !v.label.equals("s"); v = parent[index_node(v.label)].from) {
				int cap_u_to_v = parent[index_node(v.label)].capacity;
				path_flow = Math.min(path_flow, cap_u_to_v);
			}
			// update residual capacities along the taken augmenting path
			for (v = t; !v.label.equals("s"); v = parent[index_node(v.label)].from) {
				parent[index_node(v.label)].capacity -= path_flow; // residual capacity= capacity-max_flow
				Edge a = parent[index_node(v.label)].symetric; // reverse edge
				if (a != null)
					a.capacity += path_flow;
			}

			// Add path flow to overall flow
			max_flow += path_flow;
		}
		return max_flow;
	}

	// print min cut edges
	public void min_cut(FileWriter myWriter) throws IOException {

		bfs_reachable();
		// Print all edges that are from a reachable vertex to
		// non-reachable vertex in the original graph
		for (int i = 0; i < ver_num; i++) {
			Node a = nodes[i];
			for (Edge e : a.adjacentNodes) {
				if (e.original == true && e.from.is_visited == true && e.to.is_visited == false) {
					if (!e.from.label.equals("s")) // imaginary source it is not part of the original graph
						myWriter.write(e.from.label + " ");
					myWriter.write(e.to.label + "\n");
				}
			}

		}

	}
}