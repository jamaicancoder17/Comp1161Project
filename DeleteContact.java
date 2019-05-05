import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DeleteContact extends JFrame {

	private JPanel contentPane;
	private final JLabel lblNewLabel;
	private JDialog dialog;
	private JButton btnNewButton;
	private DataManager dM = new DataManager();
	private	AddressBook adBook = dM.pullAddressBook();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteContact frame = new DeleteContact();
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
	public DeleteContact() {
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
		
		lblNewLabel = new JLabel("Are you sure you want to delete?");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		btnNewButton = new JButton("YES");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				adBook.deleteContact(index);
				dM.saveAddressBook(adBook);
				JOptionPane.showMessageDialog(dialog, "Deleted Successfully.");
				setVisible(false);
				new MainMenu().setVisible(true);
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 139));
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnChoose = new JButton("NO");
		btnChoose.setForeground(new Color(0, 0, 139));
		btnChoose.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new MainMenu().setVisible(true);
			}
		});
		GridBagConstraints gbc_btnChoose = new GridBagConstraints();
		gbc_btnChoose.insets = new Insets(0, 0, 5, 0);
		gbc_btnChoose.gridx = 1;
		gbc_btnChoose.gridy = 3;
		contentPane.add(btnChoose, gbc_btnChoose);

	}

}
