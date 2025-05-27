package com.example;
import java.util.HashMap;
import java.util.Date;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Person {
    private String personId;
    private String firstName;
    private String lastName;
    private String address;
    private String birthdate;
    private HashMap<Date, Integer> demeritPoints;
    private boolean isSuspended;


    public boolean addPerson() {
        
        if this.personId.length() == 10 {
            if ((int)this.personId[0] > 2 && (int)this.personId[0] < 9) && ((int)this.personId[1] > 2 && (int)this.personId[1] < 9) {
                if (Character.isUpperCase(this.personId[this.personId.length()-1]) && Character.isUpperCase(this.personId[this.personId.length()-2])){
                    String[] parts = this.address.split("|")
                    for (string part : parts){
                        
                    }
                }
            }
        }

    }

    public boolean updatePersonalDetails {
        


    }

    public String addDemeritPoints() {
        // Read txt file + parse into an ArrayList

    }




}