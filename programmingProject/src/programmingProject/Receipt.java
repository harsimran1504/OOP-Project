package programmingProject;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Receipt {
	
	private double amount;
    private Address address;
    private LocalDate date;
    private int cardNumber;
    private String paypalEmail;

    
    
    // Constructors
    public Receipt(double amount, Address address, LocalDate date, int cardNumber) {
    	setAmount(amount);
    	setAddress(address);
    	setDate(date);
    	setCardNumber(cardNumber);
    }
    
    public Receipt(double amount, Address address, LocalDate date, String paypalEmail) {
    	setAmount(amount);
    	setAddress(address);
    	setDate(date);
    	setPaypalEmail(paypalEmail);
    }
    
    
    public String toString(String paymentMethod) {
    	if (paymentMethod.equals("paypal")) {
    		String string = "£" + amount + " paid by PayPal using " + paypalEmail+ " on " + date + " and the delivery address is " + address;
    		return string;
    	} else if (paymentMethod.equals("credit card")) {
    		String string = "£" + amount + " paid by Credit Card using " + cardNumber + " on " + date + " and the delivery address is " + address;
    		return string;
    	}
    	return null;
    }


    
    // Getters
    public double getAmount() {
        return amount;
    }
    
    public Address getAddress() {
        return address;
    }
    
	public LocalDate getDate() {
		return date;
	}
    
	public int getCardNumber() {
		return cardNumber;
	}

	public String getPaypalEmail() {
		return paypalEmail;
	}


	
	
	// Setters
	
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}


	public void setPaypalEmail(String paypalEmail) {
		this.paypalEmail = paypalEmail;
	}


	
}
