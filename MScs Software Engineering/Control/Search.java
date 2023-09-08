//Search class for JSS
//@author James Hawes and Simon Ogilvie, reviewed by Eric Khalif
//@version ver 1.0.0
package Control;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Search {
    String category;
    String location;
    String keyword;
    ArrayList<String> jobTypes;
    String salaryRange;
    String[] skills;
    public User currentUser;
    private StringBuilder searchString;

    public StringBuilder getSearchString()
    {
        return searchString;
    }

    public void setSearchString(StringBuilder searchString)
    {
        this.searchString = searchString;
    }

    //Calculates degree to which a JobSeeker's profile matches a Job they applied for.
    //Reviewed by Eric Khalif.
    public void calculateMatch()
    {
    //Planned functionality
    }

    //Displays calculated match.
    //Reviewed by Eric Khalif.
    public void displayMatch()
    {
     //Planned functionality
    }

    public String getCategory()
    {
        return category;
    }

    public ArrayList<String> getJobTypes()
    {
        return jobTypes;
    }

    public String getKeyword()
    {
        return keyword;
    }

    public String getLocation()
    {
        return location;
    }

    public String getSalaryRange()
    {
        return salaryRange;
    }

    public String[] getSkills()
    {
        return skills;
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

    //Retrieves saved search state.
    //Reviewed by Eric Khalif.
    public void recallState()
    {
        //Planned functionality
    }

    //Saves search state so that it can be reloaded.
    //Reviewed by Eric Khalif.
    public void saveState()
    {
        //Planned functionality
    }

    //Builds the search string from values in the homepages to be passed to the search results pages
    //Reviewed by Eric Khalif.
    public void search()
    {
        StringBuilder newSearchString = new StringBuilder();
        setSearchString(newSearchString);
        buildBaseQuery();
        buildCategoryFilter(newSearchString);
        buildLocationFilter(newSearchString);
        buildSalaryRangeFilter(newSearchString);
        buildJobTypeFilter(newSearchString);
        buildKeywordFilter(newSearchString);

        //System.out.println(newSearchString);
    }

    //Initialises the base of the search query and allows only published jobs to return.
    //Reviewed by Eric Khalif.
    private void buildBaseQuery()
    {
        searchString.append("SELECT * FROM JOB_LISTING " +
                "JOIN JOB_CATEGORY ON JOB_LISTING.category = JOB_CATEGORY.cat_id " +
                "JOIN LOCATION ON JOB_LISTING.loc_id = LOCATION.loc_id " +
                "WHERE JOB_LISTING.status = 'P' ");
    }

    //Adds a category to search if selected.
    //Reviewed by Eric Khalif.
    private void buildCategoryFilter(StringBuilder searchString)
    {
        if (isValidCategory(getCategory())) {
            searchString.append(" AND JOB_CATEGORY.cat_name = '").append(getCategory()).append("'");
        }
    }

    //Adds a location to search if selected.
    //Reviewed by Eric Khalif.
    private void buildLocationFilter(StringBuilder searchString)
    {
        if (!Objects.equals(getLocation(), "Australia")) {
            searchString.append(" AND LOCATION.loc_name = '").append(getLocation()).append("'");
        }
    }

    //Adds salary range to search if selected
    //Reviewed by Eric Khalif.
    private void buildSalaryRangeFilter(StringBuilder searchString)
    {
        if (!Objects.equals(getSalaryRange(), "Any")) {
            int salaryRangeAsInt = Integer.parseInt(getSalaryRange().replaceAll("\\D", ""));
            searchString.append(" AND JOB_LISTING.salary_min >= ").append(salaryRangeAsInt);
        }
    }

    //Adds selected job types to search if selected
    //Reviewed by Eric Khalif.
    private void buildJobTypeFilter(StringBuilder searchString)
    {
        if (getJobTypes() != null && !getJobTypes().isEmpty()) {
            searchString.append(" AND JOB_LISTING.job_id IN (SELECT job_id FROM JOB_EMPLOYMENT_TYPE WHERE emp_type_id IN (");
            for (int i = 0; i < getJobTypes().size(); i++) {
                searchString.append(getJobTypes().get(i));
                if (i < getJobTypes().size() - 1) {
                    searchString.append(",");
                }
            }
            searchString.append("))");
        }
    }

    //Keyword filter queries skills, title, employer and description fields if added.
    //Reviewed by Eric Khalif.
    private void buildKeywordFilter(StringBuilder searchString)
    {
        //searches skills first
        if (!Objects.equals(getKeyword(), "")) {
            String[] keywords = getKeyword().split("\\s+");
            searchString.append(" AND (JOB_LISTING.job_id IN (SELECT job_listing.job_id FROM JOB_SKILLS JOIN " +
                    "JOB_LISTING ON JOB_SKILLS.job_id = JOB_LISTING.job_id JOIN SKILLS " +
                    "ON JOB_SKILLS.skill_id = SKILLS.skill_id WHERE ");
            boolean isFirstKeyword = true;
            for (String keyword : keywords) {
                if (!isFirstKeyword) {
                    searchString.append(" OR ");
                }
                isFirstKeyword = false;
                searchString.append(" SKILLS.skill_name LIKE '%").append(keyword).append("%' ");
            }

            searchString.append(")");
        }
        //then searches title, employer and description, applies basic relevance sort
        if (!Objects.equals(getKeyword(), "")) {
            String[] keywords = getKeyword().split("\\s+");
            searchString.append(" OR (");

            boolean isFirstKeyword = true;
            for (String keyword : keywords) {
                if (!isFirstKeyword) {
                    searchString.append(" OR ");
                }

                searchString.append(" (");
                searchString.append(" (CASE WHEN JOB_LISTING.title LIKE '%").append(keyword).append("%' THEN 3 ELSE 0 END)");
                searchString.append(" + (CASE WHEN JOB_LISTING.employer LIKE '%").append(keyword).append("%' THEN 1 ELSE 0 END)");
                searchString.append(" + (CASE WHEN JOB_LISTING.description LIKE '%").append(keyword).append("%' THEN 1 ELSE 0 END)");
                searchString.append(" > 0)");

                isFirstKeyword = false;
            }

            searchString.append("))");
            searchString.append(" ORDER BY");
            for (String keyword : keywords) {
                searchString.append(" (CASE WHEN JOB_LISTING.title LIKE '%").append(keyword).append("%' THEN 3 ELSE 0 END) DESC,");
                searchString.append(" (CASE WHEN JOB_LISTING.employer LIKE '%").append(keyword).append("%' THEN 1 ELSE 0 END) DESC,");
                searchString.append(" (CASE WHEN JOB_LISTING.description LIKE '%").append(keyword).append("%' THEN 1 ELSE 0 END) DESC,");
            }
            //remove final trailing comma
            searchString.setLength(searchString.length() - 1);
        }
    }

    //under construction - required? maybe for match making?
    /*private void buildSkillsFilter(StringBuilder searchString)
    {

    }*/

    //Category validation to ensure no errors.
    //Reviewed by Eric Khalif.
    private boolean isValidCategory(String category)
    {
        return category != null && !category.equals("Select a Category");
    }

    public void setCategory (String newCategory)
    {
        category = newCategory;
    }

    public void setJobTypes (ArrayList<String> jobType)
    {
        jobTypes = jobType;
    }

    public void setKeyword (String newKeyword)
    {
        keyword = newKeyword;
    }

    public void setLocation (String newLocation)
    {
        location = newLocation;
    }

    public void setSalaryRange (String newSalaryRange)
    {
        salaryRange = newSalaryRange;
    }

    public void setSkills (String[] newSkill)
    {
        skills = newSkill;
    }

    public String toString()
    {
        return "";
    }
}
