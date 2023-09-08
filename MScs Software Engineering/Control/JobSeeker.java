//JobSeeker class for JSS
//@author James Hawes, reviewed by Eric Khalif
//@version ver 1.0.0

package Control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobSeeker extends User{
    private final int seekerID;
    private String[] seekingJobType;
    private String[] skills;

    //JobSeeker constructor using only userID.
    //Reviewed by Eric Khalif.
    public JobSeeker(int userid)
    {
        seekerID = userid;
    }

    public int getSeekerID()
    {
        return seekerID;
    }

    //Updates JSS database with new employment types a JobSeeker profile is seeking.
    //Receives string array containing employment types to be added against user profile.
    //Reviewed by Eric Khalif.
    public void addSeekingJobType(String[] newSeeking)
    {
        for (String empType: newSeeking)
        {
            //Database query for employment types provided.
            try (ResultSet rs = (new DBaccess().query("SELECT * FROM EMPLOYMENT_TYPE WHERE emp_type_name = '"
                    + empType + "'" )))
            {
                //Insert SEEKER_EMPLOYMENT_TYPE entries for each employment type selected by the user.
                while (rs.next())
                {
                    int emp = rs.getInt(1);
                    new DBaccess().query("INSERT INTO SEEKER_EMPLOYMENT_TYPE(user_id, emp_type_id) SELECT "
                            + seekerID + ", " +  emp + "WHERE NOT EXISTS (SELECT * FROM SEEKER_EMPLOYMENT_TYPE WHERE user_id = "
                            + seekerID + " AND emp_type_id = " + emp + ")");
                }
            }
            catch (SQLException ex)
            {
                // Handle any errors.
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        // Update JobSeeker object to reflect selected employment types.
        seekingJobType = newSeeking;
    }

    //Gets the employment type a give JobSeeker user has entered against their profile from JJS database.
    //Reviewed by Eric Khalif.
    public String[] getSeekingJobType()
    {
        List<String> seekingType = new ArrayList<>();
        //Database query for selected employment types.
        try (ResultSet rs = (new DBaccess().query("SELECT * FROM SEEKER_EMPLOYMENT_TYPE WHERE user_id = "
                + seekerID)))
        {
            //Database query for employment type names associated with given JobSeeker user.
            while (rs.next())
            {
                ResultSet rs2 = (new DBaccess().query("SELECT emp_type_name FROM EMPLOYMENT_TYPE WHERE emp_type_id = "
                        + rs.getInt(2)));
                while (rs2.next())
                {
                    seekingType.add(rs2.getString(1));
                }
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors.
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        seekingJobType = seekingType.toArray(new String[0]);
        return seekingJobType;
    }

    //Gets the skills JobSeekers have entered against their profile from JJS database.
    //Reviewed by Eric Khalif.
    public String[] getSkills()
    {
        List<String> skillList = new ArrayList<>();
        //Database query for skills entered against JobSeeker profile.
        try (ResultSet rs = (new DBaccess().query("SELECT * FROM SEEKER_SKILLS WHERE user_id = " + seekerID)))
        {
            while (rs.next())
            {
                //Database query for skill names for skills entered against JobSeeker profile.
                ResultSet rs2 = (new DBaccess().query("SELECT skill_name FROM SKILLS WHERE skill_id = "
                        + rs.getInt(2)));
                while (rs2.next())
                {
                    skillList.add(rs2.getString(1));
                }
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        //Updates JobSeeker object's skills field.
        skills = skillList.toArray(new String[0]);
        return skills;
    }

    //Remove association between employment type and user profile to denote that a JobSeeker no longer wishes to
    //seek jobs with a particular employment type.
    //String array containing employment types to be removed from user profile.
    //Reviewed by Eric Khalif.
    public void removeSeekingJobType(String[] remSeeking)
    {
        for (String empType: remSeeking)
        {
            //Retrieves each employment type name from JSS database for each entry in the provided array.
            try (ResultSet rs = (new DBaccess().query("SELECT * FROM EMPLOYMENT_TYPE WHERE emp_type_name = '"
                    + empType + "'" )))
            {
                //Removes each employment type association for the given JobSeeker from JSS database
                //for each entry in the provided array.
                while (rs.next())
                {
                    new DBaccess().query("DELETE FROM SEEKER_EMPLOYMENT_TYPE WHERE user_id = " + seekerID
                    + " AND emp_type_id = " + rs.getInt(1));
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
        //Updates JobSeeker object's seekingJobType field.
        seekingJobType = getSeekingJobType();
    }


    //Removes a single skill from a user profile.
    //Receives sting containing the skill that will be removed from the users profile.
    //Reviewed by Eric Khalif.
    public void removeSingleSkill(int removeSkill)
    {
        new DBaccess().query("DELETE FROM SEEKER_SKILLS WHERE user_id = " +
                + seekerID + " AND skill_id = " + removeSkill + ";");
    }

    //Updates JSS database with new skills entered against a JobSeeker profile.
    //Receives ID of user whose skills will be updated and a string containing the skill
    //to be added against user profile.
    //Reviewed by Eric Khalif.
    public void setSkills(int userid, String newSkill)
    {
        int skillID = 0;
        //Retrieves skill id of selected skill from JSS database.
        try (ResultSet rs = (new DBaccess().query("SELECT skill_id FROM SKILLS WHERE skill_name = '"
                + newSkill + "'")))
        {
            while (rs.next())
            {
                skillID = rs.getInt(1);
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors.
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        //Inserts record into SEEKER_SKILLS table containing the user_id and skill_id.
        new DBaccess().query("INSERT INTO SEEKER_SKILLS(user_id, skill_id) SELECT "
                + seekerID + ", " + skillID +  " WHERE NOT EXISTS (SELECT * FROM SEEKER_SKILLS WHERE user_id = "
                + seekerID + "AND skill_id = " + seekerID + ")");
    }

    public String toString()
    {
        return "Full User Record :"
                +"\n UserID : " + seekerID
                +"\n User Type : " + getUserType(seekerID)
                +"\n First Name : " + getFirstName(seekerID)
                +"\n Last Name : " + getLastName(seekerID)
                +"\n Email address : " + getPassword(seekerID)
                +"\n Password : " + getPassword(seekerID)
                +"\n Location : " + getLocation(seekerID)
                +"\n Seeking Employment type: " + Arrays.toString(getSeekingJobType())
                +"\n Skills : " + Arrays.toString(getSkills());
    }
}
