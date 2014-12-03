package com.visa.hackathon;

import javax.servlet.http.HttpServletRequest;

import com.visa.vdp.api.exception.VisaApiException;
import com.visa.vdp.api.payment.OriginalCreditTransactionsRequest;
import com.visa.vdp.api.payment.OriginalCreditTransactionsResponse;
import com.visa.vdp.api.services.VisaService;

public class PaymentService {
	
String apiKey="YU61R615DKXQP195HVKY21qjHi4NqQivhwWurF7rOHJJQUl-0";	
String sharedSecret = "@xTW5Ux4boHPKwp8cV@P3zU#0gdtM5QmBp/OTCXx";

VisaService client = new VisaService (apiKey, sharedSecret);
	

	
	public OriginalCreditTransactionsResponse processOCTrequest(HttpServletRequest req)
	{
    OriginalCreditTransactionsRequest OCTrequest=RequestUtil.createrequestforOCT(req);
    OriginalCreditTransactionsResponse OCTresponse=null;	
	
   
    	try {
			OCTresponse = client.originalCreditTransactions(OCTrequest);
		} catch (VisaApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	return OCTresponse ;
	
	}
	
	
	
	
	
	
	
	
	

}
