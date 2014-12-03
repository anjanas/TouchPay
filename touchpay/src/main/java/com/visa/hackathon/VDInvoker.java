package com.visa.hackathon;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.json.JSONObject;

public class VDInvoker {
	Map<String, String> requestMap;

	public VDInvoker(Map<String, String> requestMap) {
		this.requestMap = requestMap;
	}
	
	String prepareVDRequest(Map<String, String> requestMap) throws Exception {
		String payload = (String) new VdcConfig().getPropValues().get("payloadOCT");

		JSONObject jsonObject = new JSONObject(payload);
		jsonObject.put("Amount", requestMap.get("amount"));

//		String senderPAN = (String) requestMap.get("senderPAN");
//		String recipientPAN = (String) requestMap.get("recipientPAN");
		
		String senderPAN = "4957030420210462";
		String recipientPAN = "4895070000008881";
		if (senderPAN != null) {
			jsonObject.put("SenderAccountNumber", senderPAN);
		}
		if (recipientPAN != null) {
			jsonObject.put("RecipientCardPrimaryAccountNumber", recipientPAN);
		}
		java.util.logging.Logger.getLogger("touchpay").log(Level.INFO, "Request to Visa Direct: " + jsonObject.toString());
		return jsonObject.toString();
	}
	
	 Map<String, String> prepareVDResponse(String response) throws Exception {
		 JSONObject jsonObject = new JSONObject(response);
		 java.util.logging.Logger.getLogger("touchpay").log(Level.INFO, "Response from Visa Direct: " + jsonObject.toString());
		 Map map = new HashMap<String, String>();
		 System.out.println("VDC Response:" +jsonObject.toString());
		 map.put("ActionCode", jsonObject.get("ActionCode"));
		 map.put("TransactionIdentifier", jsonObject.get("TransactionIdentifier"));
		 map.put("ApprovalCode", jsonObject.get("ApprovalCode"));
		 map.put("SuccessMSG", "Hurrey !!!! Congratulations to Hackathon winners !!!!!");
		return map;
	}
	 
	 public Map<String, String> invoke(){	
		 String resp=null;
		try {
			java.util.logging.Logger.getLogger("touchpay").log(Level.INFO, "request MAP: " + requestMap);
			String vdRequest = prepareVDRequest(requestMap);
			 resp = call(vdRequest);
			 java.util.logging.Logger.getLogger("touchpay").log(Level.INFO, "RESPONSE: "+ resp);
			 return prepareVDResponse(resp);
		} catch (Exception e) {
			java.util.logging.Logger.getLogger("touchpay").log(Level.INFO, "ERROR OCCURRED IN INVOKE METHOD: "+e);
			e.printStackTrace();
		}
		return null;
		 
	 }

	private String call(String newpayload) throws Exception{
			String apiKey=(String) new VdcConfig().getPropValues().get("apiKey");
			String sharedSecret= (String) new VdcConfig().getPropValues().get("sharedSecret");
			VdcClient client = new VdcClient();
			String  endpoint = (String)new VdcConfig().getPropValues().get("urlOCT") + "?apikey=" + apiKey;
			 String token = new VdcAlgorithm().generateXpaytoken(newpayload, (String)new VdcConfig().getPropValues().get("pathOCT"), apiKey, sharedSecret);	
			 return client.getResponse(newpayload,endpoint, token);
		
	}
}
