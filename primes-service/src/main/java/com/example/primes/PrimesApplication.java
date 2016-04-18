package com.example.primes;

import com.example.primes.resources.PrimesResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PrimesApplication extends Application<PrimesConfiguration> {
    public static void main(String[] args) throws Exception {
        new PrimesApplication().run(args);
    }

    @Override
    public String getName() {
        return "primes";
    }

    @Override
    public void initialize(Bootstrap<PrimesConfiguration> bootstrap) {
    }

    @Override
    public void run(PrimesConfiguration configuration, Environment environment) {
        environment.jersey().register(new PrimesResource());
    }
}
