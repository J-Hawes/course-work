//Message Control class for JSS.
//@author Reza Daryay and James Hawes, reviewed by James Hawes
//@version ver 1.0.0

package Control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Message {
    String displayMessage;
    int jobID;
    boolean readStatus;
    int receiverID;
    String replyText;
    int senderID;
    String subject;
    LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Message(String displayMessage, int jobID, boolean readStatus, int receiverID,
                   String replyText, int senderID, String subject) {
        this.displayMessage = displayMessage;
        this.jobID = jobID;
        this.readStatus = readStatus;
        this.receiverID = receiverID;
        this.replyText = replyText;
        this.senderID = senderID;
        this.subject = subject;
    }
    public Message(int receiverID) {
        this.receiverID = receiverID;
    }

    public void deleteMessage()
    {
    }

    public String getDisplayMessage()
    {
        return displayMessage;
    }

    public boolean isRead()
    {
        return readStatus;
    }

    public int getJobID()
    {
        return jobID;
    }

    //Returns results from database base on query string
    public ResultSet populateMessageList(int userid)
    {
        return new DBaccess()
                    .query("SELECT * FROM MESSAGES JOIN USER_ACCOUNT ON MESSAGES.sender_id = USER_ACCOUNT.user_id  WHERE receiver_id = "
                            + userid + ";");
    }

    public int getReceiverID()
    {
        return receiverID;
    }

    public String getReplyText()
    {
        return replyText;
    }

    public int getSenderID()
    {
        return senderID;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setDisplayMessage (String disMessage)
    {
        displayMessage = disMessage;
    }

    public void setJobID (int jobId)
    {
        jobID = jobId;
    }

    public void setReadStatus (boolean read)
    {
        readStatus = read;
    }

    public void setReceiverID (int receiver)
    {
        receiverID = receiver;
    }

    public void setReplyText (String reply)
    {
        replyText = reply;
    }

    public void setSenderID (int sender)
    {
        senderID = sender;
    }

    public void setSubject (String Newsubject)
    {
        subject = Newsubject;
    }

    public String toString()
    {
        return "";
    }

    //Returns message based on database query
    public void getMessage()
    {
        try (ResultSet rs = new DataAccess().getMessage(receiverID))
        {
            while (rs.next())
            {
                this.setSenderID(rs.getInt(1));
                this.setDate(LocalDate.parse(rs.getString(2)));
                this.setSubject(rs.getString(3));
                this.setDisplayMessage(rs.getString(4));
                this.setReadStatus(rs.getBoolean(5));
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

    //Inserts message into database
    public void messageDbInsert()
    {
        LocalDate postedDate = LocalDate.now();
        String app = "(" + receiverID + ", " + senderID + ", '"
                + postedDate + "', '" + subject + "', '" + displayMessage + "', '" + 0 + "')";
        new DBaccess()
                .query("SET DATEFORMAT dmy;" + "INSERT INTO MESSAGES (receiver_id, sender_id, date, subject, message, read_status) VALUES "
                        + app + ";");
    }
}
