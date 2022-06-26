public class Clock
{
    private int Hour, Minutes, Seconds;

    public Clock()
    {
        Hour = 00;
        Minutes = 00;
        Seconds = 00;
    }

    public Clock(int newHour)
    {
        Hour = newHour;
        Minutes = 00;
        Seconds = 00;
    }

    public Clock(int newHour, int newMinutes, int newSeconds)
    {
        Hour = newHour;
        Minutes = newMinutes;
        Seconds = newSeconds;
    }

    public int getHour()
    {
        return Hour;
    }

    public int getMinutes()
    {
        return Minutes;
    }

    public int getSeconds()
    {
        return Seconds;
    }

    public void setHours(int newHour)
    {
        Hour = newHour;
    }

    public void setMinutes(int newMinutes)
    {
        setHours(newMinutes/60);
        Minutes = newMinutes % 60;
    }

    public void setSeconds(int newSeconds)
    {
        Seconds = newSeconds;
    }

    public void showTime()
    {
        System.out.println(Hour +":"+ Minutes +":"+ Seconds);
    }

    public static void main(String[] args)
    {
        Clock objClock = new Clock();
        objClock.showTime();
        objClock.setMinutes(75);
        objClock.showTime();
    }   
}
