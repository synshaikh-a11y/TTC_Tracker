package com.transit.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    private String routeTag;
    private String title;
    private String colour;
    private List<Stop> stops;
}