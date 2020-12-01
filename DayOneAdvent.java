import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.ArrayList;
import java.util.Comparator;

import javax.net.ssl.HttpsURLConnection;


public class DayOneAdvent {

    public static void main(String[] args) {
        ArrayList<Integer> data = readDataFromFile("input1.txt");
        
        Integer solution1 = findSolutionOne(data);
        Integer solution2 = findSolutionTwo(data);

        System.out.println("First solution: " + solution1);
        System.out.println("Second solution: " + solution2);
    }

    public static ArrayList<Integer> readDataFromFile(String filename) {
        ArrayList<Integer> output = new ArrayList<Integer>();

        try {
            File myFile = new File (filename);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                output.add(Integer.parseInt(line));
            }

            myReader.close();
        }
        catch (FileNotFoundException exception) {
            System.out.println("File not found");
            exception.printStackTrace();
        }

        return output;
    }

    public static Integer findSolutionOne(ArrayList<Integer> data) {
        boolean foundSolution = false;
        Integer solution = -1;

        int counter = 0;

        int i = 0;
        int j = 1;

        data.sort(Comparator.naturalOrder());

        while(!foundSolution && i < data.size()) {
            counter++;
            int sum = data.get(i) + data.get(j);

            if (sum == 2020 ) {
                foundSolution = true;
                solution = data.get(i) * data.get(j);
                System.out.println("Solution #1 found after " + counter + " checks.");
            }
            else {
                j++;
                if (j == data.size()) {
                    i++;
                    j = i + 1;
                }
            }
        }

        return solution;
    }

    public static Integer findSolutionTwo(ArrayList<Integer> data) {
        boolean foundSolution = false;
        Integer solution = -1;
        data.sort(Comparator.naturalOrder());

        int i = 0;
        int j = 1;
        int k = 2;

        int counter = 0;

        int size = data.size();

        //System.out.println("Size: " + data.size());

        while(!foundSolution && i < (size - 2)) {
            counter++;

            int sum1 = data.get(i) + data.get(j);
            int sum = sum1 + data.get(k);

            if (sum == 2020 ) {
                foundSolution = true;
                solution = data.get(i) * data.get(j) * data.get(k);
                System.out.println("Solution #2 found after " + counter + " checks.");
            }
            else {
                k++;
                if ( (k == size) || (sum1 > 2020) ) {
                    j++;
                    k = j + 1;

                    if (j == (size - 1)) {
                        i++;
                        j = i + 1;
                        k = j + 1;
                    }
                }
            }
            //System.out.println(i + " " + j + " " + k + "  Iteration: " + counter);
        }

        return solution;
    }
}