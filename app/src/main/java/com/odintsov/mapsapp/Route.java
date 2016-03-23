package com.odintsov.mapsapp;

public class Route {

    private String id;

    private String routeTitle;

    private PointPair routePoints;

    public Route(String routeTitle, PointPair routePoints) {
        this.routeTitle = routeTitle;
        this.routePoints = routePoints;
    }

    public String getRouteTitle() {
        return routeTitle;
    }

    public PointPair getRoutePoints() {
        return routePoints;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Route[" +
                "id='" + id + '\'' +
                ", routeTitle='" + routeTitle + '\'' +
                ", routePoints=" + routePoints +
                ']';
    }
}
