package Ex2.dataStructure;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class DGraph implements graph,Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 568702754958880042L;
	int countEdges = 0;
	int MC = 0;
	Hashtable<Integer, node_data> vertexs;
	// src					DEST
	Hashtable<node_data, Hashtable<Integer, edge_data>> edgesPerVertex;


	public DGraph() {
		vertexs = new Hashtable<Integer, node_data>();
		edgesPerVertex = new Hashtable<node_data, Hashtable<Integer,edge_data>>();
		countEdges = 0;
		MC = 0;
	}

	@Override
	public node_data getNode(int key) {
		//MC++;
		return vertexs.get(key);

	}

	@Override
	public edge_data getEdge(int src, int dest) {
		//MC++;
		node_data vert = vertexs.get(src);
		edge_data edg = edgesPerVertex.get(vert).get(dest);
		return edg;
	}

	@Override
	public void addNode(node_data n) {
		MC++;
			vertexs.put(n.getKey(), n);
			edgesPerVertex.put(n, new Hashtable<Integer, edge_data>());
	}

	@Override
	public void connect(int src, int dest, double w) {
		if(src == dest)
		{
			return;
		}
		if(w < 0 )
		{
			throw new RuntimeException( "Weight cant be negative" );
		}
		MC++;
		node_data vert = vertexs.get(src);
		edgesPerVertex.get(vert).put(dest, new edges(src, dest, w));
		countEdges++;
	}

	@Override
	public Collection<node_data> getV() {
		return vertexs.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		node_data vert = vertexs.get(node_id);
		return edgesPerVertex.get(vert).values();
	}

	@Override
	public node_data removeNode(int key) {
		MC++;
		node_data vert = vertexs.get(key);
		Set<node_data> sets = edgesPerVertex.keySet();
		for (node_data node_data : sets) {
			edge_data edg = edgesPerVertex.get(node_data).remove(key);
			if(edg != null)
			{
				countEdges--;
			}
		}
		countEdges -= edgesPerVertex.get(vert).size();
		edgesPerVertex.remove(vert);
		return vertexs.remove(key);

	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		MC++;
		node_data vert = vertexs.get(src);
		edge_data ans =  edgesPerVertex.get(vert).remove(dest);
		if(ans!= null){
			countEdges--;
			MC++;
		}
		return ans;
	}

	@Override
	public int nodeSize() {
		return vertexs.size();
	}

	@Override
	public int edgeSize() {
		return countEdges;
	}

	@Override
	public int getMC() {
		return MC;
	}

}
