package nl.josephk.cat.food.dispenser.client;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static nl.josephk.cat.food.dispenser.dto.YunInput.DEMO;
import static nl.josephk.cat.food.dispenser.dto.YunInput.START;
import static nl.josephk.cat.food.dispenser.dto.YunInput.STOP;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.apache.commons.lang3.time.StopWatch;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import nl.josephk.cat.food.dispenser.YunService;
import nl.josephk.cat.food.dispenser.dto.Result;

@Component
@Slf4j
public class YunServiceClient implements YunService {


    @Override
    public Result servoDemo(String command) {

        switch (command){
            case START:
                sendRequest("servo", START);
                break;
            case STOP:
                sendRequest("servo", STOP);
                break;
            default:
                throw new UnsupportedOperationException(command);
        }


        return sendRequest(DEMO, command);
    }

    @Override
    public Result changePin12State(String command) {
        return sendRequest(DEMO, command);
    }

    private Result sendRequest(String program, String command) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Client client = ClientBuilder.newClient();

        String uri = "http://dop.local/arduino/";

        String arduinoReading = client.target(uri)//
                .path(program)//
                .path(command)//
                .request(APPLICATION_JSON)//
                .header("Authorization", "Basic cm9vdDpsZmRmNzYzMw==")//
                .get(String.class);

        stopWatch.stop();

        long responseTime = stopWatch.getTime();
        log.info("Time Arduino took to respond [{} ms]", responseTime);

        if (null != arduinoReading && !arduinoReading.isEmpty()) {
            arduinoReading = arduinoReading.replaceAll( ("(\r\n|\n)"), "");
        }


        return new Result()//
                .setResponseMessage(arduinoReading)//
                .setResponseTime(responseTime);
    }
}
