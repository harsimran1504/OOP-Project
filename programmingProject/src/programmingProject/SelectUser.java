package programmingProject;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;

import javax.swing.Box;


public class SelectUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectUser frame = new SelectUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	
	
	public SelectUser() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 987, 686);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 0, 0, 0));
		
		JComboBox<Names> comboBox = new JComboBox<Names>();
		comboBox.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 36));
		comboBox.setModel(new DefaultComboBoxModel<Names>(Names.values()));
		contentPane.add(comboBox);
		
		Component verticalGlue = Box.createVerticalGlue();
		contentPane.add(verticalGlue);
		
		JButton btnNewButton = new JButton("Select User");
		btnNewButton.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 36));
		
		btnNewButton.addActionListener(e -> {
			
			Names name = (Names) comboBox.getSelectedItem();
			
			String role = getRole(name);
			
			if (role.equals("admin")) {
				Admin user = (Admin) getUserDetails(name);
				dispose();
				MainAdmin mainWindow = new MainAdmin(user);
				mainWindow.pack();
				mainWindow.setVisible(true);
				
				
			} else if (role.equals("customer")) {
				Customer user = (Customer) getUserDetails(name);
				dispose();
				MainCustomer mainWindow = new MainCustomer(user);
				mainWindow.pack();
				mainWindow.setVisible(true);
				
			} else {
				System.out.println("Error");
			}
				
			
		});
		;
		contentPane.add(btnNewButton);
	}
	
	
	public String getRole(Names inputName) {
		File file = new File("UserAccounts.txt");
		try {
			Scanner fileScanner = new Scanner(file);
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] fields = line.split(", ");
				Names name = Names.valueOf(fields[2].toUpperCase());
				String role = fields[6];
				
				
				if (inputName.equals(name)) {
					fileScanner.close();
					return role;
				}
				
			}
			
			fileScanner.close();
			return null;
				
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}	
		
	}
	
	
	public User getUserDetails(Names inputName) {
		File file = new File("UserAccounts.txt");
		try {
			Scanner fileScanner = new Scanner(file);
			// List<String> userDetails = new ArrayList<String>();
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] fields = line.split(", ");
				int userID = Integer.parseInt(fields[0]);
				String username = fields[1];
				Names name = Names.valueOf(fields[2].toUpperCase());
				int houseNumber = Integer.parseInt(fields[3]);
				String postcode = fields[4];
				String city = fields[5];
				String role = fields[6];
				
				
				if (inputName.equals(name)) {
				
					if (role.equals("admin")) {
						Admin user = new Admin(userID, username, name, houseNumber, postcode, city, role);
						fileScanner.close();
						return user;
					}
					
					else if (role.equals("customer")) {
						Customer user = new Customer(userID, username, name, houseNumber, postcode, city, role);
						fileScanner.close();
						return user;
					}
					
				}
				
			}
			
			fileScanner.close();
			return null;
				
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}	
	}



}
