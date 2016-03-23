package com.odintsov.mapsapp;

public class PointPair {

    private Point start;

    private Point finish;

    public PointPair(Point start, Point finish) {
        this.start = start;
        this.finish = finish;
    }

    public Point getStart() {
        return start;
    }

    public Point getFinish() {
        return finish;
    }

    @Override
    public String toString() {
        return "Pair [" +
                "start=" + start +
                ", finish=" + finish +
                ']';
    }

}

