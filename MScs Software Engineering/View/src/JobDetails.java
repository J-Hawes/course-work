//Admin class for JSS
//@author Eric Khalif, reviewed by Simon Ogilvie
//@version ver 1.0.0

package View.src;

import Control.Job;
import Control.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobDetails extends JDialog {
    private JToolBar toolBar;
    private JButton searchButton;
    private JButton recruitButton;
    private JButton messagesButton;
    private JButton accountButton;
    private JTextField jobTitleTextField;
    private JTable skillsTable;
    private JTextArea jobDescriptionTextArea;
    private JButton backToSearchButton;
    private JButton applyButton;
    private JTextField employerTextField;
    private JTextField salaryTextField;
    private JTextField locationTextField;
    private JTextField categoryTextField;
    private JTextField postedTextField;
    private JTextField closingDateTextField;
    private JPanel JobDetails;
    private int userID;

    private int jobID;

    //constructor for class - reviewed by Simon
    public JobDetails(JFrame parent, User user, Job job) {
        super(parent);
        setTitle("JobDetails");
        setContentPane(JobDetails);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        userID = user.getUserID();
        jobID = job.getJobID();
        recruitButton.setVisible(user.getUserType(userID) >= 3);

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
                Inbox messages = new Inbox(parent, user);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserHomePage search = new UserHomePage(parent, user);
            }
        });

        backToSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SubmitApplication submitApplication = new SubmitApplication(null, user, job);
            }
        });

        setVisible(true);

    }

    //creates table used to populate skills - reviewed by Simon
    public DefaultTableModel generateSkillsTableModel(String[] skills)
    {
        DefaultTableModel model = new DefaultTableModel(skills, 0);
        return model;
    };

    //populates details from selected job - reviewed by Simon
    public void populateJobDetails() {
        Job job1 = new Job(jobID);
        jobTitleTextField.setText(job1.getTitle());
        employerTextField.setText(job1.getEmployer());
        locationTextField.setText(job1.getLocation());
        postedTextField.setText(job1.getPostedDate().toString());
        salaryTextField.setText("$" + job1.getSalaryMin() + " - $" + job1.getSalaryMin());
        categoryTextField.setText(job1.getCategory());
        closingDateTextField.setText(job1.getClosingDate().toString());
        jobDescriptionTextArea.setText(job1.getDescription());
        skillsTable.setModel(generateSkillsTableModel(job1.getSkills()));

        jobTitleTextField.setEditable(false);
        employerTextField.setEditable(false);
        locationTextField.setEditable(false);
        postedTextField.setEditable(false);
        salaryTextField.setEditable(false);
        categoryTextField.setEditable(false);
        closingDateTextField.setEditable(false);
        jobDescriptionTextArea.setEditable(false);
    }
}
