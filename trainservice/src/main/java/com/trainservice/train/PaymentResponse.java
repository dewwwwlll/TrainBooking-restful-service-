package com.trainservice.train;

//payment response to send
public class PaymentResponse {
	private String paymentRespones;
	private String emailRespones;
	private String code;
	
	public PaymentResponse(String paymentRespones, String emailRespones, String code) {
		super();
		this.paymentRespones = paymentRespones;
		this.emailRespones = emailRespones;
		this.code = code;
	}

	public String getPaymentRespones() {
		return paymentRespones;
	}

	public void setPaymentRespones(String paymentRespones) {
		this.paymentRespones = paymentRespones;
	}

	public String getEmailRespones() {
		return emailRespones;
	}

	public void setEmailRespones(String emailRespones) {
		this.emailRespones = emailRespones;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	} 

	
}
