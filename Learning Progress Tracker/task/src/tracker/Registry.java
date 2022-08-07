package tracker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Registry {

    Map<String, Student> register = new HashMap<>();
    Map<Integer, List<Integer>> courses = new HashMap<>();

    private boolean inValidName(String name) {
        if (!name.matches("[A-Za-z]+[ A-Za-z-']*[A-Za-z]+")) {
            return true;
        }
        return name.matches(".*[-']{2}.*");
    }

    private boolean inValidEmail(String email) {
        return !email.matches("[\\w.-]+@[\\w.-]+\\.[\\w]+");
    }

    private boolean inValidNumber(String num) {
        return !num.matches("[0-9]|[1-9][0-9]*");
    }

    private boolean isTakenEmail(String email) {
        return register.containsKey(email);
    }

    public void list() {
        if(!register.isEmpty()) {
            System.out.println("Students: ");
            register.forEach((k, v) -> System.out.println(v.getId()));
        } else {
            System.out.println("No students found");
        }
    }

    public Student addStudent(String str) {
        String[] tokens = str.split("\\s+");
        if (tokens.length < 3) {
            System.out.println("Incorrect credentials");
            return null;
        }
        String firstName = tokens[0];
        if (inValidName(firstName)) {
            System.out.println("Incorrect first name");
            return null;
        }
        String lastName = String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length - 1));
        if (inValidName(lastName)) {
            System.out.println("Incorrect last name");
            return null;
        }
        String email = tokens[tokens.length - 1];
        if (inValidEmail(email)) {
            System.out.println("Incorrect email");
            return null;
        }
        if (isTakenEmail(email)) {
            System.out.println("This email is already taken.");
            return null;
        }
        Student student = new Student(register.size() + 1, firstName, lastName, email);
        register.put(student.getEmail(), student);
        courses.put(register.size(), null);
        return student;
    }

    public String addPoint(String str) {
        String[] tokens = str.split("\\s+");
        if (inValidNumber(tokens[0])) {
            String output = String.format("No student is found for id=%s", tokens[0]);
            System.out.println(output);
            return null;
        }
        Integer studentId = Integer.parseInt(tokens[0]);
        boolean studentExist = courses.containsKey(studentId);
        if(!studentExist) {
            String output = String.format("No student is found for id=%s", studentId);
            System.out.println(output);
            return null;
        }
        if (Arrays.stream(tokens).anyMatch(this::inValidNumber)) {
            System.out.println("Incorrect points format");
            return null;
        }
        List<Integer> points = Arrays.stream(str.split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        if(tokens.length != 5) {
            System.out.println("Incorrect points format");
            return null;
        }
        if(courses.get(studentId) != null) {
            List<Integer> existingPoints = courses.get(studentId);
            List<Integer> updatedPoints = existingPoints.stream().map(x -> x + points.get(existingPoints.indexOf(x))).collect(Collectors.toList());
            courses.replace(studentId, updatedPoints);
            return "Points updated";
        }
        courses.replace(studentId, points);
        return "Points updated";
    }
    public String find(String id) {
        int num = Integer.parseInt(id);
        if(courses.containsKey(num)) {
            List<Integer> points = courses.get(num);
            return String.format("%d points: Java=%s; DSA=%s; Databases=%s; Spring=%s", num, points.get(1), points.get(2), points.get(3), points.get(4));
        }
        String output = String.format("No student is found for id=%s", id);
        System.out.println(output);
        return null;
    }


}
