package test;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import test.qualifiers.Slow;
import test.qualifiers.Sometimes;
import test.qualifiers.SometimesWithRetry;
import test.qualifiers.TimeoutAndFallback;
import test.qualifiers.TimeoutQ;
import test.qualifiers.Vanilla;

import java.io.IOException;

@Path("props")
@ApplicationScoped
public class ServiceARest {
    @Inject @Vanilla
    private FetchServiceProp helper;
    
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("{propName}")
    public Prop getProperty(@PathParam("propName") String propName) 
            throws IOException {
        return helper.getProperty(propName);
    }        
}