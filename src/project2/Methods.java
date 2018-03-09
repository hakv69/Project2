package project2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import static project2.Main.employees;

class Methods {

    public static Scanner sc = new Scanner(System.in);

    public static String enterDate() throws ParseException {
        String date;

        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("(YYYY-MM-DD) : ");
            date = sc.nextLine();
        } while ((!date.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) || (!isDateValid(date)));

        return date;

    }

    private static boolean isDateValid(String date) {
        try {
            DateFormat dates = new SimpleDateFormat("yyyy-MM-dd");
            dates.setLenient(false);
            dates.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }



    public static String[] enterStrings() {
        String[] strArr = new String[10];
        int i = 0;
        boolean quit = false;
        do {
            System.out.print(": ");
            String tmpStr = sc.nextLine();
            if (tmpStr.isEmpty()) {
                return strArr;
            }
            strArr[i] = tmpStr;
            i++;
        } while (!quit);
        return null;
    }

    public static void printMenuTitle(String menuTitle){
        System.out.println("");
        System.out.println("=========================================");
        System.out.println(menuTitle);
        System.out.println("=========================================");
    }
    public static int menuGetChoise(String[] args) {

        for (int i = 1; i < args.length; i++) {
            System.out.println(i + ". " + args[i]);
        }
        System.out.println("0. " + args[0]); //Print option 0 last

        int answer = 0;
        boolean validChoise = false;
        boolean validInt = false;
        do {
            System.out.print("Please enter your choise: ");
            validInt = false;
            do {
                try {
                    answer = sc.nextInt();
                    sc.nextLine();
                    validInt = true;
                } catch (Exception e) {
                    System.out.println("Not a valid entry!");
                    System.out.print("Please enter your choise: ");
                    sc.nextLine(); //Clear buffer
                }

            } while (!validInt);
            
            for (int i = 0; i < args.length; i++) {
                if (answer == i) {
                    validChoise = true;
                }
            }

        } while (!validChoise);

        return answer;
    }

    public static boolean yesNoQuestion(String question) {
        String answer;
        System.out.print(question);
        boolean loop = true;
        do {
            answer = sc.nextLine();
            if (!answer.matches("[ynYN]")) {
                System.out.println("Not a valid entry!");
            } else {
                loop = false;
            }
        } while (loop);

        if (answer.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }
    }

    public static Employee searchName(ArrayList<Employee> employees) {
        System.out.print("Please enter name to find: ");
        String searcString = sc.nextLine();
        if(searcString.equals("*")) searcString = " "; //Search all employees
        int numberOfHits = 0;
        ArrayList<Employee> tmpEmployees = new ArrayList<>();
        for (Employee empl : employees) {
            String fullName = empl.getFirstName() + " " + empl.getLastName();
            if (fullName.toLowerCase().contains(searcString.toLowerCase())) {
                numberOfHits++;
                System.out.println(empl.getEmplId() + " " + fullName);
                tmpEmployees.add(empl); //Store all empl that match
            }
        }
        if (numberOfHits > 0) {

            System.out.print("Choose an ID from the list (0 to skip): ");
            int selectedId = 0;
            boolean loop2 = true;
            do {
                try {
                    selectedId = sc.nextInt();
                    //sc.nextLine();
                    loop2 = false;
                } catch (Exception e) {
                    System.out.println("Not a valid entry!");
                    System.out.print("Please enter your choise: ");
                    //sc.nextLine(); //Clear buffer
                }
                sc.nextLine(); //Clear buffer
            } while (loop2);

            if(selectedId==0) return null;
            
            for (Employee empl : tmpEmployees) {
                if (empl.getEmplId() == selectedId) {
                    return empl;
                }
            }
            System.out.println("Selected Id not found!");

            return null;
        } else {
            System.out.println("Could not find \"" + searcString + "\"!");
            return null;
        }

    }

    static Employee searchID(ArrayList<Employee> employees) {
            int selectedId = 0;
            boolean loop = true;
            do {
                System.out.print("Please enter ID (0 to skip): ");
                try {
                    selectedId = sc.nextInt();
                    loop = false;
                } catch (Exception e) {
                    System.out.println("Unvalid entry.");
                }
                sc.nextLine();
            } while (loop);
            if(selectedId==0) return null;
            for (Employee employee : employees) {
                if(employee.getEmplId()==selectedId){
                    return employee;
                }
            }
            System.out.println("ID " + selectedId + " was not found.");
            return null;
    }

    
    
    public static void createDB() {
        //Add Secretaries
        Secretary secr1 = new Secretary("Elsa", "Smith", "1980-02-25", "F", 30000, new String[]{"English", "Swedish"});
        Secretary secr2 = new Secretary("Ella", "Svensson", "1983-08-05", "F", 27000, new String[]{"English", "Spanish"});
        Secretary secr3 = new Secretary("Michael", "LeBlanc", "1980-09-15", "M", 29500, new String[]{"French", "Swedish"});
        employees.add(secr1);
        employees.add(secr2);
        employees.add(secr3);

        //Add Programmers
        Programmer progr1 = new Programmer("Peter", "White", "1980-02-15", "M", 49000, new String[]{"Java SE","Java EE","C++"});
        Programmer progr2 = new Programmer("Olivia", "White","1981-02-05", "F", 64000, new String[]{"Java SE", "Java EE", "C++"});
        Programmer progr3 = new Programmer("Sven", "Persson","1982-02-05", "M", 38000, new String[]{"Java SE"});
        Programmer progr4 = new Programmer("Constantin", "Opel", "1983-02-05", "M", 49000, new String[]{"Pascal", "Basic", "C"});
        Programmer progr5 = new Programmer("Georgina", "Brown","1984-02-05", "F", 56000, new String[]{"C++", "C", "Java SE"});
        Programmer progr6 = new Programmer("Henry", "Black", "1985-12-05", "M", 51000, new String[]{"Java SE", "Java EE", "C++"});
        Programmer progr7 = new Programmer("Harryson", "Ford","1986-02-05", "M", 46500, new String[]{"Java SE", "Java EE"});
        Programmer progr8 = new Programmer("Stephen", "King","1987-02-05", "M", 61000, new String[]{"Java SE", "Java EE", "C++"});
        Programmer progr9 = new Programmer("Stephanie", "Evans","1988-02-05", "F", 63000, new String[]{"Java SE", "Java EE", "C++"});
        employees.add(progr1);
        employees.add(progr2);
        employees.add(progr3);
        employees.add(progr4);
        employees.add(progr5);
        employees.add(progr6);
        employees.add(progr7);
        employees.add(progr8);
        employees.add(progr9);

        Technician tech1 = new Technician("Susan", "Baker","1970-02-05", "F", 49000, new String[]{"DB Backup", "Windows Server", "Windows PC"});
        Technician tech2 = new Technician("Oliver", "Twist","1960-02-05", "M", 49000, new String[]{"Windows Server", "Windows PC"});
        Technician tech3 = new Technician("Sam", "De Geer","1966-02-05", "F", 49000, new String[]{"Windows Server", "Windows PC"});
        Technician tech4 = new Technician("Lars", "Baker","1955-02-05", "M", 32000, new String[]{"DB Backup", "Linux Server", "Windows PC"});
        Technician tech5 = new Technician("Mohammad", "Ali","1977-02-05", "M", 39000, new String[]{"Sun Solaris", "Linux PC"});
        Technician tech6 = new Technician("Eva", "Burman","1989-02-05", "F", 36000, new String[]{"Windows Server", "Telephony"});
        employees.add(tech1);
        employees.add(tech2);
        employees.add(tech3);
        employees.add(tech4);
        employees.add(tech5);
        employees.add(tech6);

    }

    static String selectRole() {
        String role = null;
                    int whatToDisplay = Methods.menuGetChoise(new String[]{"Back", "All", "Secretaries", "Technicians", "Programmers"});
            switch (whatToDisplay) {
                case 0:
                    //Back
                    break;
                case 1:
                    role = "All";
                    break;
                case 2:
                    role = "Secretary";
                    break;
                case 3:
                    role = "Technician";
                    break;
                case 4:
                    role = "Programmer";
                    break;
            }
        return role;
    }


}


