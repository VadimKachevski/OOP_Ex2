package dataStructure;


import utils.Point3D;

public class vertex implements node_data {

	int key;
	Point3D location;
	double weight;
	String metadata;
	int tag;
	
	public vertex(int key) {
		this.key = key;
		this.location = null;
		this.weight = 0;
		this.metadata = "";
		this.tag = 0;
	}
	public vertex(int key,Point3D location,double weight) {
		this.key = key;
		this.location = new Point3D(location);
		this.weight = weight;
		this.metadata = "";
	}
	public vertex(int key,Point3D location,double weight,int tag) {
		this.key = key;
		this.location = new Point3D(location);
		this.weight = weight;
		this.tag = tag;
		this.metadata = "";
	}
	public vertex(vertex other)
	{
		this(other.key,other.location,other.weight,other.tag);
		this.metadata = other.metadata;
		
	}
	@Override
	public int getKey() {
		return this.key;
	}

	@Override
	public Point3D getLocation() {
		return this.location;
	}

	@Override
	public void setLocation(Point3D p) {
		////// Check if i need to change weight later
		this.location  = new Point3D(p);
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		this.weight = w;

	}

	@Override
	public String getInfo() {
		return this.metadata;
	}

	@Override
	public void setInfo(String s) {
		this.metadata = s;

	}

	@Override
	public int getTag() {
		return tag;
	}

	@Override
	public void setTag(int t) {
		this.tag = t;

	}
}
