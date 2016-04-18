package com.example.primes.algo;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleFinderTest {
    @Test
    public void testFirstTenPrimes() {
        final SimplePrimeFinder simplePrimeFinder = new SimplePrimeFinder();
        final List<Integer> actualPrimes = ImmutableList.copyOf(simplePrimeFinder.primes().take(10).toBlocking().toIterable());
        assertThat(actualPrimes, is(ImmutableList.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)));
    }

    @Test
    public void testSkipFirstTenPrimes() {
        final SimplePrimeFinder simplePrimeFinder = new SimplePrimeFinder();
        final Integer eleventhPrime = simplePrimeFinder.primes().skip(10).toBlocking().toIterable().iterator().next();
        assertThat(eleventhPrime, is(31));
    }
}
