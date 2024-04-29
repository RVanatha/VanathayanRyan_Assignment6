import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyRoadTest {

    @Test
    public void testGetName() {
        Road road = new Road(new Town("Smithville"), new Town("Oakland"), 34, "I395-Highway");
        assertEquals("I395-Highway", road.getName());
    }

    @Test
    public void testGetSource() {
        Town town1 = new Town("Smithville");
        Town town2 = new Town("Oakland");
        Road road = new Road(town1, town2, 34, "G67-Rootway");
        assertEquals(town1, road.getSource());
    }

    @Test
    public void testGetDestination() {
        Town town1 = new Town("Smithville");
        Town town2 = new Town("Oakland");
        Road road = new Road(town1, town2, 34, "G67-Rootway");
        assertEquals(town2, road.getDestination());
    }

    @Test
    public void testGetWeight() {
        Road road = new Road(new Town("Smithville"), new Town("Oakland"), 34, "T21-Jull");
        assertEquals(34, road.getWeight());
    }

    @Test
    public void testHashcode() {
        Road road = new Road(new Town("Smithville"), new Town("Oakland"), 34, "Route396");
        assertEquals(road.hashCode(), road.hashCode());
    }

    @Test
    public void testContains() {
        Town town1 = new Town("Smithville");
        Town town2 = new Town("Oakland");
        Road road = new Road(town1, town2, 23, "G67-Rootway");
        assertEquals(true, road.contains(town2));
        assertEquals(false, road.contains(new Town("Rockville")));
    }
}