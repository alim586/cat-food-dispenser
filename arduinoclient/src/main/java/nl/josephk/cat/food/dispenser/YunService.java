package nl.josephk.cat.food.dispenser;


import nl.josephk.cat.food.dispenser.dto.ReadResult;

public interface YunService {

      ReadResult testServo(String state);

      ReadResult changePin12State(String state);
}
