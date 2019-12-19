package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.vertex;
import utils.Point3D;

class Graph_AlgoJUNIT {

	Graph_Algo a = new Graph_Algo();
	@BeforeEach
	void setup()
	{
		a.init(createGraphBefore());
	}
	
	
	@Test
	void testInitGraph() {
		fail("Not yet implemented");
	}

	@Test
	void testInitString() {
		fail("Not yet implemented");
	}

	@Test
	void testSave() {
		a.save("testSave.txt");
	}

	@Test
	void testIsConnected() {
		fail("Not yet implemented");
	}

	@Test
	void testShortestPathDist() {
		fail("Not yet implemented");
	}

	@Test
	void testShortestPath() {
		fail("Not yet implemented");
	}

	@Test
	void testTSP() {
		fail("Not yet implemented");
	}

	@Test
	void testCopy() {
		fail("Not yet implemented");
	}
	
	
	graph createGraphBefore()
	{
		graph graph = new DGraph();
		graph.addNode(new vertex(1, new Point3D(50 ,20), 30));
		graph.addNode(new vertex(2));
		graph.addNode(new vertex(3));
		graph.addNode(new vertex(4));
		graph.addNode(new vertex(5));
		graph.addNode(new vertex(6));
		graph.connect(1, 2, 20);
		graph.connect(2, 3, 20);
		graph.connect(3, 4, 4);
		graph.connect(4, 5, 8);
		graph.connect(5, 6,8);
		return graph;
		
	}

}
