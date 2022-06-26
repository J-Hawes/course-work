package ClockDisplay;

public class ClockDemo
{
    public static void main(String[] args) 
    {
        ClockDisplay firstClock = new ClockDisplay();
        firstClock.setClock(10000);
        firstClock.timeTick();
        firstClock.Display();
        firstClock.timeTick();
        firstClock.Display();
        ClockDisplay secondClock = new ClockDisplay();
        secondClock.setTime(5,45,45);
        for (int i=0; i < 9; i++)
            {
                secondClock.timeTick();
                secondClock.Display();
            }
        System.out.println("\n" + secondClock.getTime() + 
        " - " + firstClock.getTime());
        ClockDisplay thirdClock = (secondClock.subtractClock(firstClock));
        thirdClock.Display();
    }
}
