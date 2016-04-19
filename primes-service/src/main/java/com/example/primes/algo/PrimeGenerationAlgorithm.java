package com.example.primes.algo;

public enum PrimeGenerationAlgorithm {
    SIMPLE,
    INCREMENTAL_SIEVE;

    @SuppressWarnings("unused")
    public static PrimeGenerationAlgorithm fromString(final String s) {
        return PrimeGenerationAlgorithm.valueOf(s.toUpperCase());
    }
}
