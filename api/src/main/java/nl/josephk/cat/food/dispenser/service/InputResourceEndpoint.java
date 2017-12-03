package nl.josephk.cat.food.dispenser.service;

import static nl.josephk.cat.food.dispenser.dto.YunInput.PIN_12;
import static nl.josephk.cat.food.dispenser.dto.YunInput.PIN_13;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.josephk.cat.food.dispenser.client.YunServiceClient;
import nl.josephk.cat.food.dispenser.dto.ReadResult;

@Component
@Path("/pin")
public class InputResourceEndpoint {

    @Autowired
    private YunServiceClient yunServiceClient;

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response putDigitalPin(@PathParam("id") String id, @QueryParam("state") String state) {

        switch (id) {
            case PIN_13:
                ReadResult digitalResult = yunServiceClient.testServo(state);
                return Response.status(201).header("Access-Control-Allow-Origin", "*").entity(digitalResult).build();
            case PIN_12:
                ReadResult analogResult = yunServiceClient.changePin12State(state);
                return Response.status(201).header("Access-Control-Allow-Origin", "*").entity(analogResult).build();
            default:
                ReadResult defaultResult = yunServiceClient.testServo("0");
                return Response.status(201).header("Access-Control-Allow-Origin", "*").entity(defaultResult).build();

        }
    }
}
