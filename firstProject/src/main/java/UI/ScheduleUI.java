package UI;

import Model.ClassModel;
import Model.ScheduleModel;
import Services.ClassService;
import Services.ScheduleService;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ScheduleUI {
    private ScheduleService scheduleService = new ScheduleService();
    private ScheduleModel scheduleModel = new ScheduleModel();
    private ClassService classService = new ClassService();
    private ClassModel classModel = new ClassModel();
    private ClassUI classUI = new ClassUI();
    Scanner scanner = new Scanner(System.in);

    public void startScheduleApp() {
        int option = -1;
        while (option != 0) {
            System.out.println("This is the Schedules Management!");
            System.out.println("-------------------------------");
            System.out.println("Choose your option:");
            System.out.println(" 1.Add a schedule.");
            System.out.println(" 2.View schedules.");
            System.out.println(" 3.Find specific schedule.");
            System.out.println(" 4.Update schedules");
            System.out.println(" 5.Delete schedules.");
            System.out.println(" 0.Back");
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                addSchedules();
            } else if (option == 2) {
                viewSchedules();
            } else if (option == 3) {
                findSchedule();
            } else if (option == 4) {
                updateSchedule();
            } else if (option == 5) {
                deleteSchedule();
            }
        }
    }

    /*
    private void setScheduletoClass() {
        viewSchedules();
        System.out.println("Choose schedule Id:");
        scheduleService.getSchedules(scheduleModel);
        int scheduleId = scanner.nextInt();
        scanner.nextLine();
        Optional<ScheduleModel> scheduleModelOptional = scheduleService.findByIdOptional(scheduleModel, scheduleId);
        ScheduleModel scheduleModel = scheduleModelOptional.get();
        classUI.viewClasses();
        System.out.println("Choose Class Id:");
        classService.getClasses(classModel);
        int classId = scanner.nextInt();
        scanner.nextLine();
        Optional<ClassModel> classModelOptional = classService.findByIdOptional(classModel, classId);
        ClassModel classModel = classModelOptional.get();
        List<ScheduleModel> scheduleModelList = scheduleService.getSchedules(scheduleModel);
        scheduleModelList.add(scheduleModel);
        classService.updateClasses(classModel);
        scheduleService.updateSchedule(scheduleModel);

    }
      Metoda asta si metoda nostra de update fac acelasi lucru deci nu mai avem nevoie de ea ,
      la addSchedule ne pune sa asignam si o clasa existenta , si la update o reasignam,
      deci metoda setClassToSchedule e inutila.

     */

    private void deleteSchedule() {
        viewSchedules();
        System.out.println("Enter schedule Id for delete:");
        int scheduleId = scanner.nextInt();
        scanner.nextLine();
        Optional<ScheduleModel> scheduleModelOptional = scheduleService.findByIdOptional(scheduleModel, scheduleId);
        if (!scheduleModelOptional.isPresent()) {
            System.out.println("Schedule not available!");
        } else {
            ScheduleModel scheduleModel = scheduleModelOptional.get();
            scheduleService.deleteSchedule(scheduleModel);
            System.out.println("***Deleted***");
        }
    }

    private void updateSchedule() {
        viewSchedules();
        System.out.println("Enter Schedule Id for update:");
        int scheduleId = scanner.nextInt();
        scanner.nextLine();
        Optional<ScheduleModel> scheduleModelOptional = scheduleService.findByIdOptional(scheduleModel, scheduleId);
        if (!scheduleModelOptional.isPresent()) {
            System.out.println("Schedule not available!");
        } else {
            ScheduleModel scheduleModel = scheduleModelOptional.get();
            int update = -1;
            System.out.println("What do you want to update?");
            System.out.println("1.Date.");
            System.out.println("2.Start hour.");
            System.out.println("3.End hour.");
            System.out.println("4.Class.");
            update = scanner.nextInt();
            scanner.nextLine();
            if (update == 1) {
                System.out.println("Enter new Date:");
                String myNewDate = scanner.nextLine();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                Date date = null;
                try {
                    date = dateFormat.parse(myNewDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                scheduleModel.setDate(date);
                scheduleService.updateSchedule(scheduleModel);
                System.out.println("***Updated***");
            } else if (update == 2) {
                System.out.println("Enter new Start hour:");
                String newStartHour = scanner.nextLine();
                Time startTime = Time.valueOf(newStartHour);
                scheduleModel.setStartHour(startTime);
                scheduleService.updateSchedule(scheduleModel);
                System.out.println("***Updated***");
            } else if (update == 3) {
                System.out.println("Enter new End hour:");
                String newEndHour = scanner.nextLine();
                Time startTime = Time.valueOf(newEndHour);
                scheduleModel.setStartHour(startTime);
                System.out.println("***Updated***");
            } else if (update == 4) {
                classUI.viewClasses();
                System.out.println("Enter new classId:");
                int classId = scanner.nextInt();
                scanner.nextLine();
                Optional<ClassModel> classModelOptional = classService.findByIdOptional(classModel, classId);
                if (!classModelOptional.isPresent()) {
                    System.out.println("Class not found!");
                } else {
                    ClassModel classModel1 = classModelOptional.get();
                    List<ScheduleModel> scheduleModelList = scheduleService.getSchedules(scheduleModel);
                    scheduleModel.setClassModel(classModel1);
                    scheduleModelList.add(scheduleModel);
                    classService.updateClasses(classModel1);
                    scheduleService.updateSchedule(scheduleModel);
                    System.out.println("***Updated***");
                /* Aici in updateClass am modificat si integrat si metoda setScheduleToClass
                  ca sa nu o mai folosim pe aia de sus.
                 */
                }
            }
        }
    }

    private void findSchedule() {
        System.out.println("Enter schedule id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<ScheduleModel> scheduleModelOptional = scheduleService.findByIdOptional(scheduleModel, id);
        if (!scheduleModelOptional.isPresent()) {
            System.out.println("Schedule not available!");
        } else {
            ScheduleModel scheduleModel = scheduleModelOptional.get();
            System.out.println("***");
            System.out.println("Found: " + scheduleModel.getIdSchedule() + "\n" + scheduleModel.getDate() + scheduleModel.getStartHour() + scheduleModel.getEndHour());
            System.out.println("***");
        }
    }

    public void viewSchedules() {
        // Data apare ciudat  , este setata de tip -> "2020-02-02" ... si apare->  2020-01-02 00:02:00.0 "
        List<ScheduleModel> scheduleModelList = scheduleService.getSchedules(scheduleModel);
        scheduleModelList.forEach(scheduleModel1 -> {
                    System.out.println("Schedule Id:" + scheduleModel1.getIdSchedule() + "\n"
                            + scheduleModel1.getDate() +
                            "\n" + "Time " + scheduleModel1.getStartHour() + "-" + scheduleModel1.getEndHour());
                    ClassModel classModel = scheduleModel1.getClassModel();
                    if (classModel != null) {
                        System.out.println("ClassId:" + scheduleModel1.getClassModel().getIdClass() + " "
                                + scheduleModel1.getClassModel().getName() + "\n*****");
                    } else {
                        System.out.println();
                    }
                }
        );
    }

  /*  private boolean verifyDateTime(Date dateV, Time startHourV, Time endHourV , int classId) {
        if (scheduleModel.getDate().equals(dateV) && scheduleModel.getStartHour().before(startHourV) && scheduleModel.getStartHour().after(startHourV) && scheduleModel.getClassModel().getIdClass() == classId
                || scheduleModel.getDate().equals(dateV) && scheduleModel.getStartHour().after(startHourV) && scheduleModel.getStartHour().before(endHourV) && scheduleModel.getClassModel().getIdClass() == classId
                || scheduleModel.getDate().equals(dateV) && scheduleModel.getStartHour().before(endHourV) && scheduleModel.getEndHour().after(endHourV) && scheduleModel.getClassModel().getIdClass() == classId) {
            return true;
        } else {
            return false;
        }
    }

   */

    private void addSchedules() {
        System.out.println("Date:");
        Scanner scanner = new Scanner(System.in);
        String myDate = scanner.nextLine();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date = null;
        try {
            date = dateFormat.parse(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        scheduleModel.setDate(date);
        System.out.println("Start hour:");
        String startHour = scanner.nextLine();
        Time startTime = Time.valueOf(startHour);
        scheduleModel.setStartHour(startTime);
        System.out.println("End hour:");
        String endHour = scanner.nextLine();
        Time endTime = Time.valueOf(endHour);
        scheduleModel.setEndHour(endTime);

        ClassUI classUI = new ClassUI();
        classUI.viewClasses();
        System.out.println("Enter class:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<ClassModel> classModelOptional = classService.findByIdOptional(classModel, id);
        ClassModel foundClass = classModelOptional.get();
        scheduleModel.setClassModel(foundClass);
        scheduleService.addSchedule(scheduleModel);
        System.out.println("***Schedule added***");
    }

}

