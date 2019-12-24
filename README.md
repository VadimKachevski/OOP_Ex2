# OOP_Ex2 - The Maze of Waze

This is a homework project Excersice #2 in the Object-oriented programming course.  
This project is mainly about Graphs, GUI representation of those Graphs and usage of algorithem on those graphs.

A graph is a collection of Vertexs and Edges to connect those Vertexs.  
Each Vertex has a key and location, key is a Integer and location is Point with X and Y.  
Each edge has a Source, destination and weight of the edge.  


With a given Graph you can perform those actions :

* addNode - will add a node to the graph.
* getNode - will return the node with a given node key.
* connect - will create a edge from source node to destination with given weight.
* getEdge - will return the edge of given source and destination.
* getV - will return a collection that contains all the vertecis.
* getE - will return a collection of edges of a given vertex key.
* removeNode - will remove the node and all the adjecnt edges.
* removeEdge - will remove the edge with a given source and destination.

each action is counted in action counter.  

The algorithem that can performed on those graphs:
* init - initate a graph from a file.
* save - save a graph to a file.
* isConnected - checks if the graph is connected, if every node can get to every node.
* shortestPathDist - calculates the shortest distance from source to destination using dijkstra.
* shortestPath - return the shortest path from the source to destination using dijkstra.
* TSP - with a given list of keys will try to create the shortest path between all those vertics.
