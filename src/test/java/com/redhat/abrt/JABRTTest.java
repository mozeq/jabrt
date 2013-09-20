package com.redhat.abrt;

import java.io.PrintWriter;
import java.io.StringWriter;

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

    @Test
    public void testAddThrowable()
    {
        Throwable t = new Throwable("Testing throwable");
        ProblemDataKey key = ProblemDataKey.BACKTRACE;
        ProblemData pd = new ProblemDataAbrt();

        StringWriter stringWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(stringWriter);
        t.printStackTrace(pWriter);

        pd.add(key, t);
        org.junit.Assert.assertTrue("Trying to get the value of the key: '"+key+"'", pd.get(key).compareTo(stringWriter.toString()) == 0);

    }
}
