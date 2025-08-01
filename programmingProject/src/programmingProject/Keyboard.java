package programmingProject;

public class Keyboard extends Product{

	private String layout;

	public Keyboard(int barcode, String brand, String color, ConnectivityType connectivity, int quantityInStock,
			double originalCost, double retailPrice, ProductCategory category, String layout) {
		super(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category);
		this.setLayout(layout);
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}
	
	public String toString(String deviceType) {
		return getBarcode() + ", " + String.valueOf(getCategory()).toLowerCase() + ", " + deviceType + ", " + getBrand() + ", " + getColor() + ", " + 
	String.valueOf(getConnectivity()).toLowerCase() + ", " + getQuantityInStock() + ", " + getOriginalCost() + ", " + getRetailPrice() + ", " + layout;
		
	}
	
	@Override
	public String toString() {
		return "Barcode: " + getBarcode() + ", Category: " + String.valueOf(getCategory()).toLowerCase() + ", Brand: " + getBrand() + ", Color: " + getColor() + ", Connectivity: " + 
	String.valueOf(getConnectivity()).toLowerCase() + ", Quantity In Stock: " + getQuantityInStock() + ", Retail Price: " + getRetailPrice() + ", Layout: " + layout;
		
	}

}
