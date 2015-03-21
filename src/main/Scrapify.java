package main;

public class Scrapify {
    
    public static void main(String[] args) {
        String path = "/.abc[0]/.def[0]/text()";
        String path2 = "/.abc[0]/.def[0]/@data-test";
        
        String html = "<html><head><title>123</title></head><body><div class=\"abc\"><div class=\"def\" " +
            "data-test=\"test\">123</div>456</div>789</body></html>";
    }
    
}