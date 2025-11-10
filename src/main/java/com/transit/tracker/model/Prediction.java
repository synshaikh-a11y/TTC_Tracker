package com.transit.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prediction {
    private String routeTag;
    private String routeTitle;
    private String stopTag;
    private String stopTitle;
    private Integer seconds;
    private Integer minutes;
    private String directionTitle;
    private String vehicle;
}