package com.example.primes.algo;

import rx.Observable;

import java.util.Iterator;

public class SimplePrimeFinder implements PrimeFinder {
    private static class AllPositiveIntegersIterator implements Iterator<Integer> {
        private int current = 2;

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Integer next() {
            return current++;
        }
    }

    private static boolean isPrime(final int l) {
        return !Observable
            .from(AllPositiveIntegersIterator::new)
            .takeWhile(n -> n * n <= l)
            .filter(n -> l % n == 0)
            .toBlocking()
            .toIterable()
            .iterator()
            .hasNext();
    }

    @Override
    public Observable<Integer> primes() {
        return Observable
            .from(AllPositiveIntegersIterator::new)
            .filter(SimplePrimeFinder::isPrime);
    }
}
