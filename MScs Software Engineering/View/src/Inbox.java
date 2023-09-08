//Inbox screen class for JSS.
//@author Eric Khalif, James Hawes and Simon Ogilvie reviewed by James Hawes
//@version ver 1.0.0


package View.src;
import Control.Message;
import Control.User;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inbox extends JDialog {
    private JPanel inboxPane;
    private JButton searchButton;
    private JList messageList;
    private JToolBar toolBar;
    private JButton recruitButton;
    private JButton messagesButton;
    private JButton accountButton;
    private JLabel inboxLabel;
    private JButton viewMessageButton;
    private java.util.List<String> messages;
    private List<Integer> messageIDs;

    public Inbox(JFrame parent, User user) {
        super(parent);
        setTitle("Inbox");
        setContentPane(inboxPane);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        populateMessageList(user);

        //Hide recruiter button for user if not recruiter
        recruitButton.setVisible(user.getUserType(user.getUserID()) >= 3);

        //Directs to account screen
        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user.getUserType(user.getUserID()) == 4)
                {
                    dispose();
                    RecruiterAccountDetails recruiterAccountDetails = new RecruiterAccountDetails(parent, user);
                }
                else
                {
                    dispose();
                    AccountDetails accountDetails = new AccountDetails(parent, user);
                }
            }
        });

        //Directs to inbox screen
        messagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Inbox messages = new Inbox(parent, user);
            }
        });

        //Directs to recruiter screen
        recruitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JobListingManagement recruiter = new JobListingManagement(parent, user);
            }
        });

        //Directs to search screen
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserHomePage userHomePage = new UserHomePage(parent, user);
            }
        });

        //Directs to reply message screen
        viewMessageButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ReplyMessage view = new ReplyMessage(null, user);
            }
        });

        setVisible(true);
    }

    //Builds out the message object and adds it to the list array
    public void populateMessageList(User user) {
        messages = new ArrayList<>();
        messageIDs = new ArrayList<>();
        Message userMessage = new Message(user.getUserID());
        List<String> message = new ArrayList<>();
        StringBuilder messageDetails = new StringBuilder(("From : "));
        try (ResultSet rs = userMessage.populateMessageList(user.getUserID()))
        {
            while (rs.next()) {
                messageIDs.add(rs.getInt(1));


                messageDetails.append(rs.getString(10));
                messageDetails.append(" ");
                messageDetails.append(rs.getString(11));
                messageDetails.append(", Sent : ");
                messageDetails.append(rs.getString(4));
                messageDetails.append(", Subject : ");
                messageDetails.append(rs.getString(5));
                messages.add(String.valueOf(messageDetails));

            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        messageList.setListData(messages.toArray());
    }
}
