package com.example.primes.algo;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

// Handy table https://primes.utm.edu/lists/small/1000.txt
public class IncrementalSievePrimeGeneratorTest {
    @Test
    public void testFirstTenPrimes() {
        final PrimeGenerator generator = new IncrementalSievePrimeGenerator();
        final List<Integer> actualPrimes = ImmutableList.copyOf(generator.primes().limit(10).iterator());
        assertThat(actualPrimes, is(ImmutableList.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)));
    }

    @Test
    public void testSkipFirstTenPrimes() {
        final PrimeGenerator generator = new IncrementalSievePrimeGenerator();
        final Integer eleventhPrime = generator.primes().skip(10).iterator().next();
        assertThat(eleventhPrime, is(31));
    }

    @Test
    public void testThirdTenPrimes() {
        final PrimeGenerator generator = new IncrementalSievePrimeGenerator();
        final List<Integer> actualPrimes = ImmutableList.copyOf(generator.primes().limit(30).skip(20).iterator());
        assertThat(actualPrimes, is(ImmutableList.of(73, 79, 83, 89, 97, 101, 103, 107, 109, 113)));
    }

    @Test
    public void testCompareWithSimple() {
        final PrimeGenerator simpleGenerator = new SimplePrimeGenerator();
        final PrimeGenerator sieveGenerator = new IncrementalSievePrimeGenerator();

        final IntStream simplePrimes = simpleGenerator.primes();
        final IntStream sievePrimes = sieveGenerator.primes();

        final int N = 50000;

        final List<Integer> firstSimplePrimes = simplePrimes.limit(N).boxed().collect(Collectors.toList());
        final List<Integer> firstSievePrimes = sievePrimes.limit(N).boxed().collect(Collectors.toList());

        assertThat(firstSievePrimes, is(firstSimplePrimes));
    }
}
