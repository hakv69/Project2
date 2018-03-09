package project2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.YEARS;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class EmployeeStatistics {

    static void menu(ArrayList<Employee> employees) throws ParseException {
        boolean loop = true;
        do {
            Methods.printMenuTitle("Employee Statistics");
            int menuChoise = Methods.menuGetChoise(new String[]{"Back to main menu", "Gender Statistics", "Age Statistics", "Salary Statistics", "Bonus Statistics"});
            switch (menuChoise) {
                case 1: //Gender Statistics
                    EmployeeStatistics.genderStatistics(employees);
                    break;

                case 2: //Age Statistics
                    EmployeeStatistics.ageStatistics(employees);
                    break;

                case 3: //Salary Statistics
                    EmployeeStatistics.salaryStatistics(employees);
                    break;

                case 4: //Bonus Statistics
                    EmployeeStatistics.bonusStatistics(employees);
                    break;

                case 0: //Back to main menu
                    loop = false;
                    break;
            }
        } while (loop);
    }

    static void genderStatistics(ArrayList<Employee> employees) {
        System.out.println("");
        System.out.println("-----------------------------------------");
        System.out.println("Gender Statistics");
        System.out.println("-----------------------------------------");

        int noOfMale = 0;
        int noOfFemale = 0;
        int noOfUnknown = 0;
        int noOfEmployees = 0;

        for (Employee empl : employees) {

            //%MALE, %FEMALE, %UNKNOWN
            if (empl.getGender() == GenderType.MALE) {
                noOfMale++;
            } else if (empl.getGender() == GenderType.FEMALE) {
                noOfFemale++;
            } else {
                noOfUnknown++;
            }
        }
        if (employees.size() > 0) {
            double malePercent = noOfMale / (double) employees.size() * 100;
            double femalePercent = noOfFemale / (double) employees.size() * 100;
            double unknownPercent = noOfUnknown / (double) employees.size() * 100;

            System.out.println("Number of employees");
            System.out.println("   Total: " + employees.size());
            System.out.printf("     Men: %d (%.1f%s)\n", noOfMale, (double) noOfMale / employees.size() * 100, "%");
            System.out.printf("   Women: %d (%.1f%s)\n", noOfFemale, (double) noOfFemale / employees.size() * 100, "%");
            if (noOfUnknown > 0) {
                System.out.printf(" Unknown: %d (%.1f%s)\n", noOfUnknown, (double) noOfUnknown / employees.size() * 100, "%");
            }

        } else {
            System.out.println("Sorry. No employees found.");
        }

    }

    static void ageStatistics(ArrayList<Employee> employees) throws ParseException {
        System.out.println("");
        System.out.println("-----------------------------------------");
        System.out.println("Age Statistics");
        System.out.println("-----------------------------------------");

        List<Date> datee = new ArrayList<Date>();
        Date date;

        String avrageAge = null;
        String medianAge = null;
        String youngestEmpolyee = null;
        String OldestEmployee = null;
        long datesum = 0;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (Employee employee : employees) {

            date = formatter.parse(employee.getBirthday());
            datee.add(date);
        }
        Collections.sort(datee);
        int size = datee.size() - 1;

        String youngest = formatter.format(datee.get(size));
        String oldest = formatter.format(datee.get(0));
        String median = formatter.format(datee.get(((int) size / 2)));
        System.out.print("Youngest  : " + youngest + "   age : ");
        ageCal(youngest);
        System.out.println("");
        System.out.print("Oldest    : " + oldest + "   age : ");
        ageCal(oldest);
        System.out.println("");
        System.out.print("Median    : " + median + "   age : ");
        ageCal(median);
        System.out.println("");

        for (Date date1 : datee) {
            datesum += date1.getTime();
        }

        Date averageDate = new Date(datesum / datee.size());
        String average = formatter.format(averageDate);
        System.out.print("Average   : " + average + "   age : ");
        ageCal(average);

    }

    public static void ageCal(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthday = LocalDate.parse(date, formatter);
        calculateAge(birthday);

    }

    public static long calculateAge(LocalDate birthdate) {

        LocalDate now = LocalDate.now();
        long age = YEARS.between(birthdate, now);
        System.out.println(age);
        return age;

    }

    static void salaryStatistics(ArrayList<Employee> employees) {
        System.out.println("");
        System.out.println("-----------------------------------------");
        System.out.println("Salary Statistics");
        System.out.println("-----------------------------------------");
        if (employees.isEmpty()) {
            System.out.println("Sorry. No employees"); //No employees. Quit
        } else {
            double totalSalary = 0;
            double totalSalaryMales = 0;
            double totalSalaryFemales = 0;
            double totalSalaryUnknownGender = 0;

            double maxSalary = employees.get(0).getSalary(); //Set initial values
            double minSalary = employees.get(0).getSalary();

            int noOfMale = 0;
            int noOfFemale = 0;
            int noOfUnknownGender = 0;

            for (Employee empl : employees) {
                totalSalary += empl.getSalary();

                if (maxSalary < empl.getSalary()) { //Find max salary
                    maxSalary = empl.getSalary();
                }
                if (minSalary > empl.getSalary()) { //Find min salary
                    minSalary = empl.getSalary();

                }

                if (empl.getGender() == GenderType.MALE) {
                    noOfMale++;
                    totalSalaryMales += empl.getSalary();
                } else if (empl.getGender() == GenderType.FEMALE) {
                    noOfFemale++;
                    totalSalaryFemales += empl.getSalary();
                } else {
                    noOfUnknownGender++;
                    totalSalaryUnknownGender += empl.getSalary();
                }
            }
            //Print Statistics
            System.out.printf("%25s : %-10.2f\n", "Max salary", maxSalary);
            System.out.printf("%25s : %-10.2f\n", "Min salary", minSalary);
            System.out.printf("%25s : %-10.2f\n", ("Average salary All (" + employees.size() + ")"), (totalSalary / employees.size()));
            if (noOfMale > 0) {
                System.out.printf("%25s : %-10.2f\n", ("Men (" + noOfMale + ")"), (totalSalaryMales / noOfMale));
            }
            if (noOfFemale > 0) {
                System.out.printf("%25s : %-10.2f\n", ("Women (" + noOfFemale + ")"), (totalSalaryFemales / noOfFemale));
            }
            if (noOfUnknownGender > 0) {
                System.out.printf("%25s (%3d): %-10.2f\n", "Unknown", noOfUnknownGender, (totalSalaryUnknownGender / noOfUnknownGender));
            }
            System.out.println("-----------------------------------------");

            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    static void bonusStatistics(ArrayList<Employee> employees) {
        System.out.println("");
        System.out.println("-----------------------------------------");
        System.out.println("Bonus Statistics");
        System.out.println("-----------------------------------------");

        double companyTotalBonus = 0;
        double companyTotalSalary = 0;
        int noOfEmployees = 0;

        double maleTotalBonus = 0;
        double maleTotalSalary = 0;
        double maxBonusMales = 0;
        double minBonusMales = 0;
        int noOfMales = 0;

        double femaleTotalBonus = 0;
        double femaleTotalSalary = 0;
        int noOfFemales = 0;
        double maxBonusFemales = 0;
        double minBonusFemales = 0;

        double unknownGenderTotalBonus = 0;
        double unknownGenderTotalSalary = 0;
        int noOfUnknownGender = 0;
        double maxBonusUnknownGender = 0;
        double minBonusUnknownGender = 0;

        for (Employee empl : employees) {

            double emplBonus = empl.calcBonus();
            double emplSalary = empl.getSalary();

            companyTotalBonus += emplBonus;
            companyTotalSalary += empl.getSalary();
            noOfEmployees++;

            if (empl.getGender() == GenderType.MALE) {
                if (noOfMales == 0) {
                    maxBonusMales = emplBonus;
                    minBonusMales = emplBonus;
                }
                noOfMales++;
                maleTotalBonus += emplBonus;
                maleTotalSalary += emplSalary;
                if (maxBonusMales < emplBonus) {
                    maxBonusMales = emplBonus;
                }
                if (minBonusMales > emplBonus) {
                    minBonusMales = emplBonus;
                }

            } else if (empl.getGender() == GenderType.FEMALE) {
                if (noOfFemales == 0) {
                    maxBonusFemales = emplBonus;
                    minBonusFemales = emplBonus;
                }
                noOfFemales++;
                femaleTotalBonus += emplBonus;
                femaleTotalSalary += emplSalary;
                if (maxBonusFemales < emplBonus) {
                    maxBonusFemales = emplBonus;
                }
                if (minBonusFemales > emplBonus) {
                    minBonusFemales = emplBonus;
                }

            } else {
                if (noOfUnknownGender == 0) {
                    maxBonusUnknownGender = emplBonus;
                    minBonusUnknownGender = emplBonus;
                }
                noOfUnknownGender++;
                unknownGenderTotalBonus += emplBonus;
                unknownGenderTotalSalary += emplSalary;
                if (maxBonusUnknownGender < emplBonus) {
                    maxBonusUnknownGender = emplBonus;
                }
                if (minBonusUnknownGender > emplBonus) {
                    minBonusUnknownGender = emplBonus;
                }
            }
        }
        System.out.println("Company");
        System.out.println("-------");
        if (noOfEmployees > 0) {
            System.out.printf("   Total bonus: %.0f\n", companyTotalBonus);
            System.out.printf("   Average bonus: %.0f (%.1f%s)\n", companyTotalBonus / noOfEmployees, companyTotalBonus / companyTotalSalary / 12 * 100, "%");
        } else {
            System.out.println("No employees found.");
        }

        System.out.println("Gender");
        System.out.println("------");
        System.out.println("Men");
        if (noOfMales > 0) {
            System.out.printf("   Average bonus: %6.0f (%.1f%s)\n", (maleTotalBonus / noOfMales), maleTotalBonus / maleTotalSalary / 12 * 100, "%");
            System.out.printf("   Max bonus: %.0f\n", maxBonusMales);
            System.out.printf("   Min bonus: %.0f\n", minBonusMales);
        } else {
            System.out.println("No Males.");
        }

        System.out.println("Women");
        if (noOfFemales > 0) {
            System.out.printf("   Average bonus: %6.0f (%.1f%s)\n", (femaleTotalBonus / noOfFemales), femaleTotalBonus / femaleTotalSalary / 12 * 100, "%");
            System.out.printf("   Max bonus: %.0f\n", maxBonusFemales);
            System.out.printf("   Min bonus: %.0f\n", minBonusFemales);
        } else {
            System.out.println("No Females.");
        }

        if (noOfUnknownGender > 0) {
            System.out.println("Unknown Gender");
            System.out.printf("   Average bonus: %6.0f (%.1f%s)\n", (unknownGenderTotalBonus / noOfUnknownGender), unknownGenderTotalBonus / unknownGenderTotalSalary / 12 * 100, "%");
            System.out.printf("   Max bonus: %.0f\n", maxBonusUnknownGender);
            System.out.printf("   Min bonus: %.0f\n", minBonusUnknownGender);
        }

        //Calculate Bonus Statistics per branch
        double secrTotalSalary = 0;
        double secrTotalBonus = 0;
        double secrMaxBonus = 0;
        double secrMinBonus = 0;
        int noOfSecretaries = 0;

        double techTotalSalary = 0;
        double techTotalBonus = 0;
        double techMaxBonus = 0;
        double techMinBonus = 0;
        int noOfTechnicians = 0;

        double progrTotalSalary = 0;
        double progrTotalBonus = 0;
        double progrMaxBonus = 0;
        double progrMinBonus = 0;
        int noOfProgrammers = 0;

        for (Employee empl : employees) {

            double emplBonus = empl.calcBonus();
            double emplSalary = empl.getSalary();

            if (empl instanceof Secretary) {
                if (noOfSecretaries == 0) {
                    secrMaxBonus = emplBonus;
                    secrMinBonus = emplBonus;
                }
                noOfSecretaries++;
                secrTotalSalary += emplSalary;
                secrTotalBonus += emplBonus;
                if (secrMaxBonus < emplBonus) {
                    secrMaxBonus = emplBonus;
                }
                if (secrMinBonus > emplBonus) {
                    secrMinBonus = emplBonus;
                }
            } else if (empl instanceof Technician) {
                if (noOfTechnicians == 0) {
                    techMaxBonus = emplBonus;
                    techMinBonus = emplBonus;
                }
                noOfTechnicians++;

                techTotalSalary += emplSalary;
                techTotalBonus += emplBonus;
                if (techMaxBonus < emplBonus) {
                    techMaxBonus = emplBonus;
                }
                if (techMinBonus > emplBonus) {
                    techMinBonus = emplBonus;
                }
            } else if (empl instanceof Programmer) {
                if (noOfProgrammers == 0) {
                    progrMaxBonus = emplBonus;
                    progrMinBonus = emplBonus;
                }
                noOfProgrammers++;

                progrTotalSalary += emplSalary;
                progrTotalBonus += emplBonus;
                if (progrMaxBonus < emplBonus) {
                    progrMaxBonus = emplBonus;
                }
                if (progrMinBonus > emplBonus) {
                    progrMinBonus = emplBonus;
                }
            }
        }

        // Print Bonus Statistics per branch
        System.out.println("Branch");
        System.out.println("------");
        if (noOfSecretaries > 0) {
            System.out.println("Secretary");
            System.out.printf("   Average bonus: %.0f (%.1f%s)\n", secrTotalBonus / noOfSecretaries, secrTotalBonus / secrTotalSalary / 12 * 100, "%");
            System.out.printf("   Max bonus: %.0f\n", secrMaxBonus);
            System.out.printf("   Min bonus: %.0f\n", secrMinBonus);
        }
        if (noOfTechnicians > 0) {
            System.out.println("Technician");
            System.out.printf("   Average bonus: %.0f (%.1f%s)\n", techTotalBonus / noOfTechnicians, techTotalBonus / techTotalSalary / 12 * 100, "%");
            System.out.printf("   Max bonus: %.0f\n", techMaxBonus);
            System.out.printf("   Min bonus: %.0f\n", techMinBonus);
        }
        if (noOfProgrammers > 0) {
            System.out.println("Programmer");
            System.out.printf("   Average bonus: %.0f (%.1f%s)\n", progrTotalBonus / noOfProgrammers, progrTotalBonus / progrTotalSalary / 12 * 100, "%");
            System.out.printf("   Max bonus: %.0f\n", progrMaxBonus);
            System.out.printf("   Min bonus: %.0f\n", progrMinBonus);
        }

    }

}
