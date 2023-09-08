//Job class for JSS
//@author James Hawes, reviewed by Simon Ogilvie
//@version ver 1.0.0
package Control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Job {
    private int jobID;
    private LocalDate postedDate;
    private String title;
    private String employer;
    private String category;
    private String location;
    private int salaryMin;
    private int salaryMax;
    private LocalDate closingDate;
    private String description;
    private String[] skills;
    private String[] empType;
    private char status;
    private boolean previouslyApplied;
    private int userID;

    //constructor creates new job object as a specific existing job - reviewed by Simon
    public Job(int newJobId)
    {
        jobID = newJobId;
    }

    //constructor creates new job and job object based on filled data - reviewed by Simon
    public Job(int userid, int jobId)
    {
        userID = userid;
        jobID = jobId;
        postedDate = getPostedDate();
        title = getTitle();
        employer = getEmployer();
        category = getCategory();
        location = getLocation();
        salaryMin = getSalaryMin();
        salaryMax = getSalaryMax();
        closingDate = getClosingDate();
        empType = getEmpType();
        skills = getSkills();
        description = getDescription();
        status = getStatus();
    }

    //future functionality to allow more seamless application submission for job - reviewed by Simon
    public void applyNow()
    {

    }

    //Create a new job and insert it into database - reviewed by Simon
    public int createJob(String newJob)
    {
        try (ResultSet rs = new DBaccess()
                .query("SET DATEFORMAT dmy;"
                        + "INSERT INTO JOB_LISTING (poster_id, posted_date, title, employer, category,"
                        + " loc_id, salary_min, salary_max, closing_date, description, status) VALUES " + newJob + ";"
        + "SELECT SCOPE_IDENTITY();"))
        {
            while (rs.next())
            {
                jobID = rs.getInt(1);
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        status = getStatus();
        return jobID;
    }

    //Look up category name from category id in database and return as String value - reviewed by Simon
    public String getCategory()
    {
        try (ResultSet rs = new DBaccess().query("SELECT category FROM JOB_LISTING WHERE job_id = " + jobID))
        {
            while (rs.next())
            {
                try (ResultSet rs2 = new DBaccess().query("SELECT cat_name FROM JOB_CATEGORY WHERE cat_id = "
                        + rs.getInt(1)))
                {
                    while (rs2.next())
                    {
                        category = rs2.getString(1);
                    }
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
        return category;
    }

    //Return localised date from database - reviewed by Simon
    public LocalDate getClosingDate()

    {
        try (ResultSet rs = new DBaccess()
                .query("SELECT closing_date FROM JOB_LISTING WHERE job_id = " + jobID))
        {
            while (rs.next())
            {
                closingDate = LocalDate.parse(rs.getString(1));
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return closingDate;
    }

    //Return description from database - reviewed by Simon
    public String getDescription()
    {
        try (ResultSet rs = new DBaccess().query("SELECT description FROM JOB_LISTING WHERE job_id = " + jobID))
        {
            while (rs.next())
            {
                description = rs.getString(1);
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return description;
    }

    //Return employer field from database - reviewed by Simon
    public String getEmployer()
    {
        try (ResultSet rs = new DBaccess().query("SELECT employer FROM JOB_LISTING WHERE job_id = " + jobID))
        {
            while (rs.next())
            {
                employer = rs.getString(1);
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return employer;
    }

    //Look up employment type name from employment type id and return String name - reviewed by Simon
    public String[] getEmpType()
    {
        List<String> jobType = new ArrayList<>();
        try (ResultSet rs = new DBaccess().query("SELECT emp_type_id FROM JOB_EMPLOYMENT_TYPE WHERE job_id = "
                + jobID))
        {
            while (rs.next())
            {
                try (ResultSet rs2 = new DBaccess()
                        .query("SELECT emp_type_name FROM EMPLOYMENT_TYPE where emp_type_id = "
                        + (rs.getInt(1))))
                {
                    while (rs2.next())
                    {
                        jobType.add(rs2.getString(1));
                    }
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
        empType = jobType.toArray(new String[0]);
        return empType;
    }

    //Returns job ID - reviewed by Simon
    public int getJobID()
    {
        return jobID;
    }

    //Look up location name from location id and return as String name - reviewed by Simon
    public String getLocation()
    {
        try (ResultSet rs = new DBaccess().query("SELECT loc_id FROM JOB_LISTING WHERE job_id = " + jobID))
        {
            while (rs.next())
            {
                try (ResultSet rs2 = new DBaccess().query("SELECT loc_name FROM LOCATION WHERE loc_id = "
                        + rs.getInt(1)))
                {
                    while (rs2.next())
                    {
                        location = rs2.getString(1);
                    }
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
        return location;
    }

    //Returns localised date value from database - reviewed by Simon
    public LocalDate getPostedDate()
    {
        try (ResultSet rs = new DBaccess().query("SELECT posted_date FROM JOB_LISTING WHERE job_id = " + jobID))
        {
            while (rs.next())
            {
                postedDate = LocalDate.parse(rs.getString(1));
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return postedDate;
    }

    //Returns the maximum salary from the database - reviewed by Simon
    public int getSalaryMax()
    {
        try (ResultSet rs = new DBaccess().query("SELECT salary_max FROM JOB_LISTING WHERE job_id = " + jobID))
        {
            while (rs.next())
            {
                salaryMax = rs.getInt(1);
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return salaryMax;
    }

    //Returns the minimum salary from the database - reviewed by Simon
    public int getSalaryMin()
    {
        try (ResultSet rs = new DBaccess().query("SELECT salary_min FROM JOB_LISTING WHERE job_id = " + jobID))
        {
            while (rs.next())
            {
                salaryMin = rs.getInt(1);
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return salaryMin;
    }

    //Returns job status character from database, [C]losed, [D]raft, [P]ublished - reviewed by Simon
    public char getStatus()
    {
        try (ResultSet rs = new DBaccess().query("SELECT status FROM JOB_LISTING WHERE job_id = " + jobID))
        {
            while (rs.next())
            {
                status = rs.getString(1).charAt(0);
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return status;
    }

    //Look up skill name from skill id and return String name value - reviewed by Simon
    public String[] getSkills()
    {
        List<String> skillList = new ArrayList<>();
        try (ResultSet rs = new DBaccess().query("SELECT skill_id FROM JOB_SKILLS WHERE job_id = " + jobID))
        {
            while (rs.next())
            {
                try (ResultSet rs2 = new DBaccess().query("SELECT skill_name FROM SKILLS where skill_id = "
                        + (rs.getInt(1))))
                {
                    while (rs2.next())
                    {
                        skillList.add(rs2.getString(1));
                    }
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
        skills = skillList.toArray(new String[0]);
        return skills;
    }

    //Returns title field in database - reviewed by Simon
    public String getTitle()
    {
        try (ResultSet rs = new DBaccess().query("SELECT title FROM JOB_LISTING WHERE job_id = " + jobID))
        {
            while (rs.next())
            {
                title = rs.getString(1);
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return title;
    }

    //returns the userID of the user who posted the job  - reviewed by Simon
    public int getUserID()
    {
        return userID;
    }

    //user specific setting, not currently setup - reviewed by Simon
    public boolean isPreviouslyApplied()
    {
        return previouslyApplied;
    }

    //Updates the category selection in the database, and sets the object value - reviewed by Simon
    public void setCategory (String newCategory)
    {
        try (ResultSet rs = new DBaccess().query("SELECT cat_id FROM JOB_CATEGORY WHERE cat_name = '"
                + newCategory + "'"))
        {
            while (rs.next())
            {
                new DBaccess().query("UPDATE JOB_LISTING SET cat_id = "
                        + rs.getInt(1) + " WHERE job_id = " + jobID);
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        category = newCategory;
    }

    //Updates the closing date in the database, and sets the object value - reviewed by Simon
    public void setClosingDate(LocalDate closingDate) {
        new DBaccess().query("UPDATE JOB_LISTING SET closing_date = '" + closingDate
                + "' WHERE job_id = " + jobID);
        this.closingDate = closingDate;
    }

    //Updates the job description in the database, and sets the object value - reviewed by Simon
    public void setDescription (String newDescription)
    {
        new DBaccess().query("UPDATE JOB_LISTING SET description = '" + description
                + "' WHERE job_id = " + jobID);
        description = newDescription;
    }

    //Updates the employer in the database, and sets the object value - reviewed by Simon
    public void setEmployer(String newCompany)
    {
        new DBaccess().query("UPDATE JOB_LISTING SET employer = '" + employer
                + "' WHERE job_id = " + jobID);
        employer = newCompany;
    }

    //Removes all the employment data for specific job, then sets the new values - reviewed by Simon
    public void setEmpType(String[] newEmpType)
    {
        empType = newEmpType;
        new DBaccess().query("DELETE FROM JOB_EMPLOYMENT_TYPE WHERE job_id = " + jobID);
        for (String type: newEmpType)
        {
            try (ResultSet rs = new DBaccess()
                    .query("SELECT emp_type_id FROM EMPLOYMENT_TYPE WHERE emp_type_name = '" + type + "'"))
            {
                while (rs.next())
                {
                    new DBaccess().query("INSERT INTO JOB_EMPLOYMENT_TYPE(job_id, emp_type_id) VALUES ("
                            + jobID + ", " + rs.getInt(1) + ")");
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
    }

    //future function to set the jobID for a job - reviewed by Simon
    public void setJobID (int jobId)
    {
        jobID = jobId;
    }

    //Updates the location ID in the database, and sets the object value to the String name
    public void setLocation(String location) {

        try (ResultSet rs = new DBaccess().query("SELECT loc_id FROM LOCATION WHERE loc_name = '"
                + location + "'"))
        {
            while (rs.next())
            {
                new DBaccess().query("UPDATE JOB_LISTING SET loc_id = "
                        + rs.getInt(1) + " WHERE job_id = " + jobID);
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        this.location = location;
    }

    //Updates the posted date in the database, then sets the value of the object - reviewed by Simon
    public void setPostedDate(LocalDate postedDate) {
        new DBaccess().query("UPDATE JOB_LISTING SET posted_date = '" + postedDate
                + "' WHERE job_id = " + jobID);
        this.postedDate = postedDate;
    }

    //User specific setting, currently not in use - reviewed by Simon
    public void setPreviouslyApplied (boolean Applied)
    {
        previouslyApplied = Applied;
    }

    //Updates the maximum salary in the database, then sets the value of the object - reviewed by Simon
    public void setSalaryMax(int salaryMax) {
        new DBaccess().query("UPDATE JOB_LISTING SET salary_max = " + salaryMax + " WHERE job_id = " + jobID);
        this.salaryMax = salaryMax;
    }

    //Updates the minimum salary in the database, then sets the value of the object - reviewed by Simon
    public void setSalaryMin(int salaryMin) {
        new DBaccess().query("UPDATE JOB_LISTING SET salary_min = " + salaryMin + " WHERE job_id = " + jobID);
        this.salaryMin = salaryMin;
    }

    //Inserts skills matched to job only if not already listed in database - reviewed by Simon
    public void setSkills (String[] newSkill)
    {
        for (String skill: newSkill)
        {
            try (ResultSet rs = (new DBaccess().query("SELECT skill_id FROM SKILLS WHERE skill_name = '"
                    + skill + "'")))
            {
                while (rs.next())
                {
                    new DBaccess().query("INSERT INTO JOB_SKILLS(job_id, skill_id) SELECT "
                            + jobID + ", " +  + rs.getInt(1)
                            + " WHERE NOT EXISTS (SELECT * FROM SEEKER_SKILLS WHERE user_id = "
                            + jobID + "AND skill_id = " + jobID + ")");
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
    }

    //Updates the database with the new status, sets the object with the new value - reviewed by Simon
    public void setStatus (char newStatus)
    {
        new DBaccess().query("UPDATE JOB_LISTING SET status = 'C' WHERE job_id = " + jobID);
        status = newStatus;
    }

    //Updates the database with the new title, sets the object with the new value - reviewed by Simon
    public void setTitle (String newTitle)
    {
        new DBaccess().query("UPDATE JOB_LISTING SET title = '" + newTitle + "' WHERE job_id = " + jobID);
        title = newTitle;
    }

    //sets userID to denote the recruiter who submitted the job  - reviewed by Simon
    public void setUserID (int userId)
    {
        userID = userId;
    }

    //display method to return String for all job details - reviewed by Simon
    public String toString()
    {
        return "Full User Record :"
                +"\n UserID : " + userID
                +"\n JobID : " + jobID
                +"\n Posted Date : " + postedDate
                +"\n Closing Date : " + closingDate
                +"\n Title : " + title
                +"\n Employer : " + employer
                +"\n Category : " + category
                +"\n Location : " + location
                +"\n Minimum Salary : $" + salaryMin
                +"\n Maximum Salary : $" + salaryMax
                +"\n Employment Type : " + Arrays.toString(empType)
                +"\n Skills Required : " + Arrays.toString(skills)
                +"\n Status : " + status
                +"\n Description : " + description;
    }
}
