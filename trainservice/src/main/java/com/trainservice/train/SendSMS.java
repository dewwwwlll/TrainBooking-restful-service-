package com.trainservice.train;

import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SendSMS {
	
	 public String sendSms(String phoneNumber, String smsbody){
	        Twilio.init("AC6c1949fd5a3fda3250f618fffc94a89f", "dd64b85925d7c006fcff85e3e5324114");
	        Message message = Message.creator(
	                new com.twilio.type.PhoneNumber("+94" + phoneNumber),
	                new com.twilio.type.PhoneNumber("+12066735250"), smsbody)
	            .create();

	        return message.getSid();
	    }

}
