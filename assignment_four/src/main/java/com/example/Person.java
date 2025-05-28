package com.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Scanner;

import java.io.PrintWriter;
import java.io.File;

public class Person {
    private String personId;
    private String firstName;
    private String lastName;
    private String address;
    private String birthdate;
    private HashMap<LocalDate, Integer> demeritPoints = new HashMap<>();
    private boolean isSuspended;

    
    public boolean addPerson() {
        Scanner scnr = new Scanner(System.in);
        String inputPersonId = "";
        String inputAddress = "";
        String inputFirstName = "";
        String inputLastName = "";
        String inputDate ="";
        String testFile = scnr.next();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/java/com/example/"+testFile))) {
            inputPersonId = reader.readLine();
             inputAddress = reader.readLine();
             inputDate = reader.readLine();
            inputFirstName = reader.readLine();
            inputLastName = reader.readLine();


            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        if (inputPersonId.length() == 10 ){
            if (((Integer.parseInt("" + inputPersonId.charAt(0))) > 2 && (Integer.parseInt( "" + inputPersonId.charAt(0))) < 9) && ((Integer.parseInt("" + inputPersonId.charAt(1))) > 2 && (Integer.parseInt("" + inputPersonId.charAt(1)) < 9))) {
                if (Character.isUpperCase(inputPersonId.charAt(inputPersonId.length()-1)) && Character.isUpperCase(inputPersonId.charAt(inputPersonId.length()-2))){
                    String[] parts = inputAddress.split("\\|");
                    boolean valid = true;
                    if (parts.length == 5) {
                        for (int i = 1; i< parts.length; ++i){
                            if (!Character.isUpperCase(parts[i].charAt(0))) {
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
                                            this.firstName = inputFirstName;
                                            this.lastName = inputLastName;
                                            System.out.println("Person details:");
                                            System.out.println("First name: " + this.firstName);
                                            System.out.println("Last name: " + this.lastName);
                                            System.out.println("Person id: " + this.personId);
                                            System.out.println("Address: " + this.address);
                                            System.out.println("Birthdate: " + this.birthdate);
                                            return true;
                                        }else {
                                            System.out.println("failed month check");
                                        }
                                    }else {
                                        System.out.println("failed date check");
                                    }
                                }else {
                                    System.out.println("failed date parts count check");
                                }
                            }else {
                                System.out.println("failed Victoria check");
                            }
                        }
                        else {
                            System.out.println("failed uppercase for address check");
                        }

                    }else {
                        System.out.println("failed number of address parts check");
                        System.out.println(parts.length);
                    }
                    
                }else {
                    System.out.println("failed uppercase check");
                }
            }else {
                System.out.println("failed personid check" );
            }
        }
        else {
            System.out.println("length not 10" );
        }
        return false;



    }

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
        
    }

    public String addDemeritPoints() {

        String failedMessage = "Failed";
        String successMessage = "Success";

        boolean isValid = true;

        // Read txt file + parse into strings
        Scanner scnr = new Scanner(System.in);
        String inputDate = "";
        String inputDemeritPoints = "";

        String testFile;

        System.out.println("Enter the name of demerit points file:");


        testFile = scnr.next();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/java/com/example/"+testFile))) {
            inputDate = reader.readLine();
            inputDemeritPoints = reader.readLine();
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            isValid = false;
        }

        if (!isValid) {
            return failedMessage;
        }

        // Parse date
        String[] dates = inputDate.split("-");
        int day = -1;
        int month = -1;
        int year = -1;
        
        // Validate date format
        if (dates.length != 3) {
            System.out.println("Date must be in the format dd-mm-yyyy");
            isValid = false;
        }
        else if (dates[0].length() != 2 ||
                 dates[1].length() != 2 ||
                 dates[2].length() != 4) {
            System.out.println("Date must be in the format dd-mm-yyyy");
            isValid = false;
        }
        else try {
            
            // determine whether or not date values are numeric
            day = Integer.parseInt(dates[0]);
            month = Integer.parseInt(dates[1]);
            year = Integer.parseInt(dates[2]);

        } catch (NumberFormatException e) {
            System.out.println("Date values must be numeric: " + e.getMessage());
            isValid = false;
        }

        if (!isValid) {
            return failedMessage;
        }

        // further validate date values
        if (day < 1 || day > 31) {
            System.out.println("Day must be between 1 and 31 (inclusive)");
            isValid = false;
        }
        if (month < 1 || month > 12) {
            System.out.println("Month must be between 1 and 12 (inclusive)");
            isValid = false;
        }
        if (year < 1950 || year > 2023) {
            System.out.println("Year must be between 1950 and 2023 (inclusive)");
            isValid = false;
        }

        if (!isValid) {
            return failedMessage;
        }

        // validate date values relative to each other (e.g. Feb 31st not valid)
        //first, validate February dates
        if (month == 2) {
            if (day > 29) {
                System.out.println("Inputted date does not exist");
                isValid = false;

            // validate leap years
            } else if (day == 29 && (year % 4 != 0) ||
                                    (year % 100 == 0 && year % 400 != 0)) {
                System.out.println("Inputted date does not exist");
                isValid = false;
            }
        // validate months with 30 days
        } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
            System.out.println("Inputted date does not exist");
            isValid = false;
        }

        if (!isValid) {
            return failedMessage;
        }

        // Parse demerit points
        int demerit = -1;
        try {
            demerit = Integer.parseInt(inputDemeritPoints);
        } catch (NumberFormatException e) {
            System.out.println("Demerit points must be numeric: " + e.getMessage());
            isValid = false;
        }

        // Validate demerit points
        if (demerit < 0 || demerit > 6) {
            System.out.println("Demerit points must be between 0 and 6 (inclusive)");
            isValid = false;
        }

        if (!isValid) {
            return failedMessage;
        }

        // If all validations pass, add demerit points
        LocalDate date = LocalDate.of(year, month, day);

        this.demeritPoints.put(date, demerit);

        // Parse birthDate of person; assumes that birthDate is in the format dd-mm-yyyy
        String[] birthDateParts = this.birthdate.split("-");
        int birthDay = Integer.parseInt(birthDateParts[0]);
        int birthMonth = Integer.parseInt(birthDateParts[1]);
        int birthYear = Integer.parseInt(birthDateParts[2]);

        LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);

        // Calculate age
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(birthDate, currentDate);
        int ageYears = age.getYears();



        // Total demerit points from last 2 years
        int totalDemerit = 0;
        for (LocalDate demeritDate : this.demeritPoints.keySet()) {
            Period timeSinceDemerit = Period.between(demeritDate, currentDate);
            if (timeSinceDemerit.getYears() < 2) {
                demerit += this.demeritPoints.get(demeritDate);
            }
        }

        // Check if person is suspended
        if (ageYears < 21 && totalDemerit > 6 ||
            ageYears >= 21 && totalDemerit > 12) {
            this.isSuspended = true;
            System.out.println("Person is suspended due to exceeding demerit point limit");
        } else {
            this.isSuspended = false;
            System.out.println("Person is not suspended");
        }

        // Write txt file with demerit points
        /*
        String outputFileName = "addDemeritPointsOut.txt";
        File outputFile = new File(*path*);

        PrintWriter writer = new PrintWriter(outputFile);

        writer.println("Demerit Report - " + this.firstName + " " + this.lastName);
        writer.println("Person ID: " + this.personId);
        writer.println();
        writer.println("Total Active Demerit Points: " + totalDemerit);

        if (this.isSuspended) {
            writer.println("Status: Suspended");
        } else {
            writer.println("Status: Not Suspended");
        }

        writer.close();
        */

        return successMessage;
    }




}