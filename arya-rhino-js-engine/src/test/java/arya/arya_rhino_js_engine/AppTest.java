package arya.arya_rhino_js_engine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
    
	public static void main(String[] args) {

		String script=
				"		a = function() {print(javaFunction1('a'));};"
				+ "		b = function() {print(javaFunction1('b'));};"
				+ " a();";
		
		JsEngineUtil r = new JsEngineUtil();
		r.jsRun(script);
	}
    
    
}
