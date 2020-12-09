package com.city.controller;

import com.city.service.ICitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class CitiesController {

    @Autowired
    private ICitiesService ICitiesService;

    @GetMapping("/connected")
    public String isConnected(@PathParam("origin") String origin, @PathParam("destination") String destination){

        return ICitiesService.isConnected(origin, destination);

    }
}
