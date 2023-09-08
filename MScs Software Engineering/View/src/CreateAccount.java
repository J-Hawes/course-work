//CreateAccount class for JSS
//@author James Hawes, reviewed by Simon Ogilvie
//@version ver 1.0.0

package View.src;

import Control.DataAccess;
import Control.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class CreateAccount extends JDialog {
    private JToolBar ToolBar;
    private JButton searchButton;
    private JButton logIn2Button;
    private JButton loginButton;
    private JTextField firstNameTextField;
    private JPasswordField passwordTextField;
    private JComboBox locationComboBox;
    private JCheckBox jobSeekerCheckBox;
    private JButton signUpButton;
    private JPanel CreateAccount;
    private JTextField lastNameTextField;
    private JTextField emailTextField;
    private JPasswordField confirmPassTextField;
    private JCheckBox recruiterCheckBox;
    private int userType;

    //constructor for class - reviewed by Simon
    public CreateAccount(JFrame parent) {
        super(parent);
        setTitle("CreateAccount");
        setContentPane(CreateAccount);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginPage loginPage = new LoginPage(parent);
            }
        });

        logIn2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginPage loginPage = new LoginPage(parent);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomePage home = new HomePage(parent);
            }
        });

        signUpButton.addActionListener((new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                User newUser = new User();
                String email = emailTextField.getText();
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String location = String.valueOf(locationComboBox.getSelectedItem());
                String password = Arrays.toString(passwordTextField.getPassword());
                String confirm = Arrays.toString(confirmPassTextField.getPassword());

                //set User type based on check boxes
                if(jobSeekerCheckBox.isSelected() && !recruiterCheckBox.isSelected())
                {
                    userType = 2;
                } else if (jobSeekerCheckBox.isSelected() && recruiterCheckBox.isSelected())
                {
                    userType = 3;
                } else if (!jobSeekerCheckBox.isSelected() && recruiterCheckBox.isSelected())
                {
                    userType = 4;
                }
                else
                {
                    //default to job seeker if no user type selected, notify user
                    JOptionPane.showMessageDialog(null, "No User Type Selected!");
                    userType = 2;
                }

                //Validate if user email is already in use with another account
                if(!newUser.checkUserExists(email))
                {
                    emailTextField.setText("");
                }

                //If input has email and confirm matched password, create user
                if(emailTextField.getText() != null)
                {
                    //validate password and confirmation password match
                    if(password.equals(confirm))
                    {
                        try
                        {
                            String tempPassword = password.trim()
                                    .replace("[","")
                                    .replace(", ", "")
                                    .replace("]","");
                            newUser.createUser(userType,firstName,lastName,email,tempPassword,location);
                        }
                        catch (SQLException ex)
                        {
                            // Handle any errors
                            System.out.println("SQLException: " + ex.getMessage());
                            System.out.println("SQLState: " + ex.getSQLState());
                            System.out.println("VendorError: " + ex.getErrorCode());
                        }
                        dispose();
                        UserHomePage userHomePage = new UserHomePage(parent, newUser);
                    }
                    else
                    {
                        System.out.println("passwords do not match!");
                        //Reset password text fields
                        passwordTextField.setText("");
                        confirmPassTextField.setText("");
                    }
                }

            }
        }));

        //populate location drop down list with unique values from LOCATION table in database
        try (ResultSet rs = new DataAccess().getLocationList())
        {
            while (rs.next())
            {
                locationComboBox.addItem(rs.getString(1));
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        setVisible(true);
    }
}
