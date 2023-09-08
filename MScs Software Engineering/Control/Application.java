//Application class for JSS
//@author Reza Safari Daryay & James Hawes, reviewed by Simon Ogilvie
//@version ver 1.0.0

package Control;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Application {
    int userID;
    int jobID;
    String additionalComments;
    LocalDate date;

    // constructor to create new application object for class - reviewed by Simon
    public Application(int userID, int jobID, String additionalComments, LocalDate date) {
        this.userID = userID;
        this.jobID = jobID;
        this.additionalComments = additionalComments;
        this.date = date;
    }
    //constructor to create new application object - reviewed by Simon
    public Application(int jobID) {
        this.jobID = jobID;
    }

    // Insert new application into DB - reviewed by Simon
    public void applicationDBInsert(File resumeFile, File coverFile, File additionalFile)
    {
        LocalDate postedDate = LocalDate.now();
        String app = "(" + userID + ", " + jobID + ", '"
                + postedDate + "', '" + additionalComments + "')";
        new DBaccess()
                .query("SET DATEFORMAT dmy;" + "INSERT INTO APPLICATION (user_id, job_id, application_date, additional_comments) VALUES "
                        + app + ";");
    }

    //returns application's additional comments
    public String getAdditionalComments()
    {
        return additionalComments;
    }

    // get application from db - reviewed by Simon
    public void getApplication()
    {
        try (ResultSet rs = new DataAccess().getApplication(jobID))
        {
            while (rs.next())
            {
                this.setDate(LocalDate.parse(rs.getString(2)));
                this.setAdditionalComments(rs.getString(3));
                this.setUserID(rs.getInt(1));
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

    //returns application's date - reviewed by Simon
    public LocalDate getDate()
    {
        return date;
    }

    //returns the application's jobID - reviewed by Simon
    public int getJobID()
    {
        return jobID;
    }

    //returns the application's userID - reviewed by Simon
    public int getUserID()
    {
        return userID;
    }

    //sets additional comments on the application object - reviewed by Simon
    public void setAdditionalComments (String newComments)
    {
        additionalComments = newComments;
    }

    //sets date on application object - reviewed by Simon
    public void setDate (LocalDate newDate)
    {
        date = newDate;
    }

    //sets jobID on application object - reviewed by Simon
    public void setJobID (int jobId)
    {
        jobID = jobId;
    }

    //sets userID assigned to application - reviewed by Simon
    public void setUserID (int userId)
    {
        userID = userId;
    }

    //display method for application - reviewed by Simon
    public String toString()
    {
        return "";
    }
}
