public class Lamp
{
    private boolean isOn;

    public Lamp()
    {
        isOn = false;
    }

    public Lamp(boolean isOn)
    {
        this.isOn = isOn;
    }

    public String Display()
    {
        String status = "The current Lamp status is :";
        status += ((isOn) ? " on" : " off");
        return status;
    }

    public boolean getLamp()
    {
        return isOn;
    }

    public static void main(String[] args)
    {
        Lamp lamp1 = new Lamp(true);
        Lamp lamp2 = new Lamp();
        System.out.println("Displaying Lamp one : \n" +lamp1.Display());
        System.out.println("Turning Lamp one off!");
        lamp1.turnOff();
        System.out.println("Displaying Lamp two : \n" +lamp2.Display());
        lamp2.turnOn();
        System.out.println("Displaying both lamps : ");
        System.out.println("Lamp one : " + lamp1.Display());
        System.out.println("Lamp two : " + lamp2.Display());
        
    }

    public void setLamp(boolean isOn)
    {
        this.isOn = isOn;
    }

    public void turnOff()
    {
        this.isOn = false;
    }

    public void turnOn()
    {
        this.isOn = true;
    }
}
