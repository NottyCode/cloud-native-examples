package test.helpers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import test.FetchServiceProp;
import test.Prop;
import test.ServiceC;
import test.qualifiers.SometimesWithRetry;

@ApplicationScoped
@SometimesWithRetry
public class SometimesFailWithRetryHelper implements FetchServiceProp {
    @Inject
    @RestClient
    private ServiceC client;
    
    @Retry
    public Prop getProperty(String propName) {
        return client.getProp("sometimes", propName);
    }
}