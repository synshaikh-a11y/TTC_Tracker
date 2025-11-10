package com.transit.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private String id;
    private String routeTag;
    private String dirTag;
    private Double lat;
    private Double lon;
    private Integer heading;
    private Long lastUpdate;
}