package project2;

public abstract class Employee {
    private static int id;
    private final int emplId;
    private String firstName;
    private String lastName;
    private String birthday;
    private GenderType gender;
    private double salary;
    private double bonus;
    private double bonusRate = 0;

    public Employee(String firstName, String lastName, String birthday, String gender, double salary, double bonusRate) {
        emplId = ++id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        if(gender.equalsIgnoreCase("M")) this.gender = GenderType.MALE;
        if(gender.equalsIgnoreCase("F")) this.gender = GenderType.FEMALE;
        if(gender.equalsIgnoreCase("U")) this.gender = GenderType.UNKOWN;
        this.bonus = 0; //Initial bonus value
        this.salary = salary;
        this.bonusRate = bonusRate;
    }

    public int getEmplId() {
        return emplId;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public GenderType getGender() {
        return gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
    public void setBonusRate(double bonusRate) {
        this.bonusRate = bonusRate;
    }

    public double getBonusRate() {
        return bonusRate;
    }

    public static int getId() {
        return id;
    }
        
    @Override
    public abstract String toString();
    
    public abstract double calcBonus();

    public void printAll(){
        System.out.println("Employee ID: " + emplId);
        System.out.println(" First name: " + firstName);
        System.out.println("  Last name: " + lastName);
        System.out.println("   Birthday: " + birthday);
        System.out.println("     Gender: " + gender);
        System.out.printf("     Salary: %.0f\n", salary);
    }
    
    public void printList(){
        System.out.printf("%3d %-15s %-15s %10s %-7s %6.0f ",emplId,firstName,lastName,birthday,gender.toString(),salary);
    }
    
}
