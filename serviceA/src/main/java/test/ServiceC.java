package test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.annotation.ManagedBean;
import javax.enterprise.context.Dependent;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("props")
@Dependent
public interface ServiceC {

    @GET
    @Path("{propName}")
    @Produces("application/json")
    public Prop getProp(@QueryParam("mode")String mode, @PathParam("propName") String propName);
}