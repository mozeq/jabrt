package com.redhat.abrt;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import com.redhat.abrt.ProblemData;
import com.redhat.abrt.ProblemDataAbrt;
/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
        ProblemData pd = new ProblemDataAbrt();
        pd.add("hello", "world");
        System.out.println("Yay!");
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
