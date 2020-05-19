import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import org.mapdb.*;
import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentNavigableMap;



public class Register  extends JFrame{

	String nameString;
	char[] passwordString;
	JTextField nameTextbox;
	JPasswordField passwordTextbox;

	public Register() {



		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		setTitle("Register");

		setBounds(300, 200, 400, 400);
		Container container=getContentPane();	
		container.setLayout(null);	

		JLabel title=new JLabel("Welcome to Register page");
		title.setBounds(this.getWidth()/2-80, 10, 200, 40);

		JLabel nameLabel=new JLabel("Username: ");
		//username position
		nameLabel.setBounds(60, 70, 200, 30);
		nameTextbox=new JTextField();	
		nameTextbox.setBounds(130, 70, 150, 30);	
		nameString = nameTextbox.getText();

		JLabel passwordLabel=new JLabel("Password: ");
		//password position
		passwordLabel.setBounds(60, 130, 200, 30);
		passwordTextbox=new JPasswordField();	
		passwordTextbox.setBounds(130, 130, 150, 30);	
		passwordString = passwordTextbox.getPassword();



		JLabel passwordConfirmLabel=new JLabel("Same Password: ");
		//passwordconfirm position
		passwordConfirmLabel.setBounds(30, 190, 200, 30);
		JPasswordField passwordConfirmtextBox=new JPasswordField();	
		passwordConfirmtextBox.setBounds(130, 190, 150, 30);	




		JButton registerButton=new JButton("Register");	

		registerButton.addActionListener(new ActionListener(){		

			public void actionPerformed(ActionEvent arg0) {

				if(nameTextbox.getText().trim().length()==0||new String(passwordTextbox.getPassword()).trim().length()==0){
					JOptionPane.showMessageDialog(null, "Username and password cannot be null");
					
				}

				// check if both password are the same
				else if(Arrays.equals(passwordConfirmtextBox.getPassword(), passwordTextbox.getPassword())  == false) {
					JOptionPane.showMessageDialog(null, "You must enter both same password!");
				}
				// register!
				else if(register(nameTextbox.getText(), passwordTextbox.getPassword(),"user")==true) {
					closeWin();

				}else if(register(nameTextbox.getText(), passwordTextbox.getPassword(),"user")==false) {
					JOptionPane.showMessageDialog(null, "User name already exist");

				}

			}
		});
		// login button position
		registerButton.setBounds(60, 260, 90, 30);	
		container.add(registerButton);	


		JButton resetButton = new JButton();
		resetButton.setText("Reset");
		resetButton.addActionListener(new ActionListener(){		

			public void actionPerformed(ActionEvent arg0) {

				nameTextbox.setText("");
				passwordTextbox.setText("");
				passwordConfirmtextBox.setText("");
			}


		});
		// reset button position
		resetButton.setBounds(200, 260, 80, 30);	
		getContentPane().add(resetButton);



		container.add(title);
		container.add(nameLabel);
		container.add(nameTextbox);
		container.add(passwordLabel);
		container.add(passwordTextbox);
		container.add(passwordConfirmLabel);
		container.add(passwordConfirmtextBox);


		setVisible(true);
	}
	public boolean register(String acc, char[] pass, String file) {
		// register using mapDB
		DB db = DBMaker.newFileDB(new File("MultiSnake"))
				.closeOnJvmShutdown()
				.encryptionEnable("password")
				.make();

		// create a treeMap or table call
		ConcurrentNavigableMap<String, char[]> table = db.getTreeMap(file);

		//System.out.println("Password: " + pass);

		// search for name
		if(findAcc(acc,pass,file)==true) {
			
			// register
			table.put(acc, pass);


			JOptionPane.showMessageDialog(null, "Register successfully!");

			db.commit();

			db.close();
			return true;
		// user name already exist
		}else {
			db.close();
			return false;
		}
	}


	public boolean findAcc(String acc, char[] pass, String file) {
		DB db = DBMaker.newFileDB(new File("MultiSnake"))
				.closeOnJvmShutdown()
				.encryptionEnable("password")
				.make();

		// create a treeMap or table call
		ConcurrentNavigableMap<String, char[]> table = db.getTreeMap(file);

		if (table.containsKey(acc) == false) {
			db.close();
			return true;
		}
		else {
			db.close();
			return false;
		}
	}

	public void closeWin() {
		this.dispose();
	}


}
