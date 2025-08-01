package programmingProject;

public class Mouse extends Product{

	private int noButtons;

	public Mouse(int barcode, String brand, String color, ConnectivityType connectivity, int quantityInStock,
			double originalCost, double retailPrice, ProductCategory category, int noButtons) {
		super(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category);
		this.setNoButtons(noButtons);
	}	

	public int getNoButtons() {
		return noButtons;
	}

	public void setNoButtons(int noButtons) {
		this.noButtons = noButtons;
	}
	
	public String toString(String deviceType) {
		return getBarcode() + ", " + String.valueOf(getCategory()).toLowerCase() + ", " + deviceType + ", " + getBrand() + ", " + getColor() + ", " + 
	String.valueOf(getConnectivity()).toLowerCase() + ", " + getQuantityInStock() + ", " + getOriginalCost() + ", " + getRetailPrice() + ", " + noButtons;
	}
	
	
	@Override
	public String toString() {
		return "Barcode: " + getBarcode() + ", Category: " + String.valueOf(getCategory()).toLowerCase() + ", Brand: " + getBrand() + ", Color: " + getColor() + ", Connectivity: " + 
	String.valueOf(getConnectivity()).toLowerCase() + ", Quantity In Stock: " + getQuantityInStock() + ", Retail Price: " + getRetailPrice() + ", Number Of Buttons: " + noButtons;
	}

}
