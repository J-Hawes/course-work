//Reply Message screen class for JSS.
//@author Reza Daryay, James Hawes and Eric Khalif, reviewed by James Hawes
//@version ver 1.0.0


package View.src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Control.*;

public class ReplyMessage extends JDialog {
    private JPanel Result;
    private JButton logInButton2;
    private JList list1;
    private JToolBar toolBar;
    private JTextArea yourMessage;
    private JTextArea commingMessage;
    private JTextField subject;
    private JLabel subjectLbel;
    private JTextField receiver;
    private JTextField sendDate;
    private JButton reply;
    private User messageReceiver;
    private int jobId;

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public User getMessageReceiver() {
        return messageReceiver;
    }

    public void setMessageReceiver(User messageReceiver) {
        this.messageReceiver = messageReceiver;
    }

    public ReplyMessage(JFrame parent, User user) {
        super(parent);
        setTitle("Result");
        setContentPane(Result);
        setMinimumSize(new Dimension(450, 475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        populateProperties(user);
        subject.setEditable(false);
        receiver.setEditable(false);
        sendDate.setEditable(false);
        commingMessage.setEditable(false);

        reply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                populateMessage(user, messageReceiver, jobId);
                HomePage homePage = new HomePage(parent);

            }
        });


        setVisible(true);
    }

    public void populateMessage(User newSender, User newReceiver, int jobId)
    {
        User sender = new User(newSender.getUserID());
        User receiver = new User(newReceiver.getUserID());
        Message message = new Message(yourMessage.getText(), jobId, false,
                receiver.getUserID(), new String(), sender.getUserID(), subject.getText());
        message.messageDbInsert();
        JOptionPane.showMessageDialog(null, "Your message has been sent!");
    }

    public void populateProperties(User user)
    {
        Message message = new Message(user.getUserID());
        message.getMessage();
        subject.setText(message.getSubject());
        sendDate.setText(message.getDate().toString());
        commingMessage.setText(message.getDisplayMessage());

        User messageReceiver = new User(message.getSenderID());
        receiver.setText(messageReceiver.getFirstName(messageReceiver.getUserID())
                + " " + messageReceiver.getLastName(messageReceiver.getUserID()));
        setMessageReceiver(messageReceiver);
        setJobId(message.getJobID());

    }

}
