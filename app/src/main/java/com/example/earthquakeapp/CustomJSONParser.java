package com.example.earthquakeapp;

import android.content.res.AssetManager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomJSONParser {
    public List <Feature> readJsonWithObjectMapper(Map <String, String> searchCriteria, InputStream earthquakeData) throws IOException {
        //Jackson ObjectMapper: https://springframework.guru/processing-json-jackson/
        ObjectMapper objectMapper = new ObjectMapper();
        List <Feature> searchResult = null;
        try {
            //Turning JSON file into a stream/string

            //FileInputStream earthquakeData = new FileInputStream("app/assets/EarthquakeData.json");
            //Making a Type Reference to a list of Features
            TypeReference<List<Feature>> typeReference= new TypeReference<List<Feature>>(){};

            //Convert JSON data into list of features using Jackson objectMapper
            List <Feature> feature = objectMapper.readValue(earthquakeData, typeReference);

            //Early code to make sure JSON was parsed correctly
            //Feature feature = objectMapper.readValue(new FileInputStream("src/main/resources/EarthquakeData.json"), Feature.class);

            //An example of filtering the parsed JSON file

          //  for (Feature f: feature) {
           //     System.out.println(f.getProperties().getPlace());
           // }

            Stream<Feature> chaining = feature.stream();

            for (String key : searchCriteria.keySet()) {
                System.out.println("Value: " + searchCriteria.get(key));
                System.out.println("Key: " + key);
                switch (key) {
                    case "mag":
                        chaining = chaining.filter(ftr->ftr.getProperties().getMag() == Double.parseDouble(searchCriteria.get(key)));
                        break;
                    case "place":
                        chaining = chaining.filter(ftr->ftr.getProperties().getPlace().contains(searchCriteria.get(key)));
                        break;
                    case "time":
                        chaining = chaining.filter(ftr->ftr.getProperties().getTime() == Long.parseLong(searchCriteria.get(key)));
                        break;
                    case "updated":
                        chaining = chaining.filter(ftr->ftr.getProperties().getUpdated() == Long.parseLong(searchCriteria.get(key)));
                        break;
                    case "tz":
                        chaining = chaining.filter(ftr->ftr.getProperties().getTz().contains(searchCriteria.get(key)));
                        break;
                    case "url":
                        chaining = chaining.filter(ftr->ftr.getProperties().getUrl().contains(searchCriteria.get(key)));
                        break;
                    case "detail":
                        chaining = chaining.filter(ftr->ftr.getProperties().getDetail().contains(searchCriteria.get(key)));
                        break;
                    case "felt":
                        chaining = chaining.filter(ftr->ftr.getProperties().getFelt() == Integer.parseInt(searchCriteria.get(key)));
                        break;
                    case "cdi":
                        chaining = chaining.filter(ftr->ftr.getProperties().getCdi() == Double.parseDouble(searchCriteria.get(key)));
                        break;
                    case "mmi":
                        chaining = chaining.filter(ftr->ftr.getProperties().getMmi() == Double.parseDouble(searchCriteria.get(key)));
                        break;
                    case "alert":
                        chaining = chaining.filter(ftr->ftr.getProperties().getAlert().contains(searchCriteria.get(key)));
                        break;
                    case "status":
                        chaining = chaining.filter(ftr->ftr.getProperties().getStatus().contains(searchCriteria.get(key)));
                        break;
                    case "tsunami":
                        chaining = chaining.filter(ftr->ftr.getProperties().getTsunami() == Boolean.parseBoolean(searchCriteria.get(key)));
                        break;
                    case "sig":
                        chaining = chaining.filter(ftr->ftr.getProperties().getSig() == Integer.parseInt(searchCriteria.get(key)));
                        break;
                    case "net":
                        chaining = chaining.filter(ftr->ftr.getProperties().getNet().contains(searchCriteria.get(key)));
                        break;
                    case "code":
                        chaining = chaining.filter(ftr->ftr.getProperties().getCode().contains(searchCriteria.get(key)));
                        break;
                    case "ids":
                        chaining = chaining.filter(ftr->ftr.getProperties().getIds().contains(searchCriteria.get(key)));
                        break;
                    case "sources":
                        chaining = chaining.filter(ftr->ftr.getProperties().getSources().contains(searchCriteria.get(key)));
                        break;
                    case "types":
                        chaining = chaining.filter(ftr->ftr.getProperties().getTypes().contains(searchCriteria.get(key)));
                        break;
                    case "nst":
                        chaining = chaining.filter(ftr->ftr.getProperties().getNst() == Integer.parseInt(searchCriteria.get(key)));
                        break;
                    case "dmin":
                        chaining = chaining.filter(ftr->ftr.getProperties().getDmin() == Double.parseDouble(searchCriteria.get(key)));
                        break;
                    case "rms":
                        chaining = chaining.filter(ftr->ftr.getProperties().getRms() == Integer.parseInt(searchCriteria.get(key)));
                        break;
                    case "gap":
                        chaining = chaining.filter(ftr->ftr.getProperties().getGap() == Integer.parseInt(searchCriteria.get(key)));
                        break;
                    case "magType":
                        chaining = chaining.filter(ftr->ftr.getProperties().getMagType().contains(searchCriteria.get(key)));
                        break;
                    case "type_properties":
                        chaining = chaining.filter(ftr->ftr.getProperties().getType().contains(searchCriteria.get(key)));
                        break;
                        //string
                    case "title":
                        chaining = chaining.filter(ftr->ftr.getProperties().getTitle().contains(searchCriteria.get(key)));
                        break;
                    case "type_geometry":
                        chaining = chaining.filter(ftr->ftr.getGeometry().getType().contains(searchCriteria.get(key)));
                        break;
                    case "coordinates":
                        //Currently figuring out how to do array comparisons specifically for filtering
                        break;
                }
            }
            //chain all key-value pairs
            searchResult = chaining.collect(Collectors.toList());
            if(searchResult == null || searchResult.size() == 0) {
                System.out.println("No such object found");
                return null;
            } return searchResult;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
