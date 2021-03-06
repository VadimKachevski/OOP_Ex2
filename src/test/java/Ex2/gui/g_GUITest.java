package Ex2.gui;

import java.util.Collection;
import java.util.Random;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import Ex2.algorithms.*;
import Ex2.dataStructure.*;
import Ex2.utils.Point3D;

class g_GUITest {

	public static void main(String[] args) {
		testmaybe();
	}
	
	
	@Test
	static void testDraw() {
		//fail("Not yet implemented");
		graph g = new DGraph();
		g.addNode(new vertex(1, new Point3D(130, 130)));
		g.addNode(new vertex(2, new Point3D(180, 160)));
		g.addNode(new vertex(3, new Point3D(210, 180)));
		g.addNode(new vertex(4, new Point3D(240, 190)));
		g.addNode(new vertex(5, new Point3D(160, 200)));
		g.addNode(new vertex(6, new Point3D(160, 230)));
		g.addNode(new vertex(7, new Point3D(160, 210)));
		g.addNode(new vertex(8, new Point3D(210, 140)));
		g.addNode(new vertex(9, new Point3D(240, 250)));
		g.addNode(new vertex(10, new Point3D(210, 210)));
		g.addNode(new vertex(11, new Point3D(340, 300)));
		g.connect(1, 2, 30);
		g.connect(1, 5, 40);
		g.connect(2, 3, 50);
		g.connect(4, 7, 60);
		g.connect(2, 11, 70);
		g.connect(3, 4, 80);
		g.connect(9, 1, 30);
		g.connect(4, 2, 40);
		g.connect(3, 5, 50);
		g.connect(10, 11, 60);
		g.connect(5, 6, 10);
		g.connect(6, 1, 50);
		g_GUI gui = new g_GUI(g);
		Graph_Algo gg = new Graph_Algo();
		gg.init(g);
		double ee = gg.shortestPathDist(1, 6);
		System.out.println(ee);
	}
	@Test
	static void testmaybe()
	{
		graph gggg = graphFactory();
		Graph_Algo GA = new Graph_Algo();
		g_GUI gui = new g_GUI(gggg);
	//	GA.init(gggg);
		//System.out.println();
	}
	static graph graphFactory()
	{
		graph graph = new DGraph();
		Random rand = new Random();
		for (int i = 0; i < 100; i++) {
			
			Point3D p = new Point3D(rand.nextInt(600)+100, rand.nextInt(600)+100);
			graph.addNode(new vertex(i,p));
		}
		Collection<node_data> nd = graph.getV();
		for (node_data node_data : nd) {
			int amount_of_edegs = rand.nextInt(5);
			for (int i = 0; i < amount_of_edegs; i++) {
				int dest = rand.nextInt( 100 );
				while (dest == node_data.getKey( )) {
					dest = rand.nextInt( 100 );
				}
				int weight = rand.nextInt( 20 );
				//weight*= 10;
				graph.connect( node_data.getKey( ), dest, weight );
			}
		}
		return graph;
	}
	@Test
	void AssertTrue()
	{
		boolean t = true;
		Assert.assertTrue( t );
	}

}
