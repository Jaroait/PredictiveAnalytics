import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendEmail {
	
	String GoodItemsAndNumbers = "   ";
	String BadItemsAndNumbers = "   ";
	
	String username = "";
	String storeNumber = "";
	
	SendEmail(){
		initializeUsernameAndStore();
	}
	
	private void initializeUsernameAndStore(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the email you wish to use to receive"
				+ " notifications about resupply:");
		this.username = scanner.nextLine();
		System.out.println("Enter the store name you are in charge of:");
		this.storeNumber = scanner.nextLine().trim();
		scanner.close();
	}
	
	public void cleanPrint(String[] items, int[] num, boolean[] posNeg, int[] lower, int[] upper) {
		for(int i = 0; i< posNeg.length; i++){
			if(posNeg[i]){
				GoodItemsAndNumbers += ("Item " + (i+1) + ": " + items[i] + " with " + num[i] + "\n   ");
			}
			else{
				BadItemsAndNumbers += ("Item " + (i+1) + ": " + items[i] + " with " + num[i] + "\n   "
										+ "    We expected a value between: " + lower[i] + " and " + upper[i] + "\n   ");
			}
		}
	}
	
	public void sendEmailCall() {
		sendEmail();
	}
	
    private void sendEmail() {
    	//Email coming from:
        final String fromUsername = "gnfr.resupplyscript@gmail.com";
        final String fromPassword = "capstone7!";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromUsername, fromPassword);
            }
          });

        try {
        	Date date = new Date();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("gnfr.resupplyscript@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(this.username));
            message.setSubject("Reordering GNFR on " + date.toString() + " for store " + this.storeNumber);
            message.setText("Dear Employee of Store #: " + this.storeNumber + ", "
                + "\n\n This is the list of supplies for goods that did meet the requirements: \n"
                	+ GoodItemsAndNumbers
                + "\n\n This is the list of supplies for goods that didn't meet the requirements: \n"
                	+ BadItemsAndNumbers
                + "\n\n\n This is an automated email, please do not respond.");

            Transport.send(message);

            System.out.println("EmailSent");

        } catch (MessagingException e) {
        	System.out.println("Exception");
            throw new RuntimeException(e);
        }
    }
}