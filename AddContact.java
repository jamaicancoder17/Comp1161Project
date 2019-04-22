import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddContact extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private final ButtonGroup bttnGrp = new ButtonGroup();
	private JDialog dialog;
	private DataManager dM = new DataManager();
	private	AddressBook adBook;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddContact frame = new AddContact();
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
	public AddContact() {
		adBook = dM.pullAddressBook();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setForeground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Enter Contact Info");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblFirstName = new JLabel("First Name");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 2;
		gbc_lblFirstName.gridy = 2;
		contentPane.add(lblFirstName, gbc_lblFirstName);
		
		textField = new JTextField();
		textField.setText("Enter first name: ");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 2;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		textField.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		    	textField.setText("");
		    }

		    public void focusLost(FocusEvent e) {
		    	//do nothing
		    }
		});
		
		JLabel lblLastName = new JLabel("Last Name");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.EAST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 2;
		gbc_lblLastName.gridy = 3;
		contentPane.add(lblLastName, gbc_lblLastName);
		
		textField_1 = new JTextField();
		textField_1.setText("Enter last name: ");
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 3;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		textField_1.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		    	textField_1.setText("");
		    }

		    public void focusLost(FocusEvent e) {
		    	//do nothing
		    }
		});
		
		JLabel lblGender = new JLabel("Gender");
		GridBagConstraints gbc_lblGender = new GridBagConstraints();
		gbc_lblGender.insets = new Insets(0, 0, 5, 5);
		gbc_lblGender.gridx = 2;
		gbc_lblGender.gridy = 4;
		contentPane.add(lblGender, gbc_lblGender);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		GridBagConstraints gbc_rdbtnMale = new GridBagConstraints();
		gbc_rdbtnMale.anchor = GridBagConstraints.WEST;
		gbc_rdbtnMale.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnMale.gridx = 3;
		gbc_rdbtnMale.gridy = 4;
		contentPane.add(rdbtnMale, gbc_rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		GridBagConstraints gbc_rdbtnFemale = new GridBagConstraints();
		gbc_rdbtnFemale.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnFemale.anchor = GridBagConstraints.WEST;
		gbc_rdbtnFemale.gridx = 3;
		gbc_rdbtnFemale.gridy = 5;
		contentPane.add(rdbtnFemale, gbc_rdbtnFemale);
		
		bttnGrp.add(rdbtnMale);
		bttnGrp.add(rdbtnFemale);
		
		JLabel lblAlias = new JLabel("Alias");
		GridBagConstraints gbc_lblAlias = new GridBagConstraints();
		gbc_lblAlias.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlias.gridx = 2;
		gbc_lblAlias.gridy = 6;
		contentPane.add(lblAlias, gbc_lblAlias);
		
		textField_2 = new JTextField();
		textField_2.setText("Enter alias: ");
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 6;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		textField_2.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		    	textField_2.setText("");
		    }

		    public void focusLost(FocusEvent e) {
		    	//do nothing
		    }
		});
		
		JLabel lblNewLabel_1 = new JLabel("Address");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 7;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JTextArea txtrEnterTheAddress = new JTextArea();
		txtrEnterTheAddress.setText("Enter the address with the lines separated by semicolons(;)");
		GridBagConstraints gbc_txtrEnterTheAddress = new GridBagConstraints();
		gbc_txtrEnterTheAddress.insets = new Insets(0, 0, 5, 0);
		gbc_txtrEnterTheAddress.fill = GridBagConstraints.BOTH;
		gbc_txtrEnterTheAddress.gridx = 3;
		gbc_txtrEnterTheAddress.gridy = 7;
		contentPane.add(txtrEnterTheAddress, gbc_txtrEnterTheAddress);
		txtrEnterTheAddress.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		    	txtrEnterTheAddress.setText("");
		    }

		    public void focusLost(FocusEvent e) {
		    	//do nothing
		    }
		});
		
		JLabel lblNewLabel_2 = new JLabel("Date of Birth");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 8;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblDay = new JLabel("Day");
		GridBagConstraints gbc_lblDay = new GridBagConstraints();
		gbc_lblDay.insets = new Insets(0, 0, 5, 5);
		gbc_lblDay.anchor = GridBagConstraints.EAST;
		gbc_lblDay.gridx = 2;
		gbc_lblDay.gridy = 8;
		contentPane.add(lblDay, gbc_lblDay);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 3;
		gbc_comboBox_1.gridy = 8;
		contentPane.add(comboBox_1, gbc_comboBox_1);
		
		JLabel lblMonth = new JLabel("Month");
		GridBagConstraints gbc_lblMonth = new GridBagConstraints();
		gbc_lblMonth.insets = new Insets(0, 0, 5, 5);
		gbc_lblMonth.anchor = GridBagConstraints.EAST;
		gbc_lblMonth.gridx = 2;
		gbc_lblMonth.gridy = 9;
		contentPane.add(lblMonth, gbc_lblMonth);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "06", "07", "08", "09", "10", "11", "12"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 9;
		contentPane.add(comboBox, gbc_comboBox);
		
		JLabel lblYear = new JLabel("Year");
		GridBagConstraints gbc_lblYear = new GridBagConstraints();
		gbc_lblYear.anchor = GridBagConstraints.EAST;
		gbc_lblYear.insets = new Insets(0, 0, 5, 5);
		gbc_lblYear.gridx = 2;
		gbc_lblYear.gridy = 10;
		contentPane.add(lblYear, gbc_lblYear);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900"}));
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 3;
		gbc_comboBox_2.gridy = 10;
		contentPane.add(comboBox_2, gbc_comboBox_2);
		
		JButton btnDone = new JButton("DONE");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fName = textField.getText();
				String lName = textField_1.getText();
				Gender gen = Gender.MALE;
				if (rdbtnMale.isSelected())
				{
					gen = Gender.MALE;
				}
				else if (rdbtnFemale.isSelected())
				{
					gen = Gender.FEMALE;
				}
				String alias = textField_2.getText();
				String address = txtrEnterTheAddress.getText();
				String dob = ((String) comboBox_2.getSelectedItem() + (String) comboBox.getSelectedItem() + (String) comboBox_1.getSelectedItem());
				
				
				int flag = 0;//flag to make sure all fields are filled out correctly
				if (flag==0)
				{
					if (fName!=null && lName!=null && (rdbtnMale.isSelected() || (rdbtnFemale.isSelected())) && alias!=null)
					{
						flag=1;
					}
					if (flag==1)
					{
						adBook.makeContact(fName, lName, gen, alias, address, Long.parseLong(dob));
						JOptionPane.showMessageDialog(dialog, "Added Successfully.");
						//break;
					}
					else
					{
						JOptionPane.showMessageDialog(dialog, "Not all the fields were filled out.");
					}
				}
				dM.saveAddressBook(adBook);
				setVisible(false);
				new MainMenu().setVisible(true);
			}
		});
		btnDone.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		GridBagConstraints gbc_btnDone = new GridBagConstraints();
		gbc_btnDone.gridx = 3;
		gbc_btnDone.gridy = 11;
		contentPane.add(btnDone, gbc_btnDone);
	}

}
