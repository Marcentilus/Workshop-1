package pl.coderslab;
import com.sun.source.tree.BreakTree;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import static java.util.Arrays.copyOf;


public class TaskManager {

    public static void main(String[] args) {
        Options();

    }
    public static void Options(){
        String[] OptionsArr ={"add", "remove", "list", "exit"};
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        for (String option: OptionsArr) {
            System.out.println(ConsoleColors.RESET+ option);
        }
    }
    public static String[][] fileIntoArr(){
        Path filePath = Paths.get("/home/rafa/Workshop-1/Workshop-1/src/test/tasks.csv");
        String[][] extendedArr = new String[0][0];
        try(Scanner scan = new Scanner(filePath)) {
            String line = "";
            int Counter = 0;
            while (scan.hasNextLine()){
                line = scan.nextLine();
                String[] lineIntoArr = line.split(",");
                extendedArr = Arrays.copyOf(extendedArr,extendedArr.length +1);
                extendedArr[Counter] = lineIntoArr;
                Counter ++;
            }
            //System.out.println(Arrays.toString(extendedArr[2]));

        }catch (IOException e){
            System.out.println("File not found");
        }
        return extendedArr;
    }
    public static String[][] Add(){
        String[][] arr = fileIntoArr();
        StringBuilder SB = new StringBuilder();
        Scanner scan = new Scanner(System.in);
        System.out.println("Please add task description");
        SB = new StringBuilder(scan.nextLine());
        System.out.println("Please add task due date");
        SB.append(", ").append(scan.nextLine());
        System.out.println("Is your task important: true/false");
        while(!scan.hasNextBoolean()){
            System.out.println("You must input true or false");
        }
        SB.append(", ").append(scan.nextLine());
        String newLine = SB.toString();
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = newLine.split(",");
        return arr;
    }



}
