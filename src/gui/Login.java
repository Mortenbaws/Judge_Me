package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JTextField;

import client.Connector;
import server.ServerRun;
import server.ServerStart;

import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

public class Login {

	private JFrame frame;
	private JTextField usernameTxtField;
	private JPasswordField passwordTxtField;
	private static JPanel panel;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	private JTextField textField6;
	private Connector connecter;
	private JLabel loginFail, failLabel;
	private JButton btnCreateAccount;
	private JButton loginButton;
	
	private boolean accessConfirmed;
	private boolean profileCreated;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		try {
			connecter = new Connector();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.textHighlight);
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Judge Me");
		panel = new JPanel();
		panel.setBounds(0, 0, 450, 600);
		panel.setBackground(SystemColor.textHighlight);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		JLabel headline = new JLabel("Create new account");
		headline.setFont(GuiData.getHeadlineFont());
		headline.setSize(headline.getPreferredSize());
		headline.setBounds((panel.getWidth() - headline.getWidth())/2, 30, headline.getWidth(), headline.getHeight());
		panel.add(headline);
		
		JLabel newUsername = new JLabel("Username:");
		newUsername.setFont(GuiData.getUserPassFont());
		newUsername.setSize(newUsername.getPreferredSize());
		newUsername.setBounds(40, 100, newUsername.getWidth(), newUsername.getHeight());
		panel.add(newUsername);
		
		JLabel newPassword = new JLabel("Password:");
		newPassword.setFont(GuiData.getUserPassFont());
		newPassword.setSize(newPassword.getPreferredSize());
		newPassword.setBounds(newUsername.getX(), newUsername.getY() + GuiData.getY(), newPassword.getWidth(), newPassword.getHeight());
		panel.add(newPassword);
		
		JLabel newPassword2 = new JLabel("Confirm Password:");		
		newPassword2.setFont(GuiData.getUserPassFont());
		newPassword2.setSize(newPassword2.getPreferredSize());
		newPassword2.setBounds(newUsername.getX(), newPassword.getY() + GuiData.getY(), newPassword2.getWidth(), newPassword2.getHeight());
		panel.add(newPassword2);
		
		JLabel email = new JLabel("Email:");
		email.setFont(GuiData.getUserPassFont());
		email.setSize(email.getPreferredSize());
		email.setBounds(newUsername.getX(), newPassword2.getY() + GuiData.getY(), email.getWidth(), email.getHeight());
		panel.add(email);
		
		JLabel confirmEmail = new JLabel("Confirm Email:");
		confirmEmail.setFont(GuiData.getUserPassFont());
		confirmEmail.setSize(confirmEmail.getPreferredSize());
		confirmEmail.setBounds(newUsername.getX(), email.getY() + GuiData.getY(), confirmEmail.getWidth(), confirmEmail.getHeight());
		panel.add(confirmEmail);
		
		JLabel Age = new JLabel("Gender:");
		Age.setFont(GuiData.getUserPassFont());
		Age.setSize(Age.getPreferredSize());
		Age.setBounds(newUsername.getX(), confirmEmail.getY() + GuiData.getY(), Age.getWidth(), Age.getHeight());
		panel.add(Age);
		
		textField1 = new JTextField();
		textField1.setBounds(210, 90, 200, 40);
		panel.add(textField1);
		textField1.setColumns(10);
		
//		for password
		textField2 = new JPasswordField();
		textField2.setBounds(textField1.getX(), textField1.getY() + GuiData.getY(), 200, 40);
		panel.add(textField2);
		textField2.setColumns(10);
		
//		rewrite password correctly
		textField3 = new JPasswordField();
		textField3.setBounds(textField1.getX(), textField2.getY() + GuiData.getY(), 200, 40);
		panel.add(textField3);
		textField3.setColumns(10);
		
		textField4 = new JTextField();
		textField4.setBounds(textField1.getX(), textField3.getY() + GuiData.getY(), 200, 40);
		panel.add(textField4);
		textField4.setColumns(10);
		
		textField5 = new JTextField();
		textField5.setBounds(textField1.getX(), textField4.getY() + GuiData.getY(), 200, 40);
		panel.add(textField5);
		textField5.setColumns(10);
		
		textField6 = new JTextField();
		textField6.setText("male or female..");
		textField6.setBounds(textField1.getX(), textField5.getY() + GuiData.getY(), 200, 40);
		panel.add(textField6);
		textField6.setColumns(10);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Do you accept our policy?");
		chckbxNewCheckBox.setBounds(125, Age.getY() + GuiData.getY(), 200, 23);
		panel.add(chckbxNewCheckBox);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				panel.setVisible(false);
				frame.setBounds(100, 100, 450, 300);
				btnCreateAccount.setVisible(true);
				loginButton.setVisible(true);
			}
		});
		backButton.setBounds(newUsername.getX(), chckbxNewCheckBox.getY() + GuiData.getY() - 10, 180, 50);
		panel.add(backButton);
		
//		Fail label
		JLabel failL = new JLabel("");
		failL.setFont(GuiData.getUserPassFont());
		failL.setSize(failL.getPreferredSize());
		failL.setBounds(newUsername.getX(), headline.getY() + 20, failL.getWidth(), failL.getHeight());
		panel.add(failL);
		
		JButton createButton = new JButton("Create");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(match() && contains()){
				
					 String s = connecter.createUser(textField1.getText(), textField2.getText(), textField6.getText(), textField3.getText());
					 System.out.println(s);
					 if(s.equals("User is create and login successful"))
					 {
						 //window should become smaller
						panel.setVisible(false);
						btnCreateAccount.setVisible(true);
						loginButton.setVisible(true);
					 } else{
						 failLabel.setText(s);
					 }
				}
			}
		});
		createButton.setBounds(newUsername.getX() + backButton.getWidth() + 10, chckbxNewCheckBox.getY() + GuiData.getY() - 10, 180, 50);
		panel.add(createButton);
		
		panel.setVisible(false);
		
		
		
		
		
		
		
		
		
		
//		THIS IS THE FIRST GUI
		JLabel username = new JLabel("Username: ");
		username.setFont(GuiData.getUserPassFont());
		username.setSize(username.getPreferredSize());
		username.setBounds(60,70, username.getWidth(), username.getHeight());
		frame.getContentPane().add(username);
		
		JLabel password = new JLabel("Password: ");
		password.setFont(GuiData.getUserPassFont());
		password.setSize(password.getPreferredSize());
		password.setBounds(username.getX(), username.getY() + username.getHeight() + 30, password.getWidth(), password.getHeight());
		frame.getContentPane().add(password);
		
		usernameTxtField = new JTextField();
		usernameTxtField.setFont(GuiData.getLoginFieldFont());
		usernameTxtField.setBounds(username.getX() + username.getWidth() + 20, username.getY() - 10, 200, 40);
		frame.getContentPane().add(usernameTxtField);
		usernameTxtField.setColumns(10);
		
		passwordTxtField = new JPasswordField();
		passwordTxtField.setFont(GuiData.getLoginFieldFont());
		passwordTxtField.setBounds(password.getX() + username.getWidth() + 20, password.getY() - 10, 200, 40);
		frame.getContentPane().add(passwordTxtField);
		
//		The login fail text
		loginFail = new JLabel("Sorry either your username or password is wrong");
		loginFail.setFont(GuiData.getCornerFont());
		loginFail.setForeground(Color.red);
		loginFail.setSize(loginFail.getPreferredSize());
		loginFail.setText("");
		loginFail.setBounds(password.getX(),20, loginFail.getWidth(), loginFail.getHeight());
		frame.getContentPane().add(loginFail);

		btnCreateAccount = new JButton("Create Profile");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				frame.setBounds(100, 100, 450, 600);
				panel.setVisible(true);
				btnCreateAccount.setVisible(false);
				loginButton.setVisible(false);
			}
		});
		btnCreateAccount.setBounds(password.getX(), password.getY() + password.getHeight() + 20, passwordTxtField.getX() - password.getX() - 10, 40);
		frame.getContentPane().add(btnCreateAccount);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				
				if(!usernameTxtField.getText().isEmpty() && !passwordTxtField.getText().isEmpty()) {	
					System.out.println(passwordTxtField.getText());
					accessConfirmed = connecter.login(usernameTxtField.getText(), passwordTxtField.getText());
					MainGui mg = new MainGui(connecter);
				
					if(accessConfirmed == true) 
					{
						frame.dispose();
						mg.setVisible(true);
					} else {
						System.out.println("Could not login either the username or password is wrong");
						loginFail.setText("Sorry either your username or password is wrong");
						passwordTxtField.setText("");
					}
				
				} else {
					loginFail.setText("Sorry fields must not be empty");
				}
			}
		});
		loginButton.setBounds(passwordTxtField.getX(), btnCreateAccount.getY(), passwordTxtField.getWidth(), 40);
		frame.getContentPane().add(loginButton);
		
	
	}
	
	public boolean match(){
		return textField2.getText().equals(textField3.getText()) && textField4.getText().equals(textField5.getText());
	}
	
	public boolean contains(){
		return !textField1.getText().isEmpty() && !textField2.getText().isEmpty() && !textField3.getText().isEmpty() 
				&& !textField4.getText().isEmpty() && !textField5.getText().isEmpty() && !textField6.getText().isEmpty();
	}
}
	

	
	

