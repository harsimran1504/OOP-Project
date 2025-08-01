package programmingProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class User {
	
	private int userID;
	private String username;
	private Names name;
	private int houseNumber;
	private String postcode;
	private String city;
	private String role;
	
	public User(int userID, String username, Names name, int houseNumber, String postcode, String city, String role){
		this.setUserID(userID);
		this.setUsername(username);
		this.setName(name);
		this.setHouseNumber(houseNumber);
		this.setPostcode(postcode);
		this.setCity(city);
		this.setRole(role);
	}
	
	
	// Getter methods
	
	public int getUserID() {
		return userID;
	}

	public String getUsername() {
		return username;
	}

	public Names getName() {
		return name;
	}

	public int getHouseNumber() {
		return houseNumber;
	}
	
	public String getPostcode() {
		return postcode;
	}
	
	public String getCity() {
		return city;
	}

	public String getRole() {
		return role;
	}
	
	
	// Setter methods
	
	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setName(Names name) {
		this.name = name;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Product> viewAllProducts() {
		File file = new File("Stock.txt");
		try {
			Scanner fileScanner = new Scanner(file);
			List<Product> products = new ArrayList<Product>();
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] fields = line.split(", ");
				
				int barcode = Integer.parseInt(fields[0]);
				String brand = fields[3];
				String color = fields[4];
				ConnectivityType connectivity = ConnectivityType.valueOf(fields[5].toUpperCase());
				int quantityInStock = Integer.parseInt(fields[6]);
				double originalCost = Double.parseDouble(fields[7]);
				double retailPrice = Double.parseDouble(fields[8]);
				ProductCategory category = ProductCategory.valueOf(fields[1].toUpperCase());
				
				if (category.equals(ProductCategory.MOUSE)) {
					int noButtons = Integer.parseInt(fields[9]);
					Mouse mouse = new Mouse(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category, noButtons);
					products.add(mouse);
					
				} else if (category.equals(ProductCategory.KEYBOARD)) {
					String layout = fields[9];
					Keyboard keyboard = new Keyboard(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category, layout);
					products.add(keyboard);
				}
				
			}
			
			fileScanner.close();
			return products;
				
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}	
		
	}

}
