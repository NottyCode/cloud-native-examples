package test;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Health
@ApplicationScoped
public class ServiceCHealth implements HealthCheck {

  public boolean isHealthy() {
    String url = UriBuilder.fromUri("/serviceC/props/host").host("localhost").port(9081).scheme("http").build().toString();

    Client client = ClientBuilder.newClient();
    Response response = client.target(url).request(MediaType.APPLICATION_JSON)
                              .get();
    if (response.getStatus() != 200) {
      return false;
    }
    return true;
  }

  @Override
  public HealthCheckResponse call() {
    if (!isHealthy()) {
      return HealthCheckResponse.named(ServiceC.class.getSimpleName())
                                .down().build();
    }
    return HealthCheckResponse.named(ServiceC.class.getSimpleName())
                              .up().build();
  }
}
