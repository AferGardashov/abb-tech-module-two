package homeworks.homework15;

import homeworks.homework15.dto.StudentDto;
import homeworks.homework15.repository.StudentFileRepositoryImpl;
import homeworks.homework15.repository.StudentListRepositoryImpl;
import homeworks.homework15.service.StudentService;
import homeworks.homework15.service.StudentServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        StudentService studentService;
        String path = "C:\\Users\\009046\\IdeaProjects\\ABB Tech\\abb-tech-module-two\\src\\test.txt";

        System.out.println("Where do you want to add or get student data: ");
        System.out.println("Options: ");
        System.out.println("1. Static List");
        System.out.println("2. File (path: C:\\Users\\009046\\IdeaProjects\\ABB Tech\\abb-tech-module-two\\src\\test.txt)");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1:
                studentService = new StudentServiceImpl(new StudentListRepositoryImpl());
                main.studentTask(studentService);
                break;
            case 2:
                studentService = new StudentServiceImpl(new StudentFileRepositoryImpl(path));
                main.studentTask(studentService);
                break;
            default:
                break;
        }


    }

    private void studentTask(StudentService studentService) {
        whileOption:
        while (true) {
            System.out.println("1. Add new student, 2. Get all students, 3. Remove by ID, 4. Exit");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student ID: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter student scholarship: ");
                    BigDecimal scholarship = scanner.nextBigDecimal();
                    StudentDto studentDto = new StudentDto(name, age, scholarship);
                    studentService.save(studentDto);
                    System.out.println("Successfully saved student");
                    break;
                case 2:
                    List<StudentDto> students = studentService.findAll();
                    students.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    studentService.removeById(id);
                    System.out.println("Successfully removed student");
                    break;
                case 4:
                    scanner.close();
                    break whileOption;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }


}
