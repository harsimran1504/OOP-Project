package programmingProject;

public class Address {
	
	private int houseNumber;
	private String city;
	private String postcode;
	
	public Address(int houseNumber, String city, String postcode) {
		this.setHouseNumber(houseNumber);
		this.setCity(city);
		this.setPostcode(postcode);	
	}
	
	// Getter Methods
	
	public int getHouseNumber() {
		return houseNumber;
	}

	public String getCity() {
		return city;
	}
	
	public String getPostcode() {
		return postcode;
	}
	
	// Setter Methods
	
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	@Override
	public String toString() {
		return houseNumber + ", " + city + ", " + postcode;
		
	}
	
	

}
