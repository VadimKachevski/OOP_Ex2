package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms,Serializable{

	
	
	graph graph;
	
	
	@Override
	public void init(graph g) {
		this.graph =g;
		
	}
	@Override
	public void init(String file_name) {
		// TODO Auto-generated method stub
		try
		{
			FileInputStream f = new FileInputStream(file_name);
			ObjectInputStream o = new ObjectInputStream(f);
			//o.writeObject(this.graph);
			this.graph = (graph)o.readObject();
			o.close();
			f.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(String file_name) {
	
		try
		{
			FileOutputStream f = new FileOutputStream(file_name);
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(this.graph);
			o.close();
			f.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public boolean isConnected() {
		//HashMap<node_data, Integer> connectedNodes = new HashMap<node_data, Integer>(); 
		Collection<node_data> s = graph.getV();
		for (node_data node_data : s) {
			cleanTags();
			//node_data.setTag(1);
			//connectedNodes.put(node_data, 1);
			int ans = isConnectedHelper(node_data);
			if(ans != s.size())
			{
				return false;
			}
			
		}
		return true;
	}
	
	public int isConnectedHelper(node_data n)
	{
		if(n.getTag() == 1)
		{
			return 0;
		}
		n.setTag(1);
		Collection<edge_data> edge = graph.getE(n.getKey());
		int counter=1;
		for (edge_data edge_data : edge) {
			counter += isConnectedHelper(graph.getNode(edge_data.getDest()));
		}
		return counter;
	}
	
	
	private void cleanTags()
	{
		Collection<node_data> s = graph.getV();
		for (node_data node_data : s) {
			node_data.setTag(0);
		}
	}
	
	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
