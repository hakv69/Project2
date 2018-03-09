package project2;

import java.text.ParseException;
import java.util.ArrayList;

public class Main {

    static ArrayList<Employee> employees = new ArrayList<>();

    public static void main(String[] args) throws ParseException {
        Methods.createDB();

        boolean loop = true;
        do {
            Methods.printMenuTitle("Main menu");
            int mainMenuChoise = Methods.menuGetChoise(new String[]{"Exit", "Employee Management", "Employee Statistics"});
            switch (mainMenuChoise) {
                case 0: //Exit
                    if (Methods.yesNoQuestion("Do you really want to quit? (y/n)")) {
                        loop = false;
                    }
                    break;

                case 1: //Employee Management
                    EmployeeManagement.menu(employees);
                    break;

                case 2: //Employee Statistics
                    EmployeeStatistics.menu(employees);
                    break;
            }
        } while (loop);

        EmployeeManagement.sc.close();
        Methods.sc.close();

    }

}
