package test;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
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
  @ConfigProperty(name="serviceC.url")
  private String serviceCURL;

  public boolean isHealthy() {

    System.out.println(serviceCURL);

    Client c = ClientBuilder.newClient();
    WebTarget t = c.target(serviceCURL + "/" + "test");
    Response r = t.request().get();

    if (r.getStatus() != 200) {
      return false;
    }
    return true;
  }

  @Override
  public HealthCheckResponse call() {
    if (!isHealthy()) {
      return HealthCheckResponse.named(ServiceA.class.getSimpleName())
                                .down().build();
    }
    return HealthCheckResponse.named(ServiceA.class.getSimpleName())
                              .up().build();
  }
}
