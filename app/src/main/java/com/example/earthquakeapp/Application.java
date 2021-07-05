package com.example.earthquakeapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application{
    public static void main(String args[]){
        CustomJSONParser JSONParser = new CustomJSONParser();
        Map <String, String > inputMap = new HashMap();
        //TODO: Tie in Android app input to execute .put command(s) below
        //Use below line as a template to look up things; more details in CustomJSONParser.java
        inputMap.put("coordinates", "-155.482,19.164,33.95");
        try{
            List<Feature> searchResult = JSONParser.readJsonWithObjectMapper(inputMap);
            System.out.println(searchResult);
        }catch(Exception e){

        }
    }
}
