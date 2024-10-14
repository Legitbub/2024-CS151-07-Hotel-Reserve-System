public class Employee {
    private String ID;
    private String name;
    private double hoursWorked = 0;
    private String position;
    private double wage;
    private EmployeePayment emplPayment;
    

    public Employee(String ID) {
        this.ID = ID;
    }
    
    public Employee(String ID, String name, String position, double wage) {
        this.ID = ID;
        this.name = name;
        this.position = position;
        this.wage = wage;
        //Create an EmployeePayment for each Employee
        this.emplPayment = new EmployeePayment(this);
    }
    
    public EmployeePayment getPayment(){
        return emplPayment;
    }
    //Employee can log hours, which will call and update the logHour method from EmployeePayment class
    public void logHours(double hours){
        if(hours > 0)
        {
            this.hoursWorked += hours;
            this.emplPayment.logHours(hours); 
        }
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

    public void modifyRoom(Room r) {
        r.guest.checkout();
    }

    public void modifyRoom(Room r, Guest guest, Hotel h) {
        r.guest.checkout();
        h.reservation(r, guest);
    }

    public void modifyAmenity(Amenity a, Guest g) {
        a.cancel(g);
    }

    public void modifyAmenity(Amenity a, Guest cancelled, Guest replacement, Hotel h) {
        a.cancel(cancelled);
        a.reserve(replacement);
    }

    public void displayEmployeeDetails() {
        System.out.println("Name: %s , ID: %s, Position: %s, ");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return ID.equals(((Employee) obj).ID);
    }
}
