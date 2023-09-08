//LoginPage class for JSS
//@author James Hawes, reviewed by Simon Ogilvie
//@version ver 1.0.0
package View.src;

import Control.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginPage extends JDialog {
    private JPanel LoginPane;
    private JToolBar ToolBar;
    private JButton searchButton;
    private JTextField emailTextField;
    private JButton signInButton;
    private JPasswordField passwordField;
    private JButton createNewAcc;

    //constructor for class - reviewed by Simon
    public LoginPage(JFrame parent){
        super(parent);
        setTitle("LoginPage");
        setContentPane(LoginPane);
        setMinimumSize(new Dimension(450, 475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        createNewAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CreateAccount createAccount = new CreateAccount(null);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomePage home = new HomePage(parent);
            }
        });

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                //Extract password into a temporary String and compare with password in database
                String password = Arrays.toString(passwordField.getPassword());
                String tempPassword = password.trim()
                        .replace("[","")
                        .replace(", ", "")
                        .replace("]","");
                if(user.login(emailTextField.getText().toLowerCase(), tempPassword))
                {
                    if (user.getUserType(user.getUserID()) == 1)
                    {
                        dispose();
                        AdminHome adminHome = new AdminHome(parent, user);
                    }
                    else
                    {
                        dispose();
                        UserHomePage userHomePage = new UserHomePage(parent, user);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, user.getLoginError());
                    //Reset fields to allow more attempts
                    emailTextField.setText("");
                    passwordField.setText("");
                }
            }
        });

        setVisible(true);
    }

}
