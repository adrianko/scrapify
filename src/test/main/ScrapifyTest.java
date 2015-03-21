package test.main;

import main.Scrapify;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ScrapifyTest {
    
    Scrapify scrapify = new Scrapify();
    List<String> absolutePath = Arrays.asList("", ".abc[0]", ".def[0]", "text()");
    List<String> relativePath = Arrays.asList(".abc[0]", ".def[0]", "@data-test");
    
    @Test
    public void parse() {
        List<String> exp = new LinkedList<>(absolutePath);
        List<String> act = scrapify.parse("/.abc[0]/.def[0]/text()");

        Assert.assertEquals(exp, act);
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