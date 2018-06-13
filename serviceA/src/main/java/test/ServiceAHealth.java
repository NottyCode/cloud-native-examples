package test;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.WebTarget;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Health
@ApplicationScoped
public class ServiceAHealth implements HealthCheck {

  @Inject
  @ConfigProperty(name="test.ServiceC/mp-rest/url")
  private String serviceCURL;

  public boolean isHealthy() {

    try {
      Client c = ClientBuilder.newClient();
      WebTarget t = c.target(serviceCURL + "/props/os.name");
      Response r = t.request().get();

      int status = r.getStatus();
      if (status != 200) {
        return false;
      } else {
        System.err.println(status);
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

  }

  @Override
  public HealthCheckResponse call() {
    boolean up = isHealthy();
    return HealthCheckResponse.named("ServiceC").state(up).build();
  }
}
