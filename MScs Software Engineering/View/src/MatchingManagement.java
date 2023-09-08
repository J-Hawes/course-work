//Matchmaking Management screen class for JSS.
//@author Eric Khalif, reviewed by James Hawes
//@version ver 1.0.0
//Not currently implemented

package View.src;
import javax.swing.*;
import java.awt.*;

public class MatchingManagement extends JDialog {
    private JPanel Result;
    private JToolBar ToolBar;
    private JButton searchButton;
    private JButton logInButton2;
    private JList list1;
    private JToolBar toolBar;
    private JButton recruitButton;
    private JButton messagesButton;
    private JButton account;
    private JComboBox comboBox1;

    public MatchingManagement(JFrame parent) {
        super(parent);
        setTitle("Result");
        setContentPane(Result);
        setMinimumSize(new Dimension(450, 475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
