package pl.coderslab;
import com.sun.source.tree.BreakTree;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import static java.util.Arrays.copyOf;


public class TaskManager {
    static String[][] extendedArr = new String[0][0];
    static String[][] modArr = new String[0][0];


    public static void Options() {
        String[] OptionsArr = {"add", "remove", "list", "exit"};
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        for (String option : OptionsArr) {
            System.out.println(ConsoleColors.RESET + option);
        }
    }

    public static void fileIntoArr() {
        Path filePath = Paths.get("/home/rafa/Workshop-1/Workshop-1/src/test/tasks.csv");
        try (Scanner scan = new Scanner(filePath)) {
            String line = "";
            int Counter = 0;
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                String[] lineIntoArr = line.split(",");
                extendedArr = Arrays.copyOf(extendedArr, extendedArr.length + 1);
                extendedArr[Counter] = lineIntoArr;
                Counter++;
            }
            //System.out.println(Arrays.toString(extendedArr[2]));

        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public static void addTask() {
        new StringBuilder();
        StringBuilder SB;
        Scanner scan = new Scanner(System.in);
        System.out.println("Please add task description");
        SB = new StringBuilder(scan.nextLine());
        System.out.println("Please add task due date");
        SB.append(",").append(scan.nextLine());
        System.out.println("Is your task important: true/false");
        while (!scan.hasNextBoolean()) {
            System.out.println("You must input true or false");
            scan.next();
        }
        SB.append(",").append(scan.nextLine());
        String newLine = SB.toString();
        extendedArr = Arrays.copyOf(extendedArr, extendedArr.length + 1);
        extendedArr[extendedArr.length - 1] = newLine.split(",");
    }

    public static void listTasks() {
        for (int i = 0; i < extendedArr.length; i++) {
            System.out.println(i + " : " + Arrays.toString(extendedArr[i]).replace("[","").replace("]",""));

        }

    }

    public static void removeTask() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please select number to remove");
        int input = 0;
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("You did not input a number. Your input was: " + input + ".Please input a valid number");
        }
        input = scan.nextInt();
        if (input < 0) {
            System.out.println("Number must be greater than 0");
        }
        try {
            modArr = ArrayUtils.remove(extendedArr, input);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index not found");

        }
    }



    public static void exitTask() {
        Path filePath = Paths.get("/home/rafa/Workshop-1/Workshop-1/src/test/tasks.csv");
        try (FileWriter fileWriter = new FileWriter(filePath.toFile())) {
            for (int i = 0; i < modArr.length; i++) {
                fileWriter.write(Arrays.toString(modArr[i]).replace("[", "").replace("]","") + "\n");
            }
            System.out.println(ConsoleColors.RED + "bye bye");

        } catch (IOException e) {
            System.out.println("File not found");
        }


    }

    public static void main(String[] args) {

        Options();
        fileIntoArr();
        String input = "";
        while (!input.equals("exit"))
        {Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        switch (input) {
            case "add":
                addTask();
                break;
            case "list":
                listTasks();
                break;
            case "remove":
                removeTask();
                break;
            case "exit":
                exitTask();
                break;
            default:
                System.out.println("Please select a correct option");

        }
        }
    }
}