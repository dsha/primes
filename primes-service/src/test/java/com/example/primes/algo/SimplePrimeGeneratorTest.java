package com.example.primes.algo;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimplePrimeGeneratorTest {
    @Test
    public void testFirstTenPrimes() {
        final SimplePrimeGenerator simplePrimeFinder = new SimplePrimeGenerator();
        final List<Integer> actualPrimes = ImmutableList.copyOf(simplePrimeFinder.primes().limit(10).iterator());
        assertThat(actualPrimes, is(ImmutableList.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)));
    }

    @Test
    public void testSkipFirstTenPrimes() {
        final SimplePrimeGenerator simplePrimeFinder = new SimplePrimeGenerator();
        final Integer eleventhPrime = simplePrimeFinder.primes().skip(10).iterator().next();
        assertThat(eleventhPrime, is(31));
    }
}
