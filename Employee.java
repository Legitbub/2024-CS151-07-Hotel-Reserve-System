public class Employee {
    private String ID;
    private String name;
    private double hoursWorked = 0;
    private String position;
    private double wage;
    private Hotel assignHotel;
    

    public Employee() {

    }

    public Employee(String ID, String name, String position, double wage, Hotel assignHotel) {
        this.ID = ID;
        this.name = name;
        this.position = position;
        this.wage = wage;
        //Assign the employee to a Hotel.
        this.assignHotel = assignHotel;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public String getId() {
        return ID;
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
