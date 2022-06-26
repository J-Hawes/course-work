package ClockDisplay;

public class ClockDisplay
{
    private NumberDisplay hours = new NumberDisplay(24);
    private NumberDisplay minutes = new NumberDisplay(60);
    private NumberDisplay seconds = new NumberDisplay(60);
    private String displayString = "";

    public void Display() 
    {
        System.out.println(displayString);
    }

    public int getHours() 
    {
        return hours.getValue();
    }

    public int getMinutes() 
    {
        return minutes.getValue();
    }

    public int getSeconds()
    {
        return seconds.getValue();
    }

    public String getTime()
    {
        return displayString;
    }

    public void setHours (int newHour) 
    {
        hours.setValue(newHour);
    }

    public void setMinutes (int newMinute) 
    {
        minutes.setValue(newMinute);
    }

    public void setSeconds (int newSecond) 
    {
        seconds.setValue(newSecond);
    }

    public void setTime(int hour, int minute, int second)
    {
        setHours(hour);
        setMinutes(minute);
        setSeconds(second);
        updateDisplay();
        Display();
    }

    public void setClock(int newSeconds) 
    {
        hours.setValue((newSeconds / 3600) % hours.getLimit());
        minutes.setValue((newSeconds % 3660) / minutes.getLimit());
        seconds.setValue(newSeconds % seconds.getLimit());
        updateDisplay();
        Display();
    }

    public ClockDisplay subtractClock(ClockDisplay testDisplay)
    {
        new ClockDisplay();
        testDisplay.setTime(this.getHours() - testDisplay.getHours(),
        this.getMinutes() - testDisplay.getMinutes(),
        this.getSeconds() - testDisplay.getSeconds());
        return testDisplay;
    }

    public void tickDown() 
    {
        seconds.Decrement();
        updateDisplay();
    }

    public void timeTick() 
    {
        seconds.Increment();
        updateDisplay();
    }
    
    public void updateDisplay() 
    {
        displayString = hours.getValue()+":"+minutes.getValue()+":"+ seconds.getValue();
    }
}
