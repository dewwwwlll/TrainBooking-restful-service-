package com.trainservice.train;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

//sending email using javax.mail and java activation libraries
public class Email {

	private String USER_NAME = "testMail"; //sender email
	private String PASSWORD = "testmail123"; //sender password
	private String RECIPIENT = "";
	
	//setting customer's email
	public Email(String toEmail) {
		this.RECIPIENT = toEmail; 
	}
	
	public void setSendCredentials(String userName, String password) {
		this.USER_NAME = userName;
		this.PASSWORD = password;
	}
	
	//final setting email subject and the message to send the email
	public String sendMailMessageSet(String subject,String message) {
	    String from = USER_NAME;
	    String pass = PASSWORD;
	    String to = RECIPIENT; 

	    if(sendEmail(from, pass, to, subject, message)) 
	    	return "sent";
	    else
	    	return "failed";
	}
	
	//email send configuration and sending the email 
	private Boolean sendEmail(String from, String pass, String to, String subject, String body) {
	    
		Properties props = System.getProperties();
	    
	    String host = "smtp.gmail.com";

	    props.put("mail.smtp.starttls.enable", "true");

	    props.put("mail.smtp.ssl.trust", host);
	    props.put("mail.smtp.user", from);
	    props.put("mail.smtp.password", pass);
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");


	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {


	        message.setFrom(new InternetAddress(from));
	        InternetAddress toAddress = new InternetAddress(to);
	        
	       
            message.addRecipient(Message.RecipientType.TO, toAddress);
	        
	        message.setSubject(subject);
	        message.setText(body);

	        Transport transport = session.getTransport("smtp");

	        transport.connect(host, from, pass);	     
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();

	        return true;
	        
	    }
	    catch (AddressException ae) {
	        ae.printStackTrace();
	        return false;
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();
	        return false;
	    }
	    }
	   
}
