package UI;

import Model.StudentModel;
import Services.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class StudentUI {
    private StudentModel studentModel = new StudentModel();
    private StudentService studentService = new StudentService();
    Scanner scanner = new Scanner(System.in);

    public void startStudentApp(){
        int option = -1 ;
        while (option != 0){
            System.out.println("This is the Students Management!");
            System.out.println("-------------------------------");
            System.out.println("Choose your option:");
            System.out.println(" 1.Add student.");
            System.out.println(" 2.View students.");
            System.out.println(" 3.Find specific student.");
            System.out.println(" 4.Update students");
            System.out.println(" 5.Delete student.");
            System.out.println(" 0.Back");

            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1){
                addStudent();
            }
            else if (option == 2){
                viewStudents();
            }
            else if (option ==3){
                findStudent();
            }
            else if (option == 4){
                updateStudentInfo();
            }
            else if (option == 5){
                deleteStudent();
            }
        }
    }

    private void deleteStudent() {
        viewStudents();
        System.out.println("Enter CNP for delete:");
        int cnp = scanner.nextInt();
        scanner.nextLine();
        Optional<StudentModel> optionalStudentModel = studentService.findByIdOptional(studentModel, cnp);
        if (!optionalStudentModel.isPresent()) {
            System.out.println("Incorrect CNP!");
        } else {
            StudentModel studentModel = optionalStudentModel.get();
            studentModel.setCnp(cnp);
            studentService.deleteStudent(studentModel);
            System.out.println("***Student Deleted***");
        }
    }

    private void updateStudentInfo() {
        viewStudents();
        System.out.println("Enter student CNP for update:");
        int cnp = scanner.nextInt();
        scanner.nextLine();
        Optional<StudentModel> optionalStudentModel = studentService.findByIdOptional(studentModel , cnp);
        if (!optionalStudentModel.isPresent()){
            System.out.println("Incorrect CNP!");
        }
        else{
            StudentModel studentModel = optionalStudentModel.get();
            int update = -1;
            System.out.println("What do you want to update?");
            System.out.println("1.First name.");
            System.out.println("2.Last name.");
            update = scanner.nextInt();
            scanner.nextLine();
             if (update ==1){
                 System.out.println("Enter new first name:");
                 String newFirst = scanner.nextLine();
                 studentModel.setFirstName(newFirst);
                 studentService.updateStudent(studentModel);
                 System.out.println("***Updated***");
             }
             else if (update == 2){
                 System.out.println("Enter new last name:");
                 String newLast = scanner.nextLine();
                 studentModel.setLastName(newLast);
                 studentService.updateStudent(studentModel);
                 System.out.println("***Updated***");
             }

        }
    }

    private void findStudent() {
        System.out.println("Enter student's CNP:");
        int cnp = scanner.nextInt();
        scanner.nextLine();
        Optional<StudentModel> optionalStudentModel = studentService.findByIdOptional(studentModel,cnp);
        if (!optionalStudentModel.isPresent()){
            System.out.println("Student missing!");
        }
        else {
           StudentModel studentModel = optionalStudentModel.get();
            System.out.println("*****");
            System.out.println("Found: " + studentModel.getCnp() + "\n" + studentModel.getFirstName() + " " + studentModel.getLastName());
            System.out.println("*****");
        }
    }

    public void viewStudents() {
       List<StudentModel> studentModelList = studentService.getStudents(studentModel);
       studentModelList.forEach(studentModel1 -> System.out.println("CNP: " + studentModel1.getCnp() + "\n"
       + studentModel1.getFirstName() + " " + studentModel1.getLastName() + "\n******"));
    }

    private void addStudent() {
        System.out.println("First name:");
        String firstName = scanner.nextLine();
        System.out.println("Last name:");
        String lastName = scanner.nextLine();
        studentModel.setFirstName(firstName);
        studentModel.setLastName(lastName);
        studentService.addStudent(studentModel);
        System.out.println("***Student added***");
    }
}
