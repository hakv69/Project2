package project2;

public class Technician extends Employee {

    private final static double BONUSRATE = 0.1;
    private String[] technicalArea;  //telefoni  electricity  plummer

    public Technician(String firstName, String lastName, String birthday, String gender, double salary, String[] technicalArea) {
        super(firstName, lastName, birthday, gender, salary, BONUSRATE);
        this.technicalArea = technicalArea;
    }

    public String[] getTechnicalArea() {
        return technicalArea;
    }

    public void setTechnicalArea(String[] technicalArea) {
        this.technicalArea = technicalArea;
    }
    
    @Override
    public String toString() {
        return getEmplId() + " " + getFirstName() + " " + getLastName() + " Technician " + getSalary();
    }

    @Override
    public double calcBonus() {
        return getBonus() + getSalary() * 12 * getBonusRate();
    }

    @Override
    public void printAll() {
        System.out.println("Technician");
        super.printAll();
        System.out.printf("     Bonus: %.0f\n", calcBonus());
        System.out.print("Technical Area: ");
        for (String techArea : technicalArea) {
            if(techArea!=null) System.out.print(techArea + ", ");
        }
        System.out.println();
    }

    @Override
    public void printList() {
        super.printList();
        System.out.printf("%-10s %7.0f ","Technician", calcBonus() );
        for (String techArea : technicalArea) {
            if(techArea!=null) System.out.print(techArea + ", ");
        }
        System.out.println("");

    }
}
