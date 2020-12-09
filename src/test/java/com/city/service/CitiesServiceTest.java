package com.city.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

public class CitiesServiceTest {

    private static CitiesService cityConnectionServiceImpl1;
    private static CitiesService cityConnectionServiceImpl2;
    private static CitiesService cityConnectionServiceImpl3;

    @BeforeAll
    public static void init() throws IOException {
        cityConnectionServiceImpl1 =
                new CitiesService();

        cityConnectionServiceImpl1.setFileLocation("classpath:cityTest1.txt");
        cityConnectionServiceImpl1.storeGraph();

        cityConnectionServiceImpl2 =
                new CitiesService();

        cityConnectionServiceImpl2.setFileLocation("classpath:cityTest2.txt");
        cityConnectionServiceImpl2.storeGraph();

        cityConnectionServiceImpl3 =
                new CitiesService();

        cityConnectionServiceImpl3.setFileLocation("classpath:cityTest3.txt");
        cityConnectionServiceImpl3.storeGraph();

    }

    @DisplayName("storeGraphTest1: Create adjacent list, create graph with with single connection")
    @Test
    public void createGraphTest1() throws IOException {

        Map<String, Set<String>> expected = new HashMap<>();
        expected.put("Boston", new HashSet<>(Arrays.asList("New York")));
        expected.put("New York", new HashSet<>(Arrays.asList("Boston")));
        expected.put("Albany", new HashSet<>(Arrays.asList("Trenton")));
        expected.put("Trenton", new HashSet<>(Arrays.asList("Albany")));

        Assertions.assertEquals(expected, cityConnectionServiceImpl1.getCityGraph());

    }

    @DisplayName("storeGraphTest2: Create adjacent list, create graph with with complex connection")
    @Test
    public void createGraphTest2() throws IOException {

        Map<String, Set<String>> expected = new HashMap<>();
        expected.put("Boston", new HashSet<>(Arrays.asList("New York", "Newark")));
        expected.put("New York", new HashSet<>(Arrays.asList("Boston")));
        expected.put("Newark", new HashSet<>(Arrays.asList("Boston", "Philadelphia")));
        expected.put("Philadelphia", new HashSet<>(Arrays.asList("Newark")));
        expected.put("Albany", new HashSet<>(Arrays.asList("Trenton")));
        expected.put("Trenton", new HashSet<>(Arrays.asList("Albany")));

        Assertions.assertEquals(expected, cityConnectionServiceImpl2.getCityGraph());

    }

    @DisplayName("isConnectedTest1: test two neighbour cities, Boston and new york")
    @Test
    public void isConnected1(){
        Assertions.assertEquals("Yes",
                cityConnectionServiceImpl1.isConnected("Boston", "New York"));
    }

    @DisplayName("isConnectedTest2: test two unconnected cities, Boston and Albany")
    @Test
    public void isConnected2(){
        Assertions.assertEquals("No",
                cityConnectionServiceImpl1.isConnected("Boston", "Albany"));
    }

    @DisplayName("isConnectedTest3: test non-existed cities, Boston and Chicago")
    @Test
    public void isConnected3(){
        Assertions.assertEquals("No",
                cityConnectionServiceImpl1.isConnected("Boston", "Chicago"));
    }

    @DisplayName("isConnectedTest4: test connected cities, Boston, Philadelphia in complex graph")
    @Test
    public void isConnected4(){
        Assertions.assertEquals("Yes",
                cityConnectionServiceImpl2.isConnected("Boston", "Philadelphia"));
    }

    @DisplayName("isConnectedTest5: test connected cities, Boston, Albany in complex graph")
    @Test
    public void isConnected5(){
        Assertions.assertEquals("No",
                cityConnectionServiceImpl2.isConnected("Boston", "Albany"));
    }

    @DisplayName("isConnectedTest6: test invalid cities, Boston, Albany in complex graph")
    @Test
    public void isConnected6(){
        Assertions.assertEquals("No",
                cityConnectionServiceImpl2.isConnected("Boston", "Clark"));
    }

    @DisplayName("emptyGraphTest")
    @Test
    public void emptyTest1() throws IOException {

        Map<String, Set<String>> expected = new HashMap<>();

        Assertions.assertEquals(expected, cityConnectionServiceImpl3.getCityGraph());

    }

    @DisplayName("emptyGraphTest")
    @Test
    public void emptyTest2(){
        Assertions.assertEquals("No",
                cityConnectionServiceImpl3.isConnected("Boston", "Albany"));
    }

}
