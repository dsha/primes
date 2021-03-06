package com.example.primes.resources;

import com.example.primes.PrimesApplication;
import com.example.primes.PrimesConfiguration;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.net.URI;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PrimesResourceTest {
    @ClassRule
    public static DropwizardAppRule<PrimesConfiguration> primes = new DropwizardAppRule<>(
        PrimesApplication.class, resourcePath("/primes.yml"));

    private static String resourcePath(String name) {
        return PrimesResourceTest.class.getResource(name).getPath();
    }

    private static Client buildClient() {
        return ClientBuilder.newClient();
    }

    private static WebTarget getPrimesWebTarget(Client client, int index) {
        return client.target(URI.create("http://localhost:" + primes.getLocalPort() + "/primes/" + index));
    }

    @Test
    public void testOnePrime() {
        final Client client = buildClient();
        final Integer firstPrime = getPrimesWebTarget(client, 1)
            .request()
            .get(Integer.class);
        assertThat(firstPrime, is(2));
    }

    @Test
    public void testOnePrimeWithSieve() {
        final Client client = buildClient();
        final Integer firstPrime = getPrimesWebTarget(client, 11)
            .queryParam("algo", "incremental_sieve")
            .request()
            .get(Integer.class);
        assertThat(firstPrime, is(31));
    }

    @Test
    public void testEleventhPrime() {
        final Client client = buildClient();
        final Integer firstPrime = getPrimesWebTarget(client, 11)
            .request()
            .get(Integer.class);
        assertThat(firstPrime, is(31));
    }

    @Test(expected = BadRequestException.class)
    public void testNegativeIndex() {
        final Client client = buildClient();
        getPrimesWebTarget(client, -1)
            .request()
            .get(Integer.class);
    }

    @Test(expected = BadRequestException.class)
    public void testZeroIndex() {
        final Client client = buildClient();
        getPrimesWebTarget(client, 0)
            .request()
            .get(Integer.class);
    }
}
