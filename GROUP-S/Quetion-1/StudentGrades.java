
import java.util.Scanner;

public class StudentGrades {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
       
        int[] gradeCount = new int[10];  
        
        System.out.println("Physics Quiz - Five Students");
        System.out.println("-----------------------------");
        System.out.println("Enter scores for 5 students (each out of 100)\n");
        
        int studentNumber = 1;
        
       
        while (studentNumber <= 5) {
            System.out.print("Enter score for student " + studentNumber + " (0-100): ");
            
            if (!input.hasNextInt()) {
                System.out.println("Sorry, you must enter a valid number.");
                input.next();  
                continue;     
            }
            
            int marks = input.nextInt();
            
            if (marks < 0 || marks > 100) {
                System.out.println("Score must be between 0 and 100 only. Try again.");
                continue;  
            }
            
          
            String grd = "";
            String rem = "";
            
            if (marks >= 80) {
                grd = "1";
                rem = "D1";
            } else if (marks >= 75) {
                grd = "2";
                rem = "D2";
            } else if (marks >= 66) {
                grd = "3";
                rem = "C3";
            } else if (marks >= 60) {
                grd = "4";
                rem = "C4";
            } else if (marks >= 50) {
                grd = "5";
                rem = "C5";
            } else if (marks >= 45) {
                grd = "6";
                rem = "C6";
            } else if (marks >= 35) {
                grd = "7";
                rem = "P7";
            } else if (marks >= 30) {
                grd = "8";
                rem = "P8";
            } else {
                grd = "9";
                rem = "F";
            }
            
            int gradeNum = Integer.parseInt(grd);
            gradeCount[gradeNum]++;
            
         
            System.out.println("\nStudent " + studentNumber + " Result");
            System.out.println("-------------------");
            System.out.println("Score  : " + marks);
            System.out.println("Grade  : " + grd);
            System.out.println("Remark : " + rem);
            System.out.println();
            
            studentNumber++;  
        }
        
        
        System.out.println("=====================================");
        System.out.println("SUMMARY OF GRADES FOR 5 STUDENTS");
        System.out.println("=====================================");
        
        for (int g = 1; g <= 9; g++) {
            System.out.println("Grade " + g + " : " + gradeCount[g] + " student(s)");
        }
        
        input.close();
    }
}
