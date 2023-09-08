//Admin class for JSS
//@author James Hawes, reviewed by Simon Ogilvie
//@version ver 1.0.0

package Control;

import java.sql.ResultSet;

public class Admin extends User{

    //empty constructor - reviewed by Simon
    public Admin()
    {

    }

    //creates a new category on the database - reviewed by Simon
    public void createCategory(String newCategory)
    {
        new DBaccess().query("INSERT INTO JOB_CATEGORY VALUES ('" + newCategory + "')");
    }

    //creates a new skill on the database - reviewed by Simon
    public void createSkills(String newSkill)
    {
        new DBaccess().query("INSERT INTO SKILLS VALUES ('" + newSkill + "')");
    }

    //removes an existing category on the database - reviewed by Simon
    public void deleteCategory(String catName)
    {
        new DBaccess().query("DELETE FROM JOB_CATEGORY WHERE cat_name = '" + catName + "'");
    }

    //deletes a skill on the database - reviewed by Simon
    public void deleteSkills(String skillName)
    {
        new DBaccess().query("DELETE FROM SKILLS WHERE skill_name = '" + skillName + "'");
    }

    //edits existing category on database - reviewed by Simon
    public void editCategory(int catID, String newCategory)
    {
        new DBaccess().query("UPDATE JOB_CATEGORY SET cat_name = '" + newCategory + "' WHERE cat_id = '"
                + catID + "'");
    }

    //edits existing skill on database - reviewed by Simon
    public void editSkills(int skillID, String newSkill)
    {
        new DBaccess().query("UPDATE SKILLS SET skill_name = '" + newSkill + "' WHERE skill_id = '"
                + skillID + "'");
    }

    //deletes job listing - reviewed by Simon
    public void removeJob(int jobId)
    {
        new DBaccess().query("DELETE FROM JOB_SKILLS WHERE job_id = " + jobId
                + "; DELETE FROM JOB_EMPLOYMENT_TYPE WHERE job_id = " + jobId
                + "; DELETE FROM JOB_LISTING WHERE job_id = " + jobId);
    }

    //populates list of jobs in admin view - reviewed by Simon
    public ResultSet viewJobsList()
    {
        return new DBaccess().query("SELECT * FROM JOB_LISTING ORDER BY posted_date;");
    }
}
