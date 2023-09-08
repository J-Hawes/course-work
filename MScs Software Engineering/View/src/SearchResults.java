package View.src;
import Control.DBaccess;
import Control.Job;
import Control.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reviewed by Reza
 * public methods better to have comment explaining what the method does.
 * other than that look good.
 */

public class SearchResults extends JDialog {
    private JPanel Result;
    private JToolBar ToolBar;
    private JButton searchButton;
    private JButton logInButton2;
    private JList resultsList;
    private JToolBar toolBar;
    private JButton recruitButton;
    private JButton messagesButton;
    private JButton accountButton;
    private JButton viewJobButton;
    private String searchQuery;
    private User currentUser;
    private List<String> searchResults;
    private List<Integer> jobIDs;
    public String getSearchQuery()
    {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery)
    {
        this.searchQuery = searchQuery;
    }
    public JPanel getResult() {
        return Result;
    }

    public void setResult(JPanel result) {
        Result = result;
    }

    public JToolBar getToolBar() {
        return ToolBar;
    }

    public void setToolBar(JToolBar toolBar) {
        ToolBar = toolBar;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(JButton searchButton) {
        this.searchButton = searchButton;
    }

    public JButton getLogInButton2() {
        return logInButton2;
    }

    public void setLogInButton2(JButton logInButton2) {
        this.logInButton2 = logInButton2;
    }

    public JList getList1() {
        return resultsList;
    }

    public void setList1(JList list1) {
        this.resultsList = list1;
    }

    public SearchResults(JFrame parent, String searchQuery, User user) {
        super(parent);
        setTitle("Result");
        setContentPane(Result);
        setMinimumSize(new Dimension(850, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.searchQuery = searchQuery;

        recruitButton.setVisible(user.getUserType(user.getUserID()) >= 3);

        searchButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });

        searchResults = new ArrayList<>();
        jobIDs = new ArrayList<>();
        try (ResultSet rs = new DBaccess().query(searchQuery))
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
                job.append(", Location : ");
                job.append(rs.getString(16));
                job.append(", Posted Date : ");
                job.append(rs.getString(3));
                searchResults.add(String.valueOf(job));
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        resultsList.setListData(searchResults.toArray());

        viewJobButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Job job = new Job(jobIDs.get(resultsList.getSelectedIndex()));
                JobDetails jobDetails = new JobDetails(null, user, job);
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
                ReplyMessage replyMessage = new ReplyMessage(parent, user);
            }
        });

        setVisible(true);
    }
}
