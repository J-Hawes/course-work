package View.src;

import Control.DBaccess;
import Control.DataAccess;
import Control.Search;
import Control.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Reviewed by Reza
 * public methods better to have comment explaining what the method does.
 * line 70 it is better to have construction with argument in search class rather to set each property.
 * Line 135-146 there is no good practice to hard code values better to add them into database !!
 * line 80 better to have boolean value rather than 1, 2, 3, 4. more meaningful arguments.
 * All would be personal suggestions.
 * other than that look good.
 */

public class HomePage extends JDialog{
    private JPanel homePagePane;
    private JToolBar ToolBar;
    private JButton searchButton;
    private JTextField jobSearchTextField;
    private JTextField searchByTitleSkillTextField;
    private JLabel Address;
    private JLabel SalaryRange;
    private JCheckBox fullTimeCheckBox;
    private JCheckBox partTimeCheckBox;
    private JCheckBox casualCheckBox;
    private JCheckBox remoteCheckBox;
    private JComboBox categoryComboBox;
    private JComboBox locationComboBox;
    private JComboBox salaryRangeComboBox;
    private JButton createAccountButton;
    private JButton logInButton;
    private JButton searchButton1;
    private JButton logInButton2;


    public HomePage(JFrame parent){
        super(parent);
        setTitle("HomePage");
        setContentPane(homePagePane);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginPage login = new LoginPage(parent);
            }
        });
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CreateAccount createAccount = new CreateAccount(parent);
            }
        });

        searchButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search newSearch = new Search();
                newSearch.setKeyword(searchByTitleSkillTextField.getText());
                newSearch.setCategory(categoryComboBox.getSelectedItem().toString());
                newSearch.setLocation(locationComboBox.getSelectedItem().toString());
                newSearch.setSalaryRange(salaryRangeComboBox.getSelectedItem().toString());
                ArrayList<String> searchJobTypes = new ArrayList<>();
                newSearch.setJobTypes(searchJobTypes);
                if (fullTimeCheckBox.isSelected()) {
                    newSearch.getJobTypes().add("1");
                }
                if (partTimeCheckBox.isSelected()) {
                    newSearch.getJobTypes().add("2");
                }
                if (casualCheckBox.isSelected()) {
                    newSearch.getJobTypes().add("3");
                }
                if (remoteCheckBox.isSelected()) {
                    newSearch.getJobTypes().add("4");
                }

                newSearch.search();
                ANonLogInSearchResults searchResults = new ANonLogInSearchResults(null, newSearch.getSearchString().toString());
            }
        });
        logInButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginPage loginPage = new LoginPage(parent);
            }
        });

        //populate category drop down list with values from JOB_CATEGORY table in database
        try (ResultSet rs = (new DataAccess().getCategoryList()))
        {
            while (rs.next())
            {
                categoryComboBox.addItem(rs.getString(1));
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
        try (ResultSet rs = new DataAccess().getLocationList())
        {
            while (rs.next())
            {
                locationComboBox.addItem(rs.getString(1));
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        //populate salary range with hard-coded salary ranges
        salaryRangeComboBox.addItem("Any");
        salaryRangeComboBox.addItem("$30,000+");
        salaryRangeComboBox.addItem("$50,000+");
        salaryRangeComboBox.addItem("$70,000+");
        salaryRangeComboBox.addItem("$90,000+");
        salaryRangeComboBox.addItem("$110,000+");
        salaryRangeComboBox.addItem("$130,000+");
        salaryRangeComboBox.addItem("$150,000+");
        salaryRangeComboBox.addItem("$200,000+");
        salaryRangeComboBox.addItem("$300,000+");

        setVisible(true);
    }

    public static void main(String[] args) {
        HomePage dialog = new HomePage(null);
        User temp = new User();
    }
}
