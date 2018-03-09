package project2;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeManagement {

    public static Scanner sc = new Scanner(System.in);

    static void menu(ArrayList<Employee> employees) throws ParseException {

        boolean loop = true;
        do {
            Methods.printMenuTitle("Employee Management");
            int menuChoise = Methods.menuGetChoise(new String[]{"Back", "Register Employee",
                "Delete Employee", "Update  Employee", "Search Employee",
                "Display Employees"});
            switch (menuChoise) {
                case 0: //Back to main menu
                    loop = false;
                    break;

                case 1: //Register Employee
                    System.out.println("-----------------------------------------");
                    System.out.println("Register Employee");
                    System.out.println("-----------------------------------------");

                    registerEmployee(employees);
                    break;

                case 2: //Delete Employee
                    System.out.println("-----------------------------------------");
                    System.out.println("Delete Employee");
                    System.out.println("-----------------------------------------");
                    deleteEmployee(employees, Methods.searchName(employees));
                    break;

                case 3: //Update  Employee
                    System.out.println("-----------------------------------------");
                    System.out.println("Update Employee");
                    System.out.println("-----------------------------------------");
                    EmployeeManagement.updateEmployee(employees, Methods.searchName(employees));
                    break;

                case 4: //Search Employee
                    searchEmployee(employees);
                    break;

                case 5: //Display All Employees
                    System.out.println("-----------------------------------------");
                    System.out.println("Display Employees");
                    System.out.println("-----------------------------------------");
                    displayEmployees(employees, Methods.selectRole());
                    break;

            }
        } while (loop);

    }

    static void registerEmployee(ArrayList<Employee> employees) throws ParseException {

        System.out.print("First name : ");
        String firstName = sc.nextLine();

        System.out.print("Last name : ");
        String lastName = sc.nextLine();

        System.out.print("Birthday ");//

        String birthday = Methods.enterDate();

        //sale.setDate(sc.next());
        System.out.print("Gender (M)Male (F)Female (U)Unknown: ");
        boolean cont = true;
        String gender;
        do {
            gender = sc.nextLine();
            if (gender.toLowerCase().matches("[mfu]")) {
                cont = false;
            } else {
                System.out.print("Please enter M,F or U!");
            }
        } while (cont);

        System.out.print("Salary : ");
        double salary = 0;
        boolean sal = true;
        do {
            try {
                salary = sc.nextDouble();
                sc.nextLine();
                sal = false;
            } catch (InputMismatchException e) {
                System.out.println(e + " Not a valid entry!,try agin");

            }
        } while (sal);

        //********* Employee branch***********
        boolean quitMenu = false;
        do {
            System.out.println("What type of employee?");
            int menuChoise = Methods.menuGetChoise(new String[]{"Back", "Secretary", "Programmer", "Technician"});

            switch (menuChoise) {
                case 0: //Back to main menu
                    break;

                case 1: //Secretary
                    System.out.println("Please enter Languages:");
                    String[] language = Methods.enterStrings();
                    Secretary secr = new Secretary(firstName, lastName, birthday, gender, salary, language);
                    employees.add(secr);
                    System.out.println("Added");
                    secr.printAll();
                    quitMenu = true;
                    break;

                case 2: //Programmer
                    System.out.println("Please enter Programming languages:");
                    String[] programmingLanguage = Methods.enterStrings();
                    Programmer progr = new Programmer(firstName, lastName, birthday, gender, salary, programmingLanguage);
                    employees.add(progr);
                    System.out.println("Added");
                    progr.printAll();
                    quitMenu = true;
                    break;

                case 3: //Technician
                    System.out.println("Please enter Technical areas:");
                    String[] technicalArea = Methods.enterStrings();
                    Technician tech = new Technician(firstName, lastName, birthday, gender, salary, technicalArea);
                    employees.add(tech);
                    System.out.println("Added");
                    tech.printAll();
                    quitMenu = true;
                    break;

            }

        } while (!quitMenu);

    }

    public static void deleteEmployee(ArrayList<Employee> employees, Employee empl) {
        if (empl != null) {
            if (Methods.yesNoQuestion("Do you want to delete:  " + empl.toString() + "? (y/n)")) {
                employees.remove(empl);
                System.out.println("Deleted: " + empl.toString());
            } else {
                System.out.println("Not deleted.");
            }
        }
    }

    public static void updateEmployee(ArrayList<Employee> employees, Employee empl) {
        //Employee empl = Methods.searchName(employees);
        if (empl != null) {
            boolean loop = true;
            do {
                System.out.println("-----------------------------------------");
                empl.printAll();
                System.out.println("-----------------------------------------");
                System.out.println("What do you want to update?");
                int update = Methods.menuGetChoise(new String[]{"Back", "First Name ", "Last Name", "Salary", "Birthday", "Bonus Rate"});

                switch (update) {
                    case 0: //Back
                        loop = false;
                        break;

                    case 1: //FirstName
                        updateFirstName(empl);
                        break;

                    case 2: // LastNAme
                        updateLastName(empl);
                        break;

                    case 3: //Salary
                        updateSalary(empl);
                        break;
                    case 4: //Birthday
                        updateBirthday(empl);
                        break;
                    case 5: //BonusRate
                        updateBonusRate(empl);
                        break;
                }
            } while (loop);
        }
    }

    public static void searchEmployee(ArrayList<Employee> employees) {
        System.out.println("-----------------------------------------");
        System.out.println("Search Employee");
        System.out.println("-----------------------------------------");
        Employee empl = null;
        System.out.println("Search Employee");
        int menuChoise = Methods.menuGetChoise(new String[]{"Back", "Search by Name", "Search by ID"});
        boolean loop = true;
        switch (menuChoise) {
            case 0: //Back to main menu
                loop = false;
                break;

            case 1: //Searc by Name
                empl = Methods.searchName(employees);
                break;
            case 2:
                empl = Methods.searchID(employees);
                break;
        }

        if (empl == null) {
            //System.out.println("Sorry. Not found!");
        } else {
            System.out.println("-----------------------------------------");
            empl.printAll();
            System.out.println("-----------------------------------------");
            System.out.println("What do you want to do?");
            int todo = Methods.menuGetChoise(new String[]{"Back", "Update", "Delete"});
            switch (todo) {
                case 0: //Back
                    break;
                case 1: //Edit
                    updateEmployee(employees, empl);
                    break;
                case 2: //Delete
                    deleteEmployee(employees, empl);
                    break;
            }
        }
    }

    static void displayEmployees(ArrayList<Employee> employees, String role) {
//        System.out.println("-----------------------------------------");
//        System.out.println("Display Employees");
//        System.out.println("-----------------------------------------");
        if (role != null) {
            System.out.println("-----------------------------------------");
            System.out.println(role);
            System.out.println("-----------------------------------------");

            System.out.printf("%-3s %-15s %-15s %-10s %-7s %-6s %-10s %7s Work area\n", "ID", "First name", "Last name", "Birthdate", "Gender", "Salary", "Role", "Bonus");
            for (Employee employee : employees) {
                switch (role) {
                    case "All":
                        employee.printList();
                        break;
                    case "Secretary":
                        if (employee instanceof Secretary) {
                            employee.printList();
                        }
                        break;
                    case "Technician":
                        if (employee instanceof Technician) {
                            employee.printList();
                        }
                        break;
                    case "Programmer":
                        if (employee instanceof Programmer) {
                            employee.printList();
                        }
                        break;
                }

            }
        }
    }

    public static void updateFirstName(Employee empl) {
        System.out.println("First name: " + empl.getFirstName());
        System.out.print("Enter new First name: ");
        empl.setFirstName(sc.nextLine());

    }

    public static void updateLastName(Employee empl) {
        System.out.println("Last name: " + empl.getLastName());
        System.out.print("Enter new Last name: ");
        empl.setLastName(sc.nextLine());

    }

    public static void updateSalary(Employee empl) {
        System.out.println("Salary: " + empl.getSalary());
        System.out.print("Enter new Salary: ");
        boolean loop = true;
        do {
            try {
                empl.setSalary(sc.nextDouble());
                //sc.nextLine();
                loop = false;
            } catch (InputMismatchException e) {
                System.out.print("Invalod Entry, try again: ");
            }
            sc.nextLine(); //Clear buffer
        } while (loop);
    }

    private static void updateBirthday(Employee empl) {
        System.out.println("Salary: " + empl.getBirthday());
        System.out.print("Enter new Salary: ");
        try {
            empl.setBirthday(Methods.enterDate());
        } catch (ParseException ex) {
            System.out.println("Not successful!");
        }

    }

    private static void updateBonusRate(Employee empl) {
        System.out.println("Bonus rate: " + empl.getBonusRate());
        System.out.print("Enter new Bonus rate: ");
        boolean loop = true;
        do {
            try {
                empl.setBonusRate(sc.nextDouble());
                //sc.nextLine();
                loop = false;
            } catch (InputMismatchException e) {
                System.out.print("Invalod Entry, try again: ");
            }
            sc.nextLine(); //Clear buffer
        } while (loop);
    }
}
