package programmingProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

public class ShoppingBasket {
	
	private HashMap<Product, Integer> basket;
	
	public ShoppingBasket(HashMap<Product, Integer> basket) {
		setBasket(basket);	
	}
	
	public void addToBasket(Product product, int quantity) {
		
		if (basket.containsKey(product)){
			basket.put(product, basket.get(product) + quantity);
		} else {
			basket.put(product, quantity);
		}
		
	}
	
	
	public List<String> viewBasket() {
		List<String> items = new ArrayList<>();
		BiConsumer<? super Product, ? super Integer> printBiconsumer = (key, val) -> {
			items.add("Product: '" + key + ", Quantity: " + val);
			};
			basket.forEach(printBiconsumer);
			return items;
		
	}
	
	public ShoppingBasket removeItem(Product product) {
		basket.remove(product);
		this.setBasket(basket);
		return this;
	}
	
	public void clearBasket() {
		basket.clear();
	}

	public HashMap<Product, Integer> getBasket() {
		return basket;
	}

	public void setBasket(HashMap<Product, Integer> basket) {
		this.basket = basket;
	}




}
