package nl.josephk.cat.food.dispenser.client;

import static java.util.Optional.empty;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static nl.josephk.cat.food.dispenser.dto.YunInput.ANALOG;
import static nl.josephk.cat.food.dispenser.dto.YunInput.DIGITAL;
import static nl.josephk.cat.food.dispenser.dto.YunInput.PIN_12;
import static nl.josephk.cat.food.dispenser.dto.YunInput.PIN_13;

import java.util.Optional;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.apache.commons.lang3.time.StopWatch;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import nl.josephk.cat.food.dispenser.YunService;
import nl.josephk.cat.food.dispenser.dto.ReadResult;

@Component
@Slf4j
public class YunServiceClient implements YunService {


    @Override
    public ReadResult testServo(String state) {
        return sendRequest(DIGITAL, PIN_13, state);
    }

    @Override
    public ReadResult changePin12State(String state) {
        return sendRequest(ANALOG, PIN_12, state);
    }

    private ReadResult sendRequest(String pinType, String pin, String state) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Client client = ClientBuilder.newClient();

        String uri = "http://dop.local/arduino/";

        String arduinoReading = client.target(uri)//
                .path(pinType)//
                .path(pin).path(state)//
                .request(APPLICATION_JSON)//
                .header("Authorization", "Basic cm9vdDpsZmRmNzYzMw==")//
                .get(String.class);

        stopWatch.stop();

        long responseTime = stopWatch.getTime();
        log.info("Time Arduino took to respond [{} ms]", responseTime);

        if (null != arduinoReading && !arduinoReading.isEmpty()) {
            arduinoReading = arduinoReading.replaceAll( ("(\r\n|\n)"), "");
        }

        String[] split = arduinoReading.split(",");
        Optional<String> pinNumber = empty();
        Optional<String> pinValue = empty();

        if (split.length > 0) {
            String value = split[0];
            if (null != pin) {
                pinNumber = Optional.of(value);
            }
            value = split[1];
            if (null != value) {
                pinValue = Optional.of(value);
            }
        }

        return new ReadResult()//
                .setPin(pinNumber.get())//
                .setPinValue(pinValue.get())//
                .setResponseTime(responseTime);
    }
}
