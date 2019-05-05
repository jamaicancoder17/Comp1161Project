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
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DisplayOptions extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField txtEnterEntry;
	private JTextField txtEnterName;
	private JDialog dialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayOptions frame = new DisplayOptions();
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
	public DisplayOptions() {
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
		
		lblNewLabel = new JLabel("What would you like to sort by?");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnNewButton = new JButton("ENTRY NUMBER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Writer wr;
				try {
					wr = new FileWriter("Choice.txt");//writing the index to file
					wr.write( String.valueOf(-1) );//Letting it know to display all contacts
					wr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				setVisible(false);
				new Display().setVisible(true);
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 139));
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnChoose = new JButton("LAST NAME");
		btnChoose.setForeground(new Color(0, 0, 139));
		btnChoose.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Writer wr;
				try {
					wr = new FileWriter("Choice.txt");//writing the index to file
					wr.write( String.valueOf(-2) );//Letting it know to display all contacts
					wr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				setVisible(false);
				new Display().setVisible(true);
			}
		});
		GridBagConstraints gbc_btnChoose = new GridBagConstraints();
		gbc_btnChoose.insets = new Insets(0, 0, 5, 0);
		gbc_btnChoose.gridx = 1;
		gbc_btnChoose.gridy = 3;
		contentPane.add(btnChoose, gbc_btnChoose);
	}
}