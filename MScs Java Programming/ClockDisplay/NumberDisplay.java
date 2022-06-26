package ClockDisplay;

public class NumberDisplay {
    private int limit;
    private int value;

    public NumberDisplay() 
    {
        limit=0;
        value=00;
    }

    public NumberDisplay(int newLimit) 
    {
        limit = newLimit;
    }

    public void Display() 
    {
        System.out.println("the current limit is : " + limit + ", and the current value is : " + value);
    }

    public void Decrement() 
    {
        value--;
    }

    public void Increment() 
    {
        value++;
    }

    public int getLimit() 
    {
        return limit;
    }

    public void setLimit(int newLimit) 
    {
        limit = newLimit;
    }

    public int getValue() 
    {
        return value;
    }

    public void setValue (int newValue) 
    {
        value = newValue;
    }
}
