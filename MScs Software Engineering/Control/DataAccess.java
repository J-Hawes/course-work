//DataAccess class for JSS
//@author James Hawes, reviewed by Simon Ogilvie
//@version ver 1.0.0

package Control;

import java.sql.ResultSet;

public class DataAccess {

    //returns applications submitted based on the job - Reviewed by Simon
    public ResultSet getApplication(int jobID)
    {
        return new DBaccess().query("SELECT user_id, application_date, additional_comments, resume, cover_letter, additional_files FROM APPLICATION WHERE job_id = '"
                + jobID + "'");
    }

    //Returns a single category ID based on the category name - Reviewed by Simon
    public ResultSet getCategoryID(String cat)
    {
        return new DBaccess().query("SELECT * FROM JOB_CATEGORY WHERE cat_name = '" + cat + "'");
    }

    //Returns complete list of categories in database - Reviewed by Simon
    public ResultSet getCategoryList()
    {
        return new DBaccess().query("SELECT cat_name FROM JOB_CATEGORY");
    }

    //Returns a single location ID based on the location name - Reviewed by Simon
    public ResultSet getLocationID(String loc)
    {
        return new DBaccess().query("SELECT * FROM LOCATION WHERE loc_name = '" + loc + "'");
    }

    //Returns unique list of location names in database - Reviewed by Simon
    public ResultSet getLocationList()
    {
        return new DBaccess().query("SELECT DISTINCT(loc_name) FROM LOCATION");
    }

    //returns messages for receiver - Reviewed by Simon
    public ResultSet getMessage(int receiverID)
    {
        return new DBaccess().query("SELECT sender_id, date, subject, message, read_status FROM MESSAGES WHERE receiver_id = '"
                + receiverID + "'");
    }

    //Returns a single skill ID based on name input - Reviewed by Simon
    public ResultSet getSkillId(String skill)
    {
        return new DBaccess().query("SELECT skill_id FROM SKILLS WHERE skill_name = '"
                + skill + "'");
    }

    //Returns list of skill names from database - Reviewed by Simon
    public ResultSet getSkillsList()
    {
        return new DBaccess().query("SELECT * FROM SKILLS");
    }

}
