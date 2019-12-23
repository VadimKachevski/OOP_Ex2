package graphs_GUI;
import dataStructure.*;
import utils.Point3D;
import algorithms.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class g_GUI extends JFrame implements ActionListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7377758160791730941L;
	graph graph;
	//Graph_Algo GA;
	public g_GUI(graph g)
	{
		this.graph = g;
		//this.GA.init(g);
		initGUI();

	}
	public g_GUI()
	{
		graph = null;
		//GA.init(graph);
		initGUI();
	}
	private void initGUI()
	{
		//JFrame j = new JFrame();
		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Options");
		menuBar.add(menu);
		this.setMenuBar(menuBar);
		MenuItem item1 = new MenuItem("Draw graph");
		MenuItem item2 = new MenuItem("Draw from file");
		MenuItem item3 = new MenuItem("Save to file");
		MenuItem item4 = new MenuItem("find Shortest path");
		MenuItem item5 = new MenuItem("find Shortest path distance");
		MenuItem item6 = new MenuItem("TSP");
		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
		item4.addActionListener(this);
		item5.addActionListener(this);
		item6.addActionListener(this);
		menu.add(item1);
		menu.add(item2);
		menu.add(item3);
		menu.add(item4);
		menu.add(item5);
		menu.add(item6);
		

	}

	public void paint(Graphics g)
	{
		super.paint(g);

		if(graph != null)
		{
			//System.out.println("AAAAAAAAAAAAAA");
			Collection<node_data> nodes = graph.getV();
			for (node_data node_data : nodes) {
				//System.out.println("AAAAAAAAAAAAAAeeeee");
				g.setColor(Color.BLUE);
				Point3D p = node_data.getLocation();
				if(p != null)
				{
					g.fillOval(p.ix(), p.iy(), 12, 12);
					g.drawString(node_data.getKey()+"", p.ix(), p.iy()-1);
					Collection<edge_data> edges = graph.getE(node_data.getKey());
					for (edge_data edge : edges) {
						Graphics2D g2d = (Graphics2D)g;
						Stroke bs = g2d.getStroke();
						if(edge.getTag() == 999)
						{
							
							edge.setTag(0);
							g2d.setStroke(new BasicStroke(5));
							g.setColor(Color.GREEN);
							
						}
						else
						{
						g.setColor(Color.RED);
						}
						node_data dest = graph.getNode(edge.getDest());
						Point3D pd = dest.getLocation();
						if(pd!=null)
						{
							g.drawLine(p.ix()+5, p.iy()+5, pd.ix()+5, pd.iy()+5);
							g.drawString(edge.getWeight()+"", (p.ix()+pd.ix())/2, (p.iy()+pd.iy())/2);
							g.setColor(Color.YELLOW);
							int xhalf = (((p.ix()+pd.ix())/2)+pd.ix())/2;
							int yhalf = (((p.iy()+pd.iy())/2)+pd.iy())/2;
							g.fillOval((xhalf+pd.ix())/2, (yhalf+pd.iy())/2, 5, 5);
						}
						g2d.setStroke(bs);
					}
				}
			}
		}
	}
	
	private void DrawFromGraph() {
		Graph_Algo ga = new Graph_Algo();
		//ga.init(graph);
		System.out.println("DRAW");
		JFileChooser jf = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnV = jf.showOpenDialog(null);
		if (returnV == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jf.getSelectedFile();
			//System.out.println(selectedFile.getAbsolutePath());
			ga.init(selectedFile.getAbsolutePath());
			
			this.graph = ga.copy();
			//System.out.println(selectedFile.getAbsolutePath());
			repaint();
			//System.out.println(selectedFile.getAbsolutePath());
		}
		
	}
	private void saveToFile()
	{
		String sb = "TEST CONTENT";
		Graph_Algo ga = new Graph_Algo();
		ga.init(graph);
		JFileChooser jf = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnV = jf.showOpenDialog(null);
		if (returnV == JFileChooser.APPROVE_OPTION) {
			try {
				ga.save(jf.getSelectedFile()+".txt");
//	            FileWriter fw = new FileWriter(jf.getSelectedFile()+".txt");
//	            fw.write(sb.toString());
//	            fw.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
			//File selectedFile = jf.getSelectedFile();
			//System.out.println(selectedFile.getAbsolutePath());
			
		}
		System.out.println("SAVE");
	}
	private void shortestPath()
	{
		JFrame jinput = new JFrame();
		String fromS = JOptionPane.showInputDialog(jinput,"Enter From");		
		//JFrame from2 = new JFrame();
		String to = JOptionPane.showInputDialog(jinput,"Enter To");
		//System.out.println("From "+ fromS +" TO "+fromT);
		try
		{
			int fromN = Integer.parseInt(fromS);
			int toN = Integer.parseInt(to);
			Graph_Algo ga = new Graph_Algo();
			ga.init(graph);
			List<node_data> ans = ga.shortestPath(fromN, toN);
			for (int j = 0; j < ans.size()-1; j++) {
				graph.getEdge(ans.get(j).getKey(), ans.get(j+1).getKey()).setTag(999);
			}
			repaint();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void ShortestPathDist()
	{
		JFrame jinput = new JFrame();
		String fromS = JOptionPane.showInputDialog(jinput,"Enter From");		
		//JFrame from2 = new JFrame();
		String to = JOptionPane.showInputDialog(jinput,"Enter To");
		try
		{
			int fromN = Integer.parseInt(fromS);
			int toN = Integer.parseInt(to);
			Graph_Algo ga = new Graph_Algo();
			ga.init(graph);
			double x = ga.shortestPathDist(fromN, toN);
			JOptionPane.showMessageDialog(jinput, "the shortest distance is:" + x);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String act = e.getActionCommand();
		switch (act) {
		case "Draw graph":
			repaint();
			break;
		case "Draw from file":DrawFromGraph();
		break;
		case "Save to file" :saveToFile();
		break;
		case "find Shortest path" : shortestPath();
		break;
		case "find Shortest path distance" : ShortestPathDist();
		break;
		default:
			break;
		}
	}
}
