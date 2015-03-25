package test.main;

import main.Scrapify;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
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
        Assert.assertEquals(scrapify.parse("").getClass(), org.jsoup.nodes.Element.class);
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
    
}