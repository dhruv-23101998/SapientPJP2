package com.sapient.maven;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestCases {
    private CheckEven app;
    
    @Before
    public void setup() {
        app = new CheckEven();
    }
    
    
    @Test
    public void test1() {
        boolean ans = app.check(5);
        assertTrue(ans == false);
    }

    @Test
    public void test2() {
        boolean ans = app.check(89);
        assertTrue(ans == false);
    }

    @Test
    public void test3() {
        boolean ans = app.check(58);
        assertTrue(ans == true);
    }

    @Test
    public void test4() {
        boolean ans = app.check(92);
        assertTrue(ans == true);
    }

    @Test
    public void test5() {
        boolean ans = app.check(17);
        assertTrue(ans == false);
    }
}