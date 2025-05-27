package com.example;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //System.out.print("hi");
        Person person = new Person();
        boolean success = person.addPerson();    
        if (success) {
            System.out.println("Added person successfully");
            
        }

        else {
            System.out.println("Failed to add person");
        }
    }
}
