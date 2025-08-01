package programmingProject;

import java.time.LocalDate;

public class PayPal implements PaymentMethod{
	
	private String paypalEmail;
	
	
	public PayPal(String paypalEmail) {
		setPaypalEmail(paypalEmail);
	}

	@Override
	public Receipt processPayment(double amount, Address fullAddress) {
		LocalDate date = LocalDate.now();
		Receipt receipt = new Receipt(amount, fullAddress, date, paypalEmail);
		return receipt;
	}


	public String getPaypalEmail() {
		return paypalEmail;
	}


	public void setPaypalEmail(String paypalEmail) {
		this.paypalEmail = paypalEmail;
	}

}
