package nl.josephk.cat.food.dispenser;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("nl.josephk.cat.food.dispenser.client")
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new Application()
                .configure(new SpringApplicationBuilder(ClientApplication.class))//
                .run(args);
    }


}