package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.node_data;
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
		a.init("testSave.txt");
	}

	@Test
	void testSave() {
		a.save("testSave.txt");
	}

	@Test
	void testIsConnected() {
		graph s = new DGraph();
		s.addNode(new vertex(1));
		s.addNode(new vertex(2));
		s.addNode(new vertex(3));
		s.addNode(new vertex(4));
		s.addNode(new vertex(5));
		s.addNode(new vertex(6));
		s.addNode(new vertex(7));
		s.connect(1, 2, 0);
		s.connect(1, 3, 0);
		s.connect(2, 4, 0);
		s.connect(2, 5, 0);
		s.connect(3, 2, 0);
		s.connect(4, 6, 0);
		s.connect(4, 5, 0);
		s.connect(5, 6, 0);
		s.connect(5, 7, 0);
		s.connect(6, 7, 0);
		s.connect(7, 1, 0);
		Graph_Algo e = new Graph_Algo();
		e.init(s);
		assertEquals(true, e.isConnected());
	}

	@Test
	void testShortestPathDist() {
		graph s = new DGraph();
		s.addNode(new vertex(1));
		s.addNode(new vertex(2));
		s.addNode(new vertex(3));
		s.addNode(new vertex(4));
		s.addNode(new vertex(5));
		s.addNode(new vertex(6));
		s.addNode(new vertex(7));
		s.addNode(new vertex(8));
		s.connect(1, 2, 1);
		s.connect(1, 3, 7);
		s.connect(1, 4, 4);
		s.connect(2, 3, 2);
		s.connect(3, 4, 4);
		s.connect(3, 5, 3);
		s.connect(4, 5, 5);
		s.connect(5, 6, 4);
		s.connect(5, 7, 13);
		s.connect(6, 7, 8);
		s.connect(6, 8, 10);
		s.connect(7, 8, 3);
		Graph_Algo e = new Graph_Algo();
		e.init(s);
		assertEquals(20.0, e.shortestPathDist(1, 8),0.001);
	}

	@Test
	void testShortestPath() {
		graph s = new DGraph();
		s.addNode(new vertex(1));
		s.addNode(new vertex(2));
		s.addNode(new vertex(3));
		s.addNode(new vertex(4));
		s.addNode(new vertex(5));
		s.addNode(new vertex(6));
		s.addNode(new vertex(7));
		s.addNode(new vertex(8));
		s.connect(1, 2, 1);
		s.connect(1, 3, 7);
		s.connect(1, 4, 4);
		s.connect(2, 3, 2);
		s.connect(3, 4, 4);
		s.connect(3, 5, 3);
		s.connect(4, 5, 5);
		s.connect(5, 6, 4);
		s.connect(5, 7, 13);
		s.connect(6, 7, 8);
		s.connect(6, 8, 10);
		s.connect(7, 8, 3);
		Graph_Algo e = new Graph_Algo();
		e.init(s);
		List<node_data> ans = e.shortestPath(1, 8);
		System.out.println(ans.toString());
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
