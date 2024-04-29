import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Graph implements GraphInterface<Town,Road>{

	//Attributes
	private HashMap<Town,Town> previous;
	private HashMap<Town,Integer> distances;
	private HashSet<Town> towns;
	private HashSet<Road> roads;
	
	//Constructor
	public Graph() {
		
		previous = new HashMap<Town,Town>();
		distances = new HashMap<Town, Integer>();
		towns = new HashSet<Town>();
		roads = new HashSet<Road>();
		
	}
	
	//Creates a new edge in this graph, going from the source vertex to the target vertex, and returns the created edge. 
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) 
		   throws NullPointerException, IllegalArgumentException {
		
		if(sourceVertex == null ||  destinationVertex == null)
			throw new NullPointerException();
		
		if(!(towns.contains(sourceVertex)||towns.contains(destinationVertex)))
			throw new IllegalArgumentException();
		
		Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
		
		try {
			roads.add(newRoad);
		}
		catch(Exception e) {
			return null;
		}
		
		return newRoad;
		
	}
	

	//Returns an edge connecting source vertex to target vertex if such vertices and such edge exist in this graph.
	//Otherwise returns null. If any of the specified vertices is null returns null
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		
		if(sourceVertex == null || destinationVertex == null)
			return null;
		
		else {
			
			for(Road rd : roads) {
				
				if(rd.contains(destinationVertex) && rd.contains(sourceVertex)) 	
					return rd;		
			}
		}
		return null;
	}
	
	//Adds the specified vertex to this graph if not already present
	@Override
	public boolean addVertex(Town v) throws NullPointerException {
		
		if(v == null)
			throw new NullPointerException();
		
		for(Town element: towns) {
			
			if(element.equals(v))
				return false;
		}
		
		towns.add(v);
		
		return true;
	}

	//Returns true if and only if this graph contains an edge going from the source vertex to the target vertex.
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		
		if(sourceVertex == null || destinationVertex == null)
			return false;
		
		if(!(towns.contains(sourceVertex) ||towns.contains(destinationVertex)))
			return false;
		
		for(Road rd : roads) {
			
			if(rd.contains(destinationVertex) && rd.contains(sourceVertex))
				return true;		
		}
		
		return false;
	}

	//Returns true if this graph contains the specified vertex
	@Override
	public boolean containsVertex(Town v) {
		
		for(Town element : towns) {
			
			if(element.equals(v))
				return true;		
		}
		
		return false;
	}

	//Returns a set of the edges contained in this graph
	@Override
	public Set<Road> edgeSet() {
		
		return roads;
	}

	//Returns a set of all edges touching the specified vertex
	@Override
	public Set<Road> edgesOf(Town vertex) throws IllegalArgumentException, NullPointerException {
		
		HashSet<Road> edgeSet = new HashSet<Road>();
		
		for(Road element : roads) {
			
			if(element.contains(vertex)) 
				edgeSet.add(element);		
		}
		
		return edgeSet;
	}

	//Removes an edge going from source vertex to target vertex
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		
		Road removedRoad = null;
		
		for(Road element : roads) {
			
			if(element.contains(sourceVertex) && element.contains(destinationVertex) && element.getWeight() == weight && 
			   element.getName().equals(description)) {
				
				roads.remove(element); 
				return element;
			}
		}
		
		return removedRoad;
	}

	//Removes the specified vertex from this graph
	@Override
	public boolean removeVertex(Town v) {
		
		return towns.remove(v);
	}

	//Returns a set of the vertices contained in this graph
	@Override
	public Set<Town> vertexSet() {
		
		return towns;
	}

	//Methods
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		
		Set<Town> remainingTowns = new HashSet<>(towns);

		for (Town value : towns)
			distances.put(value, Integer.MAX_VALUE);
		
		distances.put(sourceVertex, 0);

		while (!remainingTowns.isEmpty()) {
			
			int minDistance = Integer.MAX_VALUE;
			
			Town miniVertex = null;

			for (Town element : distances.keySet()) {
				
				if (minDistance > distances.get(element) && remainingTowns.contains(element)) {
					
					minDistance = distances.get(element);
					miniVertex = element;
				}
			}

			if (miniVertex == null)
				break;
			
			sourceVertex = miniVertex;

			for (Road index : roads) {
				
				if (index.contains(sourceVertex)) {
					
					if (!index.getDestination().equals(sourceVertex) && remainingTowns.contains(index.getDestination())) {
						
						if (distances.get(sourceVertex) + index.getWeight() < distances.get(index.getDestination())) {
							
							previous.put(index.getDestination(), sourceVertex);
							distances.put(index.getDestination(), index.getWeight() + distances.get(sourceVertex));
						}
						
					}
					
					if (!index.getSource().equals(sourceVertex) && remainingTowns.contains(index.getSource())) {
						
						if (distances.get(sourceVertex) + index.getWeight() < distances.get(index.getSource())) {
							previous.put(index.getSource(), sourceVertex);
							distances.put(index.getSource(), index.getWeight() + distances.get(sourceVertex));
						}
					}
					
				}
			}

			
			remainingTowns.remove(sourceVertex);
		}
		
	}
	
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		
	    ArrayList<String> shortest = new ArrayList<String>();
	    
	    dijkstraShortestPath(sourceVertex);
	    
	    if (!previous.containsKey(destinationVertex)) 
	        return shortest;
	   
	    
	    while(!sourceVertex.equals(destinationVertex)) {
	    	
	        for(Road element : roads) {
	        	
	            if (element.contains(destinationVertex) && element.contains(previous.get(destinationVertex))) {
	            	
	            	shortest.add(0, previous.get(destinationVertex).getName() + " via " + element.getName() + " to "
	                             + destinationVertex.getName() + " " + element.getWeight() + " mi");
	            }
	        }
	        
	        destinationVertex = previous.get(destinationVertex);
	    }
	    
	    return shortest;
	}

	
}
