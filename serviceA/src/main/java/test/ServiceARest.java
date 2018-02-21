package test;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.io.IOException;

@Path("props")
@RequestScoped
public class ServiceARest {
    @Inject
    private ServiceCHelper serviceC;

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("{propName}")
    public Prop getProperty(@PathParam("propName") String propName) 
            throws IOException {
        return serviceC.getPropertyEasy(propName);
    }        
}