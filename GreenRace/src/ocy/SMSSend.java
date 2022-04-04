package ocy;

import java.util.HashMap;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleSend
 * @brief This sample code demonstrate how to send sms through CoolSMS Rest API PHP
 */

// Message 객체 생성 -> send 메서드 사용
public class SMSSend {
	
	String api_key = "NCSERSHMHNLVP3H8";
    String api_secret = "IZXBGIKKOOPARK6NXIAZ8NUGUQYOC9MO";
    Message coolsms = new Message(api_key, api_secret);
    
    int authNo = (int)(Math.random() * (9999 - 1000 + 1)) + 1000;
    String certificationNumber = Integer.toString(authNo);
    
    // 4 params(to, from, type, text) are mandatory. must be filled
    HashMap<String, String> params = new HashMap<String, String>();
	
	public SMSSend(String phone) {
	    params.put("to", phone);
	    params.put("from", "01033339573");
	    params.put("type", "SMS");
	    params.put("text", "[그린마사회] 인증번호 : " + certificationNumber);
	    params.put("app_version", "test app 1.2"); // application name and version
	}
	
	public String send() {
		try {
		      JSONObject obj = (JSONObject) coolsms.send(params);
		      System.out.println(obj.toString());
		    } catch (CoolsmsException e) {
		      System.out.println(e.getMessage());
		      System.out.println(e.getCode());
		    }
		
		return certificationNumber;
	}
  
}
