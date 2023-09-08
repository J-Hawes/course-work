//NewJobListing class for JSS
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class NewJobListing extends JDialog {
    private JPanel newJobPane;
    private JToolBar toolBar;
    private JButton searchButton;
    private JButton recruitButton;
    private JButton messagesButton;
    private JButton accountButton;
    private JComboBox locationComboBox;
    private JTextField jobTitleTextField;
    private JCheckBox fullTimeCheckBox;
    private JCheckBox partTimeCheckBox;
    private JCheckBox casualCheckBox;
    private JCheckBox remoteCheckBox;
    private JTextArea descriptionTextArea;
    private JTextField employerTextField;
    private JComboBox categoryComboBox;
    private JTextField salaryMinTextField;
    private JTextField salaryMaxTextField;
    private JTextField closingDateTextField;
    private JComboBox skillComboBox;
    private JList skillsList;
    private JButton saveDraftButton;
    private JButton cancelButton;
    private JButton addSkillButton;
    private JButton publishJobButton;
    private int jobID;

    //constructor for class
    public NewJobListing(JFrame parent, User user) {
        super(parent);
        setTitle("New Job Listing");
        setContentPane(newJobPane);
        setMinimumSize(new Dimension(700, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Recruiter recruiter = new Recruiter(user.getUserID());
        List<String> skills = new ArrayList<>();

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
        categoryComboBox.setSelectedItem("Select a Category");

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
                skills.add(Objects.requireNonNull(skillComboBox.getSelectedItem()).toString());
                skillsList.setListData(skills.toArray());
            }
        });

        //Returns to the job listing management screen
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JobListingManagement jobListingManagement = new JobListingManagement(parent, user);
            }
        });

        //Inserts the job into the database as a draft
        saveDraftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validateListing())
                {
                    String newJobQuery = draftJobInsert(user);
                    Job newJob = new Job(recruiter.getRecruiterID());
                    jobID = newJob.createJob(newJobQuery);
                    empTypeInsert(jobID);
                    newJob.setSkills(skills.toArray(new String[0]));
                }
            }
        });

        //Inserts the job into the database and returns to job listing management screen
        publishJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validateListing())
                {
                    String newJobQuery = newJobInsert(user);
                    Job newJob = new Job(recruiter.getRecruiterID());
                    jobID = newJob.createJob(newJobQuery);
                    empTypeInsert(jobID);
                    newJob.setSkills(skills.toArray(new String[0]));
                    dispose();
                    JobListingManagement jobListingManagement = new JobListingManagement(parent, user);
                }
            }
        });

        setVisible(true);
    }

    //Builds a query to insert a new job into database as draft status - reviewed by Simon
    public String draftJobInsert(User user)
    {
        LocalDate postedDate = LocalDate.now();
        //Create query string based on user input vales for JOB_LISTING table
        return "(" + user.getUserID() + ", '" + postedDate + "', '"
                + jobTitleTextField.getText() + "', '" + employerTextField.getText()
                + "', " + getCategoryId() + ", " + getLocationId() + ", "
                + Integer.parseInt(salaryMinTextField.getText()) + ", "
                + Integer.parseInt(salaryMaxTextField.getText()) + ", '"
                + closingDateTextField.getText() + "', '"
                + descriptionTextArea.getText() + "', 'D')";
    }

    //Builds an array of selected employment type check boxes - reviewed by Simon
    public void empTypeInsert(int jobID)
    {
        List<String> empType = new ArrayList<>();
        Job newJob = new Job(jobID);
        if (fullTimeCheckBox.isSelected())
        {
            empType.add("Full Time");
        }
        if (partTimeCheckBox.isSelected())
        {
            empType.add("Part Time");
        }
        if (casualCheckBox.isSelected())
        {
            empType.add("Casual");
        }
        if (remoteCheckBox.isSelected())
        {
            empType.add("Remote");
        }
        newJob.setEmpType(empType.toArray(new String[0]));
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

    //Builds a query to insert a new job into database as published status - reviewed by Simon
    public String newJobInsert(User user)
    {
        LocalDate postedDate = LocalDate.now();
        //Create query string based on user input vales for JOB_LISTING table
        return "(" + user.getUserID() + ", '" + postedDate + "', '"
                + jobTitleTextField.getText() + "', '" + employerTextField.getText()
                + "', " + getCategoryId() + ", " + getLocationId() + ", "
                + Integer.parseInt(salaryMinTextField.getText()) + ", "
                + Integer.parseInt(salaryMaxTextField.getText()) + ", '"
                + closingDateTextField.getText() + "', '"
                + descriptionTextArea.getText() + "', 'P')";
    }

    //Validate data before creating job - reviewed by Simon
    public boolean validateListing()
    {
        boolean val = true;

        if(jobTitleTextField.getText().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Job Title Field Is Empty");
            val = false;
        }
        if(employerTextField.getText().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Employer Field Is Empty");
            val = false;
        }

        if(salaryMinTextField.getText().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Please Enter A Minimum Salary");
            val = false;
        }
        salaryMinTextField.getText();
        try {
            double min = Double.parseDouble(salaryMinTextField.getText());
            if (min <= 0) {
                JOptionPane.showMessageDialog(null, "Minimum Salary Value Must Be Above 0");
                val = false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Minimum Salary Value Must Be A Number");
            val = false;
        }
        if(salaryMaxTextField.getText().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Please Enter A Maximum Salary");
            val = false;
        }
        salaryMaxTextField.getText();
        try {
            double max = Double.parseDouble(salaryMaxTextField.getText());
            if (max <= 0 || max <= Double.parseDouble(salaryMinTextField.getText())) {
                JOptionPane.showMessageDialog(null,
                        "Maximum Salary Value Must Be Higher than Minimum Salary");
                val = false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Maximum Salary Value Must Be A Number");
            val = false;
        }
        if(closingDateTextField.getText().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Please Enter A Closing Date");
            val = false;
        }
        if (!(closingDateTextField.getText().length() == 0))
        {
            try
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate date = LocalDate.parse(closingDateTextField.getText(), formatter);
                if (date.isBefore(LocalDate.now()))
                {
                    JOptionPane.showMessageDialog(null, "Closing Date Must Be In The Future");
                    val = false;
                }
            }
            catch (DateTimeParseException ex)
            {
                JOptionPane.showMessageDialog(null, "Please Enter A Valid Date in DD-MM-YYY Format");
                val = false;
            }
        }
        if( !(fullTimeCheckBox.isSelected() || partTimeCheckBox.isSelected()
                || casualCheckBox.isSelected() || remoteCheckBox.isSelected()))
        {
            JOptionPane.showMessageDialog(null, "Please Select An Employment Type");
            val = false;
        }
        if(skillsList.getModel().getSize() == 0)
        {
            JOptionPane.showMessageDialog(null, "Please List At Least One Skill");
            val = false;
        }
        if(descriptionTextArea.getText().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Job Description Is Empty");
            val = false;
        }

        return val;
    }
}
