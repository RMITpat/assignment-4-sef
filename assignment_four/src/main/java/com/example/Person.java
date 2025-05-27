package com.example;
import java.util.HashMap;
import java.util.Date;

public class Person {
    private String personId;
    private String firstName;
    private String lastName;
    private String address;
    private String birthdate;
    private HashMap<Date, Integer> demeritPoints;
    private boolean isSuspended;

    
    public boolean addPerson() {


    }

    public boolean updatePersonalDetails (  String personId, String firstName, String lastName, String address, String birthdate) {
        boolean noUpdate = false;
        boolean changedAge = false;
        
        if (!(this.personId.equals(personId))) {
            
        }
        //TODO: This method allows updating a given person's ID, firstName, lastName, address and birthday in a TXT file.
        //Changing personal details will not affect their demerit points or the suspension status.
        // All relevant conditions discussed for the addPerson function also need to be considered and checked in the updatPerson function.
        //Condition 1: If a person is under 18, their address cannot be changed.
        //Condition 2: If a person's birthday is going to be changed, then no other personal detail (i.e, person's ID, firstName, lastName, address) can be changed.
        //Condition 3: If the first character/digit of a person's ID is an even number, then their ID cannot be changed. //Instruction: If the Person's updated information meets the above conditions and any other conditions you may want to consider,
        //the Person's information should be updated in the TXT file with the updated information, and the updatePersonalDetails function should return true.
        //Otherwise, the Person's updated information should not be updated in the TXT file, and the updatePersonalDetails function should return false.


    }

    public String addDemeritPoints {


    }




}