package project2;

public class Secretary extends Employee {

    private final static double BONUSRATE = 0.05;
    private String[] language;  //english  swedish  spanish

    public Secretary(String firstName, String lastName, String birthday, String gender, double salary, String[] language) {
        super(firstName, lastName, birthday, gender, salary, BONUSRATE);
        this.language = language;
    }

    public String[] getLanguage() {
        return language;
    }

    public void setLanguage(String[] language) {
        this.language = language;
    }
    
    @Override
    public String toString() {
        return getEmplId() + " " + getFirstName() + " " + getLastName() + " Secretary " + getSalary();
    }

    @Override
    public double calcBonus() {
        return (getBonus() + getSalary() * 12 * getBonusRate() + 5000)/2;
    }

    @Override
    public void printAll() {
        System.out.println("Secretary");
        super.printAll();
        System.out.printf("     Bonus : %.0f\n", calcBonus());
          System.out.print("   Language: ");
        for (String lang : language) {
            if(lang!=null)System.out.print(lang + ", ");
        }
        System.out.println();
    }

    @Override
    public void printList() {
        super.printList();
        System.out.printf("%-10s %7.0f ","Secretary", calcBonus());
        for (String lang : language) {
            if(lang!=null) System.out.print(lang + ", ");
        }
        System.out.println("");
    }
}
