public class Employee {
    private String ID;
    private String name;
    private String position;
    private EmployeePayment emplPayment;
    

    public Employee(String ID) {
        this.ID = ID;
    }

    public Employee(String ID, String name, String position, double wage) {
        this.ID = ID;
        this.name = name;
        this.position = position;
        emplPayment = new EmployeePayment(wage);
    }

    public EmployeePayment getPayment(){
        return emplPayment;
    }
    //Employee can log hours, which will call and update the logHour method from EmployeePayment class
    public void logHours(double hours){
        emplPayment.logHours(hours);
    }

    public String getId() {
        return ID;
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
        System.out.println(name + ", " + position);
        System.out.printf("Wage: $%.2f", emplPayment.getHourlyRate());
        System.out.printf(", Hours worked: %.2f\n", emplPayment.getHoursWorked());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Employee)) {
            return false;
        }
        return ID.equals(((Employee) obj).ID);
    }
}
