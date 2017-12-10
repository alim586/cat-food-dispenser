package nl.josephk.cat.food.dispenser;


import nl.josephk.cat.food.dispenser.dto.Result;

public interface YunService {

      Result servoDemo(String state);

      Result changePin12State(String command);
}
