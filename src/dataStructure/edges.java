package dataStructure;

public class edges implements edge_data {

	static int countEdges = 0;
	int srcVertex;
	int destVertx;
	double weight;
	String metadata;
	int tag;
	
	public edges(int srcVertex,int destVerex,double weight) {
		this.srcVertex = srcVertex;
		this.destVertx = destVerex;
		this.weight = weight;
		this.tag = 0;
		
	}
	public edges(int srcVertex,int destVerex,double weight,int tag) {
		this.srcVertex = srcVertex;
		this.destVertx = destVerex;
		this.weight = weight;
		this.tag = tag;
	}
	public edges(edges other)
	{
		this(other.srcVertex,other.destVertx,other.weight,other.tag);
		this.metadata = other.metadata;
	}
	@Override
	public int getSrc() {
		return srcVertex;
	}

	@Override
	public int getDest() {
		return destVertx;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public String getInfo() {
		return metadata;
	}

	@Override
	public void setInfo(String s) {
		this.metadata = s;

	}

	@Override
	public int getTag() {
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		this.tag = t;
	}

}
