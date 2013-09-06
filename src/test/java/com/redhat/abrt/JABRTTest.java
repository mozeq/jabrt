package com.redhat.abrt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class JABRTTest
{
    @Test
    public void testAdd()
    {
        final String value = "world";
        final String key = "hello";
        ProblemData pd = new ProblemDataAbrt();
        pd.add(key, value);
        org.junit.Assert.assertTrue("Trying to get the value of the key: '"+key+"'", pd.get("hello").compareTo(value) == 0);

    }
}
