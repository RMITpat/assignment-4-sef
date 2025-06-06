package com.example;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;

public class Person {
    private String personId;
    private String firstName;
    private String lastName;
    private String address;
    private String birthdate;
    private HashMap<LocalDate, Integer> demeritPoints = new HashMap<>();
    private boolean isSuspended;

    public File createFile(String fileName) {
        File file = new File("out/actual/"+fileName);

        try {
            if (file.createNewFile()) {
              System.out.println("File created: " + file.getName());

            } else {
            
              System.out.println("File " + fileName + " already exists.");
              file.delete();
              file.createNewFile();
              System.out.println("Replaced file " + fileName + ".");
            }
          } catch (IOException e) {
            System.out.println("An error occurred.");
          }
          return file;
          
    }

    public void fileWriter( File file, ArrayList<String> outputLines) {
         try {
            FileWriter myWriter = new FileWriter(file);
            for (int i = 0; i < outputLines.size(); ++i) {
                myWriter.write(outputLines.get(i) + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
            System.out.println("An error occurred.");
            }

        
    }
    public boolean addPerson(String testFile) {
        String inputPersonId = "";
        String inputAddress = "";
        String inputFirstName = "";
        String inputLastName = "";
        String inputDate ="";

        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/java/com/example/"+testFile+".txt"))) {
            inputPersonId = reader.readLine();
            inputAddress = reader.readLine();
            inputDate = reader.readLine();
            inputFirstName = reader.readLine();
            inputLastName = reader.readLine();


            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        //the length of the id must be 10
        if (inputPersonId.length() == 10 ){
            //checks if there are 2 numbers at the starts
            if (Character.isDigit(inputPersonId.charAt(0))  && Character.isDigit(inputPersonId.charAt(1)) ) {
                //checks if the 2 numbers are above 2 and below 9
            if (((Integer.parseInt("" + inputPersonId.charAt(0))) > 2 && (Integer.parseInt( "" + inputPersonId.charAt(0))) < 9) && ((Integer.parseInt("" + inputPersonId.charAt(1))) > 2 && (Integer.parseInt("" + inputPersonId.charAt(1)) < 9))) {
                //checks if the last 2 characters are letters and they are uppercase
                if (Character.isUpperCase(inputPersonId.charAt(inputPersonId.length()-1)) && Character.isUpperCase(inputPersonId.charAt(inputPersonId.length()-2))){
                    // split up the address into parts
                    String[] parts = inputAddress.split("\\|");
                    boolean valid = true;
                    //check if there were 5 parts, house number, street,city, state, country
                    if (parts.length == 5) {
                        //check if the street, city, state and country start with a capital letter
                        for (int i = 1; i< parts.length; ++i){
                            if (!Character.isUpperCase(parts[i].charAt(0))) {
                                valid = false;
                            }
                        }
                        if (valid) {
                            //checks if the state is Victoria
                            if (parts[3].equals("Victoria")) {
                                //split up the birthdate into day month year
                                String[] dateParts = inputDate.split("-");
                                //check if there was a day month and year
                                if (dateParts.length == 3) {
                                    int day = Integer.parseInt(dateParts[0]);
                                    int month = Integer.parseInt(dateParts[1]);
                                    //check if the day is between 1 and 31 inclusive
                                    if (day >= 1 && day <= 31) {
                                        //check if the month is between 1 and 12 inclusive
                                        if (month >= 1 && month <= 12) {
                                            //all tests passed, create an output file
                                            this.personId = inputPersonId;
                                            this.address = inputAddress;
                                            this.birthdate = inputDate;
                                            this.firstName = inputFirstName;
                                            this.lastName = inputLastName;
                                            
                                            ArrayList<String> output = new ArrayList<>();
                                            output.add("Person details:");
                                            output.add("First name: " + this.firstName);
                                            output.add("Last name: " + this.lastName);
                                            output.add("Person id: " + this.personId);
                                            output.add("Address: " + this.address);
                                            output.add("Birthdate: " + this.birthdate);

                                            File createdFile = this.createFile(testFile + "_actual.txt");
                                            this.fileWriter(createdFile, output);
                                            return true;
                                        }else {
                                            System.out.println("Failed month check");
                                            
                                        }
                                    }else {
                                        System.out.println("Failed date check");
                                    }
                                }else {
                                    System.out.println("Failed date parts count check");
                                }
                            }else {
                                System.out.println("Failed Victoria check");
                            }
                        }
                        else {
                            System.out.println("Failed uppercase for address check");
                        }

                    }else {
                        System.out.println("Failed number of address parts check");
                        System.out.println(parts.length);
                    }
                    
                }else {
                    System.out.println("Failed uppercase check");
                }
            }else {
                System.out.println("Failed personid check" );
            }
        } else {
            System.out.println("Failed id number check");
        }
        }
        else {
            System.out.println("length not 10" );
        }
        this.createFile(testFile + "_actual.txt");

        return false;



    }

    public boolean updatePersonalDetails (String testFile) {
        boolean canChangeId = true;
        boolean canChangeAddress = true;
        boolean addressValid = true;
        boolean canChangeAge = true;
        boolean noChangedAge = true;
        
        String inputPersonId = "";
        String inputAddress = "";
        String inputFirstName = "";
        String inputLastName = "";
        String inputDate ="";
        
                //reads the input file from the string name
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/java/com/example/"+ testFile + ".txt"))) {

            inputPersonId = reader.readLine();
            inputAddress = reader.readLine();
            inputDate = reader.readLine();
            inputFirstName = reader.readLine();
            inputLastName = reader.readLine();


            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }


        //converts the character value of both numbers at the start of the ID into their actual numerical values
        int asciiValue = (int) inputPersonId.charAt(0);
        int numericValue = asciiValue - '0';
        int asciiValue2 = (int) inputPersonId.charAt(1);
        int numericValue2 = asciiValue2 - '0';
        String capsCheck = inputPersonId.substring(inputPersonId.length() - 2);
        boolean correctNums = false;


        
        //checks the values are within the correct ranges implied by the rubric
        if ((numericValue <= 9 && numericValue >= 2) && (numericValue2 <= 9 && numericValue2 >= 2))
            correctNums = true;
        //checks whether the address values are the same, if not it checks the validity of the new value
        if(!(this.address.equals(inputAddress))) {
            String[] parts = inputAddress.split("\\|");
            addressValid = true;
            if (parts.length == 5) {
                for (int i = 1; i< parts.length; ++i){
                    if (Character.isUpperCase(parts[i].charAt(0))) {
                        
                        if ( !(parts[3].equals("Victoria"))) {
                            addressValid = false;
                        }
                    }
                }
            }
        }
                    // first verifies the input date used as valid formatting
        String[] dateParts = inputDate.split("-");
        if (dateParts.length == 3) {
            int year = Integer.parseInt(dateParts[2]);// splitting the string xx-xx-xxxx, into day, month, year
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);

            if (day >= 1 && day <= 31) {
                if (month >= 1 && month <= 12) {
                    LocalDate currentDate = LocalDate.now();
                    LocalDate birthDate = LocalDate.of(year, month, day);
                    Period age = Period.between(birthDate, currentDate);
                    int ageYears = age.getYears();
                    canChangeAge = true;
                    System.out.println(inputDate);
                    if (ageYears < 18) {
                        canChangeAddress = false; // sets the change of adress to false when the age of the user is
                    }                               // less than 18
                    if (!inputDate.equals(this.birthdate))
                    noChangedAge = false;
                    
                }
            }
        }
                        
                    


                




        if (!(this.personId.equals(inputPersonId))) {
            canChangeId = false;
            
            if ((numericValue % 2 != 0) && (inputPersonId.length() == 10) && capsCheck.equals(capsCheck.toUpperCase()) && correctNums && noChangedAge) {
                int specCheck = 0;
                for (int i = 4; i < 8; i++) {
                    if (Character.isLetterOrDigit(inputPersonId.charAt(i)))
                        specCheck++;
                }
                if (specCheck == 2)
                    canChangeId = true;
            }

            
        } 
        // main logic for whether change should be allowed
        boolean flag = true; // only flags false when addperson rules are broken
        if (canChangeAge && canChangeId && addressValid) { // allows certain changes when my rules are broken
            if (canChangeAddress && noChangedAge)           // because otherwsie the ruleset leads to logical 
                this.address = inputAddress;                 // inconsistency
            if (canChangeAge)
                this.birthdate = inputDate;
            if (canChangeId && noChangedAge)
                this.personId = inputPersonId;
            if (noChangedAge) {
                this.firstName = inputFirstName;
                this.lastName = inputLastName;
            }
            ArrayList<String> output = new ArrayList<>();
            output.add("Person details:");
            output.add("First name: " + this.firstName);
            output.add("Last name: " + this.lastName);
            output.add("Person id: " + this.personId);
            output.add("Address: " + this.address);
            output.add("Birthdate: " + this.birthdate);

            File file = this.createFile(testFile + "_actual.txt");
            this.fileWriter(file, output);
        } 
        else {
            ArrayList<String> output = new ArrayList<>();
            File file = this.createFile(testFile + "_actual.txt");
            this.fileWriter(file, output);
            flag = false;
        }
        boolean madeUpdate = flag;
        
        return madeUpdate;
        
    }

    public String addDemeritPoints(String fileName) {

        String failedMessage = "Failed";
        String successMessage = "Success";

        File outFile;
        ArrayList<String> output = new ArrayList<>();

        boolean isValid = true;

        // Read txt file + parse into strings
        String inputDate = "";
        String inputDemeritPoints = "";
        
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/java/com/example/"+fileName+"_input.txt"))) {
            inputDate = reader.readLine();
            inputDemeritPoints = reader.readLine();
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            isValid = false;
        }

        // each time this chunk of code appears, it is a "checkpoint" for validity.
        // if input is not valid, the file is created with no content, and "Failed" is returned.
        if (!isValid) {

            // Unlike the other breakpoints, 
            // this one does not create a file,
            // since no input file was given at all.
            // this feature assists testing.
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
            output.add("Format of inputted date is invalid");
            outFile = this.createFile(fileName + "_actual.txt");
            this.fileWriter(outFile, output);

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
        // does not allow 2025 due to nature of validation
        if (year < 1950 || year > 2024) {
            System.out.println("Year must be between 1950 and 2024 (inclusive)");
            isValid = false;
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
            outFile = this.createFile(fileName + "_actual.txt");
            output.add("Inputted date does not exist");
            this.fileWriter(outFile, output);
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
            outFile = this.createFile(fileName + "_actual.txt");
            output.add("Inputted demerit value is invalid");
            this.fileWriter(outFile, output);
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
                totalDemerit += this.demeritPoints.get(demeritDate);
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

        output.add("Demerit Report - " + this.firstName + " " + this.lastName);
        output.add("Person ID: " + this.personId);
        output.add("");
        output.add("Total Active Demerit Points: " + totalDemerit);
        if (this.isSuspended) {
            output.add("Status: Suspended");
        } else {
            output.add("Status: Not Suspended");
        }

        outFile = this.createFile(fileName + "_actual.txt");
        this.fileWriter(outFile, output);

        return successMessage;
    }




}