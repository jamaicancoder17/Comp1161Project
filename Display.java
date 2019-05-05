import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class Display extends JFrame {

	private JPanel contentPane;
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
					Display frame = new Display();
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
	public Display() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblFound = new JLabel("Contact(s) found:");
		lblFound.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		GridBagConstraints gbc_lblWhatWouldYou = new GridBagConstraints();
		gbc_lblWhatWouldYou.insets = new Insets(0, 0, 5, 5);
		gbc_lblWhatWouldYou.gridx = 1;
		gbc_lblWhatWouldYou.gridy = 0;
		contentPane.add(lblFound, gbc_lblWhatWouldYou);
		
		JTextArea test = new JTextArea("");
		test.setEditable(false);
		test.setFont(new Font("Comic Sans", Font.BOLD, 14));
		test.setLineWrap(true);
		test.setWrapStyleWord(true);
		test.setPreferredSize(new Dimension(1000, 1000));
		JScrollPane scrollFrame = new JScrollPane(test);
		test.setAutoscrolls(true);
		scrollFrame.setPreferredSize(new Dimension(800, 300));
		scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scrollFrame);
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.insets = new Insets(0, 0, 0, 5);
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 1;
		gbc_textPane.gridy = 2;
		contentPane.add(test, gbc_textPane);
		
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
		
		//Reading the flag from file
			int flag;
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

		if (index== -2)//signal to display all by last name
		{
			String display = adBook.returnByName();
			test.setText(display);
			
		}
		else if (index==-1)
		{
			String display = adBook.returnByEntryNo();
			test.setText(display);
		}
		else
		{
			String display = adBook.returnStr(index);
			test.setText(display);
		}		
		
		JButton btnDone = new JButton("DONE");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				if (flag==1)
				{
					new MainMenu().setVisible(true);//back to main menu
				}
				else if (flag==2)
				{
					new EditContact().setVisible(true);//edit frame
				}
				else if (flag==3)
				{
					new DeleteContact().setVisible(true);//delete frame
				}
			}
		});
		btnDone.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		GridBagConstraints gbc_btnDone = new GridBagConstraints();
		gbc_btnDone.gridx = 1;
		gbc_btnDone.gridy = 11;
		contentPane.add(btnDone, gbc_btnDone);
	}

}
