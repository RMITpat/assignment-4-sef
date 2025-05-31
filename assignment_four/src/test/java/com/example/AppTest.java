package com.example;

import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
    }
    @Test
    public void testFiles() {
        String expectedFile = "functTest1.txt";
        String actualFile = "functTest2.txt";

        assertTrue(compareFiles(expectedFile, actualFile));
    }

    
}
