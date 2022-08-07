package tracker;

import java.util.Scanner;

public class CLI {

    private final Scanner sc = new Scanner(System.in);
    private final Registry registry = new Registry();

    public CLI() {
        menu();
    }

    public void backer(String method) {
        while (true) {
            String input = sc.nextLine().strip().toLowerCase();
            if ("back".equals(input)) {
                break;
            } else if (method.equals("find")){
               String output = registry.find(input);
                if(output != null) {
                    System.out.println(output);
                }
            } else if (method.equals("add points")) {
                String output = registry.addPoint(input);
                if(output != null) {
                    System.out.println(output);
                }
            }
        }
    }

    public void menu() {
        System.out.println("Learning Progress Tracker");
        while (true) {
            String input = sc.nextLine();
            if(input.equals("exit")) {
                System.out.println("Bye!");
                break;
            } else if(input.isBlank()) {
                System.out.println("No input");
            } else if(input.equals("add students")) {
                addStudents();
            } else if(input.equals("back")) {
                System.out.println("Enter 'exit' to exit the program.");
            } else if(input.equals("list")) {
                registry.list();
            } else if(input.equals("add points")) {
                System.out.println("Enter an id and points or 'back' to return.");
                backer(input);
            } else if(input.equals("find")) {
                System.out.println("Enter an id or 'back' to return:");
                backer(input);
            } else {
                System.out.println("Unknown command!");
            }
        };
    }

    public void addStudents() {
        System.out.println("Enter student credentials or 'back' to return:");
        int count = 0;
        while (true) {
            String input = sc.nextLine().strip().toLowerCase();
            if ("back".equals(input)) {
                System.out.printf("Total %d students have been added.%n", count);
                break;
            } else {
                Student newStudent = registry.addStudent(input);
                if (newStudent != null) {
                    count++;
                    System.out.println("The student has been added.");
                }
            }
        }
    }

}
