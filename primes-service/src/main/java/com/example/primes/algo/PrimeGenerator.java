package com.example.primes.algo;

import rx.Observable;

public interface PrimeGenerator {
    Observable<Integer> primes();
}
