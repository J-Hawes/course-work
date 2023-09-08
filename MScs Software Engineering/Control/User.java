//User class for JSS
//@author James Hawes and Reza Safari Daryay, reviewed by Eric Khalif
//@version ver 1.0.0
package Control;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class User {
    private int userID;
    private int userType;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String location;
    private String loginError;

    public User()
    {
    }

    public User(int userid)
    {
        userID = userid;
        userType = getUserType(userid);
        firstName = getFirstName(userid);
        lastName = getLastName(userid);
        email = getEmail(userid);
        password = getPassword(userid);
        location = getLocation(userid);

    }

    //If user already exists, send message to app "user with that email address is already registered".
    //Reviewed by Eric Khalif.
    public boolean checkUserExists(String email)
    {
        boolean isUserNotExist = true;
        if (getUserID(email) >= 1)
        {
            JOptionPane.showMessageDialog(null, "User with that email address is already registered.\n"
                    + "Please log into your account or register a new email address.");
            isUserNotExist = false;
        }
        return isUserNotExist;
    }

    //Creates a new user, and insert into the database.
    //Reviewed by Eric Khalif.
    public void createUser(int usertype, String fName, String lName,
                           String newEmail, String newPassword, String newLocation) throws SQLException
    {
        userType = usertype;
        firstName = fName;
        lastName = lName;
        email = newEmail;
        password = newPassword;
        location = newLocation;

        //Convert location name to location id
        int locationID = 1;
        try (ResultSet rs = new DBaccess().query("SELECT TOP (1) loc_id from LOCATION where loc_name = '"
                + location + "'"))
        {
            while (rs.next()) {
                locationID = rs.getInt(1);
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        //Enter new user into database
        {
            new DBaccess().query(
                    "INSERT INTO USER_ACCOUNT(user_type, first_name, last_name, email, password, loc_id) SELECT "
                            + userType + ", '" + firstName + "', '" + lastName + "', '" + email + "', '" + password + "', "
                            + locationID + " WHERE NOT EXISTS (SELECT * FROM USER_ACCOUNT WHERE email = '" + email + "');");

            //Use email address to set userID property of current user object
            try (ResultSet rs = new DBaccess().query("SELECT user_id FROM USER_ACCOUNT WHERE email = '"
                    + email + "'"))
            {
                while (rs.next()) {
                    userID = rs.getInt(1);
                }
            }
        }
    }

    //Delete currently logged-in user corresponding to the current user_id.
    //Reviewed by Eric Khalif.
    public void deleteProfile(int userid)
    {
        new DBaccess().query("DELETE FROM USER_ACCOUNT WHERE user_id = " + userid);
    }

    //Returns the email from the USER_ACCOUNT database, corresponding to the user_id input.
    //Reviewed by Eric Khalif.
    public String getEmail(int userid)
    {
        try (ResultSet rs = new DBaccess().query("SELECT email FROM USER_ACCOUNT WHERE user_id = " + userid))
        {
            while (rs.next()) {
                email = rs.getString(1);
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return email;
    }

    //Returns the first_name from the USER_ACCOUNT database, corresponding to the user_id input.
    //Reviewed by Eric Khalif.
    public String getFirstName(int userid){
        try (ResultSet rs = new DBaccess().query("SELECT first_name FROM USER_ACCOUNT WHERE user_id = "
                + userid))
        {
            while (rs.next())
            {
                firstName = rs.getString(1);
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return firstName;
    }

    //Returns the last_name from the USER_ACCOUNT database, corresponding to the user_id input.
    //Reviewed by Eric Khalif.
    public String getLastName(int userid)
    {
        try (ResultSet rs = new DBaccess().query("SELECT last_name FROM USER_ACCOUNT WHERE user_id = "
                + userid))
        {
            while (rs.next())
            {
                lastName = rs.getString(1);
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return lastName;
    }

    //Returns the location name from the USER_ACCOUNT database, corresponding to the user_id input.
    //Reviewed by Eric Khalif.
    public String getLocation(int userid)
    {
        //Extract the location id from the USER_ACCOUNT table in the database
        try (ResultSet rs = new DBaccess().query("SELECT loc_id FROM USER_ACCOUNT WHERE user_id = "
                + userid))
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

    public String getLoginError()
    {
        return loginError;
    }

    //Log in an existing user, using provided email address and password to populate user object.
    //Reviewed by Eric Khalif.
    public boolean login(String newEmail, String newPassword)
    {
        int userid = getUserID(newEmail);
        if(userid >= 1)
        {
            if (getPassword(userid).equals(newPassword))
            {
                userID = userid;
                userType = getUserType(userid);
                firstName = getFirstName(userid);
                lastName = getLastName(userid);
                email = newEmail;
                password = newPassword;
                location = getLocation(userid);
                return true;

            }
            else
            {
                loginError = "Incorrect Password!";
                return false;
            }
        }
        else
        {
            loginError = "Incorrect user email!";
            return false;
        }
    }

    //Returns the password from the USER_ACCOUNT database, corresponding to the user_id input.
    //Reviewed by Eric Khalif.
    public String getPassword(int userid){
        try (ResultSet rs = new DBaccess().query("SELECT password FROM USER_ACCOUNT WHERE user_id = "
                + userid))
        {
            while (rs.next())
            {
                password = rs.getString(1);
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return password;
    }

    //Constructor for User object, using only userID.
    //Reviewed by Eric Khalif.
    public int getUserID()
    {
        return userID;
    }

    //Returns the user_id from the USER_ACCOUNT database, corresponding to the email input.
    //Reviewed by Eric Khalif.
    public int getUserID(String email){
        try (ResultSet rs = new DBaccess().query("SELECT user_id FROM USER_ACCOUNT WHERE email = '"
                + email + "'"))
        {
            while (rs.next())
            {
                userID = rs.getInt(1);
                if (rs.wasNull())
                {
                    return 0;
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
        return userID;
    }

    //Returns the type_id from the USER_ACCOUNT database, corresponding to the user_id input.
    //Reviewed by Eric Khalif.
    public int getUserType(int userid)
    {
        int typeid = 0;
        try (ResultSet rs = (new DBaccess().query("SELECT user_type FROM USER_ACCOUNT WHERE user_id = "
                + userid)))
        {
            while (rs.next()) {
                typeid = rs.getInt(1);
            }
            userType = typeid;
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return typeid;
    }

    //Update users email in the JSS database.
    //Reviewed by Eric Khalif.
    public void setEmail(int userid, String newEmail)
    {
        new DBaccess().query("UPDATE dbo.USER_ACCOUNT SET email = '"
                + newEmail + "' WHERE user_id = " + userid + ";");
        email = newEmail;
    }

    //Update users first name in the JSS database.
    //Reviewed by Eric Khalif.
    public void setFirstName(int userid, String newFirstname)
    {
        new DBaccess().query("UPDATE dbo.USER_ACCOUNT SET first_name = '"
                + newFirstname + "' WHERE user_id = " + userid + ";");
        firstName = newFirstname;
    }

    //Update users last name in the JSS database.
    //Reviewed by Eric Khalif.
    public void setLastName(int userid, String newLastname)
    {
        new DBaccess().query("UPDATE dbo.USER_ACCOUNT SET last_name = '"
                + newLastname + "' WHERE user_id = " + userid + ";");
        lastName = newLastname;
    }

    //Update users location in the JSS database.
    //Reviewed by Eric Khalif.
    public void setLocation(int userid, String newLocation)
    {
        int locID = 0;
        try (ResultSet rs = (new DBaccess().query("SELECT loc_id FROM LOCATION WHERE loc_name = '"
                + newLocation + "'")))
        {
            while (rs.next())
            {
                locID = rs.getInt(1);
            }
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        new DBaccess().query("UPDATE dbo.USER_ACCOUNT SET loc_id = "
                + locID + " WHERE user_id = " + userid + ";");
        location = newLocation;
    }

    //Update users password in the JSS database.
    //Reviewed by Eric Khalif.
    public void setPassword(int userid, String newPassword)
    {
        new DBaccess().query("UPDATE dbo.USER_ACCOUNT SET password = '"
                + newPassword + "' WHERE user_id = " + userid + ";");
        password = newPassword;
    }

    //Update users type in the JSS database.
    //Reviewed by Eric Khalif.
    public void setUserType(int userid, int newUserType)
    {
        new DBaccess().query("UPDATE dbo.USER_ACCOUNT SET user_type = '"
                + newUserType + "' WHERE user_id = " + userid + ";");
    }

    public String toString()
    {
        return "Full User Record :"
                +"\n UserID : " + userID
                +"\n User Type : " + userType
                +"\n First Name : " + firstName
                +"\n Last Name : " + lastName
                +"\n Email address : " + email
                +"\n Password : " + password
                +"\n Location : " + location;
    }
}
