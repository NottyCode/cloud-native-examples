package test;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ServiceCHelper {

    @Inject
    @ConfigProperty(name="serviceC.url")
    private String serviceCURL;

    @Inject
    @RestClient
    private ServiceC client;

    public String getProperty(String name, String mode) throws IOException {
        return client.getProp(mode, name).getValue();
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