package com.transit.tracker.controller;

import com.transit.tracker.model.Prediction;
import com.transit.tracker.model.Route;
import com.transit.tracker.model.Stop;
import com.transit.tracker.model.Vehicle;
import com.transit.tracker.service.TTCService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TransitController {
    
    private final TTCService ttcService;
    
    // Constructor injection instead of @Autowired
    public TransitController(TTCService ttcService) {
        this.ttcService = ttcService;
    }
    
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("TTC Transit Tracker API is working! 🚌");
    }
    
    @GetMapping("/routes")
    public ResponseEntity<List<Route>> getAllRoutes() {
        List<Route> routes = ttcService.getAllRoutes();
        return ResponseEntity.ok(routes);
    }
    
    @GetMapping("/routes/{routeTag}/stops")
    public ResponseEntity<List<Stop>> getStopsForRoute(@PathVariable String routeTag) {
        List<Stop> stops = ttcService.getStopsForRoute(routeTag);
        return ResponseEntity.ok(stops);
    }
    
    @GetMapping("/stops/{stopId}/predictions")
    public ResponseEntity<List<Prediction>> getPredictionsForStop(@PathVariable String stopId) {
        List<Prediction> predictions = ttcService.getPredictionsForStop(stopId);
        return ResponseEntity.ok(predictions);
    }
    
    @GetMapping("/routes/{routeTag}/vehicles")
    public ResponseEntity<List<Vehicle>> getVehicleLocations(@PathVariable String routeTag) {
        List<Vehicle> vehicles = ttcService.getVehicleLocations(routeTag);
        return ResponseEntity.ok(vehicles);
    }
}