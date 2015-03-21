package test.main;

import main.Scrapify;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ScrapifyTest {
    
    Scrapify s = new Scrapify();
    List<String> path = Arrays.asList("", ".abc[0]", ".def[0]", "text()");
    
    @Test
    public void parse() {
        List<String> exp = new LinkedList<>(path);
        List<String> act = s.parse("/.abc[0]/.def[0]/text()");

        Assert.assertEquals(exp, act);
    }
    
    public void absoluteTrue() {
        boolean act = s.absolute(path);
        
        Assert.assertTrue(act);
    }
    
}