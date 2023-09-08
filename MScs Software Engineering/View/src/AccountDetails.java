//Account Details screen class for JSS class for JSS
//@author James Hawes and Eric Khalif, reviewed by Eric Khalif
//@version ver 1.0.0
package View.src;

import Control.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AccountDetails extends JDialog{
    private JPanel accountPane;
    private JToolBar toolBar;
    private JButton searchButton;
    private JButton recruitButton;
    private JButton messagesButton;
    private JButton logoutButton;
    private JTextField emailTextField;
    private JCheckBox fullTimeCheckBox;
    private JCheckBox partTimeCheckBox;
    private JCheckBox casualCheckBox;
    private JCheckBox remoteCheckBox;
    private JList skillsList;
    private JButton addNewSkillButton;
    private JComboBox skillComboBox;
    private JComboBox locationComboBox;
    private JTextField companyTextField;
    private JTextField lastNameTextField;
    private JTextField firstNameTextField;
    private JButton viewApplicationButton;
    private JButton saveButton;
    private JButton discardButton;
    private JCheckBox deleteCheckBox;
    private JLabel companyFieldLabel;
    private JCheckBox recruiterCheckBox;
    private JButton removeSkillButton;
    private int userID;

    //Constructs account details screen.
    //Reviewed by Eric Khalif.
    public AccountDetails(JFrame parent, User user) {
        super(parent);
        setTitle("AccountDetails");
        setContentPane(accountPane);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JobSeeker seeker = new JobSeeker(user.getUserID());
        skillsList.setSelectionMode(1);

        userID = user.getUserID();
        populateUserDetails(user);

        //populate skills drop down list from SKILLS table in database.
        try (ResultSet rs = new DataAccess().getSkillsList())
        {
            while (rs.next())
            {
                skillComboBox.addItem(rs.getString(2));
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        //Adds listener for logout button, redirecting to JSS homepage.
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomePage homePage = new HomePage(parent);
            }
        });

        //Adds listener for messages button, redirecting to JSS inbox.
        messagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Inbox messages = new Inbox(parent, user);
            }
        });

        //Adds listener for recruit button, redirecting to JSS job listing management screen.
        recruitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JobListingManagement recruit = new JobListingManagement(parent, user);
            }
        });

        //Adds listener for search button, redirecting to JSS search screen.
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserHomePage search = new UserHomePage(parent, user);
            }
        });

        //Adds listener for add new skill button, adding a new skill to the Job Seekers account.
        addNewSkillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String addSkill = skillComboBox.getSelectedItem().toString();
                seeker.setSkills(userID, addSkill);
                populateUserDetails(user);
            }
        });

        //Adds listener for remove skill button, removing a skill from the Job Seekers account.
        removeSkillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Checks if skill has been selected for removal.
                if(!skillsList.isSelectionEmpty())
                {
                    String removeSkill = skillsList.getSelectedValue().toString();
                    int removeSkillID = 0;
                    try (ResultSet rs = new DataAccess().getSkillId(removeSkill))
                    {
                        while (rs.next())
                        {
                            removeSkillID = rs.getInt(1);
                        }

                    }
                    catch (SQLException ex)
                    {
                        // Handle any errors
                        System.out.println("SQLException: " + ex.getMessage());
                        System.out.println("SQLState: " + ex.getSQLState());
                        System.out.println("VendorError: " + ex.getErrorCode());
                    }
                    seeker.removeSingleSkill(removeSkillID);
                    //Reload screen details
                    populateUserDetails(user);
                }
            }
        });

        //Adds listener for the discard button, discarding changes made to the Job Seekers account by the user.
        discardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateUserDetails(user);
            }
        });

        //Adds listener for the save button, applying the changes made to the Job Seekers account to the JSS database.
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUserDetails(user);
                populateUserDetails(user);
            }
        });

        setVisible(true);
    }

    //Populates account details screen with given users details.
    //Reviewed by Eric Khalif.
    public void populateUserDetails(User user)
    {
        JobSeeker seeker = new JobSeeker(userID);

        //Populate user details to text fields
        emailTextField.setText(user.getEmail(userID));
        firstNameTextField.setText(user.getFirstName(userID));
        lastNameTextField.setText(user.getLastName(userID));
        String location = user.getLocation(userID);
        int userType = user.getUserType(userID);

        //Get company details if user is a recruiter
        if (userType == 3)
        {
            Recruiter recruiter = new Recruiter(userID);
            companyTextField.setText(recruiter.getCompanyName(userID));
        }

        //Hide recruiter options for user if not recruiter
        recruiterCheckBox.setSelected(userType >= 3);
        recruitButton.setVisible(userType >= 3);
        companyFieldLabel.setVisible(userType >= 3);
        companyTextField.setVisible(userType >= 3);

        //Set employment type options
        String[] empType = seeker.getSeekingJobType();
        for (String type: empType)
        {
            switch (type)
            {
                case "Full Time" -> fullTimeCheckBox.setSelected(true);
                case "Part Time" -> partTimeCheckBox.setSelected(true);
                case "Casual" -> casualCheckBox.setSelected(true);
                case "Remote" -> remoteCheckBox.setSelected(true);
            }
        }

        //populate location drop down list with unique values from LOCATION table in database
        try (ResultSet loc = new DataAccess().getLocationList())
        {
            while (loc.next())
            {
                locationComboBox.addItem(loc.getString(1));
                //Sets the selected location to match the current location in the user object
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

        //populate skills list for user
        skillsList.setListData(seeker.getSkills());
    }

    //Updates account details on JSS database with changes made by the user on the account details screen.
    //Reviewed by Eric Khalif.
    public void updateUserDetails(User user)
    {

        int userType = user.getUserType(userID);
        user.setEmail(userID, emailTextField.getText());
        user.setFirstName(userID, firstNameTextField.getText());
        user.setLastName(userID, lastNameTextField.getText());

        //Update company if user is a recruiter
        if (userType == 3)
        {
            Recruiter recruiter = new Recruiter(userID);
            recruiter.setCompanyName(userID, companyTextField.getText());
        }

        user.setLocation(userID, Objects.requireNonNull(locationComboBox.getSelectedItem()).toString());

        //Update employment type options
        JobSeeker seeker = new JobSeeker(userID);
        List<String> addEmpType = new ArrayList<>();
        List<String> seekerType = Arrays.asList(seeker.getSeekingJobType());
        List<String> remEmpType = new ArrayList<>();
        if (fullTimeCheckBox.isSelected() && !seekerType.contains("Full Time"))
        {
            addEmpType.add("Full Time");
        }
        if (!fullTimeCheckBox.isSelected() && seekerType.contains("Full Time"))
        {
            remEmpType.add("Full Time");
        }
        if (partTimeCheckBox.isSelected() && !seekerType.contains("Part Time"))
        {
            addEmpType.add("Part Time");
        }
        if (!partTimeCheckBox.isSelected() && seekerType.contains("Part Time"))
        {
            remEmpType.add("Part Time");
        }
        if (casualCheckBox.isSelected() && !seekerType.contains("Casual"))
        {
            addEmpType.add("Casual");
        }
        if (!casualCheckBox.isSelected() && seekerType.contains("Casual"))
        {
            remEmpType.add("Casual");
        }
        if (remoteCheckBox.isSelected() && !seekerType.contains("Remote"))
        {
            addEmpType.add("Remote");
        }
        if (!remoteCheckBox.isSelected() && seekerType.contains("Remote"))
        {
            remEmpType.add("Remote");
        }
        seeker.addSeekingJobType(addEmpType.toArray(new String[0]));
        seeker.removeSeekingJobType(remEmpType.toArray(new String[0]));

        //Update user type if recruiter is selected, or not
        if (recruiterCheckBox.isSelected() && (userType == 2))
        {
            user.setUserType(userID, 3);
        }
        else if (!recruiterCheckBox.isSelected() && (userType == 3))
        {
            user.setUserType(userID, 2);
        }
    }
}
