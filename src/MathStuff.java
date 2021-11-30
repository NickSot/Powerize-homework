//# BEGIN SKELETON
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;

/**
 * Library with static mathematical functions.
 *
<!--//# BEGIN TODO Name, student id, and date-->
<p><b>Nikola, 1552686, 24/11/2021</b></p>
<!--//# END TODO-->
*/

public abstract class MathStuff {

    /**
     * Returns exponentiation, taking care of overflow.
     *
     * @param a  the base
     * @param b  the exponent
     * @pre {@code 0 <= a && 0 <= b}
     * @return {@code a ^ b} if {@code a ^ b <= Integer.MAX_VALUE}
     *      else {@code Long.MAX_VALUE}
     * @throws IllegalArgumentException  if precondition violated
     */

    public static long power(int a, int b) throws IllegalArgumentException {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("power: negative argument");
        }
        // 0 <= a && 0 <= b
        long x = a; // see invariant
        int k = b; // see invariant
        long result = 1L; // see invariant

        // invariant: 0 <= k <= b && result * x^k == a^b
        while (k != 0) {
            if (k % 2 == 0) { // even exponent
                if (x <= Integer.MAX_VALUE) {
                    x *= x;
                } else {
                    x = Long.MAX_VALUE;
                }
                k /= 2;
            } else { // odd exponent
                if (result <= Integer.MAX_VALUE && x <= Integer.MAX_VALUE) {
                    result *= x;
                } else {
                    result = Long.MAX_VALUE;
                }
                k -= 1;
            }
            // invariant holds again, k has decreased
        }
        // k == 0, hence result == a^b

        if (result > Integer.MAX_VALUE) {
            return Long.MAX_VALUE;
        }
        return result;
    }

    /**
     * Record containing a base and an exponent.
     *
     * @inv {@code 0 <= base && 0 <= exponent}
     */
    public static class Power { // BEGIN RECORD TYPE

        /** The base. */
        public int base;

        /** The exponent. */
        public int exponent;

        /**
         * Constructs a Power with given base and exponent.
         *
         * @param base  the base
         * @param exponent  the exponent
         * @pre {@code 0 <= base && 0 <= exponent}
         * @post {@code \result.base == base && \result.exponent == exponent}
         */
        public Power(int base, int exponent) {
            this.base = base;
            this.exponent = exponent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Power power = (Power) o;
            return base == power.base &&
                    exponent == power.exponent;
        }

        @Override
        public int hashCode() {
            return Objects.hash(base, exponent);
        }
    } // END RECORD TYPE

    /**
     * Returns exponentiation.
     *
     * @param p  the base and exponent
     * @pre {@code p != null}
     * @return {@code power(p.base, p.exponent)}
     * @throws IllegalArgumentException  if precondition violated
     */
    public static long power(Power p) throws IllegalArgumentException {
        return power(p.base, p.exponent);
    }

    /**
     * Writes a number as a power with maximal exponent.
     *
     * @param n  the number to 'powerize'
     * @return  power decomposition of {@code n} with maximal exponent
     * @throws IllegalArgumentException  if precondition violated
     * @pre {@code 2 <= n}
     * @post {@code n == power(\result) &&
     *     (\forall int b, int e;
     *      2 <= b && 1 <= e && n == b ^ e;
     *      e <= \result.exponent)}
     */
    public static Power powerize(int n) throws IllegalArgumentException {
        // TODO: Implement using Test Driven Development

        if (n < 2) {
            throw new IllegalArgumentException("The argument must have a value of at least 2.");
        }

        /**
         * find the prime factors of n (bases)
         * and derive their count during the factorization process (exponents)
         */

        List<Power> primePowers = primeFactors(n);

        //find the greatest common divisor amongst the exponents
        int maximalExponent = findMaximalExponent(primePowers);
        List<Long> bases = findCorrespondingBases(maximalExponent, primePowers);
        long resultBase = findMaximalBase(bases);

        return new Power((int)resultBase, maximalExponent);
    }

    // TODO Contracts and implementations of auxiliary functions.

    /**
     *
     * @param bases
     */
    public static long findMaximalBase(List<Long> bases) {
        long result = 1;

        for (int i = 0; i < bases.size(); i++) {
            result *= bases.get(i);
        }

        return result;
    }

    /**
     * return the corresponding bases to the changed exponents
     *
     * @param maximalExponent
     * @param primePowers
     * @return
     */
    public static List<Long> findCorrespondingBases(int maximalExponent, List<Power> primePowers) {
        ArrayList<Long> bases = new ArrayList<>();

        for (Power item : primePowers) {
            bases.add(power(item.base, item.exponent/maximalExponent));
        }

        return bases;
    }

    /**
     * finds the greatest common divisor of the exponents in the parameter list
     * @param primePowers
     * @return Power
     */

    public static int findMaximalExponent(List<Power> primePowers) {
        //gcd(a,b,c) = gcd(gcd(a,b),c) = gcd(a,gcd(b,c))

        int g = primePowers.get(0).exponent;

        for (int i = 0; i < primePowers.size(); i++) {
            g = gcd(g, primePowers.get(i).exponent);
        }

        return g;
    }

    /** This method returns the greatest common divisor of two numbers
     * @param a
     * @param b
     * @return gcd(a, b)
     */
    public static int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b,a % b);
    }


    /**
     * finds the prime factors of a number, and returns a list of bases and corresponding exponents
     * @param n
     * @return List<Power>
     */
    public static List<Power> primeFactors(int n)
    {
        ArrayList<Power> primeExponents = new ArrayList<>();

        if (n == 3) {
            // 3 is a boundary case
            primeExponents.add(new Power(3, 1));
            return primeExponents;
        }

        // Variable indicating the exponent
        int exponent = 0;

        // Find the exponent of 2 that fits in n
        while (n % 2 == 0) {
            exponent ++;
            n /= 2;
        }

        if (exponent > 0)
            primeExponents.add(new Power(2, exponent));

        //n must now be odd, so start with 3 and increment with 2 each iteration
        for (int i = 3; i <= n; i+=2) {
            if (i * i > n) {
                break;
            }

            exponent = 0;

            while (n % i == 0) {
                exponent++;
                n /= i;
            }

            if (exponent > 0)
                primeExponents.add(new Power(i, exponent));
        }

        return primeExponents;
    }

}
//# END SKELETON
