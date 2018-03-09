package project2;

public class Programmer extends Employee {
    
    private final static double BONUSRATE = 0.2;
    private String[] programmingLanguage;

    public Programmer(String firstName, String lastName, String birthday, String gender, double salary, String[] programmingLanguage) {
        super(firstName, lastName, birthday, gender, salary, BONUSRATE);
        this.programmingLanguage = programmingLanguage;
    }

    public void setProgrammingLanguage(String[] programmingLanguage) {
        this.setProgrammingLanguage(programmingLanguage);
    }

    public String[] getProgrammingLanguage() {
        return programmingLanguage;
    }

    @Override
    public String toString() {
        return getEmplId() + " " + getFirstName() + " " + getLastName() + " Programmer " + getSalary();
    }

    @Override
    public double calcBonus() {
        return getBonus() + getSalary() * 12 * getBonusRate() +500;
    }

    @Override
    public void printAll() {
        System.out.println("Programmer");
        super.printAll();
        System.out.printf("     Bonus : %.0f\n", calcBonus());
        System.out.print("Programming language: ");
        for (String progrLang : programmingLanguage) {
            if(progrLang!=null) System.out.print(progrLang + ", ");
        }
        System.out.println();
    }

    @Override
    public void printList() {
        super.printList();
        System.out.printf("%-10s %7.0f ","Programmer", calcBonus());
        for (String progrLang : programmingLanguage) {
            if(progrLang!=null) System.out.print(progrLang + ", ");
        }
        System.out.println("");
    }

}
