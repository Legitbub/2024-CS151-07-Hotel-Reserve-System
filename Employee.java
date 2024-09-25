public class Employee {
    private int id;
    private String name;
    private double hoursWorked = 0;
    private String position;
    private double wage;

    public Employee() {

    }

    public Employee(int id, String name, String position, double wage) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.wage = wage;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public int getId() {
        return id;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
