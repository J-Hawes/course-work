//Recruiter class for JSS
//@author James Hawes, reviewed by Eric Khalif
//@version ver 1.0.0
package Control;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Recruiter extends User{
    private int recruiterId;
    private String companyName;


    //Recruiter constructor using only userID.
    //Reviewed by Eric Khalif.
    public Recruiter(int userid)
    {
        recruiterId = userid;
    }

    //Deletes a job from the database.
    //Reviewed by Eric Khalif.
    public void deleteJob(int jobid)
    {
        new DBaccess().query("DELETE FROM JOB_LISTING WHERE job_id =" + jobid);
    }

    //Returns company name from database.
    //Reviewed by Eric Khalif.
    public String getCompanyName(int userid)
    {
        try (ResultSet rs = new DBaccess().query("SELECT company_name FROM RECRUITER_PROFILE WHERE user_id = "
                + userid))
        {
            while (rs.next()) {
                companyName = rs.getString(1);
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return companyName;
    }

    public int getRecruiterID()
    {
        return recruiterId;
    }

    //Returns an existing job list for recruiter user to populate other ares of the application.
    //Reviewed by Eric Khalif.
    public ResultSet populateJobList()
    {
        ResultSet results = new DBaccess().query("SELECT * FROM JOB_LISTING WHERE poster_id = "
                + recruiterId);
        return results;
    }

    //Inserts or updates company name in database
    //Reviewed by Eric Khalif.
    public void setCompanyName(int userid, String company)
    {
        //check if user exists in recruiter profile, then either insert or update
        if (getCompanyName(userid) == null)
        {
            new DBaccess().query("INSERT INTO dbo.RECRUITER_PROFILE(user_id, company_name) VALUES("
                    + userid + ", '" + company + "');");
        }
        else
        {
            new DBaccess().query("UPDATE dbo.RECRUITER_PROFILE SET company_name = '"
                    + company + "' WHERE user_id = " + userid + ";");
        }
        companyName = company;
    }

    //Updates the entire job in the database from the String query update.
    //Reviewed by Eric Khalif.
    public void updateJob(String update)
    {
        new DBaccess().query(update);
    }

    public String toString()
    {
        return "Full User Record :"
                +"\n UserID : " + recruiterId
                +"\n User Type : " + getUserType(recruiterId)
                +"\n First Name : " + getFirstName(recruiterId)
                +"\n Last Name : " + getLastName(recruiterId)
                +"\n Email address : " + getPassword(recruiterId)
                +"\n Password : " + getPassword(recruiterId)
                +"\n Company : " + companyName
                +"\n Location : " + getLocation(recruiterId);
    }

    //Fetches applications submitted for a given job.
    //Reviewed by Eric Khalif.
    public void viewApplications()
    {
    //Planned functionality
    }
}
