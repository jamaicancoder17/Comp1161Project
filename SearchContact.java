import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.*;

public class SearchContact extends JFrame {

	private JPanel contentPane;
	private final JLabel lblNewLabel;
	private JTextField txtEnterEmailAddress;
	private JTextField txtEnterEntry;
	private JDialog dialog;
	private DataManager dM = new DataManager();
	private	AddressBook adBook = dM.pullAddressBook();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchContact frame = new SearchContact();
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
	public SearchContact() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 139));
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblNewLabel = new JLabel("What do you want to search by?");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		if(adBook.getConList().size() ==0)
		{
			JOptionPane.showMessageDialog(dialog, "No entries in this AddressBook.");
			setVisible(false);
			new MainMenu().setVisible(true);
		}
		
		JButton btnNewButton = new JButton("ENTRY NUMBER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEnterEntry = new JTextField();
				txtEnterEntry.setText("Enter Entry Number:");
				GridBagConstraints gbc_txtEnterEntry = new GridBagConstraints();
				gbc_txtEnterEntry.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtEnterEntry.gridx = 1;
				gbc_txtEnterEntry.gridy = 5;
				contentPane.add(txtEnterEntry, gbc_txtEnterEntry);
				txtEnterEntry.setColumns(10);
				txtEnterEntry.addFocusListener(new FocusListener() {
				    public void focusGained(FocusEvent e) {
				    	txtEnterEntry.setText("");
				    }

				    public void focusLost(FocusEvent e) {
				    	//do nothing
				    }
				});
				
				JButton btnDone = new JButton("DONE");
				btnDone.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int signal = 0;//flag to make sure all fields are filled out correctly
						int index = 0;
						while (signal==0)
						{
							String field= txtEnterEntry.getText();
							signal=1;//test
							if (signal==1)
							{
								int entry = Integer.parseInt(field);
								index = adBook.searchByEntry(entry);
								break;
							}
							else
							{
								JOptionPane.showMessageDialog(dialog, "Please fill out the field.");
							}
						}
						
						if (index<0)
						{
							JOptionPane.showMessageDialog(dialog, "Contact could not be found :(");
							setVisible(false);
							new MainMenu().setVisible(true);//main menu
						}
						else
						{
							Writer wr;
							try {
								wr = new FileWriter("Choice.txt");//writing the index to file
								wr.write( String.valueOf(index) );
								wr.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							//Reading the flag from file
							int flag;
							BufferedReader br = null;
							try {
								br = new BufferedReader(new FileReader("Flag.txt"));
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
							    StringBuilder sb = new StringBuilder();
							    String line = null;
								try {
									line = br.readLine();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							    flag = Integer.parseInt(line);
							    }
							finally {
							    try {
									br.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							
							new Display().setVisible(true);
						}
					}
				});
				btnDone.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
				GridBagConstraints gbc_btnDone = new GridBagConstraints();
				gbc_btnDone.gridx = 1;
				gbc_btnDone.gridy = 9;
				contentPane.add(btnDone, gbc_btnDone);
				
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 139));
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnChoose = new JButton("EMAIL ADDRESS");
		btnChoose.setForeground(new Color(0, 0, 139));
		btnChoose.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEnterEmailAddress = new JTextField();
				txtEnterEmailAddress.setText("Enter Email Address:");
				GridBagConstraints gbc_txtEnterEmailAddress = new GridBagConstraints();
				gbc_txtEnterEmailAddress.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtEnterEmailAddress.gridx = 1;
				gbc_txtEnterEmailAddress.gridy = 5;
				contentPane.add(txtEnterEmailAddress, gbc_txtEnterEmailAddress);
				txtEnterEmailAddress.setColumns(10);
				contentPane.revalidate();
				contentPane.repaint();
				txtEnterEmailAddress.addFocusListener(new FocusListener() {
				    public void focusGained(FocusEvent e) {
				    	txtEnterEmailAddress.setText("");
				    }

				    public void focusLost(FocusEvent e) {
				    	//do nothing
				    }
				});
				
				JButton btnDone = new JButton("DONE");
				btnDone.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int signal = 0;//flag to make sure all fields are filled out correctly
						int index=0; //test
						while (signal==0)
						{
							String email = txtEnterEmailAddress.getText();
							signal=1;//test
							if (signal==1)
							{
								index = adBook.searchByEmail(email);
							}
							else
							{
								JOptionPane.showMessageDialog(dialog, "Please fill out the field.");
							}
						}
						if (index<0)
						{
							JOptionPane.showMessageDialog(dialog, "Contact could not be found :(");
							setVisible(false);
							new MainMenu().setVisible(true);//main menu
						}
						else//the index is written to file to notify the other frames of the specific index
						{
							Writer wr;
							try {
								wr = new FileWriter("Choice.txt");//writing the index to file
								wr.write( String.valueOf(index) );
								wr.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							//The flag is read from file to notify this class of which class to go to next.
							int flag;
							BufferedReader br = null;
							try {
								br = new BufferedReader(new FileReader("Flag.txt"));
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
							    StringBuilder sb = new StringBuilder();
							    String line = null;
								try {
									line = br.readLine();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							    flag = Integer.parseInt(line);
							    }
							finally {
							    try {
									br.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							
							setVisible(false);
							new Display().setVisible(true);
							if (flag==1)
							{
								new MainMenu().setVisible(true);//main menu
							}
							else if (flag==2)
							{
								new EditContact().setVisible(true);//edit frame
							}
							else
							{
								new DeleteContact().setVisible(true);//delete frame
							}
						}
					}
				});
				btnDone.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
				GridBagConstraints gbc_btnDone = new GridBagConstraints();
				gbc_btnDone.gridx = 1;
				gbc_btnDone.gridy = 9;
				contentPane.add(btnDone, gbc_btnDone);
			}
		});
		GridBagConstraints gbc_btnChoose = new GridBagConstraints();
		gbc_btnChoose.insets = new Insets(0, 0, 5, 0);
		gbc_btnChoose.gridx = 1;
		gbc_btnChoose.gridy = 3;
		contentPane.add(btnChoose, gbc_btnChoose);

	}

}
