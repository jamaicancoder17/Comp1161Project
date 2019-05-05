import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterTheUsername;
	private JPasswordField txtEnterThePassword;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JDialog dialog;
	private DataManager dM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {//Login Frame
		
		//Content Pane
		setBackground(new Color(32, 178, 170));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setForeground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 262, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		//Log In Title
		JLabel lblLogIn = new JLabel("LOG IN");
		lblLogIn.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
		GridBagConstraints gbc_lblLogIn = new GridBagConstraints();
		gbc_lblLogIn.insets = new Insets(0, 0, 5, 0);
		gbc_lblLogIn.gridx = 1;
		gbc_lblLogIn.gridy = 0;
		contentPane.add(lblLogIn, gbc_lblLogIn);
		
		//Username label
		lblUsername = new JLabel("Username");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 2;
		contentPane.add(lblUsername, gbc_lblUsername);
		
		//Username field
		txtEnterTheUsername = new JTextField();
		txtEnterTheUsername.setText("Enter the username: ");
		GridBagConstraints gbc_txtEnterTheUsername = new GridBagConstraints();
		gbc_txtEnterTheUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEnterTheUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtEnterTheUsername.gridx = 1;
		gbc_txtEnterTheUsername.gridy = 2;
		contentPane.add(txtEnterTheUsername, gbc_txtEnterTheUsername);
		txtEnterTheUsername.setColumns(10);
		txtEnterTheUsername.addFocusListener(new FocusListener() {//This is used to erase the words substituted in the field, "Enter the username".
		    public void focusGained(FocusEvent e) {//This is done so that they don't count as a string value.
		    	txtEnterTheUsername.setText("");
		    }

		    public void focusLost(FocusEvent e) {
		        // nothing
		    }
		});
		
		//Done Button
		JButton btnLogIn = new JButton("DONE\r\n");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Okay so the problem I'm having here is that after they get it wrong, the code won't let them do it again.
				String username = txtEnterTheUsername.getText();
				char[] password = txtEnterThePassword.getPassword();//Password is a 
				String pass = new String(password);
				dM = new DataManager();
				String[] info = dM.getInfo();
				int numTries = 3;

				if (numTries>=0)
				{
					username = txtEnterTheUsername.getText();
					password = txtEnterThePassword.getPassword();
					pass = new String(password);
					if(username.equals(info[0]) && (pass.equals(info[1])))
					{
						setVisible(false);
						new MainMenu().setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(dialog, "Access Denied. Incorrect Credentials Entered.");
						numTries-=1;		
						if (numTries==0)
						{
							JOptionPane.showMessageDialog(dialog, "Incorrect Login too many times.");
							setVisible(false);
						}
					}
				}
			}
		});
		btnLogIn.setBackground(new Color(224, 255, 255));
		btnLogIn.setActionCommand("LOG IN\r\n");
		
		//Password label
		lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 3;
		contentPane.add(lblPassword, gbc_lblPassword);
		
		//Password field
		txtEnterThePassword = new JPasswordField();
		GridBagConstraints gbc_txtEnterThePassword = new GridBagConstraints();
		gbc_txtEnterThePassword.insets = new Insets(0, 0, 5, 0);
		gbc_txtEnterThePassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEnterThePassword.gridx = 1;
		gbc_txtEnterThePassword.gridy = 3;
		contentPane.add(txtEnterThePassword, gbc_txtEnterThePassword);
		txtEnterThePassword.setColumns(10);
		btnLogIn.setForeground(new Color(0, 0, 128));
		btnLogIn.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		GridBagConstraints gbc_btnLogIn = new GridBagConstraints();
		gbc_btnLogIn.gridx = 1;
		gbc_btnLogIn.gridy = 5;
		contentPane.add(btnLogIn, gbc_btnLogIn);
		txtEnterThePassword.setEchoChar('*');//password masking
		txtEnterThePassword.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		    	txtEnterThePassword.setText("");
		    }

		    public void focusLost(FocusEvent e) {
		    	//do nothing
		    }
		});
		
	}
	

}
