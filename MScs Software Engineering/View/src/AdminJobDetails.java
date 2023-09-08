//Admin Job Details screen class for JSS
//@author James Hawes and Eric Khalif, reviewed by Eric Khalif
//@version ver 1.0.0
package View.src;

import Control.Job;
import Control.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminJobDetails extends JDialog {
    private JPanel adminJobDetails;
    private JToolBar toolBar;
    private JButton adminButton;
    private JButton backButton;
    private JTextField jobTitleTextField;
    private JTextField jobIDTextField;
    private JTextField employerTextField;
    private JTextField locationTextField;
    private JTextField salaryTextField;
    private JTextField categoryTextField;
    private JTextField closingDateTextField;
    private JTextField postedTextField;
    private JTextArea descriptionTextArea;
    private JList skillsList;
    private JTextField employmentTypeTextField;
    private int jobID;

    //Builds Administrator Job Details screen.
    //Reviewed by Eric Khalif.
    public AdminJobDetails(JFrame parent, User user, int jobid) {
        super(parent);
        setTitle("Admin Job Details");
        setContentPane(adminJobDetails);
        setMinimumSize(new Dimension(800, 700));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        User admin = new User(user.getUserID());
        jobID = jobid;
        populateJobDetails();

        jobTitleTextField.setEditable(false);
        jobIDTextField.setEditable(false);
        employerTextField.setEditable(false);
        locationTextField.setEditable(false);
        postedTextField.setEditable(false);
        salaryTextField.setEditable(false);
        categoryTextField.setEditable(false);
        skillsList.setEnabled(false);
        closingDateTextField.setEditable(false);
        descriptionTextArea.setEditable(false);
        employmentTypeTextField.setEditable(false);

        //Adds action listener for admin button, redirecting user to admin homepage.
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminHome adminHome = new AdminHome(parent, user);
            }
        });

        //Adds action listener for back button, closing the screen.
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    //Populates job details for review.
    //Reviewed by Eric Khalif.
    public void populateJobDetails()
    {
        Job viewJob = new Job(jobID);
        String[] skillsArray = viewJob.getSkills();
        List<String> empType = new ArrayList<>(Arrays.asList(viewJob.getEmpType()));
        jobTitleTextField.setText(viewJob.getTitle());
        jobIDTextField.setText(String.valueOf(jobID));
        employerTextField.setText(viewJob.getEmployer());
        locationTextField.setText(viewJob.getLocation());
        postedTextField.setText(viewJob.getPostedDate()
                .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        salaryTextField.setText("$" + viewJob.getSalaryMin() + " - $" + viewJob.getSalaryMax() + " p.a");
        categoryTextField.setText(viewJob.getCategory());
        skillsList.setListData(skillsArray);
        closingDateTextField.setText(viewJob.getClosingDate()
                .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        descriptionTextArea.setText(viewJob.getDescription());
        employmentTypeTextField.setText(empType.toString()
                .replace("[", "")
                .replace("]",""));
    }
}
