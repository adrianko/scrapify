package main;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

    }
    
    public void setHTML(String html) {
        this.html = html;
    }
    
    public Element parse(String path) {
        //"text": "/.abc[0]/.def[0]/text()",
        //"attr": "/.abc[0]/.def[0]/@data-test"
        Document element = Jsoup.parse(html);
        Element el = element.body();
        
        for (String e : Arrays.asList(path.split("/"))) {
            if (e.startsWith("@")) {
                System.out.println(el.attr(e.substring(1)));
                System.out.println("--------------------------------");
            } else if (e.endsWith("()")) {
                //method
            } else if (!e.equals("")) {
                // element
                int index = Integer.parseInt(e.substring(e.indexOf("[") + 1, e.indexOf("]")));
                String sel = e.substring(0, e.indexOf("["));
                el = el.select(sel).get(index);
                System.out.println(el.outerHtml());
                System.out.println("--------------------------------");
            }
        }
        
        return element.body();
    }
    
    public boolean absolute(List<String> path) {
        return path.get(0).equals("");
    }
    
    public String getHTML(String filePath) {
        try {
            return String.join("", Files.readAllLines(Paths.get(filePath))).replaceAll("[\\t\\n\\r]+", "")
                .replaceAll(">\\s+<", "><").replaceAll("\\s+", " ").replaceAll(">\\s+", ">").replaceAll("\\s+<", "<");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "";
    }
    
    public Map<String, String> getPaths(String filePath) {
        Map<String, String> paths = new HashMap<>();

        try {
            JSONObject json = (JSONObject) new JSONParser().parse(new String(Files.readAllBytes(Paths.get(filePath))));
            
            for (Object pair : json.entrySet()) {
                Map.Entry p = (Map.Entry) pair;
                paths.put(p.getKey().toString(), p.getValue().toString());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        
        return paths;
    }
    
}