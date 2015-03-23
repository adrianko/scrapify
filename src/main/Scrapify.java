package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
    
}