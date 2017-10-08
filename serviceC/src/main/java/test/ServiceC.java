package test;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("props")
@RequestScoped
public class ServiceC {
    @Inject
    private MyBean bean;

    private static transient boolean failSometimes = true;

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("{propName}")
    public Response getProperty(@QueryParam("mode") @DefaultValue("none") String mode, @PathParam("propName") String propName) {
        Prop p = new Prop(propName, bean.getProperty(propName));
        ResponseBuilder builder = null;

        if ("slow".equals(mode)) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {}
        } else if ("fail".equals(mode)) {
            builder = Response.status(500);
        } else if ("sometimes".equals(mode)) {
            if (failSometimes) {
                builder = Response.status(500);
            }
            failSometimes = !failSometimes;
        }

        if (builder == null) {
            builder = Response.ok(p);
        }

        return builder.build();
    }
}
