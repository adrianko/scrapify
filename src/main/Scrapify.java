package main;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Scrapify {
    
    public static String basePath = new File(Scrapify.class.getResource(".").getPath() + "../../../../").getAbsolutePath();
    public String html;
    
    public static void main(String[] args) {
        Scrapify s = new Scrapify();
        //Map<String, String> elements = s.getPaths(basePath + "/paths.json");
        //System.out.println(elements);
        
        String html = s.getHTML(basePath + "/data.html");
        System.out.println(html);
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
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "";
    }
    
    public Map<String, String> getPaths(String filePath) {
        Map<String, String> paths = new HashMap<>();

        try {
            String file = Files.readAllLines(Paths.get(new File(filePath).getAbsolutePath())).toString();
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