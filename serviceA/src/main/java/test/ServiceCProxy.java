package test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ServiceCProxy {

    @Inject
    @ConfigProperty(name="serviceC.url")
    private String serviceCURL;

    public String getProperty(String name, String mode) throws IOException {
        Client c = ClientBuilder.newClient();
        WebTarget t = c.target(serviceCURL + "/" + name + "?mode=" + mode);
        Response r = t.request().get();

        if (r.getStatus() == 200) {
            return r.readEntity(Prop.class).getValue();
        } else {
            throw new IOException(t.getUri() + " returns " + r.getStatus());
        }
    }

    public Prop getPropertyEasy(String name) throws IOException {
        return new Prop(name, getProperty(name, "none"));
    }

    public Prop getPropertyNoRetry(String name) throws IOException {
        return new Prop(name, getProperty(name, "sometimes"));
    }

    @Retry(retryOn=IOException.class, maxRetries=5)
    public Prop getPropertyWithRetry(String name) throws IOException {
        return new Prop(name, getProperty(name, "sometimes"));
    }

    @Timeout(500)
    public Prop getPropertyWithTimeout(String name) throws IOException {
        return new Prop(name, getProperty(name, "slow"));
    }

    @Timeout(500)
    @Fallback(fallbackMethod="fallback")
    public Prop getPropertyWithTimeoutAndFallback(String name) throws IOException {
        return new Prop(name, getProperty(name, "slow"));
    }

    public Prop fallback(String name) {
        return new Prop(name, System.getProperty(name));
    }
}