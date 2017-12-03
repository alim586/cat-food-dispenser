/*
  RestClientUtil.h - Utility library for reading REST request urls.
  Created by Meshkat Ali, December 3, 2017.
*/

#include "Arduino.h"
#include "RestClientUtil.h"
#include "BridgeClient.h"

RestClientUtil::RestClientUtil(){}
String RestClientUtil::readCommandFromRequest(BridgeClient client)
{
return client.readStringUntil('/');
}
