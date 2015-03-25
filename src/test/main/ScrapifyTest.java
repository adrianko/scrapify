package test.main;

import main.Scrapify;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ScrapifyTest {
    
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
    
}