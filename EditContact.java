import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class EditContact extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnter;
	private JTextArea txtAddress;
	private JDialog dialog;
	private JRadioButton rdbtnM;
	private JRadioButton rdbtnH;
	private JRadioButton rdbtnW;
	private ButtonGroup bttnGrp;
	private DataManager dM = new DataManager();
	private	AddressBook adBook;
	private AddressBook hold1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditContact frame = new EditContact();
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
	public EditContact() {
		adBook = dM.pullAddressBook();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblWhatWouldYou = new JLabel("What would you like to edit?");
		lblWhatWouldYou.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		GridBagConstraints gbc_lblWhatWouldYou = new GridBagConstraints();
		gbc_lblWhatWouldYou.insets = new Insets(0, 0, 5, 5);
		gbc_lblWhatWouldYou.gridx = 1;
		gbc_lblWhatWouldYou.gridy = 0;
		contentPane.add(lblWhatWouldYou, gbc_lblWhatWouldYou);
		
		//Reading the index from file
		int index;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("Choice.txt"));
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
		    index = Integer.parseInt(line);
		    }
		finally {
		    try {
				br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		JButton btnNewButton = new JButton("Last Name");
		btnNewButton.setForeground(new Color(0, 0, 139));
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 19));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEnter = new JTextField();
				txtEnter.setText("Enter Last Name:");
				GridBagConstraints gbc_txtEnter = new GridBagConstraints();
				gbc_txtEnter.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtEnter.gridx = 1;
				gbc_txtEnter.gridy = 10;
				contentPane.add(txtEnter, gbc_txtEnter);
				txtEnter.setColumns(10);
				txtEnter.addFocusListener(new FocusListener() {
				    public void focusGained(FocusEvent e) {
				    	txtEnter.setText("");
				    }

				    public void focusLost(FocusEvent e) {
				    	//do nothing
				    }
				});
				
				JButton btnDone = new JButton("DONE");
				btnDone.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int signal = 0;//flag to make sure all fields are filled out correctly
						while (signal==0)
						{
							String lName = txtEnter.getText();
							if (lName!=null)
							{
								signal=1;
							}
							if (signal==1)
							{
								hold1 = adBook;
								hold1.editLastName(lName,index);
								adBook = hold1;
							}
							else
							{
								JOptionPane.showMessageDialog(dialog, "Please fill out the field.");
							}
						}
						dM.saveAddressBook(adBook);
						JOptionPane.showMessageDialog(dialog, "Edited successfully.");
						setVisible(false);
						new MainMenu().setVisible(true);
					}
				});
				btnDone.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
				GridBagConstraints gbc_btnDone = new GridBagConstraints();
				gbc_btnDone.gridx = 1;
				gbc_btnDone.gridy = 11;
				contentPane.add(btnDone, gbc_btnDone);
				
				
				contentPane.revalidate();
				contentPane.repaint();
				
			}
		});
		
		JButton btnAddress = new JButton("Address");
		btnAddress.setForeground(new Color(0, 0, 139));
		btnAddress.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 19));
		GridBagConstraints gbc_btnAddress = new GridBagConstraints();
		gbc_btnAddress.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddress.gridx = 1;
		gbc_btnAddress.gridy = 2;
		contentPane.add(btnAddress, gbc_btnAddress);
		btnAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAddress = new JTextArea();
				txtAddress.setText("Enter the address separated by semicolons (;):");
				GridBagConstraints gbc_txtAddress = new GridBagConstraints();
				gbc_txtAddress.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtAddress.gridx = 1;
				gbc_txtAddress.gridy = 10;
				contentPane.add(txtAddress, gbc_txtAddress);
				txtAddress.setColumns(10);
				txtAddress.addFocusListener(new FocusListener() {
				    public void focusGained(FocusEvent e) {
				    	txtAddress.setText("");
				    }

				    public void focusLost(FocusEvent e) {
				    	//do nothing
				    }
				});
				
				JButton btnDone = new JButton("DONE");
				btnDone.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int signal = 0;//flag to make sure all fields are filled out correctly
						while (signal==0)
						{
							String address = txtAddress.getText();
							if (address!=null)
							{
								signal=1;
							}
							if (signal==1)
							{
								hold1 = adBook;
								hold1.editAddress(address,index);
								adBook = hold1;
							}
							else
							{
								JOptionPane.showMessageDialog(dialog, "Please fill out the field.");
							}
						}
						dM.saveAddressBook(adBook);
						JOptionPane.showMessageDialog(dialog, "Edited successfully.");
						setVisible(false);
						new MainMenu().setVisible(true);
					}
				});
				btnDone.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
				GridBagConstraints gbc_btnDone = new GridBagConstraints();
				gbc_btnDone.gridx = 1;
				gbc_btnDone.gridy = 11;
				contentPane.add(btnDone, gbc_btnDone);
				
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		JButton btnAlias = new JButton("Alias");
		btnAlias.setForeground(new Color(0, 0, 139));
		btnAlias.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 19));
		GridBagConstraints gbc_btnAlias = new GridBagConstraints();
		gbc_btnAlias.insets = new Insets(0, 0, 5, 5);
		gbc_btnAlias.gridx = 1;
		gbc_btnAlias.gridy = 3;
		contentPane.add(btnAlias, gbc_btnAlias);
		btnAlias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtEnter = new JTextField();
				txtEnter.setText("Enter Alias:");
				GridBagConstraints gbc_txtEnter = new GridBagConstraints();
				gbc_txtEnter.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtEnter.gridx = 1;
				gbc_txtEnter.gridy = 10;
				contentPane.add(txtEnter, gbc_txtEnter);
				txtEnter.setColumns(10);
				txtEnter.addFocusListener(new FocusListener() {
				    public void focusGained(FocusEvent e) {
				    	txtEnter.setText("");
				    }

				    public void focusLost(FocusEvent e) {
				    	//do nothing
				    }
				});
				
				JButton btnDone = new JButton("DONE");
				btnDone.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int signal = 0;//flag to make sure all fields are filled out correctly
						while (signal==0)
						{
							String alias = txtEnter.getText();
							if (alias!=null)
							{
								signal=1;
							}
							if (signal==1)
							{
								hold1 = adBook;
								hold1.editAlias(alias,index);
								adBook = hold1;
							}
							else
							{
								JOptionPane.showMessageDialog(dialog, "Please fill out the field.");
							}
						}
						dM.saveAddressBook(adBook);
						JOptionPane.showMessageDialog(dialog, "Edited successfully.");
						setVisible(false);
						new MainMenu().setVisible(true);
					}
				});
				btnDone.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
				GridBagConstraints gbc_btnDone = new GridBagConstraints();
				gbc_btnDone.gridx = 1;
				gbc_btnDone.gridy = 11;
				contentPane.add(btnDone, gbc_btnDone);
				
				
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		JButton btnAddPhoneNumber = new JButton("Add Phone Number");
		btnAddPhoneNumber.setForeground(new Color(0, 0, 139));
		btnAddPhoneNumber.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 19));
		GridBagConstraints gbc_btnAddPhoneNumber = new GridBagConstraints();
		gbc_btnAddPhoneNumber.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddPhoneNumber.gridx = 1;
		gbc_btnAddPhoneNumber.gridy = 4;
		contentPane.add(btnAddPhoneNumber, gbc_btnAddPhoneNumber);
		btnAddPhoneNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEnter = new JTextField();
				txtEnter.setText("Enter Last Name:");
				GridBagConstraints gbc_txtEnter = new GridBagConstraints();
				gbc_txtEnter.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtEnter.gridx = 1;
				gbc_txtEnter.gridy = 9;
				contentPane.add(txtEnter, gbc_txtEnter);
				txtEnter.setColumns(10);
				txtEnter.addFocusListener(new FocusListener() {
				    public void focusGained(FocusEvent e) {
				    	txtEnter.setText("");
				    }

				    public void focusLost(FocusEvent e) {
				    	//do nothing
				    }
				});
				
				JLabel lblType = new JLabel("Phone Type");
				GridBagConstraints gbc_lblType = new GridBagConstraints();
				gbc_lblType.insets = new Insets(0, 0, 0, 5);
				gbc_lblType.gridx = 1;
				gbc_lblType.gridy = 10;
				contentPane.add(lblType, gbc_lblType);
				
				rdbtnH = new JRadioButton("Home");
				GridBagConstraints gbc_rdbtnH = new GridBagConstraints();
				gbc_rdbtnH.insets = new Insets(0, 0, 0, 5);
				gbc_rdbtnH.gridx = 1;
				gbc_rdbtnH.gridy = 11;
				contentPane.add(rdbtnH, gbc_rdbtnH);
				
				rdbtnW = new JRadioButton("Work");
				GridBagConstraints gbc_rdbtnW = new GridBagConstraints();
				gbc_rdbtnW.insets = new Insets(0, 0, 0, 5);
				gbc_rdbtnW.gridx = 1;
				gbc_rdbtnW.gridy = 12;
				contentPane.add(rdbtnW, gbc_rdbtnW);

				rdbtnM = new JRadioButton("Mobile");
				GridBagConstraints gbc_rdbtnM = new GridBagConstraints();
				gbc_rdbtnM.insets = new Insets(0, 0, 0, 5);
				gbc_rdbtnM.gridx = 1;
				gbc_rdbtnM.gridy = 13;
				contentPane.add(rdbtnM, gbc_rdbtnM);
				
				bttnGrp = new ButtonGroup();
				bttnGrp.add(rdbtnM);
				bttnGrp.add(rdbtnH);
				bttnGrp.add(rdbtnW);
				
				
				JButton btnDone = new JButton("DONE");
				btnDone.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int signal = 0;//flag to make sure all fields are filled out correctly
						while (signal==0)
						{
							Long phone = Long.parseLong(txtEnter.getText());
							char type='R';
							if(rdbtnM.isSelected())
							{
								type = 'M';
							}
							else if(rdbtnH.isSelected())
							{
								type = 'H';
							}
							else if(rdbtnW.isSelected())
							{
								type = 'W';
							}
							
							if (txtEnter!=null && type!='R')
							{
								signal=1;
							}
							if (signal==1)
							{
								hold1 = adBook;
								hold1.addPhoneNum(type, phone,index);
								adBook = hold1;
							}
							else
							{
								JOptionPane.showMessageDialog(dialog, "Please fill out the field.");
							}
						}
						dM.saveAddressBook(adBook);
						JOptionPane.showMessageDialog(dialog, "Edited successfully.");
						setVisible(false);
						new MainMenu().setVisible(true);
					}
				});
				btnDone.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
				GridBagConstraints gbc_btnDone = new GridBagConstraints();
				gbc_btnDone.gridx = 1;
				gbc_btnDone.gridy = 14;
				contentPane.add(btnDone, gbc_btnDone);
				
				
				contentPane.revalidate();
				contentPane.repaint();
				
			}
		});
		
		JButton btnDeletePhoneNumber = new JButton("Delete Phone Number");
		btnDeletePhoneNumber.setForeground(new Color(0, 0, 139));
		btnDeletePhoneNumber.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 19));
		GridBagConstraints gbc_btnDeletePhoneNumber = new GridBagConstraints();
		gbc_btnDeletePhoneNumber.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeletePhoneNumber.gridx = 1;
		gbc_btnDeletePhoneNumber.gridy = 5;
		contentPane.add(btnDeletePhoneNumber, gbc_btnDeletePhoneNumber);
		btnDeletePhoneNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEnter = new JTextField();
				txtEnter.setText("Enter Phone Number:");
				GridBagConstraints gbc_txtEnterEntry = new GridBagConstraints();
				gbc_txtEnterEntry.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtEnterEntry.gridx = 1;
				gbc_txtEnterEntry.gridy = 9;
				contentPane.add(txtEnter, gbc_txtEnterEntry);
				txtEnter.setColumns(10);
				txtEnter.addFocusListener(new FocusListener() {
				    public void focusGained(FocusEvent e) {
				    	txtEnter.setText("");
				    }

				    public void focusLost(FocusEvent e) {
				    	//do nothing
				    }
				});
				
				JButton btnDone = new JButton("DONE");
				btnDone.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int signal = 0;//flag to make sure all fields are filled out correctly
						while (signal==0)
						{
							Long phone = Long.parseLong(txtEnter.getText());
							
							if (txtEnter!=null)
							{
								signal=1;
							}
							if (signal==1)
							{
								hold1 = adBook;
								hold1.deletePhoneNum(phone,index);
								adBook = hold1;
							}
							else
							{
								JOptionPane.showMessageDialog(dialog, "Please fill out the field.");
							}
						}
						dM.saveAddressBook(adBook);
						JOptionPane.showMessageDialog(dialog, "Edited successfully.");
						setVisible(false);
						new MainMenu().setVisible(true);
					}
				});
				btnDone.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
				GridBagConstraints gbc_btnDone = new GridBagConstraints();
				gbc_btnDone.gridx = 1;
				gbc_btnDone.gridy = 14;
				contentPane.add(btnDone, gbc_btnDone);
				
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		JButton btnAddEmailAddress = new JButton("Add Email Address");
		btnAddEmailAddress.setForeground(new Color(0, 0, 139));
		btnAddEmailAddress.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 19));
		GridBagConstraints gbc_btnAddEmailAddress = new GridBagConstraints();
		gbc_btnAddEmailAddress.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddEmailAddress.gridx = 1;
		gbc_btnAddEmailAddress.gridy = 6;
		contentPane.add(btnAddEmailAddress, gbc_btnAddEmailAddress);
		btnAddEmailAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtEnter = new JTextField();
				txtEnter.setText("Enter Email Address:");
				GridBagConstraints gbc_txtEnter = new GridBagConstraints();
				gbc_txtEnter.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtEnter.gridx = 1;
				gbc_txtEnter.gridy = 10;
				contentPane.add(txtEnter, gbc_txtEnter);
				txtEnter.setColumns(10);
				txtEnter.addFocusListener(new FocusListener() {
				    public void focusGained(FocusEvent e) {
				    	txtEnter.setText("");
				    }

				    public void focusLost(FocusEvent e) {
				    	//do nothing
				    }
				});
				
				JButton btnDone = new JButton("DONE");
				btnDone.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int signal = 0;//flag to make sure all fields are filled out correctly
						while (signal==0)
						{
							String email = txtEnter.getText();
							if (email!=null)
							{
								signal=1;
							}
							if (signal==1)
							{
								hold1 = adBook;
								hold1.updateEmail(email,index);
								adBook = hold1;
							}
							else
							{
								JOptionPane.showMessageDialog(dialog, "Please fill out the field.");
							}
						}
						dM.saveAddressBook(adBook);
						JOptionPane.showMessageDialog(dialog, "Edited successfully.");
						setVisible(false);
						new MainMenu().setVisible(true);
					}
				});
				btnDone.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
				GridBagConstraints gbc_btnDone = new GridBagConstraints();
				gbc_btnDone.gridx = 1;
				gbc_btnDone.gridy = 11;
				contentPane.add(btnDone, gbc_btnDone);
				
				
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		JButton btnDeleteEmailAddress = new JButton("Delete Email Address");
		btnDeleteEmailAddress.setForeground(new Color(0, 0, 139));
		btnDeleteEmailAddress.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 19));
		GridBagConstraints gbc_btnDeleteEmailAddress = new GridBagConstraints();
		gbc_btnDeleteEmailAddress.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteEmailAddress.gridx = 1;
		gbc_btnDeleteEmailAddress.gridy = 7;
		contentPane.add(btnDeleteEmailAddress, gbc_btnDeleteEmailAddress);
		btnDeleteEmailAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEnter = new JTextField();
				txtEnter.setText("Enter Email Address:");
				GridBagConstraints gbc_txtEnterEntry = new GridBagConstraints();
				gbc_txtEnterEntry.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtEnterEntry.gridx = 1;
				gbc_txtEnterEntry.gridy = 10;
				contentPane.add(txtEnter, gbc_txtEnterEntry);
				txtEnter.setColumns(10);
				txtEnter.addFocusListener(new FocusListener() {
				    public void focusGained(FocusEvent e) {
				    	txtEnter.setText("");
				    }

				    public void focusLost(FocusEvent e) {
				    	//do nothing
				    }
				});
				
				JButton btnDone = new JButton("DONE");
				btnDone.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int signal = 0;//flag to make sure all fields are filled out correctly
						while (signal==0)
						{
							String email = txtEnter.getText();
							if (email!=null)
							{
								signal=1;
							}
							if (signal==1)
							{
								hold1 = adBook;
								hold1.deleteEmail(email,index);
								adBook = hold1;
							}
							else
							{
								JOptionPane.showMessageDialog(dialog, "Please fill out the field.");
							}
						}
						dM.saveAddressBook(adBook);
						JOptionPane.showMessageDialog(dialog, "Edited successfully.");
						setVisible(false);
						new MainMenu().setVisible(true);
					}
				});
				btnDone.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
				GridBagConstraints gbc_btnDone = new GridBagConstraints();
				gbc_btnDone.gridx = 1;
				gbc_btnDone.gridy = 11;
				contentPane.add(btnDone, gbc_btnDone);
				
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		JButton btnExitOption = new JButton("Exit Option");
		btnExitOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new MainMenu().setVisible(true);
			}
		});
		btnExitOption.setForeground(new Color(0, 0, 139));
		btnExitOption.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 19));
		GridBagConstraints gbc_btnExitOption = new GridBagConstraints();
		gbc_btnExitOption.insets = new Insets(0, 0, 5, 5);
		gbc_btnExitOption.gridx = 1;
		gbc_btnExitOption.gridy = 8;
		contentPane.add(btnExitOption, gbc_btnExitOption);
	
	}

}
