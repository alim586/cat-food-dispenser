package nl.josephk.cat.food.dispenser;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import nl.josephk.cat.food.dispenser.service.InputResourceEndpoint;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(InputResourceEndpoint.class);
    }
}
