package test;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("props")
public class SystemProperties {

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Map getProps() {
        return System.getProperties();
    }
}


    /*
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("{prop}")
    public Prop getProp(@PathParam("prop") String propNAme) {
        return new Prop(propNAme, System.getProperty(propNAme));
    }*/
