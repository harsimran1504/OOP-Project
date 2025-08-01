package programmingProject;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;



public class MainAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Admin user;
	private JTable table;
	private JTextField barcodeField;
	private JTextField brandField;
	private JTextField colorField;
	private JTextField quantityInStockField;
	private JTextField originalCostField;
	private JTextField retailPriceField;
	private JTextField noButtonField;

	/**
	 * Create the frame.
	 */
	public MainAdmin(Admin selectedUser) {
		
		this.user = selectedUser;
		

		Border blackline = BorderFactory.createTitledBorder("Admin Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1019, 524);
		contentPane = new JPanel();
		contentPane.setBorder(blackline);

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		
		// VIEW PRODUCTS PANEL
		
		JPanel viewProductsPanel = new JPanel();
		tabbedPane.addTab("View Products", null, viewProductsPanel, null);
		viewProductsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		viewProductsPanel.add(scrollPane);
		
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setEnabled(false);

		model.addColumn("Barcode");
		model.addColumn("Brand");
		model.addColumn("Color");
		model.addColumn("Connectivity");
		model.addColumn("Quantity In Stock");
		model.addColumn("Original Cost");
		model.addColumn("Retail Price");
		model.addColumn("Category");
		model.addColumn("Layout");
		model.addColumn("NO. Buttons");

		
		
		
		List<Product> productList= user.viewAllProducts();
		
		Collections.sort(productList, Comparator.comparing(Product::getRetailPrice));
		
		for (Product product: productList) {
			Vector<Object> row = new Vector<Object>();
			row.add(product.getBarcode());
			row.add(product.getBrand());
			row.add(product.getColor());
			row.add(product.getConnectivity());
			row.add(product.getQuantityInStock());
			row.add(product.getOriginalCost());
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


		
		// ADD PRODUCTS PANEL
		
		JPanel addProductsPanel = new JPanel();
		tabbedPane.addTab("Add Products", null, addProductsPanel, null);
		addProductsPanel.setLayout(new GridLayout(12, 2, 0, 0));

		// BARCODE INPUT
		
		JLabel barcodeLabel = new JLabel("Enter Barcode: ");
		barcodeLabel.setSize(1, 1);
		addProductsPanel.add(barcodeLabel);
		
		barcodeField = new JTextField();
		addProductsPanel.add(barcodeField);
		barcodeLabel.setSize(1, 1);
		
		// CATEGORY INPUT
		
		JLabel categoryLabel = new JLabel("Select Category: ");
		addProductsPanel.add(categoryLabel);
		
		JComboBox<ProductCategory> categoryComboBox = new JComboBox<ProductCategory>();
		categoryComboBox.setModel(new DefaultComboBoxModel<ProductCategory>(ProductCategory.values()));
		addProductsPanel.add(categoryComboBox);
		
		// DEVICE TYPE INPUT
		
		JLabel deviceTypeLabel = new JLabel("Select Device Type: ");
		addProductsPanel.add(deviceTypeLabel);
		
		JComboBox<String> deviceTypeComboBox = new JComboBox<String>();
		deviceTypeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"standard", "flexible", "gaming"}));
		addProductsPanel.add(deviceTypeComboBox);
		
		// BRAND INPUT
		
		JLabel brandLabel = new JLabel("Enter Brand: ");
		addProductsPanel.add(brandLabel);
		
		brandField = new JTextField();
		addProductsPanel.add(brandField);
		brandField.setColumns(10);
		
		// COLOR INPUT
		
		JLabel colorLabel = new JLabel("Enter Color: ");
		addProductsPanel.add(colorLabel);
		
		colorField = new JTextField();
		addProductsPanel.add(colorField);
		colorField.setColumns(10);
		
		// CONNECTIVITY INPUT
		
		JLabel connectivityLabel = new JLabel("Select Connectivity: ");
		addProductsPanel.add(connectivityLabel);
		
		JComboBox<ConnectivityType> connectivityComboBox = new JComboBox<ConnectivityType>();
		connectivityComboBox.setModel(new DefaultComboBoxModel<ConnectivityType>(ConnectivityType.values()));
		addProductsPanel.add(connectivityComboBox);
		
		// QUANTITY IN STOCK INPUT
		
		JLabel quantityInStockLabel = new JLabel("Enter Quantity In Stock:");
		addProductsPanel.add(quantityInStockLabel);
		
		quantityInStockField = new JTextField();
		addProductsPanel.add(quantityInStockField);
		quantityInStockField.setColumns(10);
		
		// ORIGINAL COST INPUT
		
		JLabel originalCostLabel = new JLabel("Enter Original Cost:");
		addProductsPanel.add(originalCostLabel);
		
		originalCostField = new JTextField();
		addProductsPanel.add(originalCostField);
		originalCostField.setColumns(10);
		
		// RETAIL PRICE INPUT
		
		JLabel retailPriceLabel = new JLabel("Enter Retail Price:");
		addProductsPanel.add(retailPriceLabel);
		
		retailPriceField = new JTextField();
		addProductsPanel.add(retailPriceField);
		retailPriceField.setColumns(10);
		
		// NUMBER OF BUTTONS INPUT
		
		JLabel noButtonLabel = new JLabel("Enter Number Of Buttons: ");
		addProductsPanel.add(noButtonLabel);
		
		noButtonField = new JTextField();
		addProductsPanel.add(noButtonField);
		noButtonField.setColumns(10);
		
		// LAYOUT INPUT
		
		JLabel layoutLabel = new JLabel("Select Layout:");
		addProductsPanel.add(layoutLabel);
		
		JComboBox<String> layoutComboBox = new JComboBox<String>();
		layoutComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"UK", "US"}));
		addProductsPanel.add(layoutComboBox);
		
		
		// SUBMIT BUTTON
		
		Component horizontalGlue = Box.createHorizontalGlue();
		addProductsPanel.add(horizontalGlue);
		
		JButton submitButton = new JButton("Submit");
		addProductsPanel.add(submitButton);
		
		
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int barcode = Integer.parseInt(barcodeField.getText());
					ProductCategory category = (ProductCategory) categoryComboBox.getSelectedItem();
					String deviceType = (String) deviceTypeComboBox.getSelectedItem();
					String brand = brandField.getText();
					String color = colorField.getText();
					ConnectivityType connectivity = (ConnectivityType) connectivityComboBox.getSelectedItem();
					int quantityInStock = Integer.parseInt(quantityInStockField.getText());
					double originalCost = Double.parseDouble(originalCostField.getText());
					double retailPrice = Double.parseDouble(retailPriceField.getText());
					int noButtons = Integer.parseInt(noButtonField.getText());
					String layout = (String) layoutComboBox.getSelectedItem();
	
					
					List<Product> products = user.viewAllProducts();
					Vector<Object> row = new Vector<Object>();
					row.add(barcode);
					row.add(brand);
					row.add(color);
					row.add(connectivity);
					row.add(quantityInStock);
					row.add(originalCost);
					row.add(retailPrice);
					// CHECK BARCODE EXISTS
					if (products.stream().filter(o -> o.getBarcode() == barcode).findFirst().isPresent()){
						JOptionPane.showMessageDialog(null, "Barcode Already Exists", "Error", JOptionPane.ERROR_MESSAGE);
					} 
					// CHECK BARCODE LENGTH
					else if (String.valueOf(barcode).length() != 6){
						JOptionPane.showMessageDialog(null, "Incorrect Barcode Length", "Error", JOptionPane.ERROR_MESSAGE);
						
					} else {
						
						if (category.equals(ProductCategory.MOUSE)) {
							// Add mouse to stock.txt
							Mouse mouse = new Mouse(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category, noButtons);
							user.addProductToStock(mouse, deviceType);
							row.add(" - ");
							row.add(noButtons);
							
						} else if (category.equals(ProductCategory.KEYBOARD)) {
							// Add keyboard to stock.txt
							Keyboard keyboard = new Keyboard(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category, layout);
							user.addProductToStock(keyboard, deviceType);
							row.add(layout);
							row.add(" - ");
						}
						model.addRow(row);
						JOptionPane.showMessageDialog(null, "Item Added To Stock", "Item Added", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (java.lang.NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Please Enter Valid Details", "Error", JOptionPane.ERROR_MESSAGE);
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
				SelectUser window = new SelectUser();
				window.pack();
				window.setVisible(true);
				dispose();
			}
		});

		
	}

}
