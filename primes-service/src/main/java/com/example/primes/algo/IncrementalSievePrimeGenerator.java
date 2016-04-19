package com.example.primes.algo;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;


// This attempts to implement a simple version of the lazy incremental sieve algorithm from
// https://www.cs.hmc.edu/~oneill/papers/Sieve-JFP.pdf
class IncrementalSievePrimeGenerator implements PrimeGenerator {
    private static class SieveState {
        private final Multimap<Integer, Integer> multipleToPrimes;
        private final int current;
        private final boolean isCurrentPrime;

        private SieveState(Multimap<Integer, Integer> multipleToPrimes, int current) {
            this.multipleToPrimes = multipleToPrimes;
            this.current = current;

            if (multipleToPrimes.containsKey(current)) {
                final Collection<Integer> value = multipleToPrimes.get(current);
                final Integer[] primeFactors = value.toArray(new Integer[value.size()]);
                multipleToPrimes.removeAll(current);
                for (Integer prime : primeFactors) {
                    multipleToPrimes.put(current + prime, prime);
                }
                this.isCurrentPrime = false;
            } else {
                multipleToPrimes.put(current * current, current);
                this.isCurrentPrime = true;
            }
        }

        static SieveState initialState() {
            return new SieveState(HashMultimap.create(), 2);
        }

        SieveState next() {
            return new SieveState(multipleToPrimes, current + 1);
        }

        int getCurrent() {
            return current;
        }

        boolean isCurrentPrime() {
            return isCurrentPrime;
        }
    }


    @Override
    public IntStream primes() {
        return Stream.iterate(SieveState.initialState(), SieveState::next)
            .filter(SieveState::isCurrentPrime)
            .map(SieveState::getCurrent)
            .mapToInt(value -> value);
    }
}
