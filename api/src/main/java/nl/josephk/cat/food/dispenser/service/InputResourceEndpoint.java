package nl.josephk.cat.food.dispenser.service;

import static nl.josephk.cat.food.dispenser.dto.YunInput.DEMO;
import static nl.josephk.cat.food.dispenser.dto.YunInput.SERVO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.josephk.cat.food.dispenser.client.YunServiceClient;
import nl.josephk.cat.food.dispenser.dto.Result;

@Component
@Path("/pin")
public class InputResourceEndpoint {

    @Autowired
    private YunServiceClient yunServiceClient;

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response sendArduinoCommand(@PathParam("id") String id, @QueryParam("command") String command) {

        switch (id) {
            case SERVO:
                Result digitalResult = yunServiceClient.servoDemo(command);
                return Response.status(201).header("Access-Control-Allow-Origin", "*").entity(digitalResult).build();
            case DEMO:
                Result analogResult = yunServiceClient.changePin12State(command);
                return Response.status(201).header("Access-Control-Allow-Origin", "*").entity(analogResult).build();
            default:
                Result defaultResult = yunServiceClient.servoDemo("start");
                return Response.status(201).header("Access-Control-Allow-Origin", "*").entity(defaultResult).build();

        }
    }
}
