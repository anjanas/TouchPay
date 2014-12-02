package touchpay;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.vdp.util.RequestUtil;
import com.visa.vdp.api.cardvalidatiion.AccountLookupRequest;
import com.visa.vdp.api.cardvalidatiion.AccountLookupResponse;
import com.visa.vdp.api.cardvalidatiion.AccountVerificationRequest;
import com.visa.vdp.api.cardvalidatiion.AccountVerificationResponse;
import com.visa.vdp.api.exception.VisaApiException;
import com.visa.vdp.api.payment.AccountFundingTransactionsRequest;
import com.visa.vdp.api.payment.AccountFundingTransactionsResponse;
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