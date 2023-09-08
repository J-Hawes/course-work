//JobListingManagement class for JSS
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
import java.util.List;

public class JobListingManagement extends JDialog {
    private JPanel recruitPane;
    private JButton searchButton;
    private JList jobsList;
    private JToolBar toolBar;
    private JButton recruitButton;
    private JButton messagesButton;
    private JButton accountButton;
    private JButton addNewJobButton;
    private JButton editJobButton;
    private JButton closeJobButton;
    private JButton deleteJobButton;
    private JButton viewApplication;
    private List<String> listedJobs;
    private List<Integer> jobIDs;
    private Recruiter recruiter;

    //constructor for class - reviewed by Simon
    public JobListingManagement(JFrame parent, User user) {
        super(parent);
        setTitle("recruitPane");
        setContentPane(recruitPane);
        setMinimumSize(new Dimension(650, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        recruiter = new Recruiter(user.getUserID());
        populateJobList();

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

        addNewJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                NewJobListing newJobListing = new NewJobListing(parent, user);
            }
        });

        closeJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jobsList.isSelectionEmpty())
                {
                    int jobToClose = jobIDs.get(jobsList.getSelectedIndex());
                    Job job = new Job(jobToClose);
                    job.setStatus('C');
                    populateJobList();
                }
            }
        });

        deleteJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jobsList.isSelectionEmpty())
                {
                    int toDelete = jobIDs.get(jobsList.getSelectedIndex());
                    recruiter.deleteJob(toDelete);
                    populateJobList();
                }
            }
        });

        editJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jobsList.isSelectionEmpty())
                {
                    int jobid = jobIDs.get(jobsList.getSelectedIndex());
                    dispose();
                    EditJobListing editJobListing = new EditJobListing(parent, user, jobid);
                }
            }
        });

        viewApplication.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jobsList.isSelectionEmpty())
                {
                    int jobid = jobIDs.get(jobsList.getSelectedIndex());
                    dispose();
                    RecruiterReviewApplication recruiterReviewApplication = new RecruiterReviewApplication(parent, user, jobid);
                }

            }
        });

        setVisible(true);

    }

    //Populates job list based on recruiter ID, from jobs list in the database - reviewed by Simon
    public void populateJobList()
    {
        listedJobs = new ArrayList<>();
        jobIDs = new ArrayList<>();
        try (ResultSet rs = recruiter.populateJobList())
        {
            while (rs.next())
            {
                jobIDs.add(rs.getInt(1));

                StringBuilder job = new StringBuilder(("Job Id : "));
                job.append(rs.getInt(1));
                job.append(", Title : ");
                job.append(rs.getString(4));
                job.append(", Employer : ");
                job.append(rs.getString(5));
                job.append(", Posted Date : ");
                job.append(rs.getString(3));
                job.append(", Status : ");
                char status = rs.getString(12).charAt(0);
                switch (status) {
                    case 'P' -> job.append("Posted");
                    case 'D' -> job.append("Draft");
                    case 'C' -> job.append("Closed");
                }
                listedJobs.add(String.valueOf(job));
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        jobsList.setListData(listedJobs.toArray());
    }
}