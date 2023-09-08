//Administrator Home Page class for JSS class for JSS
//@author James Hawes and Eric Khalif, reviewed by Eric Khalif
//@version ver 1.0.0
package View.src;
import Control.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminHome extends JDialog {
    private JPanel adminPane;
    private JToolBar ToolBar;
    private JButton logoutButton;
    private JButton skillManagementButton;
    private JButton jobCategoryButton;
    private JButton jobManagementButton;

    //Constructs administrator home screen.
    //Reviewed by Eric Khalif.
    public AdminHome(JFrame parent, User user) {
        super(parent);
        setTitle("AdminHome");
        setContentPane(adminPane);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Adds listener for logout button, redirecting to JSS homepage.
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomePage homePage = new HomePage(parent);
            }
        });

        //Adds listener for job category management button, redirecting to JSS job category management screen.
        jobCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminJobCategoryManagement adminCategory = new AdminJobCategoryManagement(parent, user);
            }
        });

        //Adds listener for job management button, redirecting to JSS administrator job management screen.
        jobManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminJobManagement adminJobManagement = new AdminJobManagement(parent, user);
            }
        });

        //Adds listener for skill management button, redirecting to JSS administrator skill management screen.
        skillManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminSkillManagement adminSkillManagement = new AdminSkillManagement(parent, user);
            }
        });

        setVisible(true);
    }
}
