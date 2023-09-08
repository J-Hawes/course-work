//RecruiterReviewApplication class for JSS
//@author James Hawes & Reza Safari Daryay, reviewed by Simon Ogilvie
//@version ver 1.0.0

package View.src;

import Control.Application;
import Control.Job;
import Control.Message;
import Control.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecruiterReviewApplication extends JDialog {
    private JToolBar toolBar;
    private JButton searchButton;
    private JButton recruitButton;
    private JButton messagesButton;
    private JButton account;
    private JTextArea userComments;
    private JPanel applicationReview;
    private JTextField applicationName;
    private JTextField applicationEmail;
    private JTextField applicationLocation;
    private JTextField employer;
    private JTextField jobTitle;
    private JButton message;
    private User employee;

    //constructor for class
    public RecruiterReviewApplication(JFrame parent, User recruter, int jobid) {
        super(parent);
        setTitle("RecruiterReviewApplications");
        setContentPane(applicationReview);
        setMinimumSize(new Dimension(650, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        populateProperties(jobid);

        recruitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JobListingManagement jobListingManagement = new JobListingManagement(parent, recruter);
            }
        });
        message.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewMessage message = new NewMessage(null, recruter, employee, jobid);
            }
        });
        setVisible(true);

    }

    //returns the user who submitted the application - reviewed by Simon
    public User getEmployee() {
        return employee;
    }

    //sets the user who submitted the application - reviewed by Simon
    public void setEmployee(User employee) {
        this.employee = employee;
    }

    //populates details of submitted application
    public void populateProperties(int jobId)
    {
        Job job = new Job(jobId);
        jobTitle.setText(job.getTitle());
        employer.setText(job.getEmployer());
        Application application = new Application(jobId);
        application.getApplication();
        userComments.setText(application.getAdditionalComments());
        User user = new User(application.getUserID());
        applicationName.setText(user.getFirstName(user.getUserID()) + user.getLastName(user.getUserID()) );
        applicationEmail.setText(user.getEmail(user.getUserID()));
        applicationLocation.setText(user.getLocation(user.getUserID()));
        setEmployee(user);
    }
}
