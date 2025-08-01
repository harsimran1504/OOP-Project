package programmingProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Admin extends User{
	
	
	public Admin(int userID, String username, Names name, int houseNumber, String postcode, String city, String role) {
		super(userID, username, name, houseNumber, postcode, city, role);
	}

	// Methods
	
	
	public void addProductToStock(Product product, String deviceType) {
		
		
		if (product.getCategory().equals(ProductCategory.MOUSE)) {
			
			Mouse mouse = (Mouse) product;
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter("Stock.txt", true));
				writer.newLine();
				writer.write(mouse.toString(deviceType));
				writer.close();
				} catch (IOException ioe) {
			            System.out.println("Couldn't write to file");
			            }
			} else if (product.getCategory().equals(ProductCategory.KEYBOARD)) {
			
			Keyboard keyboard = (Keyboard) product;
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter("Stock.txt", true));
				writer.newLine();
				writer.write(keyboard.toString(deviceType));
				writer.close();
				} catch (IOException ioe) {
			            System.out.println("Couldn't write to file");
			            }
			
		}
		
	}

	
}
