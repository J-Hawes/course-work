package test.part1;

import java.util.*;

public class Part1 {

    public static void main(String[] args) {
        ArrayList<String> name = new ArrayList<String>();
        //Part 1: a)
        
	name.add("Alice");
	name.add("Bob");
	name.add("Chelsie");
	name.add("Dave");
	name.add("Edward");
        
        //Part 1: b)
        
	name.add("Francene");
        
        //Part 1: c)
        
        name.add(0, "George");
        
        //Part 1: d)
        
        System.out.println("Current index of 'Bob' is: "+ name.indexOf("Bob"));
        
        //Part 1: e)

	System.out.println("Current name of the fifth person is:"+name.get(4));
        
	//Part 1: f)
        
        name.remove("Alice");
        
        //Part 1: g)
        
        name.remove(2);
        
        //Part 1: h)
	for ( String names : name ) 
        System.out.println( names );
    }
    
}
