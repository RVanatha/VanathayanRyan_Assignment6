public class Road implements Comparable<Road> 
{

	//Attributes
	String name;
	Town town1, town2;
	int weight;

	
	//Constructors
	public Road(Town source, Town destination, int degrees, String name) {
		
	town1 = source;
	town2 = destination;
	weight = degrees;
	this.name = name;
	
	}
	
	public Road(Town source, Town destination, String name) {
		
		town1 = new Town (source);
		
		town2 = new Town (destination);
	
		weight = 1;
		this.name = name;
	}
	
	//Getters
	public String getName() {
		return name;
	}
	
	public Town getSource() {
		return town1;
	}
	
	public Town getDestination() {
		return town2;
	}
	
	public int getWeight() {
		return weight;
	}

	//toString Method
	@Override
	public String toString() {
		return "Road: " + name + ", connecting "+ town1+ " to " + town2 + " by " + weight + " mi";
	}

	//Methods
	public boolean contains(Town town) {
		
		
		if(town1.equals(town) || town2.equals(town)) 
			return true;
		
		else
			return false;
	}

	@Override
	public int compareTo(Road road) {
		
		return name.compareTo(road.getName());
	}
		
	@Override
	public boolean equals(Object obj) {
		
		Road Rd = (Road)obj;

		return ( (town1.equals(Rd.getSource())) && (town2.equals(Rd.getDestination()))) 
				|| ((town1.equals(Rd.getDestination())) && (town2.equals(Rd.getSource())));

	}

	
	
}