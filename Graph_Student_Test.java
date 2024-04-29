import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyGraphTest {
    Graph graph;

    Road road1;
    Road road2;

    Town town1;
    Town town2;
    Town town3;
    Town town4;
    Town town5;
    Town town6;

    @BeforeEach
    void setUp() throws Exception {
        graph = new Graph();

        town1 = new Town("Sky");
        town2 = new Town("Hill");
        town3 = new Town("Water");
        town4 = new Town("Fire");
        town5 = new Town("Lemon");
        town6 = new Town("Apple");

        graph.addVertex(town1);
        graph.addVertex(town2);
        graph.addVertex(town3);
        graph.addVertex(town4);
        graph.addVertex(town5);
        graph.addVertex(town6);

        road1 = new Road(town1, town2, 2, "Road1");
        road2 = new Road(town3, town4, 2, "Road2");

        graph.addEdge(town1, town2, 2, "Road1");
        graph.addEdge(town3, town4, 2, "Road2");
    }

    @AfterEach
    void tearDown() throws Exception {
        road1 = road2 = null;
        town1 = town2 = town3 = town4 = town5 = town6 = null;
        graph = null;
    }


    @Test
    public void testAddEdge() {
        graph.addEdge(town5, town6, 5, "Dark");
        assertEquals(true, graph.containsEdge(town6, town5));
    }

    @Test
    public void testAddVertex() {
        Town newTown = new Town("mac");
        assertEquals(false, graph.containsVertex(newTown));
        graph.addVertex(newTown);
        assertEquals(true, graph.containsVertex(newTown));
    }

    @Test
    public void testContainsEdge() {
        graph.addEdge(town5, town6, 5, "Dark");
        assertEquals(true, graph.containsEdge(town6, town5));
    }

    @Test
    public void testContainsVertex() {
        assertEquals(true, graph.containsVertex(new Town("Sky")));
        assertEquals(true, graph.containsVertex(new Town("Hill")));
    }

    @Test
    public void testEdgeSet() {
        Set<Road> roads = graph.edgeSet();
        ArrayList<String> roadArrayList = new ArrayList<String>();
        for(Road road : roads)
            roadArrayList.add(road.getName());
        Collections.sort(roadArrayList);

        assertEquals("Road1", roadArrayList.get(0));
    }

    @Test
    public void testEdgesOf() {
        Set<Road> roads = graph.edgesOf(town1);
        ArrayList<String> roadArrayList = new ArrayList<String>();
        for(Road road : roads)
            roadArrayList.add(road.getName());
        Collections.sort(roadArrayList);

        assertEquals("Road1", roadArrayList.get(0));
    }

    @Test
    public void testRemoveEdge() {
        assertEquals(true, graph.containsEdge(town2, town1));
        graph.removeEdge(town1, town2, 6, "Road_12");
        assertEquals(false, graph.containsEdge(town1, town2));
    }

    @Test
    public void testRemoveVertex() {
        assertEquals(true, graph.containsVertex(town1));
        graph.removeVertex(town1);
        assertEquals(false, graph.containsVertex(town1));
    }

    @Test
    public void testVertexSet() {
        Set<Town> towns = graph.vertexSet();
        assertEquals(true, towns.contains(town2));
    }
}