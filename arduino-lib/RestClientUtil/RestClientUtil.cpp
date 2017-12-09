/*
  RestClientUtil.h - Utility library for reading REST request urls.
  Created by Meshkat Ali, December 3, 2017.
*/

#include "Arduino.h"
#include "RestClientUtil.h"
#include "BridgeClient.h"

RestClientUtil::RestClientUtil(){}

String RestClientUtil::readServoCommandFromRequest(BridgeClient client)
{
    String path = client.readStringUntil('/');
    String value;
    if(path == 'servo'){
         value = client.readStringUntil('/');
    }else{
         return "command not allowed";
    }

    if(value == 'start'){
         return value;
    }
    if(value == 'stop'){
         return value;
    }

    return "command not allowed";
}

String RestClientUtil::getResponse(String command, BridgeClient client) {
      String rm = "{command:"+command+"}";
      client.print(rm);
      return rm;
}

