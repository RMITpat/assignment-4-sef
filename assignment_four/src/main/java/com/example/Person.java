package com.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
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
        Scanner scnr = new Scanner(System.in);
        String inputPersonId = "";
        String inputAddress = "";
        String inputDate ="";
        String testFile = scnr.next();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/java/com/example/"+testFile))) {
            inputPersonId = reader.readLine();
             inputAddress = reader.readLine();
             inputDate = reader.readLine();
            
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        if (inputPersonId.length() == 10 ){
            if (((int)inputPersonId.charAt(0) > 2 && (int)inputPersonId.charAt(0) < 9) && ((int)inputPersonId.charAt(1) > 2 && (int)inputPersonId.charAt(1) < 9)) {
                if (Character.isUpperCase(inputPersonId.charAt(inputPersonId.length()-1)) && Character.isUpperCase(inputPersonId.charAt(inputPersonId.length()-2))){
                    String[] parts = inputAddress.split("|");
                    boolean valid = true;
                    if (parts.length == 5) {
                        for (String part : parts){
                            if (!Character.isUpperCase(part.charAt(0))) {
                                valid = false;
                            }
    
                        }
                        if (valid) {
                            if (parts[3].equals("Victoria")) {
                                String[] dateParts = inputDate.split("-");
                                if (dateParts.length == 3) {
                                    int day = Integer.parseInt(dateParts[0]);
                                    int month = Integer.parseInt(dateParts[1]);
                                    if (day >= 1 && day <= 31) {
                                        if (month >= 1 && month <= 12) {
                                            this.personId = inputPersonId;
                                            this.address = inputAddress;
                                            this.birthdate = inputDate;
                                            return true;
                                        }
                                    }
                                }
                            }
                        }

                    }
                    
                }
            }
        }
        return false;



    }

<<<<<<< HEAD
    public boolean updatePersonalDetails (String personId, String firstName, String lastName, String address, String birthdate) {
        boolean madeUpdate = false;
        boolean canChangeId = false;
        boolean CanChangeAdress = false;
        boolean NoChangedAge = true;
        int asciiValue = (int) personId.charAt(0);
        int numericValue = asciiValue - '0';
        int asciiValue2 = (int) personId.charAt(1);
        int numericValue2 = asciiValue2 - '0';
        String capsCheck = personId.substring(personId.length() - 2);
        boolean correctNums = false;

        if ((numericValue <= 9 && numericValue >= 2) && (numericValue2 <= 9 && numericValue2 >= 2))
        correctNums = true;

            if (!(this.address.equals(address))) {
            return true; //dummy code
            }




        if (!(this.personId.equals(personId))) {
            
            if ((numericValue % 2 != 0) && (personId.length() == 10) && capsCheck.equals(capsCheck.toUpperCase()) && correctNums & NoChangedAge) {
                int specCheck = 0;
                for (int i = 4; i < 8; i++) {
                    if (Character.isLetterOrDigit(personId.charAt(i)))
                        specCheck++;
                }
                if (specCheck == 2)
                    canChangeId = true;
            }

            
        } 
        
        return madeUpdate;
=======
    public boolean updatePersonalDetails() {
>>>>>>> origin/main
        

        return false;
    }

    public String addDemeritPoints() {
        // Read txt file + parse into an ArrayList
        return "";
    }




}