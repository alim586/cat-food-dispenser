package nl.josephk.cat.food.dispenser.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nl.josephk.cat.food.dispenser.service.model.Response;

@RestController
public class HomeController {


    @RequestMapping("/hello")
    public Response hello(@RequestParam(value = "name") String name) {

        Response response = new Response();
        response.setMessage("hello" + name);

        return response;
    }

}
