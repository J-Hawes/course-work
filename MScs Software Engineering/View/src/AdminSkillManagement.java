//Admin Job Management screen class for JSS.
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

public class AdminSkillManagement extends JDialog {
    private JPanel skillManagementPanel;
    private JToolBar ToolBar;
    private JButton adminButton;
    private JButton addButton;
    private JTextField addSkillTextField;
    private JComboBox skillsComboBox;
    private JButton removeButton;

    //Builds AdminSkillManagement screen.
    //Reviewed by Eric Khalif.
    public AdminSkillManagement(JFrame parent, User user) {
        super(parent);
        setTitle("skillManagementPanel");
        setContentPane(skillManagementPanel);
        setMinimumSize(new Dimension(450, 475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Admin admin = new Admin();

        //populate Skills drop down list with values from SKILLS table in database
        populateSkillsList();

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminHome adminHome = new AdminHome(parent, user);
            }
        });

        //Adds a new skill based on the String entered into the text field
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String add = addSkillTextField.getText();
                admin.createSkills(add);
                populateSkillsList();
            }
        });

        //Removes the selected skill in the combo box
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rem = skillsComboBox.getSelectedItem().toString();
                admin.deleteSkills(rem);
                populateSkillsList();
            }
        });

        setVisible(true);
    }

    //Populates the skills list.
    //Reviewed by Eric Khalif.
    public void populateSkillsList()
    {
        skillsComboBox.removeAllItems();
        try (ResultSet skill = new DataAccess().getSkillsList())
        {
            while (skill.next())
            {
                skillsComboBox.addItem(skill.getString(2));
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
