package com.example.primes.algo;

import rx.Observable;

public interface PrimeFinder {
    Observable<Integer> primes();
}
