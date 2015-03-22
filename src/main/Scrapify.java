package main;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Scrapify {
    
    public String html;
    
    public static void main(String[] args) {
        String path = "/.abc[0]/.def[0]/text()";
        String path2 = "/.abc[0]/.def[0]/@data-test";
        
        String html = "<html><head><title>123</title></head><body><div class=\"abc\"><div class=\"def\" " +
            "data-test=\"test\">123</div>456</div>789</body></html>";
        
        Scrapify s = new Scrapify();
        s.parse(path);
    }
    
    public void setHTML(String html) {
        this.html = html;
    }
    
    public List<String> parse(String path) {
        return new LinkedList<>(Arrays.asList(path.split("/")));
    }
    
    public boolean absolute(List<String> path) {
        return path.get(0).equals("");
    }
    
}