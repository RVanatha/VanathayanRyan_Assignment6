import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TownGraphManager implements TownGraphManagerInterface {

	// Attribute
	Graph graph = new Graph();

	// Adds a road with 2 towns and a road name
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Road newRoad;
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);

		graph.addVertex(t1);
		graph.addVertex(t2);

		newRoad = graph.addEdge(t1, t2, weight, roadName);

		return newRoad != null;
	}

	// Returns the name of the road that both towns are connected through
	@Override
	public String getRoad(String town1, String town2) {
		
		Road road = graph.getEdge(new Town(town1), new Town(town2));
		
		if (road != null)
			return road.getName();
		
	    else 
	    	return null;
	    
	}
	
	// Determines if a town is already in the graph
	@Override
	public boolean containsTown(String v) {
		
		boolean check = graph.containsVertex(new Town(v));
		return check;
	}

	// Adds a town to the graph
	@Override
	public boolean addTown(String v) {
		
		boolean check = graph.addVertex(new Town(v));
		return check;
	}

	// Gets a town with a given name
	@Override
	public Town getTown(String name) {
		
		Town town = null;
		
		Set<Town> towns = graph.vertexSet();
		
		for (Town element : towns) {
			
			if (element.getName().equals(name)) {
				
				town = element;
				break;
			}
		}
		
		return town;
	}

	// Determines if a road is in the graph
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		
		boolean check = graph.containsEdge(new Town(town1), new Town(town2));
		
		return check;
	}

	// Creates an ArrayList of all road titles in sorted order by road name
	@Override
	public ArrayList<String> allRoads() {
		
		ArrayList<Road> roads = new ArrayList<>(graph.edgeSet());	
		ArrayList<String> roadNames = new ArrayList<>();
		
		for (Road element : roads)
			roadNames.add(element.getName());
		
		Collections.sort(roadNames);
		
		
		return roadNames;
	}

	// Deletes a road from the graph
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		
		Road rd = graph.getEdge(new Town(town1), new Town(town2));
		
		Road removedRoad = graph.removeEdge(new Town(town1), new Town(town2), rd.getWeight(), road);
		
		return removedRoad != null;
	}

	// Deletes a town from the graph
	@Override
	public boolean deleteTown(String v) {
		
		boolean check =  graph.removeVertex(new Town(v));
		
		return check;
	}

	// Creates an ArrayList of all towns in alphabetical order
	@Override
	public ArrayList<String> allTowns() {
		
		ArrayList<Town> towns = new ArrayList<Town>(graph.vertexSet());
		
		ArrayList<String> str= new ArrayList<String>();
		
		for(Town t: towns)
			str.add(t.getName());
		
		Collections.sort(str);
		
		return str;
	}

	// Returns the shortest path from town 1 to town 2
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		
		return graph.shortestPath(new Town(town1), new Town(town2));
	}

	public void populateTownGraph(File selectedFile) throws FileNotFoundException {
		
		String[] ar;
		String str;

		Scanner fileScanner = new Scanner(selectedFile);

		while (fileScanner.hasNextLine()) {
			
			str = fileScanner.nextLine();
			ar = str.split(",|;");
			
			graph.addVertex(new Town(ar[2]));
			graph.addVertex(new Town(ar[3]));
			
			graph.addEdge(new Town(ar[2]), new Town(ar[3]), Integer.parseInt(ar[1]), ar[0]);
		}
		
		fileScanner.close();
	
	}
	
	
}