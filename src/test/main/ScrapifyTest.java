package test.main;

import main.Scrapify;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrapifyTest {

    public static String basePath = new File(ScrapifyTest.class.getResource(".").getPath() + "../../../../../")
        .getAbsolutePath();
    Scrapify scrapify = new Scrapify();
    List<String> absolutePath = Arrays.asList("", ".abc[0]", ".def[0]", "text()");
    List<String> relativePath = Arrays.asList(".abc[0]", ".def[0]", "@data-test");
    
    @Test
    public void parse() {
        scrapify.setHTML("<html></html>");
        Assert.assertEquals(scrapify.parse("").getClass(), String.class);
    }
    
    @Test
    public void absoluteTrue() {
        boolean act = scrapify.absolute(absolutePath);
        
        Assert.assertTrue(act);
    }
    
    @Test
    public void absoluteFalse() {
        boolean act = scrapify.absolute(relativePath);
        
        Assert.assertFalse(act);
    }
    
    @Test
    public void getPaths() {
        Map<String, String> exp = new HashMap<>();
        exp.put("text", String.join("/", absolutePath));
        exp.put("attr", "/" + String.join("/", relativePath));

        Map<String, String> act = scrapify.getPaths(basePath + "/paths.json");
        
        Assert.assertEquals(exp, act);
    }
    
    @Test
    public void getHTML() {
        String exp = "<html><head><title>123</title></head><body><div class=\"abc\"><div class=\"def\" " +
            "data-test=\"test\">123</div>456</div>789</body></html>";
        String act = scrapify.getHTML(basePath + "/data.html");
        
        Assert.assertEquals(exp, act);
    }
    
    @Test
    public void basePath() {
        Assert.assertEquals(Paths.get(basePath).normalize(), Paths.get(Scrapify.basePath).normalize());
    }
    
}