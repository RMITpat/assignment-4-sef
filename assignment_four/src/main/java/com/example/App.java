package com.example;
/**
 * Hello world!
 *
 *
 * cd assignment_four
 * javac src/main/java/com/example/*.java -d out
 * java -cp out com.example.App
 *
 * 
 */
//test
public class App 
{
    public static void main( String[] args )
    {
        //System.out.print("hi");
        Person person = new Person();
        boolean success = person.addPerson("addPersonValid");    
        if (success) {
            System.out.println("Added person successfully");
            
        }

        else {
            System.out.println("Failed to add person");
        }

        boolean updatePerson = person.updatePersonalDetails("updatePersonalDetailsValid.txt");
        System.out.println(updatePerson);
        String demeritMessage = person.addDemeritPoints("addDemeritPointsTest.txt");
        System.out.println(demeritMessage);
    }
}
