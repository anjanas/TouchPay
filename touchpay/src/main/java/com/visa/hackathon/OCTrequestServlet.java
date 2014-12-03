package com.visa.hackathon;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

 
/**
  * Servlet implementation class ActionServlet
  */
 
public class OCTrequestServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
 
    
     public OCTrequestServlet() {
         // TODO Auto-generated constructor stub
     }
 

 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
   	 
	 String payload = (String)new ConfigValues().getPropValues().get("payloadOCT");
	 String senderPAN=null;
	 String recipientPAN=null;
	 JSONObject jsonObject;
	 String jsonRequest="";
	try {
		 jsonObject = new JSONObject(payload);		
		 jsonObject.put("Amount", request.getParameter("amount"));
		
		 HttpSession session = request.getSession();
			 senderPAN=(String)session.getAttribute("senderPAN");
			 recipientPAN=(String)session.getAttribute("recipientPAN");
			if(senderPAN != null){
				jsonObject.put("SenderAccountNumber",senderPAN);
			}
			if(recipientPAN != null){
				jsonObject.put("RecipientCardPrimaryAccountNumber",recipientPAN);
			}
		
		 jsonRequest= VdpUtility.convertToPrettyJsonstring(jsonObject.toString());		  
		  response.getWriter().write(jsonRequest);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

   
  }
 
 
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   // TODO Auto-generated method stub

 
}
 
}

