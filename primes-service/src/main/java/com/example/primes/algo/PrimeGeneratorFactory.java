package com.example.primes.algo;

public class PrimeGeneratorFactory {
    public static PrimeGenerator getGenerator(PrimeGenerationAlgorithm primeGenerationAlgorithm) {
        switch (primeGenerationAlgorithm) {
            case SIMPLE:
                return new SimplePrimeGenerator();
            case INCREMENTAL_SIEVE:
                return new IncrementalSievePrimeGenerator();
            default:
                throw new IllegalArgumentException("primeGenerationAlgorithm");
        }
    }
}
