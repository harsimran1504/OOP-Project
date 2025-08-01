package programmingProject;

public abstract class Product {
	
	private int barcode;
	private String brand;
	private String color;
	private ConnectivityType connectivity;
	private int quantityInStock;
	private double originalCost;
	private double retailPrice;
	private ProductCategory category;

	public Product(int barcode, String brand, String color, ConnectivityType connectivity, int quantityInStock, double originalCost, double retailPrice, ProductCategory category) {
		this.setBarcode(barcode);
		this.setBrand(brand);
		this.setColor(color);
		this.setConnectivity(connectivity);
		this.setQuantityInStock(quantityInStock);
		this.setOriginalCost(originalCost);
		this.setRetailPrice(retailPrice);
		this.setCategory(category);
	}
	
	


	// Getter Methods
	
	public int getBarcode() {
		return barcode;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getColor() {
		return color;
	}

	public ConnectivityType getConnectivity() {
		return connectivity;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}
	
	public double getOriginalCost() {
		return originalCost;
	}

	public double getRetailPrice() {
		return retailPrice;
	}
	
	public ProductCategory getCategory() {
		return category;
	}
	
	// Setter Methods
	
	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setConnectivity(ConnectivityType connectivity) {
		this.connectivity = connectivity;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public void setOriginalCost(double originalCost) {
		this.originalCost = originalCost;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "Barcode: " + getBarcode() + ", Category: " + String.valueOf(getCategory()).toLowerCase() + ", Brand: " + getBrand() + ", Color: " + getColor() + ", Connectivity: " + 
	String.valueOf(getConnectivity()).toLowerCase() + ", Quantity In Stock: " + getQuantityInStock() + ", Retail Price: " + getRetailPrice();
		
	}

}
