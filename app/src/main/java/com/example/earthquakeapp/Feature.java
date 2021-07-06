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
        return "Place: " + properties.getPlace() +
                "\nMagnitude: " + properties.getMag() +
                "\nSignificance of Earthquake(Aggregate): " + properties.getSig() +
                "\nInstrumental Intensity of Event: " + properties.getMmi();
    }

    public double[] returnCoordinates(){
        return geometry.getCoordinates();
    }

    public String returnPlace(){
        String result;
        result = properties.getPlace();
        result = result.split("of ")[1];
        return result;
    }
    public String displayCoordinates(){
        String s = "";
        for (Double d: geometry.getCoordinates()){
            s = s.concat(d.toString());
            s = s.concat(" ,");
        }
        System.out.println("THE STRING IS: "+ s);
        System.out.println("@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@");

        return s;
    }

    public String mag_string(){
        String result;
        result = String.valueOf(properties.getMag());
        result = result.split("of ")[-1];
        return result;
    }
}
