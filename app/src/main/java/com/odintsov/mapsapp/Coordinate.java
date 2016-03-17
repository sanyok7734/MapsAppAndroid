package com.odintsov.mapsapp;

public class Coordinate {

    private double lat;
    private double lot;
    private float bearing;

    public Coordinate(double lat, double lot, float bearing) {
        this.lat = lat;
        this.lot = lot;
        this.bearing = bearing;
    }

    public double getLat() {
        return lat;
    }

    public double getLot() {
        return lot;
    }

    public float getBearing() {
        return bearing;
    }
}
