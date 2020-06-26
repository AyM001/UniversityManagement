package UI;

import Model.CourseModel;
import Model.ProfessorModel;
import Model.ScheduleModel;
import Model.StudentModel;
import Services.CourseService;
import Services.ProfessorService;
import Services.ScheduleService;
import Services.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CourseUI {
    private CourseModel courseModel = new CourseModel();
    private CourseService courseService = new CourseService();
    private StudentModel studentModel = new StudentModel();
    private StudentService studentService = new StudentService();
    private ProfessorModel professorModel = new ProfessorModel();
    private ProfessorService professorService = new ProfessorService();
    private ScheduleService scheduleService = new ScheduleService();
    private ScheduleModel scheduleModel = new ScheduleModel();
    Scanner scanner = new Scanner(System.in);

    public void startCourseApp() {
        int option = -1;
        while (option != 0) {
            System.out.println("This is the Courses Management!");
            System.out.println("-------------------------------");
            System.out.println("Choose your option:");
            System.out.println(" 1.Add course.");
            System.out.println(" 2.View courses.");
            System.out.println(" 3.Find specific course.");
            System.out.println(" 4.Update courses");
            System.out.println(" 5.Delete course.");
            System.out.println(" 6.Create plan.");
            System.out.println(" 0.Back");
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                addCourse();
            } else if (option == 2) {
                viewCourses();
            } else if (option == 3) {
                findCourse();
            } else if (option == 4) {
                updateCourseInfo();
            } else if (option == 5) {
                deleteCourse();
            } else if (option == 6) {
                makeAssignment();
            }
        }
    }

    private void makeAssignment() {
        /*  1. Aici ne-ar trebuii o Metoda de a verifica daca exista deja o asignare
        pt ca user-ul daca nu stie , o sa incerce sa asigneze acelasi student la
        acelasi curs si o sa crape.
        if( asignarea exista ) ... sout(" already assigned")
        else ... asigneaza.

          2. Si o metoda care sa verifice daca intervalele de timp dintr-o zi atribuite unui curs se suprapun cu altele
          if ( se suprapun) ... sout(" schedule rezerved")
          else (create schedule)
         */

        int asign = -1;
        while (asign != 0) {
            System.out.println("Available actions:");
            System.out.println(" 1.Assign students to a course");
            System.out.println(" 2.Assign proffesors to a course");
            System.out.println(" 3.Assign schedules to courses");
            System.out.println(" 0.Back.");
            asign = scanner.nextInt();
            scanner.nextLine();
            if (asign == 1) {
                setStudentToCourse();
            } else if (asign == 2) {
                setProfessorsToCourse();
            } else if (asign == 3) {
                setScheduleToCourse();
            }
        }
    }


    private void setScheduleToCourse() {
        ScheduleUI scheduleUI = new ScheduleUI();
        scheduleUI.viewSchedules();
        System.out.println("Choose Schedule:");
        int scheduleId = scanner.nextInt();
        scanner.nextLine();
        Optional<ScheduleModel> scheduleModelOptional = scheduleService.findByIdOptional(scheduleModel, scheduleId);
        if (!scheduleModelOptional.isPresent()) {
            System.out.println("Schedule missing!");
        } else {
            ScheduleModel scheduleModel1 = scheduleModelOptional.get();
            viewCourses();
            System.out.println("Choose Course Id:");
            int courseId = scanner.nextInt();
            scanner.nextLine();
            Optional<CourseModel> courseModelOptional = courseService.findByIdOptional(courseModel, courseId);
            if (!courseModelOptional.isPresent()) {
                System.out.println("Course not found!");
            } else {
                CourseModel courseModel1 = courseModelOptional.get();
                List<ScheduleModel> scheduleModelList = courseModel1.getScheduleModelList();
                scheduleModelList.add(scheduleModel1);
                courseService.updateCourse(courseModel1);
                System.out.println("***Successfully assigned***");
            }
        }
    }

    private void setProfessorsToCourse() {
        ProfessorUI professorUI = new ProfessorUI();
        professorUI.viewProfessors();
        System.out.println("Choose Professor:");
        int professorId = scanner.nextInt();
        scanner.nextLine();
        Optional<ProfessorModel> professorModelOptional = professorService.findByIdOptional(professorModel, professorId);
        if (!professorModelOptional.isPresent()) {
            System.out.println("Professor not found!");
        } else {
            ProfessorModel professorModel1 = professorModelOptional.get();
            viewCourses();
            System.out.println("Choose Course Id:");
            int courseId = scanner.nextInt();
            scanner.nextLine();
            Optional<CourseModel> courseModelOptional = courseService.findByIdOptional(courseModel, courseId);
            if (!courseModelOptional.isPresent()) {
                System.out.println("Course not found!");
            } else {
                CourseModel courseModel1 = courseModelOptional.get();
                List<ProfessorModel> professorModelList = courseModel1.getProfessorModelList();
                professorModelList.add(professorModel1);
                courseService.updateCourse(courseModel1);
                System.out.println("***Successfully assigned***");
            }
        }
    }

    private void setStudentToCourse() {
        StudentUI studentUI = new StudentUI();
        studentUI.viewStudents();
        System.out.println("Choose Student:");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        Optional<StudentModel> studentModelOptional = studentService.findByIdOptional(studentModel, studentId);
        if (!studentModelOptional.isPresent()) {
            System.out.println("Student not found!");
        } else {
            StudentModel studentModel1 = studentModelOptional.get();
            viewCourses();
            System.out.println("Choose Course Id:");
            int courseId = scanner.nextInt();
            scanner.nextLine();
            Optional<CourseModel> courseModelOptional = courseService.findByIdOptional(courseModel, courseId);
            if (!courseModelOptional.isPresent()) {
                System.out.println("Course not found!");
            } else {
                CourseModel courseModel1 = courseModelOptional.get();
                List<StudentModel> studentModelList = courseModel1.getStudentModelList();
                studentModelList.add(studentModel1);
                courseService.updateCourse(courseModel1);
                System.out.println("***Successfully assigned***");
            }
        }
    }

    private void addCourse() {
        System.out.println("Enter course name:");
        String name = scanner.nextLine();
        System.out.println("Enter course description:");
        String description = scanner.nextLine();
        courseModel.setName(name);
        courseModel.setDescription(description);
        courseService.addCourse(courseModel);
        System.out.println("***Course added***");
    }

    private void viewCourses() {
        List<CourseModel> courseModelList = courseService.getCourses(courseModel);
        courseModelList.forEach(courseModel1 -> System.out.println("Course Id: " + courseModel1.getIdCourse() + "\n"
                + courseModel1.getName() + " " + courseModel1.getDescription() + "\n******"));
    }

    private void findCourse() {
        System.out.println("Enter course id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<CourseModel> courseModelOptional = courseService.findByIdOptional(courseModel, id);
        if (!courseModelOptional.isPresent()) {
            System.out.println("Course not found!");
        } else {
            CourseModel courseModel1 = courseModelOptional.get();
            System.out.println("*****");
            System.out.println("Found: " + courseModel1.getIdCourse() + "\n" + courseModel1.getName() + " " + courseModel1.getDescription());
            System.out.println("*****");
        }
    }

    private void updateCourseInfo() {
        viewCourses();
        System.out.println("Enter course id for update:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<CourseModel> optionalCourseModel = courseService.findByIdOptional(courseModel, id);
        if (!optionalCourseModel.isPresent()) {
            System.out.println("Incorrect id!");
        } else {
            CourseModel courseModel = optionalCourseModel.get();
            int update = -1;
            System.out.println("What do you want to update?");
            System.out.println("1.Course name.");
            System.out.println("2.Course description.");
            update = scanner.nextInt();
            scanner.nextLine();
            if (update == 1) {
                System.out.println("Enter new course name:");
                String newName = scanner.nextLine();
                courseModel.setName(newName);
                courseService.updateCourse(courseModel);
            } else if (update == 2) {
                System.out.println("Enter new course description:");
                String newDescription = scanner.nextLine();
                courseModel.setDescription(newDescription);
                courseService.updateCourse(courseModel);
            }
        }
    }

    private void deleteCourse() {
        viewCourses();
        System.out.println("Enter course id for delete:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<CourseModel> optionalCourseModel = courseService.findByIdOptional(courseModel, id);
        if (!optionalCourseModel.isPresent()) {
            System.out.println("Incorrect id!");
        } else {
            CourseModel courseModel = optionalCourseModel.get();
            courseService.deleteCourse(courseModel);
            System.out.println("***Course Deleted***");
        }
    }
}