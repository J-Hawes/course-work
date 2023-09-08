//Reply Message screen class for JSS.
//@author Reza Daryay, James Hawes and Eric Khalif, reviewed by James Hawes
//@version ver 1.0.0


package View.src;
import Control.Message;
import Control.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewMessage extends JDialog {
    private JPanel Result;
    private JButton logInButton2;
    private JList list1;
    private JToolBar toolBar;
    private JTextArea textArea1;
    private JTextField subject;
    private JTextField receiver;
    private JButton send;
    private JLabel subjectLbel;

    public NewMessage(JFrame parent, User user, User employee, int jobId) {
        super(parent);
        setTitle("Result");
        setContentPane(Result);
        setMinimumSize(new Dimension(450, 475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        User user1 = new User(user.getUserID());
        User user2 = new User(employee.getUserID());
        receiver.setText(user2.getFirstName(user2.getUserID()) + user2.getLastName(user2.getUserID()));
        receiver.setEditable(false);

        //Sends message and returns to job listing management screen
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                populateMessage(user, employee, jobId);
                JobListingManagement recruit = new JobListingManagement(parent, user);

            }
        });
        setVisible(true);
    }

    //Populates the message object in the controller class, inserts into database
    public void populateMessage(User user, User employee, int jobId)
    {
        Message message = new Message(textArea1.getText(), jobId, false,
                employee.getUserID(), "", user.getUserID(), subject.getText());
        message.messageDbInsert();
        JOptionPane.showMessageDialog(null, "Your message has been sent!");
    }
}
