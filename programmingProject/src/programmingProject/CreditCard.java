package programmingProject;

import java.time.LocalDate;

public class CreditCard implements PaymentMethod {
	
	private int cardNumber;
	private int pin;
	
	
	public CreditCard(int cardNumber, int pin) {
		setCardNumber(cardNumber);
		setPin(pin);
	}

	@Override
	public Receipt processPayment(double amount, Address fullAddress) {
		LocalDate date = LocalDate.now();
		Receipt receipt = new Receipt(amount, fullAddress, date, cardNumber);
		return receipt;
	}

	
	
	// Getter Methods
	
	public int getCardNumber() {
		return cardNumber;
	}

	public int getPin() {
		return pin;
	}
	
	
	
	// Setter Methods

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

}
