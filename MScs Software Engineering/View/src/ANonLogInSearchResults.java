package View.src;

import Control.DBaccess;

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

public class ANonLogInSearchResults extends JDialog {
    private JPanel Result;
    private JToolBar ToolBar;
    private JButton searchButton;
    private JButton logInButton;
    private JList resultsList;
    private JButton viewJobButton;
    private String searchQuery;
    private List<String> searchResults;
    private List<Integer> jobIDs;

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

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public JList getList1() {
        return resultsList;
    }

    public void setList1(JList list1) {
        this.resultsList = list1;
    }

    public ANonLogInSearchResults(JFrame parent, String searchQuery) {
        super(parent);
        setTitle("Result");
        setContentPane(Result);
        setMinimumSize(new Dimension(850, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.searchQuery = searchQuery;
        searchButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginPage login = new LoginPage(parent);
            }
        });

        viewJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Login or create an account to view and apply for jobs!");;
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
        setVisible(true);
    }

}
