package com.transit.tracker.service;

import com.transit.tracker.model.Prediction;
import com.transit.tracker.model.Route;
import com.transit.tracker.model.Stop;
import com.transit.tracker.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TTCService {
    private static final Logger logger = LoggerFactory.getLogger(TTCService.class);
    
    /**
     * Get all TTC routes
     */
    public List<Route> getAllRoutes() {
        logger.info("Fetching all TTC routes...");
        
        List<Route> routes = new ArrayList<>();
        routes.add(new Route("501", "501 Queen", "#ED1B2E", new ArrayList<>()));
        routes.add(new Route("504", "504 King", "#ED1B2E", new ArrayList<>()));
        routes.add(new Route("505", "505 Dundas", "#ED1B2E", new ArrayList<>()));
        routes.add(new Route("510", "510 Spadina", "#ED1B2E", new ArrayList<>()));
        routes.add(new Route("512", "512 St Clair", "#ED1B2E", new ArrayList<>()));
        
        logger.info("Found {} routes", routes.size());
        return routes;
    }
    
    /**
     * Get stops for a specific route
     */
    public List<Stop> getStopsForRoute(String routeTag) {
        logger.info("Fetching stops for route: {}", routeTag);
        
        List<Stop> stops = new ArrayList<>();
        
        // Testing sample route:501
        if (routeTag.equals("501")) {
            stops.add(new Stop("1", "15419", "Queen St East at Neville Park Blvd", 43.6641, -79.2988));
            stops.add(new Stop("2", "15420", "Queen St East at Woodbine Ave", 43.6641, -79.3089));
            stops.add(new Stop("3", "15421", "Queen St East at Coxwell Ave", 43.6685, -79.3188));
            stops.add(new Stop("4", "15422", "Queen St East at Greenwood Ave", 43.6685, -79.3301));
            stops.add(new Stop("5", "15423", "Queen St East at Pape Ave", 43.6692, -79.3443));
        } else {
            // Generic stops for other routes
            stops.add(new Stop("1", routeTag + "_1", "Stop 1", 43.6532, -79.3832));
            stops.add(new Stop("2", routeTag + "_2", "Stop 2", 43.6552, -79.3812));
        }
        
        logger.info("Found {} stops for route {}", stops.size(), routeTag);
        return stops;
    }
    
    /**
     * Get arrival predictions for a specific stop
     */
    public List<Prediction> getPredictionsForStop(String stopId) {
        logger.info("Fetching predictions for stop: {}", stopId);
        
        List<Prediction> predictions = new ArrayList<>();
        predictions.add(new Prediction(
            "501", "501 Queen", stopId, "Queen at Yonge",
            240, 4, "East - towards Neville Park", "4512"
        ));
        predictions.add(new Prediction(
            "501", "501 Queen", stopId, "Queen at Yonge",
            480, 8, "East - towards Neville Park", "4525"
        ));
        predictions.add(new Prediction(
            "501", "501 Queen", stopId, "Queen at Yonge",
            720, 12, "East - towards Neville Park", "4538"
        ));
        
        logger.info("Found {} predictions for stop {}", predictions.size(), stopId);
        return predictions;
    }
    
    /**
     * Get vehicle locations for a route
     */
    public List<Vehicle> getVehicleLocations(String routeTag) {
        logger.info("Fetching vehicle locations for route: {}", routeTag);
        
        List<Vehicle> vehicles = new ArrayList<>();
        
        // Sample vehicle locations for Queen streetcar
        if (routeTag.equals("501")) {
            vehicles.add(new Vehicle("4512", routeTag, "east", 43.6532, -79.3832, 90, System.currentTimeMillis()));
            vehicles.add(new Vehicle("4525", routeTag, "east", 43.6552, -79.3512, 90, System.currentTimeMillis()));
            vehicles.add(new Vehicle("4538", routeTag, "west", 43.6585, -79.3212, 270, System.currentTimeMillis()));
        } else {
            vehicles.add(new Vehicle("1001", routeTag, "north", 43.6532, -79.3832, 0, System.currentTimeMillis()));
            vehicles.add(new Vehicle("1002", routeTag, "south", 43.6552, -79.3812, 180, System.currentTimeMillis()));
        }
        
        logger.info("Found {} vehicles for route {}", vehicles.size(), routeTag);
        return vehicles;
    }
}