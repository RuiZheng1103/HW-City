package com.city.service;

import com.city.pojo.Cities;
import com.city.utils.io.FileDataReader;
import com.city.utils.io.IDataReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Component
public class CitiesService implements ICitiesService {

    @Value("${citydata.file.location}")
    private String fileLocation;

    private HashMap<String, Set<String>> cityGraph = new HashMap<>();

    @PostConstruct
    public void storeGraph() throws IOException {
        IDataReader IDataReader = new FileDataReader(fileLocation);
        System.out.println(fileLocation);
        List<Cities> cityList = IDataReader.getData();

        for (Cities cities : cityList){
            String city1 = cities.getCity1();
            String city2 = cities.getCity2();

            Set<String> city2Set = cityGraph.getOrDefault(city1, new HashSet<>());
            city2Set.add(city2);
            cityGraph.put(city1, city2Set);
            Set<String> city1Set = cityGraph.getOrDefault(city2, new HashSet<>());
            city1Set.add(city1);
            cityGraph.put(city2, city1Set);
        }
    }

    @Override
    public String isConnected(String origin, String destination) {
        if (!this.cityGraph.containsKey(origin) || !this.cityGraph.containsKey(destination)){
            return "No";
        }

        Queue<String> cityQueue = new ArrayDeque<>();
        cityQueue.offer(origin);
        Set<String> visited = new HashSet<>();

        String thisCity = null;
        Set<String> set = null;

        while (!cityQueue.isEmpty()){
            thisCity = cityQueue.poll();
            set = cityGraph.get(thisCity);
            visited.add(thisCity);

            for (String nextCity: set){
                if (!visited.contains(nextCity)){
                    if (nextCity.equals(destination)){
                        return "Yes";
                    }

                    cityQueue.offer(nextCity);
                }
            }
        }

        return "No";
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public HashMap<String, Set<String>> getCityGraph() {
        return cityGraph;
    }

    public void setCityGraph(HashMap<String, Set<String>> cityGraph) {
        this.cityGraph = cityGraph;
    }
}
