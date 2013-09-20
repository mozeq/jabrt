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
        final String value = "Some reason";
        ProblemDataKey key = ProblemDataKey.REASON;
        ProblemData pd = new ProblemDataAbrt();
        pd.add(key, value);
        org.junit.Assert.assertTrue("Trying to get the value of the key: '"+key+"'", pd.get(key).compareTo(value) == 0);

    }
}
