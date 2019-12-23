package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;


import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import dataStructure.vertex;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms,Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6198391308540997339L;
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
			Graph_Algo ee = (Graph_Algo)o.readObject();
			this.graph = ee.graph;
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
			o.writeObject(this);
			o.close();
			f.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}



	//	@Override
	//	public boolean isConnected() {
	//		//HashMap<node_data, Integer> connectedNodes = new HashMap<node_data, Integer>(); 
	//		Collection<node_data> s = graph.getV();
	//		for (node_data node_data : s) {
	//			cleanTags();
	//			//node_data.setTag(1);
	//			//connectedNodes.put(node_data, 1);
	//			int ans = isConnectedHelper(node_data);
	//			if(ans != s.size())
	//			{
	//				return false;
	//			}
	//
	//		}
	//		return true;
	//	}
	private int amountOfNodesThisNodeCanGetTo(node_data n)
	{
		if(n.getTag() == 1)
		{
			return 0;
		}
		n.setTag(1);
		Collection<edge_data> edge = graph.getE(n.getKey());
		int counter=1;
		for (edge_data edge_data : edge) {
			counter += amountOfNodesThisNodeCanGetTo(graph.getNode(edge_data.getDest()));
		}
		return counter;
	}

	// Should be faster then the org algo
	@Override
	public boolean isConnected()
	{
		Collection<node_data> s = graph.getV();
		boolean firstNode = false;
		node_data firstN;
		boolean ansBool=true;
		for (node_data node_data : s) {
			if(firstNode == false)
				cleanTags();
			firstN = node_data;
			int ans = amountOfNodesThisNodeCanGetTo(node_data);
			if(ans != s.size())
			{
				return false;
			}
			else
			{
				ansBool = ansBool && canOtherNodeGetToORG_Node(node_data, firstN);
			}

		}
		return ansBool;
	}
	private boolean canOtherNodeGetToORG_Node(node_data n,node_data firstNode)
	{
		boolean found = false;
		if(n == firstNode)
		{
			return true;
		}
		if(n.getTag() == 1)
		{
			return found;
		}
		n.setTag(1);
		Collection<edge_data> edge = graph.getE(n.getKey());
		for (edge_data edge_data : edge) {
			found =found || canOtherNodeGetToORG_Node(graph.getNode(edge_data.getDest()),firstNode);
		}
		//return counter;
		return found;
	}


	private void cleanTags()
	{
		Collection<node_data> s = graph.getV();
		for (node_data node_data : s) {
			node_data.setTag(0);
		}
	}


	private void cleanTagsSetweight()
	{
		Collection<node_data> s = graph.getV();
		for (node_data node_data : s) {
			node_data.setTag(0);
			node_data.setWeight(Integer.MAX_VALUE);
			node_data.setInfo("");
		}
	}

	//	private void Dijksta(int src)
	//	{
	//		cleanTagsSetweight();
	//
	//		node_data minNodeNotTouched = graph.getNode(src);
	//		minNodeNotTouched.setWeight(0);
	//		node_data currMin = null;
	//		while(minNodeNotTouched != null)
	//		{
	//			minNodeNotTouched.setTag(1);
	//			Collection<edge_data> edges = graph.getE(minNodeNotTouched.getKey());
	//			currMin = null;
	//			for (edge_data edge_data : edges) {
	//				node_data currNode = graph.getNode(edge_data.getDest());
	//				if(currNode.getWeight() > minNodeNotTouched.getWeight() + edge_data.getWeight())
	//				{
	//					currNode.setWeight(minNodeNotTouched.getWeight() + edge_data.getWeight());
	//					currNode.setInfo(minNodeNotTouched.getKey()+"");
	//					if(currMin == null && currNode.getTag() == 0)
	//					{
	//						currMin = currNode;
	//					}
	//					else
	//					{
	//						if(currMin.getWeight() > currNode.getWeight() && currNode.getTag() == 0);
	//						{
	//							currMin = currNode;
	//						}
	//					}
	//				}
	//			}
	//			minNodeNotTouched = currMin;
	//		}
	//	}
	private void Dijksta(int src)
	{
		cleanTagsSetweight();
		ArrayList<node_data> Mins = new ArrayList<node_data>();
		Mins.add(graph.getNode(src));
		Mins.get(0).setWeight(0);
		while(!Mins.isEmpty())
		{
			node_data currNode = Mins.get(0);
			if(currNode.getTag() ==0)
			{
				currNode.setTag(1);
				Mins.remove(0);
				Collection<edge_data> edges = graph.getE(currNode.getKey());
				for (edge_data edge_data : edges) {
					node_data destNode = graph.getNode(edge_data.getDest());
					if(destNode.getWeight() > currNode.getWeight()+edge_data.getWeight())
					{
						destNode.setWeight(currNode.getWeight()+edge_data.getWeight());
						destNode.setInfo(currNode.getKey()+"");
						if(destNode.getTag() == 0)
						{

							Mins.add(getPosInArray(Mins, destNode.getWeight()),destNode);
						}
					}
				}
			}
			else
			{
				Mins.remove(0);
			}
		}

	}


	private int getPosInArray(ArrayList<node_data> Mins,double destNodeW)
	{
		int minIndex = 0;
		int maxIndex = Mins.size()-1;
		int middle = minIndex + (maxIndex-minIndex)/2;

		while(minIndex <= maxIndex)
		{
			if(Mins.get(middle).getWeight() == destNodeW)
			{
				return middle;
			}
			if(destNodeW > Mins.get(middle).getWeight())
			{
				minIndex = middle +1;
			}
			if(destNodeW < Mins.get(middle).getWeight())
			{
				maxIndex = middle -1;
			}
			middle = minIndex+(maxIndex-minIndex)/2;
		}
		return middle;
	}

	@Override
	public double shortestPathDist(int src, int dest) {

		Dijksta(src);
		return graph.getNode(dest).getWeight();
	}



	@Override
	public List<node_data> shortestPath(int src, int dest) {
		Dijksta(src);
		List<node_data> ans = new ArrayList<node_data>();
		node_data currNode = graph.getNode(dest);
		while(!currNode.getInfo().isEmpty())
		{
			ans.add(0, currNode);
			currNode = graph.getNode(Integer.parseInt(currNode.getInfo()));
		}
		ans.add(0, currNode);
		return ans;
	}
	
	
//	public List<node_data> shortestPathForTSP(int src, int dest,List<Integer> targets,Hashtable<Integer, Integer> wasthere) {
//		Dijksta(src);
//		List<node_data> ans = new ArrayList<node_data>();
//		node_data currNode = graph.getNode(dest);
//		while(!currNode.getInfo().isEmpty())
//		{
//			if(targets.contains(currNode.getKey()))
//			{
//				wasthere.put(currNode.getKey(), currNode.getKey());
//			}
//			ans.add(0, currNode);
//			currNode = graph.getNode(Integer.parseInt(currNode.getInfo()));
//		}
//		ans.add(0, currNode);
//		return ans;
//	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		List<node_data> ans = new ArrayList<node_data>();
		int i = 0;
		//Hashtable<Integer, Integer> wasthere = new Hashtable<Integer, Integer>();
		while(i<targets.size()-1)
		{
		//	if(!wasthere.containsKey(i))
		//	{
				if(i == 0)
				{
					ans.addAll(shortestPath(targets.get(i), targets.get(i+1)));
					
				}
				else
				{
					List<node_data> pre =shortestPath(targets.get(i), targets.get(i+1));
					pre.remove(0);
					ans.addAll(pre);
				}
				i++;
		//	}
		}
		return ans;
	}
	public List<node_data> TSPv2(List<Integer> targets)
	{
		List<node_data> ans = new ArrayList<node_data>();
		HashSet<Integer> wasNotThere = new HashSet<Integer>();
		for (Integer integer : targets) {
			wasNotThere.add(integer);
		}
		int i =0;
		while(i<targets.size()-1)
		{
			if(wasNotThere.contains(targets.get(i)))
			{
				if(i == 0)
				{
					ans.addAll(shortestPath(targets.get(i), targets.get(i+1)));
					
				}
				else
				{
					List<node_data> pre =shortestPath(targets.get(i), targets.get(i+1));
					pre.remove(0);
					ans.addAll(pre);
				}
			}
			i++;
		}
		
		
		return null;
	}

	@Override
	public graph copy() {
		graph ans = new DGraph();
		Collection<node_data> nodes = graph.getV();
		for (node_data node_data : nodes) {
			ans.addNode(new vertex(node_data));
			Collection<edge_data> edges = graph.getE(node_data.getKey());
			for (edge_data edge : edges) {
				ans.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
			}
		}
		return ans;
	}

}
