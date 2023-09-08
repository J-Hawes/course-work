//Admin Job Management screen class for JSS.
//@author James Hawes and Eric Khalif, reviewed by Eric Khalif
//@version ver 1.0.0
package View.src;
import Control.Admin;
import Control.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminJobManagement extends JDialog {
    private JPanel jobManagementPanel;
    private JToolBar toolBar;
    private JButton adminButton;
    private JList jobsList;
    private JButton removeButton;
    private JButton viewJobButton;
    private java.util.List<String> listedJobs;
    private List<Integer> jobIDs;

    private Admin admin;

    //Builds Admin Job Management Screen.
    //Reviewed by Eric Khalif.
    public AdminJobManagement(JFrame parent, User user) {
        super(parent);
        setTitle("jobManagementPanel");
        setContentPane(jobManagementPanel);
        setMinimumSize(new Dimension(850, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        admin = new Admin();

        //Builds a list of job details for display to admin user
        populateJobList();


        //Add action listener for Admin button, redirecting user to admin home page.
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminHome adminHome = new AdminHome(parent, user);
            }
        });

        //Add action listener for view job button, redirecting user to admin job details screen for the selected job.
        viewJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminJobDetails adminJobDetails =
                        new AdminJobDetails(null, user, jobIDs.get(jobsList.getSelectedIndex()));
            }
        });

        //Add action listener for remove job button, removing the selected job from the JSS database.
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin.removeJob(jobIDs.get(jobsList.getSelectedIndex()));
                populateJobList();
            }
        });

        setVisible(true);
    }

    public void populateJobList()
    {
        listedJobs = new ArrayList<>();
        jobIDs = new ArrayList<>();
        try (ResultSet rs = admin.viewJobsList())
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
