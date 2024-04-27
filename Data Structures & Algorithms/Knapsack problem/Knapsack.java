/*
* Name: Nathan Deininger
* Class: CSC 364-001, Fall 2023
* Description: This program is a solution to the 0/1 Knapsack problem. The program collects
*              the capacity (in this case, number of employee work weeks), the name of the input
*              text file, and the name of the text file that the program results should written
*              in to. Program output consists of the provided capacity, the number of overall projects
*              that were considered, the number of projects chosen, the profit from the chosen projects,
*              and which projects were selected.
* */

import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.io.PrintWriter;

public class Knapsack {
    public static ArrayList<Project> readProjects(String inputFileName) throws FileNotFoundException {
        ArrayList<Project> projects = new ArrayList<>();

        try (Scanner dataFile = new Scanner(new File(inputFileName))) {
            while (dataFile.hasNextLine()) {
                String line = dataFile.nextLine();
                String[] split = line.split(" ");
                projects.add(new Project(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2])));
            }
        }
        return projects;
    }

    private static class Project {
        private String name;
        private int value;
        private int duration;

        public Project(String name, int duration, int value) {
            this.name = name;
            this.value = value;
            this.duration = duration;
        }
    }

    public static int findValue(ArrayList<Project> projects, int weeks, boolean[][] includedProjects) {
        int n = projects.size();
        int table[][] = new int[n + 1][weeks + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= weeks; w++) {
                if (projects.get(i - 1).duration <= w) {
                    table[i][w] = Math.max(table[i - 1][w], table[i - 1][w - projects.get(i - 1).duration] + projects.get(i - 1).value);
                    includedProjects[i][w] = (table[i][w] > table[i - 1][w]);
                }
                else {
                    table[i][w] = table[i - 1][w];
                }
            }
        }
        return table[n][weeks];
    }

    public static void listProjects(boolean[][] includedProjects, ArrayList<Project> projects, int weeks) {
        int n = includedProjects.length - 1;
        int w = weeks;
        for (int i = n; i >= 1; i--) {
            if (includedProjects[i][w]) {
                System.out.println(projects.get(i - 1).name + " " + projects.get(i - 1).duration + " " + projects.get(i - 1).value);
                w -= projects.get(i - 1).duration;
            }
        }
    }

    public static void writeProjectsToFile(boolean[][] includedProjects, ArrayList<Project> projects, int weeks, PrintWriter outputFile) {
        int n = includedProjects.length - 1;
        int w = weeks;
        for (int i = n; i >= 1; i--) {
            if (includedProjects[i][w]) {
                outputFile.println(projects.get(i - 1).name + " " + projects.get(i - 1).duration + " " + projects.get(i - 1).value);
                w -= projects.get(i - 1).duration;
            }
        }
    }

    public static void main(String args[]) {
        int weeks;
        String outFileName;
        String inFileName;
        int numProjects = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of available employee work weeks: ");
        weeks = scanner.nextInt();
        System.out.println("Enter the name of input file: ");
        inFileName = scanner.next();
        System.out.println("Enter the name of output file: ");
        outFileName = scanner.next();


        ArrayList<Project> projects = new ArrayList<>();
        try {
            projects = readProjects(inFileName);
            numProjects = projects.size();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + inFileName);
        }

        boolean[][] includedProjects = new boolean[projects.size() + 1][weeks + 1];
        int value = findValue(projects, weeks, includedProjects);
        int numIncludedProjects = 0;
        for (int i = projects.size(), w = weeks; i >= 1; i--) {
            if (includedProjects[i][w]) {
                numIncludedProjects++;
                w -= projects.get(i - 1).duration;
            }
        }

        System.out.println("Number of projects available: " + numProjects);
        System.out.println("Available employee work weeks: " + weeks);
        System.out.println("Number of projects chosen: " + numIncludedProjects);
        System.out.println("Total profit: " + value);
        listProjects(includedProjects, projects, weeks);

        try {
            PrintWriter outputFile = new PrintWriter(new File(outFileName));
            outputFile.println("Number of projects available: " + numProjects);
            outputFile.println("Available employee work weeks: " + weeks);
            outputFile.println("Number of projects chosen: " + numIncludedProjects);
            outputFile.println("Total profit: " + value);
            writeProjectsToFile(includedProjects, projects, weeks, outputFile);
            outputFile.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + outFileName);
        }
    }
}
