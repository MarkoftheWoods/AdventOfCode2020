package Day3;

import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class DayThreeAdvent {
    
    public static void main (String[] args) {
        char[][] map = readDataFromFile("Day3/input3.txt");
        int solution = 0;

        solution = findCollisions(map, 1, 1);
        solution *= findCollisions(map, 3, 1);
        solution *= findCollisions(map, 5, 1);
        solution *= findCollisions(map, 7, 1);
        solution *= findCollisions(map, 1, 2);

        System.out.println("The final total is " + solution);
    }

    public static void printmap(char[][] map) {
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                System.out.print(map[i][j]);
            }
            System.out.print("\n");
        }
    }

    public static int findCollisions(char[][] map, int right, int down) {
        int treeCount = 0;
        int row = 0;
        int col = 0;

        System.out.println("---------------------------");
        System.out.println("RIGHT x" + right + "DOWN x" + down);
        System.out.println("---------------------------");

        while (row < map.length) {
            if (map[row][col] == '#') {
                treeCount++;
                System.out.println("Hit at  (" + row + ", " + col + ")");
            }
            else {
                System.out.println("Miss at (" + row + ", " + col + ")");
            }
            
            row = (row + down);
            col = (col + right) % map[0].length;
        }

        System.out.println("There were " + treeCount + " collisions.");

        return treeCount;

    }

    public static char[][] readDataFromFile(String filename) {
        char[][] output;
        ArrayList<String> input = new ArrayList<String>();
        int columnCount = 0;
        int rowCount = 0;

        try {
            File myFile = new File (filename);
            Scanner myReader = new Scanner(myFile);
            String line = "";
            

            while (myReader.hasNextLine()) {
                line = myReader.nextLine();
                input.add(line);
                rowCount++;
            }
            columnCount = line.length();
            myReader.close();
        }
        catch (FileNotFoundException exception) {
            System.out.println("File not found");
            exception.printStackTrace();
        }

        output = new char[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            String row = input.get(i);
            for (int j = 0; j < columnCount; j++) {
                output[i][j] = row.charAt(j);
            }
        }

        return output;
    }
}
