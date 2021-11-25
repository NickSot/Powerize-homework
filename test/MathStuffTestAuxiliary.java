import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
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

    /**
     * Method to call from the test cases
     * @param expected
     * @param n
     */
    private void checkPrimeFactors(int n, ArrayList<MathStuff.Power> expected) {
        List<MathStuff.Power> result = MathStuff.primeFactors(n);

        result.stream().forEach(p -> {
            System.out.println(p.base + "^" + p.exponent);
        });

        assertArrayEquals("Test prime factors with 75", expected.stream().toArray(), result.stream().toArray());
    }

    @Test
    public void testPrimeFactors75(){
        ArrayList<MathStuff.Power> expected = new ArrayList<>();

        expected.add(new MathStuff.Power(3, 1));
        expected.add(new MathStuff.Power(5, 2));

        checkPrimeFactors(75,expected);
    }

    @Test
    public void testPrimeFactors65() {
        ArrayList<MathStuff.Power> expected = new ArrayList<>();

        expected.add(new MathStuff.Power(5, 1));
        expected.add(new MathStuff.Power(13, 1));

        checkPrimeFactors(65, expected);
    }

    @Test(timeout = 300)
    public void testPrimeFactors115757783() {
        ArrayList<MathStuff.Power> expected = new ArrayList<>();

        expected.add(new MathStuff.Power(5261, 1));
        expected.add(new MathStuff.Power(22003, 1));

        checkPrimeFactors(115757783, expected);
    }
}
