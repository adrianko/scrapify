package main;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Scrapify {
    
    public static String basePath = new File(Scrapify.class.getResource(".").getPath() + "../../../../")
        .getAbsolutePath();
    public String html;
    
    public static void main(String[] args) {
        Scrapify s = new Scrapify();
        s.setHTML(s.getHTML(basePath + "/data.html"));

        Map<String, Element> elements = new HashMap<>();
        s.getPaths(basePath + "/paths.json").forEach((element, path) -> {
            elements.put(element, s.parse(path));
        });

        System.out.println(elements);

    }
    
    public void setHTML(String html) {
        this.html = html;
    }
    
    public Element parse(String path) {
        //"text": "/.abc[0]/.def[0]/text()",
        //"attr": "/.abc[0]/.def[0]/@data-test"
        Document element = Jsoup.parse(html);
        
        for (String e : Arrays.asList(path.split("/"))) {
            if (e.startsWith("@") || e.endsWith("()")) {
                // attribute
            } else {
                // element
            }
        }
        
        return element.body();
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
            String file = new String(Files.readAllBytes(Paths.get(filePath)));
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