package programmingProject;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class PaymentWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField paypalField;
	private JTextField cardNumberField;
	private JPasswordField securityPinField;

	private ShoppingBasket basket;
	private double amountToPay;
	private Address address;
	
	private DefaultListModel<Object> basketModel;

	/**
	 * Create the frame.
	 */
	public PaymentWindow(double amountToPay, Address address, ShoppingBasket basket, DefaultListModel<Object> basketModel) {
		
		setAmountToPay(amountToPay);
		setAddress(address);
		setBasket(basket);
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 721, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"PayPal", "Credit Card"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		contentPane.add(comboBox, gbc_comboBox);
		
		JLabel emailAddressLabel = new JLabel("Enter PayPal Email: ");
		GridBagConstraints gbc_emailAddressLabel = new GridBagConstraints();
		gbc_emailAddressLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailAddressLabel.gridx = 0;
		gbc_emailAddressLabel.gridy = 1;
		contentPane.add(emailAddressLabel, gbc_emailAddressLabel);
		
		paypalField = new JTextField();
		GridBagConstraints gbc_paypalField = new GridBagConstraints();
		gbc_paypalField.insets = new Insets(0, 0, 5, 0);
		gbc_paypalField.fill = GridBagConstraints.HORIZONTAL;
		gbc_paypalField.gridx = 1;
		gbc_paypalField.gridy = 1;
		contentPane.add(paypalField, gbc_paypalField);
		paypalField.setColumns(10);
		
		JLabel cardNumberLabel = new JLabel("Enter Card Number: ");
		GridBagConstraints gbc_cardNumberLabel = new GridBagConstraints();
		gbc_cardNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_cardNumberLabel.gridx = 0;
		gbc_cardNumberLabel.gridy = 2;
		contentPane.add(cardNumberLabel, gbc_cardNumberLabel);
		
		cardNumberField = new JTextField();
		cardNumberField.setEditable(false);
		GridBagConstraints gbc_cardNumberField = new GridBagConstraints();
		gbc_cardNumberField.insets = new Insets(0, 0, 5, 0);
		gbc_cardNumberField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cardNumberField.gridx = 1;
		gbc_cardNumberField.gridy = 2;
		contentPane.add(cardNumberField, gbc_cardNumberField);
		cardNumberField.setColumns(10);
		
		JLabel securityPinLabel = new JLabel("Enter Security PIN: ");
		GridBagConstraints gbc_securityPinLabel = new GridBagConstraints();
		gbc_securityPinLabel.insets = new Insets(0, 0, 5, 5);
		gbc_securityPinLabel.gridx = 0;
		gbc_securityPinLabel.gridy = 3;
		contentPane.add(securityPinLabel, gbc_securityPinLabel);
		
		securityPinField = new JPasswordField();
		securityPinField.setEditable(false);
		GridBagConstraints gbc_securityPinField = new GridBagConstraints();
		gbc_securityPinField.insets = new Insets(0, 0, 5, 0);
		gbc_securityPinField.fill = GridBagConstraints.HORIZONTAL;
		gbc_securityPinField.gridx = 1;
		gbc_securityPinField.gridy = 3;
		contentPane.add(securityPinField, gbc_securityPinField);
		
		
		
		comboBox.addActionListener(new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	String selectedValue = (String) comboBox.getSelectedItem();
		    	if (selectedValue.equals("PayPal")) {
		    		paypalField.setEditable(true);
		    		cardNumberField.setEditable(false);
		    		securityPinField.setEditable(false);
		    		
		    	} else if (selectedValue.equals("Credit Card")) {
		    		paypalField.setEditable(false);
		    		cardNumberField.setEditable(true);
		    		securityPinField.setEditable(true);
		    		
		    	}
		    }
		});
		
		JLabel totalLabel = new JLabel("Total:");
		GridBagConstraints gbc_totalLabel = new GridBagConstraints();
		gbc_totalLabel.insets = new Insets(0, 0, 5, 5);
		gbc_totalLabel.gridx = 0;
		gbc_totalLabel.gridy = 4;
		contentPane.add(totalLabel, gbc_totalLabel);
		
		JLabel amountLabel = new JLabel("£" + String.valueOf(amountToPay));
		GridBagConstraints gbc_amountLabel = new GridBagConstraints();
		gbc_amountLabel.insets = new Insets(0, 0, 5, 0);
		gbc_amountLabel.gridx = 1;
		gbc_amountLabel.gridy = 4;
		contentPane.add(amountLabel, gbc_amountLabel);
		
		
		
		JButton payButton = new JButton("Pay");
		GridBagConstraints gbc_payButton = new GridBagConstraints();
		gbc_payButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_payButton.gridwidth = 3;
		gbc_payButton.gridx = 0;
		gbc_payButton.gridy = 5;
		contentPane.add(payButton, gbc_payButton);
		
		
		payButton.addActionListener(new ActionListener () {
	
			public void actionPerformed(ActionEvent e) {
		    	String selectedValue = (String) comboBox.getSelectedItem();
		    	if (selectedValue.equals("PayPal")) {
		    		String paypalString = paypalField.getText();
		    		
		    		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
		    		Pattern pat = Pattern.compile(emailRegex); 
		    		
		    		if (pat.matcher(paypalString).matches()) {
		    			PayPal paypal = new PayPal(paypalString);
		    			Receipt receipt = paypal.processPayment(amountToPay, address);
		    			JOptionPane.showMessageDialog(null, receipt.toString("paypal"), "Receipt", JOptionPane.INFORMATION_MESSAGE);
		    			basket.clearBasket();
		    			for(int index = 0 ; index <= basketModel.getSize() ; index++) {
						    basketModel.remove(0);
		    			}
		    			dispose();
		    			
		    		} else {
		    			JOptionPane.showMessageDialog(null, "Ivalid PayPal Email", "Error", JOptionPane.ERROR_MESSAGE);
		    		}
		    		
		    		
		    		
		    		
		    	} else if (selectedValue.equals("Credit Card")) {
		    		String ccNumberString = cardNumberField.getText();
					String pinString = securityPinField.getText();
		    		try {
		    			int ccNumber = Integer.parseInt(ccNumberString);
		    			int pin = Integer.parseInt(pinString);
			    		if (ccNumberString.length() == 6 && pinString.length() == 3) {
			    			CreditCard card = new CreditCard(ccNumber, pin);
			    			Receipt receipt = card.processPayment(amountToPay, address);
			    			JOptionPane.showMessageDialog(null, receipt.toString("credit card"), "Receipt", JOptionPane.INFORMATION_MESSAGE);
			    			basket.clearBasket();
			    			for(int index = 0 ; index <= basketModel.getSize() ; index++) {
							    basketModel.remove(0);
			    			}
			    			dispose();
			    		} else {
			    			JOptionPane.showMessageDialog(null, "Card Number or PIN length is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
			    		}
			    	
		    		} catch (java.lang.NumberFormatException e1) {
		    			JOptionPane.showMessageDialog(null, "Please Enter Valid Credit Card Information", "Error", JOptionPane.ERROR_MESSAGE);
		    		}
		    		
		    		
		    	}
		    }
		});
	}

	public double getAmountToPay() {
		return amountToPay;
	}

	public void setAmountToPay(double amountToPay) {
		this.amountToPay = amountToPay;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ShoppingBasket getBasket() {
		return basket;
	}

	public void setBasket(ShoppingBasket basket) {
		this.basket = basket;
	}

	public DefaultListModel<Object> getBasketModel() {
		return basketModel;
	}

	public void setBasketModel(DefaultListModel<Object> basketModel) {
		this.basketModel = basketModel;
	}

}
