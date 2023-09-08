//Administrator Job Category Management class for JSS
//@author James Hawes and Eric Khalif, reviewed by Eric Khalif
//@version ver 1.0.0
package View.src;
import Control.Admin;
import Control.DataAccess;
import Control.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminJobCategoryManagement extends JDialog {
    private JPanel jobCategoryPanel;
    private JToolBar ToolBar;
    private JButton adminButton;
    private JButton addButton;
    private JComboBox categoryComboBox;
    private JButton removeButton;
    private JTextField addCategoryTextField;

    //Constructs AdminJobCategoryManagement screen.
    //Reviewed by Eric Khalif.
    public AdminJobCategoryManagement(JFrame parent, User user) {
        super(parent);
        setTitle("jobCategoryPanel");
        setContentPane(jobCategoryPanel);
        setMinimumSize(new Dimension(450, 475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Admin admin = new Admin();

        //populate category drop down list with values from JOB_CATEGORY table in database
        populateCategoryList();

        //Adds action listener for Admin button, redirecting to administrator home screen.
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminHome adminHome = new AdminHome(parent, user);
            }
        });

        //Adds action listener for Add button, adding the newly listed skill to the JSS database.
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String add = addCategoryTextField.getText();
                admin.createCategory(add);
                populateCategoryList();
            }
        });

        //Adds action listener for Reomve button, removing the selected skill from the JSS database.
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rem = categoryComboBox.getSelectedItem().toString();
                admin.deleteCategory(rem);
                populateCategoryList();
            }
        });

        setVisible(true);
    }

    //Adds category names to drop down.
    //Reviewed by Eric Khalif.
    public void populateCategoryList()
    {
        categoryComboBox.removeAllItems();
        try (ResultSet cat = (new DataAccess().getCategoryList()))
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
    }
}
