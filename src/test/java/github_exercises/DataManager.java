package github_exercises;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.*;
import java.text.ParseException;


public class DataManager {


    public static JSONObject getJsonDefinition(String fileName) throws IOException, org.json.simple.parser.ParseException, NullPointerException{
        ClassLoader classLoader = DataManager.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        FileReader reader = new FileReader(file);
        org.json.simple.parser.JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(reader);
    }


    public static String getStringJsonDefinition(String fileName) throws IOException, ParseException, org.json.simple.parser.ParseException {
        return getJsonDefinition(fileName).toString();
    }


}
