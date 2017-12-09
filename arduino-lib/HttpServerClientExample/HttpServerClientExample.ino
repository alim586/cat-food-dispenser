
/*
  Arduino YÃºn Bridge example

  This example for the YunShield/YÃºn shows how 
  to use the Bridge library to access the digital and
  analog pins on the board through REST calls.
  It demonstrates how you can create your own API when
  using REST style calls through the browser.

  Possible commands created in this shetch:

  "/arduino/digital/13"     -> digitalRead(13)
  "/arduino/digital/13/1"   -> digitalWrite(13, HIGH)
  "/arduino/analog/2/123"   -> analogWrite(2, 123)
  "/arduino/analog/2"       -> analogRead(2)
  "/arduino/mode/13/input"  -> pinMode(13, INPUT)
  "/arduino/mode/13/output" -> pinMode(13, OUTPUT)

  This example code is part of the public domain

  http://www.arduino.cc/en/Tutorial/Bridge

*/
#include <RestClientUtil.h>
#include <Bridge.h>
#include <BridgeServer.h>
#include <BridgeClient.h>
#include <SPI.h>
#include <Wire.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>

#include <Servo.h>

//logging
#define OLED_RESET 4
Adafruit_SSD1306 display(OLED_RESET);

#define NUMFLAKES 10
#define XPOS 0
#define YPOS 1
#define DELTAY 2

#define LOGO16_GLCD_HEIGHT 16 
#define LOGO16_GLCD_WIDTH  16 

// Listen to the default port 5555, the YÃºn webserver
// will forward there all the HTTP requests you send
BridgeServer server;
Servo servo;
RestClientUtil util;

int pos = 0;    // variable to store the servo position

void setup() {

  servo.attach(9);
  
  // Bridge startup
  pinMode(13, OUTPUT);

  // Listen for incoming connection only from localhost
  // (no one from the external network could connect)
  server.listenOnLocalhost();

  server.begin();
  Serial.begin(9600);

  // by default, we'll generate the high voltage from the 3.3v line internally! (neat!)
  display.begin(SSD1306_SWITCHCAPVCC, 0x3C);  // initialize with the I2C addr 0x3C (for the 128x32) 

}

void loop() {
  // Get clients coming from server
  BridgeClient client = server.accept();

  // There is a new client?
  if (client) {
   String command;

   command = util.readServoCommandFromRequest(client);

   if(command == 'start')
   {
     runServo();
     printToScreen(util.getResponse(command, client));
   }

  display.clearDisplay();

    // Close connection and free resources.
  client.stop();
  display.clearDisplay();
  }

  delay(50); // Poll every 50ms
}

void printToScreen(String response) {
  display.setTextSize(3);
  display.setTextColor(WHITE);
  display.setCursor(10,0);
  display.clearDisplay();
  display.print(response);
  display.display();
}

void runServo(){
    
 for (pos = 0; pos <= 180; pos += 1) { // goes from 0 degrees to 180 degrees
   // in steps of 1 degree
   servo.write(pos);              // tell servo to go to position in variable 'pos'
   delay(15);                       // waits 15ms for the servo to reach the position
  }
 for (pos = 180; pos >= 0; pos -= 1) { // goes from 180 degrees to 0 degrees
  servo.write(pos);              // tell servo to go to position in variable 'pos'
  delay(15);                       // waits 15ms for the servo to reach the position
  }
}



