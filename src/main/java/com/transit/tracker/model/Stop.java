package com.transit.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stop {
    private String stopId;
    private String stopTag;
    private String name;
    private Double latitude;
    private Double longitude;
}