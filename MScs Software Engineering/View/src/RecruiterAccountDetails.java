//Admin Job Management screen class for JSS.
//@author James Hawes and Eric Khalif, reviewed by Eric Khalif
//@version ver 1.0.0
package View.src;

import Control.DataAccess;
import Control.Recruiter;
import Control.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class RecruiterAccountDetails extends JDialog{
    private JPanel recruiterPane;
    private JToolBar toolBar;
    private JButton searchButton;
    private JButton recruitButton;
    private JButton messagesButton;
    private JButton logoutButton;
    private JTextField emailTextField;
    private JButton discardButton;
    private JButton saveButton;
    private JComboBox locationComboBox;
    private JTextField companyTextField;
    private JTextField lastNameTextField;
    private JTextField firstNameTextField;
    private JCheckBox deleteCheckBox;
    private JCheckBox jobSeekerCheckBox;

    private int userID;
    private int userType;

    private Recruiter recruiter;

    //Builds RecruiterAccountDetails screen.
    //Reviewed by Eric Khalif.
    public RecruiterAccountDetails(JFrame parent, User user) {
        super(parent);
        setTitle("RecruiterAccountDetails");
        setContentPane(recruiterPane);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        userID = user.getUserID();
        recruiter = new Recruiter(userID);
        populateUserDetails(user);

        //Adds action listener for messages button, redirecting user to JSS inbox screen.
        messagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Inbox inbox = new Inbox(parent, user);
            }
        });

        //Adds action listener for recruit button, redirecting user to JSS  job listing management screen.
        recruitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JobListingManagement jobListingManagement = new JobListingManagement(parent, user);
            }
        });

        //Adds action listener for search button, redirecting user to JSS user home screen.
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserHomePage userHomePage = new UserHomePage(parent, user);
            }
        });

        //Adds action listener for logout button, redirecting user to JSS home screen.
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomePage homePage = new HomePage(parent);
            }
        });

        //Adds action listener for save button, updating the JSS database based on the changes made to the user profile.
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUserDetails(user);
                if(userType == 4)
                {
                    populateUserDetails(user);
                }
                else
                {
                    AccountDetails account = new AccountDetails(parent, user);
                }
            }
        });

        //Resets the changes back to the data in the object
        discardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateUserDetails(user);
            }
        });

        setVisible(true);

    }

    //Populates RecruiterAccountDetails with user's account details.
    //Reviewed by Eric Khalif.
    public void populateUserDetails(User user)
    {
        //Populate user details to text fields
        emailTextField.setText(user.getEmail(userID));
        firstNameTextField.setText(user.getFirstName(userID));
        lastNameTextField.setText(user.getLastName(userID));
        companyTextField.setText(recruiter.getCompanyName(userID));
        String location = user.getLocation(userID);
        userType = user.getUserType(userID);
        //Set checkbox if user is also a jobSeeker
        if (userType == 3)
        {
            jobSeekerCheckBox.setSelected(true);
        }

        //populate location drop down list with unique values from LOCATION table in database
        try (ResultSet rs = new DataAccess().getLocationList())
        {
            while (rs.next())
            {
                //Sets selected location value to match data in user object
                locationComboBox.addItem(rs.getString(1));
                for (int i = 0; i < locationComboBox.getItemCount(); i++)
                {
                    if (locationComboBox.getItemAt(i).equals(location))
                    {
                        locationComboBox.setSelectedIndex(i);
                    }
                }
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    //Updates user objects details.
    //Reviewed by Eric Khalif.
    public void updateUserDetails(User user)
    {
        if (jobSeekerCheckBox.isSelected() && (userType == 4))
        {
            user.setUserType(userID, 3);
            userType = 3;
        }
        user.setEmail(userID, emailTextField.getText());
        user.setFirstName(userID, firstNameTextField.getText());
        user.setLastName(userID, lastNameTextField.getText());
        recruiter.setCompanyName(userID, companyTextField.getText());
        user.setLocation(userID, Objects.requireNonNull(locationComboBox.getSelectedItem()).toString());
    }
}
