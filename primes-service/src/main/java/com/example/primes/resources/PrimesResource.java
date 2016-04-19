package com.example.primes.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.primes.algo.PrimeGenerator;
import com.example.primes.algo.SimplePrimeGenerator;
import io.dropwizard.jersey.caching.CacheControl;
import io.dropwizard.jersey.params.IntParam;

import javax.validation.constraints.Min;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.TimeUnit;

@Path("/primes")
@Produces(MediaType.APPLICATION_JSON)
public class PrimesResource {
    public PrimesResource() {
    }

    @GET
    @Timed
    @CacheControl(maxAge = 365, maxAgeUnit = TimeUnit.DAYS)
    public int getPrime(@QueryParam("index") @Min(1) IntParam index) {
        final PrimeGenerator primeGenerator = new SimplePrimeGenerator();
        return primeGenerator
            .primes()
            .skip(index.get() - 1)
            .iterator()
            .next();
    }
}
