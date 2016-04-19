package com.example.primes.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.primes.algo.PrimeGenerationAlgorithm;
import com.example.primes.algo.PrimeGenerator;
import com.example.primes.algo.PrimeGeneratorFactory;
import io.dropwizard.jersey.caching.CacheControl;
import io.dropwizard.jersey.params.IntParam;

import javax.validation.constraints.Min;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.TimeUnit;

@Path("/primes/{index}")
@Produces(MediaType.APPLICATION_JSON)
public class PrimesResource {
    public PrimesResource() {
    }

    @GET
    @Timed
    @CacheControl(maxAge = 365, maxAgeUnit = TimeUnit.DAYS)
    public int getPrime(@PathParam("index") @Min(1) IntParam index,
                        @QueryParam("algo") @DefaultValue("simple") PrimeGenerationAlgorithm algorithm) {
        final PrimeGenerator primeGenerator = PrimeGeneratorFactory.getGenerator(algorithm);
        return primeGenerator
            .primes()
            .skip(index.get() - 1)
            .iterator()
            .next();
    }
}
