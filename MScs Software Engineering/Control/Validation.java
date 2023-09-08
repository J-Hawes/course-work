package Control;

/**
 * Reviewed by Reza
 * public methods better to have comment explaining what the method does.
 * there are some methods with no usage !!
 * other than that look good.
 */


public class Validation {
    String textToValidate;

    public boolean validateEmail(String text)
    {
        if ((text.trim().contains("@")) && (text.trim().contains(".")))
        {
            return true;
        }
        return false;
    }

    public boolean validatePassword(String password)
    {
        //validate password against db?
        return true;
    }

    public boolean validateStringLength(String text, int minimumLength, int maximumLength)
    {
        if ((text.trim().length() >= minimumLength) && (text.trim().length() <= maximumLength))
        {
            return true;
        }
        return false;
    }
}
