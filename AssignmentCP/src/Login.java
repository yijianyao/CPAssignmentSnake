import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.concurrent.ConcurrentNavigableMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.mapdb.DB;
import org.mapdb.DBMaker;

public class Login extends JFrame{
	
	String nameString;
	char[] passwordString;
	JTextField nameTextbox;
	JPasswordField passwordTextbox;
	
	
	public Login() {
		nameString = "";
		System.out.println("Login page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		setTitle("Login");
		
		setBounds(300, 200, 400, 400);
		Container container=getContentPane();	
		container.setLayout(null);	
		
		JLabel title=new JLabel("Welcome to Snake game");
		title.setBounds(this.getWidth()/2-80, 10, 200, 40);
		
		JLabel nameLabel=new JLabel("Username: ");
		//username position
		nameLabel.setBounds(60, 70, 200, 30);
		nameTextbox=new JTextField();	
		nameTextbox.setBounds(130, 70, 150, 30);	
		
		
		JLabel passwordLabel=new JLabel("Password: ");
		//password position
		passwordLabel.setBounds(60, 130, 200, 30);
		passwordTextbox=new JPasswordField();	
		passwordTextbox.setBounds(130, 130, 150, 30);	
		passwordString = passwordTextbox.getPassword();


		
		JButton loginButton=new JButton("Login");	
		
		loginButton.addActionListener(new ActionListener(){		
			
			public void actionPerformed(ActionEvent arg0) {
				
				if(nameTextbox.getText().trim().length()==0||new String(passwordTextbox.getPassword()).trim().length()==0){
					JOptionPane.showMessageDialog(null, "Username or password cannot be null");
					return;
				}
				
				
				if(validation(nameTextbox.getText(), passwordTextbox.getPassword(),"user")){
					
				//if(nameTextbox.getText().trim().equals("admin")&&new String(passwordTextbox.getPassword()).trim().equals("123456")){
					JOptionPane.showMessageDialog(null, "Login succuessfully");
					
					
					//TODO jump to game page
				}
				else{
					JOptionPane.showMessageDialog(null, "Username or password incorrect");
				}
			}
		});
		// login button position
		loginButton.setBounds(60, 220, 80, 30);	
		container.add(loginButton);	
		

		JButton resetButton = new JButton();
		resetButton.setText("Reset");
		resetButton.addActionListener(new ActionListener(){		
	
			public void actionPerformed(ActionEvent arg0) {

				nameTextbox.setText("");
				passwordTextbox.setText("");
				
			}


		});
		// reset button position
		resetButton.setBounds(150, 220, 80, 30);	
		getContentPane().add(resetButton);
		
		
		
		JButton registerButton = new JButton();
		registerButton.setText("Regiseter");
		registerButton.addActionListener(new ActionListener(){		
	
			public void actionPerformed(ActionEvent arg0) {
				//JUMP to register page
				System.out.print("go to regsiter");
				Register reg = new Register();
			}


		});
		// register button position
		registerButton.setBounds(240, 220, 100, 30);	
		getContentPane().add(registerButton);
		
		
		

		container.add(title);
		container.add(nameLabel);
		container.add(nameTextbox);
		container.add(passwordLabel);
		container.add(passwordTextbox);
        
		setVisible(true);
	}
	
	public boolean validation(String acc, char[] pass, String file) {
		
		
		// create database
		DB db = DBMaker.newFileDB(new File("MultiSnake"))
				 .closeOnJvmShutdown()
				 .encryptionEnable("password")
				 .make();
		
		// create a treeMap or table call
		ConcurrentNavigableMap<String, char[]> table = db.getTreeMap(file);
		
		
		// can't find this user name
		if(table.get(acc) == null) {
			//System.out.println("no User name");
			db.close();
			return false;
		}
		
		char[] realPassword = table.get(acc);
		
		
		// match!!!
		if(Arrays.equals(realPassword, pass)) {
			db.close();
			return true;
		}
		//System.out.print("wrong pass");
		db.close();
		return false;
	}

	public static void main(String[] args) {
		new Login();

	}

}
