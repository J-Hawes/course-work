package View.src;

import Control.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Reviewed by Reza
 * public methods better to have comment explaining what the method does line 63.
 * there is no good practice to hard code values better to add them into database line 109-120 !!
 * line 166 it is better to have construction with argument in search class rather to set each property.
 * line 175 better to have boolean value rather than 1, 2, 3, 4.
 * All would be personal suggestions.
 * other than that look good.
 */

public class UserHomePage extends JDialog{
    private JPanel userHomePagePane;
    private JToolBar toolBar;
    private JButton searchButton;
    private JButton recruitButton;
    private JButton messagesButton;
    private JTextField searchByTitleSkillTextField;
    private JCheckBox fullTime;
    private JCheckBox casual;
    private JComboBox salaryRangeComboBox;
    private JButton createAccountButton;
    private JButton logoutButton;
    private JButton searchButton1;
    private JCheckBox partTime;
    private JCheckBox remoteCheckBox;
    private JComboBox categoryComboBox;
    private JComboBox locationComboBox;
    private JLabel address;
    private JLabel salaryRange;
    private JButton accountButton;
    private JLabel category;
    private JButton populateMySkillsButton;

    public JCheckBox getFullTime()
    {
        return fullTime;
    }

    public JCheckBox getCasual()
    {
        return casual;
    }

    public JCheckBox getPartTime()
    {
        return partTime;
    }

    public JCheckBox getRemoteCheckBox()
    {
        return remoteCheckBox;
    }

    public UserHomePage(JFrame parent, User user) {
        super(parent);
        setTitle("UserHomePage");
        setContentPane(userHomePagePane);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Hide recruiter button for user if not recruiter, allow skill populate if JS
        int userType = user.getUserType(user.getUserID());
        recruitButton.setVisible(userType >= 3);
        populateMySkillsButton.setVisible(userType == 2 || userType == 3);

        //populate category drop down list with values from JOB_CATEGORY table in database
        try (ResultSet rs = new DataAccess().getCategoryList())
        {
            while (rs.next())
            {
                categoryComboBox.addItem(rs.getString(1));
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        categoryComboBox.setSelectedItem("Select a Category");

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

        //populate salary range with hard-coded salary ranges
        salaryRangeComboBox.addItem("Any");
        salaryRangeComboBox.addItem("$30,000+");
        salaryRangeComboBox.addItem("$50,000+");
        salaryRangeComboBox.addItem("$70,000+");
        salaryRangeComboBox.addItem("$90,000+");
        salaryRangeComboBox.addItem("$110,000+");
        salaryRangeComboBox.addItem("$130,000+");
        salaryRangeComboBox.addItem("$150,000+");
        salaryRangeComboBox.addItem("$200,000+");
        salaryRangeComboBox.addItem("$300,000+");

        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userType == 4)
                {
                    dispose();
                    RecruiterAccountDetails recruitPage = new RecruiterAccountDetails(parent, user);
                }
                else
                {
                    dispose();
                    AccountDetails AccountDetails = new AccountDetails(parent, user);
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomePage homePage = new HomePage(parent);
            }
        });

        recruitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JobListingManagement recruit = new JobListingManagement(parent, user);
            }
        });
        messagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Inbox inbox = new Inbox(parent, user);

            }
        });
        searchButton1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                Search newSearch = new Search();
                //populates a new search with the selected options
                newSearch.setKeyword(searchByTitleSkillTextField.getText());
                newSearch.setCategory(categoryComboBox.getSelectedItem().toString());
                newSearch.setLocation(locationComboBox.getSelectedItem().toString());
                newSearch.setSalaryRange(salaryRangeComboBox.getSelectedItem().toString());
                ArrayList<String> searchJobTypes = new ArrayList<>();
                newSearch.setJobTypes(searchJobTypes);
                if (getFullTime().isSelected()) {
                    newSearch.getJobTypes().add("1");
                }
                if (getPartTime().isSelected()) {
                    newSearch.getJobTypes().add("2");
                }
                if (getCasual().isSelected()) {
                    newSearch.getJobTypes().add("3");
                }
                if (getRemoteCheckBox().isSelected()) {
                    newSearch.getJobTypes().add("4");
                }
                newSearch.search();
                SearchResults searchResults = new SearchResults(null, newSearch.getSearchString().toString(), user);
            }
        });

        //retrieves user's skills from profile to populate the keyword search box
        populateMySkillsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JobSeeker currentJobSeeker = new JobSeeker(user.getUserID());
                StringBuilder newKeywordString = new StringBuilder();
                for (String skill : currentJobSeeker.getSkills()) {
                    newKeywordString.append(skill + " ");
                }
                searchByTitleSkillTextField.setText(newKeywordString.toString());
            }
        });
        setVisible(true);
    }
}
