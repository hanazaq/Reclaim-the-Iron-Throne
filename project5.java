// package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class project5 {
	@SuppressWarnings("removal")
	public static void main(String[] args) {
//	long startTime = System.nanoTime();

		Graph graph;

		try {

			File myObj = new File(args[0]);
			Scanner myReader = new Scanner(myObj);

			int cities_num = Integer.parseInt(myReader.nextLine());
			graph = new Graph(cities_num);
			String[] reigion_capacities = myReader.nextLine().split(" ");
			for (int i = 0; i < 6; i++) {
				// create edges from imaginary source to regions
				Graph.nodes[0].add(Graph.nodes[i + 1], Integer.parseInt(reigion_capacities[i]));
				// add edges for regions
				String[] region_neighbours = myReader.nextLine().split(" ");
				for (int j = 1; j < region_neighbours.length; j += 2) {
					// e.g. r1.add("c0","15"): "c0">>converted to node "15">>converted to int
					Graph.nodes[i + 1].add(Graph.nodes[Graph.index_node(region_neighbours[j])],
							Integer.parseInt(region_neighbours[j + 1]));
				}
			}

			// egde info
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] adj_info = data.split(" ");
				for (int j = 1; j < adj_info.length; j += 2) {
					// e.g. c2.add("c0","15"): "c0">>converted to node "15">>converted to int
					Graph.nodes[Graph.index_node(adj_info[0])].add(Graph.nodes[Graph.index_node(adj_info[j])],
							Integer.parseInt(adj_info[j + 1]));
				}

			}
			myReader.close();
			try {
				FileWriter myWriter = new FileWriter(args[1]);
				int answer = graph.ford();
				myWriter.write(new Integer(answer).toString()+"\n");
				graph.min_cut(myWriter);
				myWriter.close();
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

//	System.out.println("Time takes: " + (System.nanoTime() - startTime) / 1000000);
//		int l= Graph.nodes.length;
//		for(int x=0; x<l; x++) {
//			System.out.println(Graph.nodes[x].label);
//			for(Edge e: Graph.nodes[x].adjacentNodes) {
//				System.out.println(e.from.label+" "+e.to.label+" "+e.capacity);
//			}
//			System.out.println("=================");
//		}

	}
}
