package programmingProject;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;



public class MainCustomer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Customer user;
	private JTable table;
	private JTextField barcodeField;
	private JTextField quantityField;
	
	private DefaultListModel<Object> basketModel = new DefaultListModel<Object>();
	private JTextField filterNumberField;


	/**
	 * Create the frame.
	 */
	public MainCustomer(Customer selectedUser) {
		
		this.user = selectedUser;
		
		int houseNumber = user.getHouseNumber();
		String city = user.getCity();
		String postcode = user.getPostcode();
	
		Address address = new Address(houseNumber, city, postcode);
		
		HashMap<Product, Integer> hashBasket = new HashMap<>();
		ShoppingBasket basket = new ShoppingBasket(hashBasket);
		
		
		List<Product> productList= user.viewAllProducts();
		
		Collections.sort(productList, Comparator.comparing(Product::getRetailPrice));
		
		Border blackline = BorderFactory.createTitledBorder("Customer Window");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1016, 564);
		contentPane = new JPanel();
		contentPane.setBorder(blackline);

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		// VIEW PRODUCTS PANEL
		
		JPanel viewProductsPanel = new JPanel();
		tabbedPane.addTab("View Products", null, viewProductsPanel, null);
		GridBagLayout gbl_viewProductsPanel = new GridBagLayout();
		gbl_viewProductsPanel.columnWidths = new int[]{200, 200, 200};
		gbl_viewProductsPanel.rowHeights = new int[] {300, 50, 50, 50};
		gbl_viewProductsPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_viewProductsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		viewProductsPanel.setLayout(gbl_viewProductsPanel);
		
		DefaultTableModel model = new DefaultTableModel();
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		viewProductsPanel.add(scrollPane, gbc_scrollPane);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JLabel enterBarcodeLabel = new JLabel("Enter Barcode: ");
		GridBagConstraints gbc_enterBarcodeLabel = new GridBagConstraints();
		gbc_enterBarcodeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_enterBarcodeLabel.gridx = 0;
		gbc_enterBarcodeLabel.gridy = 1;
		viewProductsPanel.add(enterBarcodeLabel, gbc_enterBarcodeLabel);
		
		barcodeField = new JTextField();
		GridBagConstraints gbc_barcodeField = new GridBagConstraints();
		gbc_barcodeField.fill = GridBagConstraints.HORIZONTAL;
		gbc_barcodeField.insets = new Insets(0, 0, 5, 5);
		gbc_barcodeField.gridx = 1;
		gbc_barcodeField.gridy = 1;
		viewProductsPanel.add(barcodeField, gbc_barcodeField);
		barcodeField.setColumns(10);
		
		
		// SEARCH BUTTON
		
		JButton searchButton = new JButton("Search");
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchButton.insets = new Insets(0, 0, 5, 0);
		gbc_searchButton.gridx = 2;
		gbc_searchButton.gridy = 1;
		viewProductsPanel.add(searchButton, gbc_searchButton);
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (productList.stream().filter(o -> o.getBarcode() == Integer.parseInt(barcodeField.getText())).findFirst().isPresent()) {
						Product product = productList.stream().filter(o -> o.getBarcode() == Integer.parseInt(barcodeField.getText())).findFirst().get();
						if (product.getCategory().equals(ProductCategory.MOUSE)) {
							Mouse mouse = (Mouse) product;
							JOptionPane.showMessageDialog(null, mouse.toString(), "Result", JOptionPane.INFORMATION_MESSAGE); 
						} else if (product.getCategory().equals(ProductCategory.KEYBOARD)) {
							Keyboard keyboard = (Keyboard) product;
							JOptionPane.showMessageDialog(null, keyboard.toString(), "Result", JOptionPane.INFORMATION_MESSAGE); 
						} 
					} else {
						JOptionPane.showMessageDialog(null, "Barcode Does Not Exist", "Error", JOptionPane.ERROR_MESSAGE); 
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Please Enter A Barcode", "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		
		JLabel quantityLabel = new JLabel("Enter Quantity: ");
		GridBagConstraints gbc_quantityLabel = new GridBagConstraints();
		gbc_quantityLabel.insets = new Insets(0, 0, 5, 5);
		gbc_quantityLabel.gridx = 0;
		gbc_quantityLabel.gridy = 2;
		viewProductsPanel.add(quantityLabel, gbc_quantityLabel);
		
		quantityField = new JTextField();
		GridBagConstraints gbc_quantityField = new GridBagConstraints();
		gbc_quantityField.fill = GridBagConstraints.HORIZONTAL;
		gbc_quantityField.insets = new Insets(0, 0, 5, 5);
		gbc_quantityField.gridx = 1;
		gbc_quantityField.gridy = 2;
		viewProductsPanel.add(quantityField, gbc_quantityField);
		quantityField.setColumns(10);
		
		
		// ADD TO BASKET BUTTON
		
		JButton addToBasketButton = new JButton("Add To Basket");
		GridBagConstraints gbc_addToBasketButton = new GridBagConstraints();
		gbc_addToBasketButton.insets = new Insets(0, 0, 5, 0);
		gbc_addToBasketButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_addToBasketButton.gridx = 2;
		gbc_addToBasketButton.gridy = 2;
		viewProductsPanel.add(addToBasketButton, gbc_addToBasketButton);
		
		addToBasketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (productList.stream().filter(o -> o.getBarcode() == Integer.parseInt(barcodeField.getText())).findFirst().isPresent()) {
						Product product = productList.stream().filter(o -> o.getBarcode() == Integer.parseInt(barcodeField.getText())).findFirst().get();
						int quantity = Integer.parseInt(quantityField.getText());
						
						File file = new File("Stock.txt");
						
						try {
							Scanner fileScanner = new Scanner(file);
							BufferedWriter writer = new BufferedWriter(new FileWriter("Stock.txt", true));
							List<String> appendString = new ArrayList<>();
							
							
							while (fileScanner.hasNextLine()) {
								String line = fileScanner.nextLine();
								String[] fields = line.split(", ");
								
								int barcode = Integer.parseInt(fields[0]);
								String deviceType = fields[2];
								String brand = fields[3];
								String color = fields[4];
								ConnectivityType connectivity = ConnectivityType.valueOf(fields[5].toUpperCase());
								int quantityInStock = Integer.parseInt(fields[6]);
								double originalCost = Double.parseDouble(fields[7]);
								double retailPrice = Double.parseDouble(fields[8]);
								ProductCategory category = ProductCategory.valueOf(fields[1].toUpperCase());
								
								if (barcode == product.getBarcode()) {
									
									if (quantity <= quantityInStock) {
										
										quantityInStock -= quantity;
										if (category.equals(ProductCategory.MOUSE)) {
											int noButtons = Integer.parseInt(fields[9]);
											Mouse mouse = new Mouse(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category, noButtons);
											appendString.add(mouse.toString(deviceType));
											JOptionPane.showMessageDialog(null, "Product Added To Basket", "Added To Basket", JOptionPane.INFORMATION_MESSAGE); 
										} else if (category.equals(ProductCategory.KEYBOARD)) {
											String layout = fields[9];
											Keyboard keyboard = new Keyboard(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category, layout);
											appendString.add(keyboard.toString(deviceType));
											JOptionPane.showMessageDialog(null, "Product Added To Basket", "Added To Basket", JOptionPane.INFORMATION_MESSAGE); 
										}
										product.setQuantityInStock(quantityInStock);
										basket.addToBasket(product, quantity);
										
										
										for (int row = 0; row < table.getRowCount(); row++) {
											int barcodeInTable = (int) table.getValueAt(row, 0);
											if (barcodeInTable == product.getBarcode()) {
												model.setValueAt(quantityInStock, row, 4);
											}
										}
										
									} else {
										JOptionPane.showMessageDialog(null, "Not Enough Product In Stock", "Error", JOptionPane.ERROR_MESSAGE); 
										appendString.add(line);
										
									}
									
									
								} else {
									appendString.add(line);
								}
								
								
							}
							PrintWriter w = new PrintWriter(file);
							w.print("");
							w.close();
							for (String lineToWrite : appendString) { 
				                writer.write(lineToWrite); 
				                writer.newLine();
				                
				            }
							
							writer.close();
							fileScanner.close();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						
						basket.viewBasket();
						basketModel.clear();
						List<String> basketList = basket.viewBasket();
						for (String item: basketList) {
							basketModel.addElement(item);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Barcode Does Not Exist", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Please Enter A Barcode", "Error", JOptionPane.ERROR_MESSAGE); 
				}
			
			
			}
		});

		
		
		model.addColumn("Barcode");
		model.addColumn("Brand");
		model.addColumn("Color");
		model.addColumn("Connectivity");
		model.addColumn("Quantity In Stock");
		model.addColumn("Retail Price");
		model.addColumn("Category");
		model.addColumn("Layout");
		model.addColumn("NO. Buttons");
		
		table.setEnabled(false);
		

		// FILTER 
		
		JLabel filterLabel = new JLabel("Filter By Number Of Buttons:");
		GridBagConstraints gbc_filterLabel = new GridBagConstraints();
		gbc_filterLabel.insets = new Insets(0, 0, 0, 5);
		gbc_filterLabel.gridx = 0;
		gbc_filterLabel.gridy = 3;
		viewProductsPanel.add(filterLabel, gbc_filterLabel);
		
		filterNumberField = new JTextField();
		GridBagConstraints gbc_filterNumberField = new GridBagConstraints();
		gbc_filterNumberField.insets = new Insets(0, 0, 0, 5);
		gbc_filterNumberField.fill = GridBagConstraints.HORIZONTAL;
		gbc_filterNumberField.gridx = 1;
		gbc_filterNumberField.gridy = 3;
		viewProductsPanel.add(filterNumberField, gbc_filterNumberField);
		filterNumberField.setColumns(10);
		
		JButton filterButton = new JButton("Filter");
		GridBagConstraints gbc_filterButton = new GridBagConstraints();
		gbc_filterButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_filterButton.gridx = 2;
		gbc_filterButton.gridy = 3;
		viewProductsPanel.add(filterButton, gbc_filterButton);
		
		filterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int noButtonFilter = Integer.parseInt(filterNumberField.getText());
					List<Product> mouseList = productList.stream().filter(o -> o.getCategory() == ProductCategory.MOUSE).collect(Collectors.toList());
					List<String> match = new ArrayList<>();
					for (Product productMouse: mouseList) {
						Mouse mouse = (Mouse) productMouse;
						
						if (mouse.getNoButtons() == noButtonFilter) {
							match.add(mouse.toString());
						}
						
					}
					
					
					
					if (match.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No Products Found", "Results", JOptionPane.INFORMATION_MESSAGE); 
					} else {
						String result = match.stream().collect(Collectors.joining(System.lineSeparator()));
						JOptionPane.showMessageDialog(null, result, "Results", JOptionPane.INFORMATION_MESSAGE); 
					}
					
				} catch (java.lang.NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Please Enter A Number", "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});

		

		
		for (Product product: productList) {
			Vector<Object> row = new Vector<Object>();
			row.add(product.getBarcode());
			row.add(product.getBrand());
			row.add(product.getColor());
			row.add(product.getConnectivity());
			row.add(product.getQuantityInStock());
			row.add(product.getRetailPrice());
			ProductCategory category = product.getCategory();
			row.add(category);
			
			
			if (category.equals(ProductCategory.MOUSE)) {
				row.add(" - ");
				row.add(((Mouse) product).getNoButtons());
			
			} else if (category.equals(ProductCategory.KEYBOARD)) {
				row.add(((Keyboard) product).getLayout());
				row.add(" - ");
				
			}
			
			model.addRow(row);
			
		}
		
		
		
		// BASKET PANEL
		
		JPanel basketPanel = new JPanel();
		tabbedPane.addTab("Basket", null, basketPanel, null);
		GridBagLayout gbl_basketPanel = new GridBagLayout();
		gbl_basketPanel.columnWidths = new int[] {30, 30};
		gbl_basketPanel.rowHeights = new int[] {400, 30, 30};
		gbl_basketPanel.columnWeights = new double[]{1.0, 1.0};
		gbl_basketPanel.rowWeights = new double[]{1.0, 0.0};
		basketPanel.setLayout(gbl_basketPanel);
		
		

		
		JList<Object> list = new JList<Object>(basketModel);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridwidth = 2;
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 0;
		basketPanel.add(list, gbc_list);
		
		JButton removeItemFromBasket = new JButton("Remove Item From Basket");
		GridBagConstraints gbc_removeItemFromBasket = new GridBagConstraints();
		gbc_removeItemFromBasket.fill = GridBagConstraints.HORIZONTAL;
		gbc_removeItemFromBasket.insets = new Insets(0, 0, 5, 5);
		gbc_removeItemFromBasket.gridx = 0;
		gbc_removeItemFromBasket.gridy = 1;
		basketPanel.add(removeItemFromBasket, gbc_removeItemFromBasket);
		removeItemFromBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Product product = productList.stream().filter(o -> o.getBarcode() == Integer.parseInt(barcodeField.getText())).findFirst().get();
				
				basket.removeItem(product);
				
				int removeIndex = list.getSelectedIndex();
				String productToRemove = (String) basketModel.elementAt(removeIndex);
				basketModel.remove(removeIndex);
				
				String[] listProductToRemove = productToRemove.split(", ");
				String barcodeString = listProductToRemove[0];
				int barcodeToRemove = Integer.parseInt(barcodeString.replaceAll("[^0-9]", ""));
				
				String quantityToAddString = listProductToRemove[8];
				int quantityToAdd = Integer.parseInt(quantityToAddString.replaceAll("[^0-9]", ""));
				
				try {
					File file = new File("Stock.txt");
					Scanner fileScanner = new Scanner(file);
					BufferedWriter writer = new BufferedWriter(new FileWriter("Stock.txt", true));
					List<String> appendString = new ArrayList<>();
					
					
					while (fileScanner.hasNextLine()) {
						String line = fileScanner.nextLine();
						String[] fields = line.split(", ");
						
						int barcode = Integer.parseInt(fields[0]);
						String deviceType = fields[2];
						String brand = fields[3];
						String color = fields[4];
						ConnectivityType connectivity = ConnectivityType.valueOf(fields[5].toUpperCase());
						int quantityInStock = Integer.parseInt(fields[6]);
						double originalCost = Double.parseDouble(fields[7]);
						double retailPrice = Double.parseDouble(fields[8]);
						ProductCategory category = ProductCategory.valueOf(fields[1].toUpperCase());
						
						if (barcode == barcodeToRemove) {

							quantityInStock += quantityToAdd;
							
							for (int row = 0; row < table.getRowCount(); row++) {
								int barcodeInTable = (int) table.getValueAt(row, 0);
								if (barcodeInTable == barcodeToRemove) {
									model.setValueAt(quantityInStock, row, 4);
								} 
							}
							
							if (category.equals(ProductCategory.MOUSE)) {
								int noButtons = Integer.parseInt(fields[9]);
								Mouse mouse = new Mouse(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category, noButtons);
								appendString.add(mouse.toString(deviceType));
							} else if (category.equals(ProductCategory.KEYBOARD)) {
								String layout = fields[9];
								Keyboard keyboard = new Keyboard(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category, layout);
								appendString.add(keyboard.toString(deviceType));
							}
						} else {
							appendString.add(line);
						}
						
						product.setQuantityInStock(quantityInStock);
						

						
					}
					PrintWriter w = new PrintWriter(file);
					w.print("");
					w.close();
					for (String lineToWrite : appendString) { 
		                writer.write(lineToWrite); 
		                writer.newLine();
		                
		            }
					
					writer.close();
					fileScanner.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		
		
		JButton clearBasketButton = new JButton("Clear Basket");
		GridBagConstraints gbc_clearBasketButton = new GridBagConstraints();
		gbc_clearBasketButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_clearBasketButton.insets = new Insets(0, 0, 5, 0);
		gbc_clearBasketButton.gridx = 1;
		gbc_clearBasketButton.gridy = 1;
		basketPanel.add(clearBasketButton, gbc_clearBasketButton);
		clearBasketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				basket.clearBasket();
				
				Product product = productList.stream().filter(o -> o.getBarcode() == Integer.parseInt(barcodeField.getText())).findFirst().get();
				
				for(int index = 0 ; index <= basketModel.getSize() ; index++) {
				    String productToRemove = (String) basketModel.getElementAt(0);
				    basketModel.remove(0);
				    
				    
				    String[] listProductToRemove = productToRemove.split(", ");
					String barcodeString = listProductToRemove[0];
					int barcodeToRemove = Integer.parseInt(barcodeString.replaceAll("[^0-9]", ""));
					
					String quantityToAddString = listProductToRemove[8];
					int quantityToAdd = Integer.parseInt(quantityToAddString.replaceAll("[^0-9]", ""));
					
					try {
						File file = new File("Stock.txt");
						Scanner fileScanner = new Scanner(file);
						BufferedWriter writer = new BufferedWriter(new FileWriter("Stock.txt", true));
						List<String> appendString = new ArrayList<>();
						
						
						while (fileScanner.hasNextLine()) {
							String line = fileScanner.nextLine();
							String[] fields = line.split(", ");
							
							int barcode = Integer.parseInt(fields[0]);
							String deviceType = fields[2];
							String brand = fields[3];
							String color = fields[4];
							ConnectivityType connectivity = ConnectivityType.valueOf(fields[5].toUpperCase());
							int quantityInStock = Integer.parseInt(fields[6]);
							double originalCost = Double.parseDouble(fields[7]);
							double retailPrice = Double.parseDouble(fields[8]);
							ProductCategory category = ProductCategory.valueOf(fields[1].toUpperCase());
							
							if (barcode == barcodeToRemove) {

								quantityInStock += quantityToAdd;
								
								
								for (int row = 0; row < table.getRowCount(); row++) {
									int barcodeInTable = (int) table.getValueAt(row, 0);
									if (barcodeInTable == barcodeToRemove) {
										model.setValueAt(quantityInStock, row, 4);
									} 
								}
								
								if (category.equals(ProductCategory.MOUSE)) {
									int noButtons = Integer.parseInt(fields[9]);
									Mouse mouse = new Mouse(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category, noButtons);
									appendString.add(mouse.toString(deviceType));
								} else if (category.equals(ProductCategory.KEYBOARD)) {
									String layout = fields[9];
									Keyboard keyboard = new Keyboard(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category, layout);
									appendString.add(keyboard.toString(deviceType));
								}
							} else {
								appendString.add(line);
							}
							
							product.setQuantityInStock(quantityInStock);

							
						}
						PrintWriter w = new PrintWriter(file);
						w.print("");
						w.close();
						for (String lineToWrite : appendString) { 
			                writer.write(lineToWrite); 
			                writer.newLine();
			                
			            }
						
						
						writer.close();
						fileScanner.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				    
				}
				
			}
		
		});
		
		
		
		
		
		
		
		JButton proceedToPayButton = new JButton("Proceed To Pay");
		GridBagConstraints gbc_proceedToPayButton = new GridBagConstraints();
		gbc_proceedToPayButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_proceedToPayButton.gridwidth = 2;
		gbc_proceedToPayButton.insets = new Insets(0, 0, 0, 5);
		gbc_proceedToPayButton.gridx = 0;
		gbc_proceedToPayButton.gridy = 2;
		basketPanel.add(proceedToPayButton, gbc_proceedToPayButton);
		
		
		
		
		proceedToPayButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (basket.getBasket().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Basket Is Empty", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					List<String> itemsInBasket = basket.viewBasket();
					double total = 0.0;
					
					for (String item: itemsInBasket) {
						String[] itemList =  item.split(", ");
						double price = Double.parseDouble(itemList[6].replaceAll("[^0-9.]", ""));
						int quantity = Integer.parseInt(itemList[8].replaceAll("[^0-9]", ""));
						total += price*quantity;
						
					}
					
					
					PaymentWindow paymentWindow = new PaymentWindow(total, address, basket, basketModel);
					paymentWindow.pack();
					paymentWindow.setVisible(true);
				}
			}
		
		});
		
		
		
		// LOGOUT PANEL
		
		JPanel logoutPanel = new JPanel();
		tabbedPane.addTab("Logout", null, logoutPanel, null);
		GridBagLayout gbl_logoutPanel = new GridBagLayout();
		gbl_logoutPanel.columnWidths = new int[] {200};
		gbl_logoutPanel.rowHeights = new int[] {50};
		gbl_logoutPanel.columnWeights = new double[]{0.0};
		gbl_logoutPanel.rowWeights = new double[]{0.0};
		logoutPanel.setLayout(gbl_logoutPanel);
		
		
		JButton logoutButton = new JButton("Logout");
		GridBagConstraints gbc_logoutButton = new GridBagConstraints();
		gbc_logoutButton.fill = GridBagConstraints.BOTH;
		gbc_logoutButton.gridx = 0;
		gbc_logoutButton.gridy = 0;
		logoutPanel.add(logoutButton, gbc_logoutButton);
				
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!basket.getBasket().isEmpty()) {
					basket.clearBasket();
					Product product = productList.stream().filter(o -> o.getBarcode() == Integer.parseInt(barcodeField.getText())).findFirst().get();
					
					for(int index = 0 ; index <= basketModel.getSize() ; index++) {
					    String productToRemove = (String) basketModel.getElementAt(0);
					    basketModel.remove(0);
					    
					    
					    String[] listProductToRemove = productToRemove.split(", ");
						String barcodeString = listProductToRemove[0];
						int barcodeToRemove = Integer.parseInt(barcodeString.replaceAll("[^0-9]", ""));
						
						String quantityToAddString = listProductToRemove[8];
						int quantityToAdd = Integer.parseInt(quantityToAddString.replaceAll("[^0-9]", ""));
						
						try {
							File file = new File("Stock.txt");
							Scanner fileScanner = new Scanner(file);
							BufferedWriter writer = new BufferedWriter(new FileWriter("Stock.txt", true));
							List<String> appendString = new ArrayList<>();
							
							
							while (fileScanner.hasNextLine()) {
								String line = fileScanner.nextLine();
								String[] fields = line.split(", ");
								
								int barcode = Integer.parseInt(fields[0]);
								String deviceType = fields[2];
								String brand = fields[3];
								String color = fields[4];
								ConnectivityType connectivity = ConnectivityType.valueOf(fields[5].toUpperCase());
								int quantityInStock = Integer.parseInt(fields[6]);
								double originalCost = Double.parseDouble(fields[7]);
								double retailPrice = Double.parseDouble(fields[8]);
								ProductCategory category = ProductCategory.valueOf(fields[1].toUpperCase());
								
								if (barcode == barcodeToRemove) {

									quantityInStock += quantityToAdd;
									
									
									for (int row = 0; row < table.getRowCount(); row++) {
										int barcodeInTable = (int) table.getValueAt(row, 0);
										if (barcodeInTable == barcodeToRemove) {
											model.setValueAt(quantityInStock, row, 4);
										} 
									}
									
									if (category.equals(ProductCategory.MOUSE)) {
										int noButtons = Integer.parseInt(fields[9]);
										Mouse mouse = new Mouse(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category, noButtons);
										appendString.add(mouse.toString(deviceType));
									} else if (category.equals(ProductCategory.KEYBOARD)) {
										String layout = fields[9];
										Keyboard keyboard = new Keyboard(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category, layout);
										appendString.add(keyboard.toString(deviceType));
									}
								} else {
									appendString.add(line);
								}
								
								product.setQuantityInStock(quantityInStock);

								
							}
							PrintWriter w = new PrintWriter(file);
							w.print("");
							w.close();
							for (String lineToWrite : appendString) { 
				                writer.write(lineToWrite); 
				                writer.newLine();
				                
				            }
							
							
							writer.close();
							fileScanner.close();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					    
					}
				}
				SelectUser window = new SelectUser();
				window.pack();
				window.setVisible(true);
				dispose();
			}
		});
		
		
		
		
	}


	public DefaultListModel<Object> getBasketModel() {
		return basketModel;
	}


	public void setBasketModel(DefaultListModel<Object> basketModel) {
		this.basketModel = basketModel;
	}



}
