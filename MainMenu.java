import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JMenuBar;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private JDialog dialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 250);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 32, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		//Welcome title
		JLabel lblWelcome = new JLabel("Welcome!");
		lblWelcome.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
		GridBagConstraints gbc_lblWelcome = new GridBagConstraints();
		gbc_lblWelcome.insets = new Insets(0, 0, 5, 5);
		gbc_lblWelcome.gridx = 1;
		gbc_lblWelcome.gridy = 0;
		contentPane.add(lblWelcome, gbc_lblWelcome);
		
		//Secondary title
		JLabel lblWhatWouldLike = new JLabel("What would like to do?");
		lblWhatWouldLike.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		GridBagConstraints gbc_lblWhatWouldLike = new GridBagConstraints();
		gbc_lblWhatWouldLike.insets = new Insets(0, 0, 5, 5);
		gbc_lblWhatWouldLike.gridx = 1;
		gbc_lblWhatWouldLike.gridy = 1;
		contentPane.add(lblWhatWouldLike, gbc_lblWhatWouldLike);
		
		//Add Contact Button
		JButton btnAddContact = new JButton("Add Contact");
		btnAddContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new AddContact().setVisible(true);
			}
		});
		btnAddContact.setForeground(new Color(0, 0, 139));
		btnAddContact.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 17));
		GridBagConstraints gbc_btnAddContact = new GridBagConstraints();
		gbc_btnAddContact.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddContact.gridx = 0;
		gbc_btnAddContact.gridy = 3;
		contentPane.add(btnAddContact, gbc_btnAddContact);
		
		//Search for Contact Button
		JButton btnSearchForContact = new JButton("Search for Contact");
		btnSearchForContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Writer wr;
				try {
					wr = new FileWriter("Flag.txt");//writing the flag to file
					wr.write( String.valueOf(1) );
					wr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				setVisible(false);
				new SearchContact().setVisible(true);
			}
		});
		btnSearchForContact.setForeground(new Color(0, 0, 139));
		btnSearchForContact.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 17));
		GridBagConstraints gbc_btnSearchForContact = new GridBagConstraints();
		gbc_btnSearchForContact.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearchForContact.gridx = 1;
		gbc_btnSearchForContact.gridy = 3;
		contentPane.add(btnSearchForContact, gbc_btnSearchForContact);
		
		//Edit Contact Button
		JButton btnEditContact = new JButton("Edit Contact");
		btnEditContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Writer wr;
				try {
					wr = new FileWriter("Flag.txt");//writing the flag to file
					wr.write( String.valueOf(2) );
					wr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(dialog, "First, you will have to search for the specific contact.");
				setVisible(false);
				new SearchContact().setVisible(true);
			}
		});
		btnEditContact.setForeground(new Color(0, 0, 139));
		btnEditContact.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 17));
		GridBagConstraints gbc_btnEditContact = new GridBagConstraints();
		gbc_btnEditContact.insets = new Insets(0, 0, 5, 0);
		gbc_btnEditContact.gridx = 2;
		gbc_btnEditContact.gridy = 3;
		contentPane.add(btnEditContact, gbc_btnEditContact);
		
		//Display Contacts Button
		JButton btnDisplayContacts = new JButton("Display All Contacts");
		btnDisplayContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Writer wr;
				try {
					wr = new FileWriter("Flag.txt");
					wr.write( String.valueOf(1) );
					wr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				setVisible(false);
				new DisplayOptions().setVisible(true);
			}
		});
		btnDisplayContacts.setForeground(new Color(0, 0, 139));
		btnDisplayContacts.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 17));
		GridBagConstraints gbc_btnDisplayContacts = new GridBagConstraints();
		gbc_btnDisplayContacts.insets = new Insets(0, 0, 5, 5);
		gbc_btnDisplayContacts.gridx = 0;
		gbc_btnDisplayContacts.gridy = 4;
		contentPane.add(btnDisplayContacts, gbc_btnDisplayContacts);
		
		//Delete Contact Button
		JButton btnDeleteContact = new JButton("Delete Contact");
		btnDeleteContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Writer wr;
				try {
					wr = new FileWriter("Flag.txt");
					wr.write( String.valueOf(3) );
					wr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(dialog, "First, you will have to search for the specific contact.");
				setVisible(false);
				new SearchContact().setVisible(true);
			}
		});
		btnDeleteContact.setForeground(new Color(0, 0, 139));
		btnDeleteContact.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 17));
		GridBagConstraints gbc_btnDeleteContact = new GridBagConstraints();
		gbc_btnDeleteContact.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteContact.gridx = 1;
		gbc_btnDeleteContact.gridy = 4;
		contentPane.add(btnDeleteContact, gbc_btnDeleteContact);
		
		//Exit System Button
		JButton btnExitSystem = new JButton("Exit System");
		btnExitSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ExitSystem().setVisible(true);
			}
		});
		btnExitSystem.setForeground(new Color(0, 0, 139));
		btnExitSystem.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 17));
		GridBagConstraints gbc_btnExitSystem = new GridBagConstraints();
		gbc_btnExitSystem.insets = new Insets(0, 0, 5, 0);
		gbc_btnExitSystem.gridx = 2;
		gbc_btnExitSystem.gridy = 4;
		contentPane.add(btnExitSystem, gbc_btnExitSystem);
	}
}
