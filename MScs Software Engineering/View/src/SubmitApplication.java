//Application class for JSS
//@author Eric Khalif & James Hawes, reviewed by Simon Ogilvie
//@version ver 1.0.0

package View.src;

import Control.Job;
import Control.JobSeeker;
import Control.User;
import Control.Application;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

public class SubmitApplication extends JDialog {
    private JToolBar toolBar;
    private JButton searchButton;
    private JButton recruitButton;
    private JButton messagesButton;
    private JButton accountButton;
    private JTextArea additionalTextArea;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField emailTextField;
    private JTextField phoneTextField;
    private JButton resumeButton;
    private JButton coverButton;
    private JButton additionalButton;
    private JButton cancelButton;
    private JButton submitButton;
    private JPanel SubmitApplication;
    private JTextField jobTitleTextField;
    private JTextField employerTextField;
    private int userID;
    private int jobID;
    private File resumeFile;
    private File coverFile;
    private File additionalFile;

    //constructor for class - reviewed by Simon
    public SubmitApplication(JFrame parent, User user, Job job) {
        super(parent);
        setTitle("SubmitApplication");
        setContentPane(SubmitApplication);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        userID = user.getUserID();
        jobID = job.getJobID();
        recruitButton.setVisible(user.getUserType(user.getUserID()) >= 3);

        populateUserDetails(user);
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

        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==resumeButton) {
                    JFileChooser file_upload = new JFileChooser();
                    int res = file_upload.showSaveDialog(null);

                    if(res == JFileChooser.APPROVE_OPTION){
                        resumeFile = new File(file_upload.getSelectedFile().getAbsolutePath());
                        resumeButton.setText("Upload new file");
                    }
                }
            }
        });

        coverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==coverButton) {
                    JFileChooser file_upload = new JFileChooser();
                    int res = file_upload.showSaveDialog(null);

                    if(res == JFileChooser.APPROVE_OPTION){
                        coverFile = new File(file_upload.getSelectedFile().getAbsolutePath());
                        coverButton.setText("Upload new file");
                    }
                }
            }
        });

        additionalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==additionalButton) {
                    JFileChooser file_upload = new JFileChooser();
                    int res = file_upload.showSaveDialog(null);

                    if(res == JFileChooser.APPROVE_OPTION){
                        additionalFile = new File(file_upload.getSelectedFile().getAbsolutePath());
                        additionalButton.setText("Upload new file");
                    }
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application application = new Application(user.getUserID(), job.getJobID(), additionalTextArea.getText(), LocalDate.now());
                application.applicationDBInsert(resumeFile, coverFile, additionalFile);
                dispose();
            }
        });

        setVisible(true);
    }

    //populates job relevant details to application - reviewed by Simon
    public void populateJobDetails() {
        Job job1 = new Job(jobID);
        jobTitleTextField.setText(job1.getTitle());
        employerTextField.setText(job1.getEmployer());
        jobTitleTextField.setEditable(false);
        employerTextField.setEditable(false);
    }

    //populates user relevant details to application - reviewed by Simon
    public void populateUserDetails(User user) {
        JobSeeker seeker = new JobSeeker(userID);

        //Populate user details to text fields
        emailTextField.setText(user.getEmail(userID));
        firstNameTextField.setText(user.getFirstName(userID));
        lastNameTextField.setText(user.getLastName(userID));
    }
}