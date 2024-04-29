import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

class TownGraphManager_STUDENT_Test {

	private TownGraphManagerInterface graph;
	private String[] townNames;
	  
	@Before
	public void setUp() throws Exception {
		  graph = new TownGraphManager();
		  townNames = new String[12];
		  
		  for (int i = 1; i < 12; i++) {
			  townNames[i] = "Town_" + i;
			  graph.addTown(townNames[i]);
		  }
		  
		  graph.addRoad(townNames[1], townNames[2], 2, "Julia Lane");
		  graph.addRoad(townNames[1], townNames[3], 4, "James Highway");
		  graph.addRoad(townNames[1], townNames[5], 6, "SR-332");
		  graph.addRoad(townNames[3], townNames[7], 1, "I-746");
		  graph.addRoad(townNames[3], townNames[8], 2, "SR-453");
		  graph.addRoad(townNames[4], townNames[8], 3, "West Highway");
		  graph.addRoad(townNames[6], townNames[9], 3, "West Lane");
		  graph.addRoad(townNames[9], townNames[10], 4, "East Corner");
		  graph.addRoad(townNames[8], townNames[10], 2, "Beltway");
		  graph.addRoad(townNames[5], townNames[10], 5, "Johna Highway");
		  graph.addRoad(townNames[10], townNames[11], 3, "Hat Highway");
		  graph.addRoad(townNames[2], townNames[11], 6, "Express Lane");
		 
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Beltway", roads.get(0));
		assertEquals("East Corner", roads.get(1));
		assertEquals("Express Lane", roads.get(2));
		assertEquals("Hat Highway", roads.get(3));
		graph.addRoad(townNames[4], townNames[11], 1,"Julia Lane3");
		roads = graph.allRoads();
		assertEquals("Beltway", roads.get(0));
		assertEquals("East Corner", roads.get(1));
		assertEquals("Express Lane", roads.get(2));
		assertEquals("Hat Highway", roads.get(3));
		assertEquals("I-746", roads.get(4));
		
	}

	@Test
	public void testGetRoad() {
		assertEquals("Express Lane", graph.getRoad(townNames[2], townNames[11]));
		assertEquals("I-746", graph.getRoad(townNames[3], townNames[7]));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("Town_12"));
		graph.addTown("Town_12");
		assertEquals(true, graph.containsTown("Town_12"));
	}
	
	@Test
	public void testDisjointGraph() {
		assertEquals(false, graph.containsTown("Town_12"));
		graph.addTown("Town_12");
		ArrayList<String> path = graph.getPath(townNames[1],"Town_12");
		assertFalse(path.size() > 0);
	}

	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		assertEquals(false, graph.containsTown("Town_12"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(townNames[2], townNames[11]));
		assertEquals(false, graph.containsRoadConnection(townNames[3], townNames[5]));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Beltway", roads.get(0));
		assertEquals("East Corner", roads.get(1));
		assertEquals("Express Lane", roads.get(2));
		assertEquals("West Highway", roads.get(10));
		assertEquals("West Lane", roads.get(11));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(townNames[2], townNames[11]));
		graph.deleteRoadConnection(townNames[2], townNames[11], "Express Lane");
		assertEquals(false, graph.containsRoadConnection(townNames[2], townNames[11]));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		graph.deleteTown(townNames[2]);
		assertEquals(false, graph.containsTown("Town_2"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> towns = graph.allTowns();
		assertEquals("Town_1", towns.get(0));
		assertEquals("Town_10", towns.get(1));
		assertEquals("Town_11", towns.get(2));
		assertEquals("Town_2", towns.get(3));
		assertEquals("Town_8", towns.get(9));
	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath(townNames[1],townNames[11]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_1 via Julia Lane to Town_2 2 mi", path.get(0).trim());
		  assertEquals("Town_2 via Express Lane to Town_11 6 mi", path.get(1).trim());

	}
	
	@Test
	public void testGetPathA() {
		ArrayList<String> path = graph.getPath(townNames[1],townNames[10]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_1 via James Highway to Town_3 4 mi", path.get(0).trim());
		  assertEquals("Town_3 via SR-453 to Town_8 2 mi", path.get(1).trim());
		  assertEquals("Town_8 via Beltway to Town_10 2 mi", path.get(2).trim());
	}	
	
	@Test
	public void testGetPathB() {
		ArrayList<String> path = graph.getPath(townNames[1],townNames[6]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_1 via James Highway to Town_3 4 mi", path.get(0).trim());
		  assertEquals("Town_3 via SR-453 to Town_8 2 mi", path.get(1).trim());
		  assertEquals("Town_8 via Beltway to Town_10 2 mi", path.get(2).trim());
		  assertEquals("Town_10 via East Corner to Town_9 4 mi", path.get(3).trim());
		  assertEquals("Town_9 via West Lane to Town_6 3 mi", path.get(4).trim());

	}
}