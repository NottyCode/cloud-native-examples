package test.helpers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import test.FetchServiceProp;
import test.Prop;
import test.ServiceC;
import test.qualifiers.TimeoutQ;

@ApplicationScoped
@TimeoutQ
public class TimeoutHelper implements FetchServiceProp {
    @Inject
    @RestClient
    private ServiceC client;
    
    @Timeout(500)
    public Prop getProperty(String propName) {
        return client.getProp("slow", propName);
    }
}