//EditJobListing class for JSS
//@author James Hawes, reviewed by Simon Ogilvie
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

public class EditJobListing extends JDialog {
    private JPanel editJobPane;
    private JButton searchButton;
    private JToolBar toolBar;
    private JButton recruitButton;
    private JButton messagesButton;
    private JButton accountButton;
    private JComboBox locationComboBox;
    private JTextField jobTitleTextField;
    private JCheckBox fullTimeCheckBox;
    private JCheckBox partTimeCheckBox;
    private JCheckBox casualCheckBox;
    private JCheckBox remoteCheckBox;
    private JTextArea jobDescriptionTextArea;
    private JTextField employerTextField;
    private JComboBox categoryComboBox;
    private JTextField salaryMinTextField;
    private JList skillsList;
    private JComboBox skillComboBox;
    private JTextField salaryMaxTextField;
    private JTextField closingDateTextField;
    private JButton addSkillButton;
    private JButton cancelButton;
    private JButton saveButton;
    private int jobID;
    private Recruiter recruiter;
    private List<String> skills;

    //constructor for class - reviewed by Simon
    public EditJobListing(JFrame parent, User user, int jobid) {
        super(parent);
        setTitle("Edit Job Listing");
        setContentPane(editJobPane);
        setMinimumSize(new Dimension(750, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        skills = new ArrayList<>();
        jobID = jobid;
        recruiter = new Recruiter(user.getUserID());

        //populate category drop down list with values from JOB_CATEGORY table in database
        try (ResultSet cat = new DataAccess().getCategoryList())
        {
            while (cat.next())
            {
                categoryComboBox.addItem(cat.getString(1));
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        //populate location drop down list with unique values from LOCATION table in database
        try (ResultSet loc = new DataAccess().getLocationList())
        {
            while (loc.next())
            {
                locationComboBox.addItem(loc.getString(1));
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        //populate skills drop down list from SKILLS table in database
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

        populateJobDetails();


        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user.getUserType(user.getUserID()) == 4)
                {
                    dispose();
                    RecruiterAccountDetails recruiterAccountDetails = new RecruiterAccountDetails(parent, user);
                }
                else
                {
                    dispose();
                    AccountDetails accountDetails = new AccountDetails(parent, user);
                }
            }
        });

        messagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Inbox messages = new Inbox(parent, user);
            }
        });

        recruitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JobListingManagement recruit = new JobListingManagement(parent, user);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserHomePage userHomePage = new UserHomePage(parent, user);
            }
        });

        addSkillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!skills.contains(Objects.requireNonNull(skillComboBox.getSelectedItem()).toString()))
                {
                    skills.add(skillComboBox.getSelectedItem().toString());
                    skillsList.setListData(skills.toArray());
                }
            }
        });

        //Returns to job listing management screen
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JobListingManagement jobListingManagement = new JobListingManagement(parent, user);
            }
        });

        //Updates the database with revisions and returns to the job listing management screen
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateJob();
                dispose();
                JobListingManagement jobListingManagement = new JobListingManagement(parent, user);
            }
        });

        setVisible(true);
    }

    //Obtain the CategoryId number based on the selected user input string - reviewed by Simon
    public int getCategoryId()
    {
        String cat = categoryComboBox.getSelectedItem().toString();
        int catid = 0;
        try (ResultSet rs = new DataAccess().getCategoryID(cat))
        {
            while (rs.next())
            {
                catid = rs.getInt(1);
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return catid;
    }

    //Obtain the LocationId number based on the selected user input string - reviewed by Simon
    public int getLocationId()
    {
        String loc = locationComboBox.getSelectedItem().toString();
        int locid = 0;
        try (ResultSet rs = new DataAccess().getLocationID(loc))
        {
            while (rs.next())
            {
                locid = rs.getInt(1);
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return locid;
    }

    //Returns all the data from the job object to populate the page - reviewed by Simon
    public void populateJobDetails()
    {
        Job editJob = new Job(recruiter.getUserID(), jobID);
        jobTitleTextField.setText(editJob.getTitle());
        employerTextField.setText(editJob.getEmployer());
        categoryComboBox.setSelectedItem(editJob.getCategory());
        locationComboBox.setSelectedItem(editJob.getLocation());
        salaryMinTextField.setText(String.valueOf(editJob.getSalaryMin()));
        salaryMaxTextField.setText(String.valueOf(editJob.getSalaryMax()));
        closingDateTextField.setText(String.valueOf(editJob.getClosingDate()));
        for (String type: editJob.getEmpType())
        {
            switch (type)
            {
                case "Full Time" -> fullTimeCheckBox.setSelected(true);
                case "Part Time" -> partTimeCheckBox.setSelected(true);
                case "Casual" -> casualCheckBox.setSelected(true);
                case "Remote" -> remoteCheckBox.setSelected(true);
            }
        }
        String[] skillsArray = editJob.getSkills();
        skills.addAll(Arrays.asList(skillsArray));
        skillsList.setListData(skills.toArray());
        jobDescriptionTextArea.setText(editJob.getDescription());
    }

    //Builds a query string with updates then sends as a query to update the database - reviewed by Simon
    public void updateJob()
    {
        StringBuilder updateJob = new StringBuilder("UPDATE JOB_LISTING SET title = '");
        updateJob.append(jobTitleTextField.getText());
        updateJob.append("', employer = '").append(employerTextField.getText());
        updateJob.append("', category = ").append(getCategoryId());
        updateJob.append(", loc_id = ").append(getLocationId());
        updateJob.append(", salary_min = ").append(salaryMinTextField.getText());
        updateJob.append(", salary_max = ").append(salaryMaxTextField.getText());
        updateJob.append(", closing_date = '").append(closingDateTextField.getText());
        updateJob.append("', description = '").append(jobDescriptionTextArea.getText());
        updateJob.append("' WHERE job_id = ").append(jobID).append(";");
        recruiter.updateJob(updateJob.toString());
    }
}
