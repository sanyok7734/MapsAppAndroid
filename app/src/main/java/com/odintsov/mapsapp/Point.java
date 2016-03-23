package com.odintsov.mapsapp;

public class Point {

    private double longitude;

    private double latitude;

    public Point(double latitude, double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return "Point[" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ']';
    }
}
