import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for auxiliary methods in {@code MathStuff}.
 *
<!--//# BEGIN TODO Name, student id, and date-->
<p><b>Nikola, 1552686, 24/11/2021</b></p>
<!--//# END TODO-->
*/

public class MathStuffTestAuxiliary {
    //TODO: implement test cases for the auxiliary methods **ONLY**

    /**
     * Tests for the auxiliary methods in the class MathStuff
     */

    /**
     * Tests for GCD
     */
    
    @Test
    public void testGCD45_36() {
        int a = 45;
        int b = 36;

        int expected = 9;
        int result = MathStuff.gcd(a, b);

        assertEquals("GCD: ", expected, result);
    }

    @Test
    public void testGCD1_2() {
        int a = 1;
        int b = 2;

        int expected = 1;
        int result = MathStuff.gcd(a, b);

        assertEquals("GCD: ", expected, result);
    }

    @Test
    public void testGCD2_1() {
        int a = 1;
        int b = 2;

        int expected = 1;
        int result = MathStuff.gcd(b, a);

        assertEquals("GCD: ", expected, result);
    }

    /**
     * Tests for primeFactors
     */


}
