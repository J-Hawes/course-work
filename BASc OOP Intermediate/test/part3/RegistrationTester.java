package test.part3;

import test.part3.Vehicle.Location;

public class RegistrationTester {
    public static void main(String[] args)
    {
        Vehicle[] vehicles = new Vehicle[4];
        vehicles[0] = new Car("John Smith", "ABC222", Location.INNER_METRO);
        vehicles[1] = new Motorcycle("Sandy Phillips", "DEF333", Location.OUTER_METRO, 80);
        vehicles[2] = new Car("Bob Jones", "GHI444", Location.REGIONAL);
        vehicles[3] = new Motorcycle("Jane Doe", "JKL555", Location.INNER_METRO, 250);
        for(Vehicle car : vehicles)
        {
           if (car instanceof Car)
           {
            System.out.println(car.toString() + "$" + car.calcRegfee() + "\n");
           }
        }
        for(Vehicle bike : vehicles)
        {
           if (bike instanceof Motorcycle)
           {
            System.out.println(bike.toString() + "$" + bike.calcRegfee() + "\n");
           }
        }
    }
}
