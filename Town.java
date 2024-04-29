import java.util.Objects;

public class Town implements Comparable<Town> 
{

	//Attribute
	private String name;
	
	
	//Constructors
	public Town(String str) {
		name = str;
	}
	
	public Town(Town template) {
		name = template.getName();
	}
	
	
	
	//Setter
	public void setName(String str) {
		name = str;
	}
	
	//Getter
	public String getName() {
		return name;
	}

	//toString Method
	@Override
	public String toString() {
		return name;
	}
	
	//Methods
	@Override
	public int compareTo(Town o) {
		return name.compareTo(o.getName());
	}	
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object town) {
	
	Town T = (Town) town;
	
	if(name.equals(T.getName()))
		return true;
	
    else 
		return false;
		
	}
	
	
}
