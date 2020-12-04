package Day4;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class DayFourAdvent {
    public static void main(String[] args) {
        ArrayList<String> data = readDataFromFile("Day4/input4.txt");
        
        System.out.println("Valid passports: " + processPassports(data));

    }

    public static int processPassports(ArrayList<String> data) {
        int validCount = 0;
        Hashtable<String,String> dataPairs = new Hashtable<String, String>();
        
        for (int i = 0; i < data.size(); i++) {
            String line = data.get(i);
            if (line.isBlank()){
                validCount += validatePassport(dataPairs) ? 1 : 0;
                dataPairs = new Hashtable<String, String>();
            }
            else {
                String[] pairs = line.split(" ");
                
                for (int j = 0; j < pairs.length; j++) {
                    dataPairs.put(pairs[j].split(":")[0],pairs[j].split(":")[1]);
                }
            }
        }

        validCount += validatePassport(dataPairs) ? 1 : 0;

        return validCount;
    }

    public static boolean validatePassport(Hashtable<String, String> dataPairs) {
        boolean valid = true;

        valid &= validBcl(dataPairs.get("byr"));
        valid &= validIyr(dataPairs.get("iyr"));
        valid &= validEyr(dataPairs.get("eyr"));
        valid &= validHgt(dataPairs.get("hgt"));
        valid &= validHcl(dataPairs.get("hcl"));
        valid &= validEcl(dataPairs.get("ecl"));
        valid &= validPid(dataPairs.get("pid"));

        System.out.println("PASSPORT IS: " + (valid ? "VALID" : "INVALID"));

        return valid;
    }

    public static boolean validBcl(String bcl) {
        boolean valid = false;

        if (bcl != null && bcl.length() == 4) {
            try{
                int year = Integer.parseInt(bcl);
                if (year <= 2002 && year >= 1920)
                    valid = true;
            }
            catch (Exception e) {}
        }
        System.out.println(valid ? ("Birth year " + bcl + " is between 1920 and 2002. VALID") : ("Birth year " + bcl + " is NOT between 1920 and 2002. INVALID"));
        return valid;
    }

    public static boolean validIyr(String iyr) {
        boolean valid = false;

        if (iyr != null) {
            try{
                int year = Integer.parseInt(iyr);
                if (year <= 2020 && year >= 2010)
                    valid = true;
            }
            catch (Exception e) {}
        }

        System.out.println(valid ? ("Issue year " + iyr + " is between 2010 and 2020. VALID") : ("Issue year " + iyr + " is NOT between 2010 and 2020. INVALID"));
        
        return valid;
    }
    public static boolean validEyr(String eyr) {
        boolean valid = false;

        if (eyr != null) {
            try{
                int year = Integer.parseInt(eyr);
                if (year <= 2030 && year >= 2020)
                    valid = true;
            }
            catch (Exception e) {}
        }

        System.out.println(valid ? ("Expiry year " + eyr + " is between 2020 and 2030. VALID") : ("Expiry year " + eyr + " is NOT between 2020 and 2030. INVALID"));
        
        return valid;
    }
        
    public static boolean validHgt(String hgt) {
        boolean valid = false;

        if (hgt != null) {
            if (hgt.endsWith("cm")) {
                try{
                    int height = Integer.parseInt(hgt.replaceAll("cm", ""));
                    if (height <= 193 && height >= 150)
                        valid = true;
                }
                catch (Exception e) {}
                 
            }
            else if (hgt.endsWith("in")) {
                try{
                    int height = Integer.parseInt(hgt.replaceAll("in", ""));
                    if (height <= 76 && height >= 59)
                        valid = true;
                }
                catch (Exception e) {}
            }
        }

        System.out.println(valid ? ("Height " + hgt + " is VALID") : ("Height " + hgt + " is INVALID"));

        return valid;
    }

    public static boolean validHcl(String hcl) {
        boolean valid = false;
        String regex = "#[a-z0-9]{6}";

        if (hcl != null){
            valid = hcl.matches(regex);
        }

        System.out.println(valid ? ("Hair Color " + hcl + " matches format. VALID") : ("Hair Color " + hcl + " does not match format. INVALID"));

        return valid;
    }

    public static boolean validEcl(String ecl) {
        boolean valid = false;

        if (ecl != null) {
            if (ecl.equals("amb") 
                || ecl.equals("blu") 
                || ecl.equals("brn") 
                || ecl.equals("gry") 
                || ecl.equals("grn") 
                || ecl.equals("hzl") 
                || ecl.equals("oth"))
                valid = true;
        }

        System.out.println(valid ? ("Eye Color " + ecl + " matches format. VALID") : ("Eye Color " + ecl + " does not match format. INVALID"));

        return valid;
    }
    public static boolean validPid(String pid) {
        boolean valid = false;
        String regex = "[0-9]{9}";

        if (pid != null){
            valid = pid.matches(regex);
        }

        System.out.println(valid ? ("Passport ID " + pid + " matches format. VALID") : ("Passport ID " + pid + " does not match format. INVALID"));

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
