package test.helpers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import test.FetchServiceProp;
import test.Prop;
import test.ServiceC;
import test.qualifiers.Vanilla;

@ApplicationScoped
@Vanilla
public class VanillaHelper implements FetchServiceProp {
    @Inject
    @RestClient
    private ServiceC client;
    
    public Prop getProperty(String propName) {
        return client.getProp("none", propName);
    }
}