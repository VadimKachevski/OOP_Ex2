package utils;

import dataStructure.node_data;

public class vertex implements node_data {

	
	int key;
	Point3D Pos;
	int weight;
	
	public vertex(int key,Point3D pos) {
		this.key = key;
		this.Pos = new Point3D(pos);
	}
	
	@Override
	public int getKey() {
		return key;
	}

	@Override
	public Point3D getLocation() {
		return Pos;
	}

	@Override
	public void setLocation(Point3D p) {
		this.Pos = new Point3D(p);

	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWeight(double w) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInfo(String s) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getTag() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTag(int t) {
		// TODO Auto-generated method stub

	}

}
