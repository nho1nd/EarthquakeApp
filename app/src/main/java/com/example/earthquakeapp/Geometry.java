package com.example.earthquakeapp;

import java.util.Arrays;
import java.util.List;

public class Geometry {

    private String type;
    private double [] coordinates;
    private String id;

    //Getters and setters


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Geometry{" +
                "type='" + type + '\'' +
                ", coordinates=" + Arrays.toString(coordinates) +
                ", id='" + id + '\'' +
                '}';
    }
}
