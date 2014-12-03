package com.visa.hackathon;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
 
/**
  * Servlet implementation class ActionServlet
  */
 
public class OCTresponseServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
 
    
     public OCTresponseServlet() {
         // TODO Auto-generated constructor stub
     }
 

 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	 
	 String payload="";
	 String senderPAN=null;
	 String recipientPAN=null;
	 String newpayload="";
	 String endpoint="";
	 String token="";
	 String res="";
	 
	  //get apiKey
		String apiKey = null;
				
		HttpSession session = request.getSession();
		
		apiKey = (String)session.getAttribute("apiKey");
		
		if(apiKey == null){
			apiKey = (String) new VdcConfig().getPropValues().get("apiKey");
		}
		
		//get sharedSecret
		String sharedSecret = null;
		
		HttpSession session1 = request.getSession();
		
		sharedSecret = (String)session1.getAttribute("sharedSecret");
		
		if(sharedSecret == null){
			sharedSecret = (String) new VdcConfig().getPropValues().get("sharedSecret");
					//new Config().getPropValues().get("sharedSecret");
		}
		
	 
	 
	 try{
		 
	
		 payload = (String)new VdcConfig().getPropValues().get("payloadOCT");
		 
		 JSONObject jsonObject = new JSONObject(payload);		 
	 jsonObject.put("Amount", request.getParameter("amount"));	
	 
	 HttpSession session11 = request.getSession();
	   senderPAN=(String)session11.getAttribute("senderPAN");
		 recipientPAN=(String)session11.getAttribute("recipientPAN");
		if(senderPAN != null){
			jsonObject.put("SenderAccountNumber",senderPAN);
		}
		if(recipientPAN != null){
			jsonObject.put("RecipientCardPrimaryAccountNumber",recipientPAN);
		}
		
	 
	VdcClient client = new VdcClient();
	 newpayload = jsonObject.toString();	
	 endpoint = (String)new VdcConfig().getPropValues().get("urlOCT") + "?apikey=" + apiKey;
	 token = new VdcAlgorithm().generateXpaytoken(newpayload, (String)new VdcConfig().getPropValues().get("pathOCT"), apiKey, sharedSecret);	
	 
	 res = client.getResponse(newpayload,endpoint, token);
		if(res.startsWith("{"))		
			
		{	res= VdcVdpUtil.convertToPrettyJsonstring(res);
		
		
		}
		
		   JSONObject outputJson=new JSONObject();
			PrintWriter out = response.getWriter();
			outputJson.put("response",res);
			outputJson.put("token",token);
			response.setContentType("application/json");
			out.print(outputJson);
			
	
	 
  }
	 catch (Exception e)
	 {
		e.printStackTrace() ;
		 
	 }
 }
 
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   // TODO Auto-generated method stub
   
 }
 
}
 


