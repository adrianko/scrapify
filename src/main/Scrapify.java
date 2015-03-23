package main;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Scrapify {
    
    public String html;
    
    public static void main(String[] args) {
        Scrapify s = new Scrapify();
        
    }
    
    public void setHTML(String html) {
        this.html = html;
    }
    
    public List<String> parse(String path) {
        Element element = Jsoup.parse(html);
        System.out.println(element);
        return new LinkedList<>(Arrays.asList(path.split("/")));
    }
    
    public boolean absolute(List<String> path) {
        return path.get(0).equals("");
    }
    
    public String getHTML(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath)).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "";
    }
    
    public Map<String, String> getPaths(String filePath) {
        Map<String, String> paths = new HashMap<>();

        try {
            String file = Files.readAllLines(Paths.get(filePath)).toString();
            JSONObject json = (JSONObject) new JSONParser().parse(file);

            for (Object key : json.keySet()) {
                paths.put(key.toString(), String.valueOf(json.get(key.toString())));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        
        return paths;
    }
    
}