package com.jobget.util;

import java.io.IOException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;


public class MobileOTPValidator {

	public static final String ACCOUNTSID = "AC7993edbbccfe2e0a8fb54ceebd1b95cd";
	public static final String AUTHTOKEN = "ece5581f9c3d4ab4bfe148605277af85";

	// Get OTP from Twilio and pass the value
	
	public static String setOTP() throws IOException {
		//Fetching OTP Number from SMS
		Twilio.init(ACCOUNTSID, AUTHTOKEN);
		String smsBody = getMessage("+1" + Config.getProperty("PhoneNumber"));
		String OTPNumberString = smsBody.replaceAll("[^-?0-9]+", " "); 
		System.out.println("smsbody" + smsBody);
		return OTPNumberString;
	}
 

	//This will fetch OTP send to the registered number
	public static String getMessage(String PhoneNumber ) {
		return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
				.filter(m -> m.getTo().equals("+1" +PhoneNumber)).map(Message::getBody).findFirst()
				.orElseThrow(IllegalStateException::new);
	}

	private static Stream<Message> getMessages() {
		ResourceSet<Message> messages = Message.reader(ACCOUNTSID).read();
		return StreamSupport.stream(messages.spliterator(), false);
	}

}
