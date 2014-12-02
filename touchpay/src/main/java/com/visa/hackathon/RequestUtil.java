package touchpay;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.visa.vdp.api.cardvalidatiion.AccountLookupRequest;
import com.visa.vdp.api.cardvalidatiion.AccountVerificationRequest;
import com.visa.vdp.api.common.Address;
import com.visa.vdp.api.common.CardAcceptor;
import com.visa.vdp.api.common.MagneticStripeData;
import com.visa.vdp.api.common.OriginalDataElements;
import com.visa.vdp.api.common.PointOfServiceCapability;
import com.visa.vdp.api.common.PointOfServiceData;
import com.visa.vdp.api.common.StreetAddress;
import com.visa.vdp.api.payment.AccountFundingReversalRequest;
import com.visa.vdp.api.payment.AccountFundingTransactionsRequest;
import com.visa.vdp.api.payment.OriginalCreditTransactionsRequest;

public class RequestUtil {
	

	public static OriginalCreditTransactionsRequest createrequestforOCT(
			HttpServletRequest req) {
		OriginalCreditTransactionsRequest OCTrequest = new OriginalCreditTransactionsRequest();

		CardAcceptor cardAcceptor = new CardAcceptor();

		Address address = new Address();
		address.setCountry("USA");
		address.setState("CA");
		address.setCounty("081");
		address.setZipCode("94105");

		cardAcceptor.setName("John Smith");
		cardAcceptor.setIdCode("VMT200911026070");
		cardAcceptor.setTerminalId("13655392");
		cardAcceptor.setAddress(address);

		OCTrequest.setSystemsTraceAuditNumber(new Integer(350420));
		OCTrequest.setRetrievalReferenceNumber("401010350420");
		OCTrequest.setDateAndTimeLocalTransaction("2021-10-26T21:32:52");
		OCTrequest.setAcquiringBin(409999);
		OCTrequest.setAcquirerCountryCode(new Integer(101));
		OCTrequest.setSenderReference("");
		OCTrequest.setSenderAccountNumber("4957030001013830");
		
		HttpSession session = req.getSession();
		String senderPAN=(String)session.getAttribute("senderPAN");
		
		if(senderPAN != null){
			OCTrequest.setSenderAccountNumber(senderPAN);
		}
		String recipientaccno=(String)session.getAttribute("recipientaccno");
		
		OCTrequest.setSenderCountryCode("USA");
		OCTrequest.setTransactionCurrency("USD");
		OCTrequest.setSenderName("John Smith");
		OCTrequest.setSenderAddress("44 Market St.");
		OCTrequest.setSenderCity("San Francisco");
		OCTrequest.setSenderStateCode("CA");
		OCTrequest.setRecipientCardPrimaryAccountNumber(recipientaccno);
		OCTrequest.setAmount(new BigDecimal(req.getParameter("amount")));
		OCTrequest.setBusinessApplicationID("AA");
		OCTrequest.setMerchantCategoryCode(6012);
		;
		OCTrequest.setTransactionIdentifier(new Long("234234322342343")
				.longValue());
		OCTrequest.setSourceOfFunds("03");
		OCTrequest.setCardAcceptor(cardAcceptor);
		OCTrequest.setFeeProgramIndicator("123");

		return OCTrequest;
	}

	

}
