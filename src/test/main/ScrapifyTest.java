package test.main;

import main.Scrapify;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ScrapifyTest {
    
    @Test
    public void parse() {
        List<String> exp = new LinkedList<>(Arrays.asList("", ".abc[0]", ".def[0]", "text()"));
        Scrapify s = new Scrapify();
        List<String> act = s.parse("/.abc[0]/.def[0]/text()");

        Assert.assertEquals(exp, act);
    }
    
}