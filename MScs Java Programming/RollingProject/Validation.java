package RollingProject;

public class Validation
{
    public Validation()
    {
        
    }

    public boolean isBlank(String value)
    {
        return (value.trim().length() == 0) ? true : false;
    }

    public boolean lengthWithinRange(String value, int min, int max)
    {
        return ((value.trim().length() >= min) && (value.trim().length() <= max));
    }
}
