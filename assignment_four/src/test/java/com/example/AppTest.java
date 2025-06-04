package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;



/**
 * Unit test for simple App.
 */
public class AppTest 
{
    // THE FOLLOWING FUNCTION IS WHITESPACE SENSITIVE
    public boolean compareFiles(String fileExpected, String fileActual) {

        // Initialize readers for the two files

        String line;

        ArrayList<String> lines1 = new ArrayList<>();
        ArrayList<String> lines2 = new ArrayList<>();


        try {
            BufferedReader reader1 = new BufferedReader(new FileReader("out/expected/"+fileExpected));
            while ((line = reader1.readLine()) != null) {
                lines1.add(line);
            }
            reader1.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return false;

        }

        try {
            BufferedReader reader2 = new BufferedReader(new FileReader("out/actual/"+fileActual));
            while ((line = reader2.readLine()) != null) {
                lines2.add(line);
            }
            reader2.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return false;
        }
        

    // Compare sizes first
    System.out.println("Lines in file 1: " + lines1.size());
    System.out.println("Lines in file 2: " + lines2.size());

    if (lines1.size() != lines2.size()) {
        return false;
    }

    // Compare contents line-by-line
    for (int i = 0; i < lines1.size(); i++) {
        if (!lines1.get(i).equals(lines2.get(i))) {
            System.out.println(lines1.get(1) + " is different to " + lines2.get(i));
            return false;
        }
    }
        return true;
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
        System.out.println("hi");
    }

    @Test
    public void addPersonValid() {
        Person person = new Person();
        assertTrue(person.addPerson("addPersonValid"));   
        String expectedFile = "addPersonValid_expected.txt";
        String actualFile = "addPersonValid_actual.txt";
        assertTrue(compareFiles(expectedFile, actualFile));
    }
    
    @Test
    public void addPersonInvalidIDLength() {
        Person person = new Person();
        assertFalse(person.addPerson("addPersonInvalidIDLength"));   
        String expectedFile = "addPersonInvalidIDLength_expected.txt";
        String actualFile = "addPersonInvalidIDLength_actual.txt";
        assertTrue(compareFiles(expectedFile, actualFile));
    }

    @Test
    public void addPersonInvalidID() {
        Person person = new Person();
        assertFalse(person.addPerson("addPersonInvalidID"));   
        String expectedFile = "addPersonInvalidID_expected.txt";
        String actualFile = "addPersonInvalidID_actual.txt";
        assertTrue(compareFiles(expectedFile, actualFile));
    }
    @Test
    public void addPersonInvalidState() {
        Person person = new Person();
        assertFalse(person.addPerson("addPersonInvalidState"));   
        String expectedFile = "addPersonInvalidState_expected.txt";
        String actualFile = "addPersonInvalidState_actual.txt";
        assertTrue(compareFiles(expectedFile, actualFile));
    }

    @Test
    public void addPersonInvalidAddress() {
        Person person = new Person();
        assertFalse(person.addPerson("addPersonInvalidAddress"));   
        String expectedFile = "addPersonInvalidAddress_expected.txt";
        String actualFile = "addPersonInvalidAddress_actual.txt";
        assertTrue(compareFiles(expectedFile, actualFile));
    }

    @Test
    public void addDemeritPoints() {
        Person person = new Person();
        assertTrue(person.addPerson("addPersonValid"));

        String filePrefix = "addDemeritPoints";
        String demeritMessage = person.addDemeritPoints(filePrefix);
        assertTrue(demeritMessage.contains("Success"));
        String expectedFile = filePrefix + "_expected.txt";
        String actualFile = filePrefix + "_actual.txt";
        assertTrue(compareFiles(expectedFile, actualFile));
    }

    @Test
    public void addDemeritInvalidPoints() {
        Person person = new Person();
        assertTrue(person.addPerson("addPersonValid"));

        String filePrefix = "addDemeritInvalidPoints";
        String demeritMessage = person.addDemeritPoints(filePrefix);
        assertTrue(demeritMessage.contains("Failed"));
        String expectedFile = filePrefix + "_expected.txt";
        String actualFile = filePrefix + "_actual.txt";
        assertTrue(compareFiles(expectedFile, actualFile));
    }

    @Test
    public void addDemeritSuspension() {
        Person person = new Person();
        assertTrue(person.addPerson("addPersonValid"));

        String filePrefix = "addDemeritSuspension";

        String demeritMessage = person.addDemeritPoints(filePrefix + "1");
        assertTrue(demeritMessage.contains("Success"));

        String expectedFile = filePrefix + "1" + "_expected.txt";
        String actualFile = filePrefix + "1" + "_actual.txt";
        assertTrue(compareFiles(expectedFile, actualFile));

        demeritMessage = person.addDemeritPoints(filePrefix + "2");
        assertTrue(demeritMessage.contains("Success"));

        expectedFile = filePrefix + "2" + "_expected.txt";
        actualFile = filePrefix + "2" + "_actual.txt";
        assertTrue(compareFiles(expectedFile, actualFile));

        demeritMessage = person.addDemeritPoints(filePrefix + "3");
        assertTrue(demeritMessage.contains("Success"));

        expectedFile = filePrefix + "3" + "_expected.txt";
        actualFile = filePrefix + "3" + "_actual.txt";
        assertTrue(compareFiles(expectedFile, actualFile));

    }

    @Test
    public void addDemeritPointsInactive() {
        Person person = new Person();
        assertTrue(person.addPerson("addPersonValid"));

        String filePrefix = "addDemeritPointsInactive";
        String demeritMessage = person.addDemeritPoints(filePrefix);
        assertTrue(demeritMessage.contains("Success"));
        String expectedFile = filePrefix + "_expected.txt";
        String actualFile = filePrefix + "_actual.txt";
        assertTrue(compareFiles(expectedFile, actualFile));
    }
}
