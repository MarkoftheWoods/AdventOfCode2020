package Day2;

import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class DayTwoAdvent {

    public static void main (String[] args) {
        ArrayList<String> data = readDataFromFile("Day2/input2.txt");

        //validatePasswords1(data);
        validatePasswords2(data);
    }

    public static void validatePasswords1(ArrayList<String> data) {
        int validCount = 0;

        for (int i = 0; i < data.size(); i++){
            String[] tokens = data.get(i).split(" ");
            String[] limits = tokens[0].split("-");
            String requiredChar = tokens[1].substring(0, tokens[1].length() - 1);
            String password = tokens[2];

            System.out.println("\nPassword: " + password);
            System.out.println("The character " + requiredChar + " must appear " + tokens[0] + " times.");

            if (validatePassword1(Integer.parseInt(limits[0]), Integer.parseInt(limits[1]), requiredChar, password)) {
                validCount++;
            }
        }

        System.out.println("Valid passwords: " + validCount);
    }

    public static boolean validatePassword1(int lowLimit, int highLimit, String requiredChar, String password) {
        boolean valid = false;

        int count = password.length() - password.replace(requiredChar, "").length();

        System.out.println("The character " + requiredChar + " appeared " + count + " times.");

        if ((count >= lowLimit) && (count <= highLimit)) {
            valid = true;
            System.out.println("VALID");
        }
        else {
            System.out.println("INVALID");
        }


        return valid;
    }

    public static void validatePasswords2(ArrayList<String> data) {
        int validCount = 0;

        for (int i = 0; i < data.size(); i++){
            String[] tokens = data.get(i).split(" ");
            String[] positions = tokens[0].split("-");
            String requiredChar = tokens[1].substring(0, tokens[1].length() - 1);
            String password = tokens[2];

            System.out.println("\nPassword: " + password);
            System.out.println("The character " + requiredChar + " must appear at " + positions[0] + " or " + positions[1] + ".");

            if (validatePassword2(Integer.parseInt(positions[0]) - 1, Integer.parseInt(positions[1]) - 1, requiredChar, password)) {
                validCount++;
            }
        }

        System.out.println("Valid passwords: " + validCount);
    }

    public static boolean validatePassword2(int firstPosition, int secondPosition, String requiredChar, String password) {
        boolean valid = false;

        System.out.println("Position #" + firstPosition + " is " + password.charAt(firstPosition) + ".");
        System.out.println("Position #" + secondPosition + " is " + password.charAt(secondPosition) + ".");

        if ( (password.charAt(firstPosition) == requiredChar.charAt(0)) && (password.charAt(secondPosition) != requiredChar.charAt(0)) ) {
                valid = true;            
        }

        if ( (password.charAt(secondPosition) == requiredChar.charAt(0)) && (password.charAt(firstPosition) != requiredChar.charAt(0)) ) {
            valid = true;            
    }

        if (valid)
            System.out.println("VALID");
        else
            System.out.println("INVALID");

        return valid;
    }

    public static ArrayList<String> readDataFromFile(String filename) {
        ArrayList<String> output = new ArrayList<String>();

        try {
            File myFile = new File (filename);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                output.add(line);
            }

            myReader.close();
        }
        catch (FileNotFoundException exception) {
            System.out.println("File not found");
            exception.printStackTrace();
        }

        return output;
    }
    
}
