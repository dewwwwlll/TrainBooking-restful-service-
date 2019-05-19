package com.trainservice.trainservice;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.trainservice.train.CreditCardPayment;
import com.trainservice.train.DiscountCalculator;
import com.trainservice.train.DiscountDetails;
import com.trainservice.train.Email;
import com.trainservice.train.Employee;
import com.trainservice.train.MobilePayment;
import com.trainservice.train.PaymentResponse;
import com.trainservice.train.SendSMS;

@Path("/payment")
public class PaymentConfirmation {
	
	private Email emailObject;
	private CreditCardPayment creditcardpay;
	private MobilePayment mobilepay;
	private PaymentResponse paymentResponse;
	
    
    @Path("/creditcardpayment")
    @OPTIONS
    @Produces(MediaType.APPLICATION_JSON)
    public Response getcard(@Context HttpServletRequest request) {
    	return Response.ok()
    			.header("Access-Control-Allow-Origin", request.getHeader("Origin"))
    			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
    			.header("Access-Control-Allow-Headers", "content-type")
    			.build();
    }
    
    //credit card payment and sending email to the customer
    @Path("/creditcardpayment")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getcard(CreditCardPayment creditCardPayment, @Context HttpServletResponse response, @Context HttpServletRequest request) throws NoSuchAlgorithmException {
    	response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
    	
    	//getting details require for credit card payment
    	String cardHolder = creditCardPayment.getCardHolder();
    	String cardNo = creditCardPayment.getCardNo();
    	String cvcNo = creditCardPayment.getCvcNo();
    	String expDate = creditCardPayment.getExpDate();
    	String email = creditCardPayment.getEmail();
    	double amount = creditCardPayment.getAmount();
    	String subject = creditCardPayment.getSubject();
    	String message = creditCardPayment.getMessage();
    	
    	//paying using credit card
    	creditcardpay = new CreditCardPayment(cardNo, cvcNo, expDate, cardHolder, amount, email, message, subject);
    	String paymentResult[] = creditcardpay.pay();
    	
    	emailObject = new Email(email);
    	//sending email with confirmation code
    	String emailResult = emailObject.sendMailMessageSet(subject, message + " confirmation code :" + paymentResult[1]);
    	//returning payment response
    	paymentResponse = new PaymentResponse(paymentResult[0], emailResult, paymentResult[1]);
    	
    	return Response.ok()
    			.entity(paymentResponse)
    			.header("Access-Control-Allow-Origin", request.getHeader("Origin"))
    			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
    			.header("Access-Control-Allow-Headers", "content-type")
    			.build();
    	
    }

    @Path("/mobilepayment")
    @OPTIONS
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMobile(@Context HttpServletRequest request) {
    	return Response.ok()
    			.header("Access-Control-Allow-Origin", request.getHeader("Origin"))
    			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
    			.header("Access-Control-Allow-Headers", "content-type")
    			.build();
    }
    
    //mobile payment and sending SMS to the customer
    @Path("/mobilepayment")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMobile(MobilePayment mobilePayment, @Context HttpServletResponse response, @Context HttpServletRequest request) throws NoSuchAlgorithmException {
    	response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
    	
    	//getting details require for credit card payment
    	String mobileNumber = mobilePayment.getMobileNumber();
    	String pinNumber = mobilePayment.getPinNumber();
    	String email = mobilePayment.getEmail();
    	double amount = mobilePayment.getAmount();
    	String message = mobilePayment.getMessage();
    	
    	//paying using mobile
    	mobilepay = new MobilePayment(mobileNumber, pinNumber, email, amount, message);
    	String []result = mobilepay.pay();

    	SendSMS sms =  new SendSMS();
    	String mobileResult = sms.sendSms((mobileNumber).substring(1, 10), message + ". confirmation code : "+ result[1]);

    	//returning payment response
    	paymentResponse = new PaymentResponse(result[0], mobileResult, result[1]);
    	
    	return Response.ok()
    			.entity(paymentResponse)
    			.header("Access-Control-Allow-Origin", request.getHeader("Origin"))
    			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
    			.header("Access-Control-Allow-Headers", "content-type")
    			.build();
    	
    }
}
