/*
  RestClientUtil.h - Utility library for reading REST request urls.
  Created by Meshkat Ali, December 3, 2017.
*/
#ifndef RestClientUtil_h
#define RestClientUtil_h

#include "Arduino.h"
#include "BridgeClient.h"

class RestClientUtil
{
    public:
      RestClientUtil();
      String readServoCommandFromRequest(BridgeClient client);
      String getResponse(String command, BridgeClient client);
};

#endif