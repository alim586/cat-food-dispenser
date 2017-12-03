package nl.josephk.cat.food.dispenser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j;

@SpringBootApplication
@Log4j
public class ClientApplication {

    public static void main(String args[]) {
        SpringApplication.run(ClientApplication.class);
    }

}