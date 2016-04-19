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
        private final Multimap<Long, Long> multipleToPrimes;
        private final long current;
        private final boolean isCurrentPrime;

        private SieveState(Multimap<Long, Long> multipleToPrimes, long current) {
            this.multipleToPrimes = multipleToPrimes;
            this.current = current;

            if (multipleToPrimes.containsKey(current)) {
                final Collection<Long> value = multipleToPrimes.get(current);
                final Long[] primeFactors = value.toArray(new Long[value.size()]);
                multipleToPrimes.removeAll(current);
                for (final Long prime : primeFactors) {
                    if (current + prime < current) throw new RuntimeException("overflow!");
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

        long getCurrent() {
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
            .mapToInt(value -> (int)value.longValue());
    }
}
