package test.helpers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import test.FetchServiceProp;
import test.Prop;
import test.ServiceC;
import test.qualifiers.TimeoutAndFallback;

@ApplicationScoped
@TimeoutAndFallback
public class TimeoutWithFallbackHelper implements FetchServiceProp {
    @Inject
    @RestClient
    private ServiceC client;
    
    @Timeout(500)
    @Fallback(fallbackMethod="fallback")
    public Prop getProperty(String propName) {
        return client.getProp("slow", propName);
    }

    public Prop fallback(String name) {
        return new Prop(name, System.getProperty(name));
    }
}