package com.example.primes.algo;

import java.util.stream.IntStream;

class SimplePrimeGenerator implements PrimeGenerator {

    private static boolean isPrime(final int number) {
        return IntStream
            .rangeClosed(2, (int)Math.sqrt(number))
            .allMatch(value -> number % value != 0);
    }

    @Override
    public IntStream primes() {
        return IntStream
            .iterate(2, operand -> operand + 1)
            .filter(SimplePrimeGenerator::isPrime);
    }
}
