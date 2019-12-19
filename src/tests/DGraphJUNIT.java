package tests;
import dataStructure.*;
import utils.Point3D;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DGraphJUNIT {

	private DGraph graph;
	
	@BeforeEach
	void setup()
	{
		graph = createGraphBefore();
	}
	
	
	@Test
	void testgetNode() {
		node_data a = graph.getNode(1);
		node_data b = graph.getNode(3);
		node_data c = graph.getNode(4);
		node_data d = graph.getNode(7);
	}






	DGraph createGraphBefore()
	{
		DGraph graph = new DGraph();
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
