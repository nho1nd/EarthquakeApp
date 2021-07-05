package com.example.earthquakeapp;

public class Feature {
    private Properties properties;
    private Geometry geometry;
    private String id;
    private String type;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "properties=" + properties +
                ", geometry=" + geometry +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
