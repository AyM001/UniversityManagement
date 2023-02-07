import Services.ScheduleService;
import UI.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ProjectManagementUI projectManagementUI = new ProjectManagementUI();
        projectManagementUI.runProject();

        System.out.println("That's all ok");
    }
}

