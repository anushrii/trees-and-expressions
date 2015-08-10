package tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ExpressionTest {

    @Before
    public void setUp() throws Exception {}

    @Test
    public final void testExpression() {
    	try{
        Expression e = new Expression("+ (5 10 -( *(15 20) 25) 30)");
        assertTrue(true);
        }catch(IllegalArgumentException e1){
        	assertTrue(false);
        }
    }

    @Test
    public final void testEvaluate() {
    	Expression e = new Expression("+ (5 10 -( *(15 20) 25) 30)");
    	int i = e.evaluate();
    	assertEquals(i,320);
    }

    @Test
    public final void testToString() {
    	Expression e = new Expression("+ (5 10 -( *(15 20) 25) 30)");
    	
    	String actual = e.toString();
    	String expected = "(5 + 10 + ((15 * 20) - 25) + 30)";
    	System.out.println(actual);
    	assertEquals(actual,expected);
    }

}
