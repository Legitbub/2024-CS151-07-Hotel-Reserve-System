import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager extends Employee {
    public Manager(String ID) {
        super(ID);
    }

    public Manager(String ID, String name, String position, double wage) {
        super(ID, name, position, wage);
    }

    // Fire an employee and remove from the list of employees;
    public void fireEmployee(Scanner input, Hotel h){
        System.out.println("Select an employee to crush the dreams " +
            "of (enter the number in the list, or 0 to show mercy): ");
        List<Employee> l = h.getEmployeeList();
        for (int i = 0; i < l.size(); i++) {
            System.out.println((i + 1) + ". " + l.get(i).getName() +
                    " (" + l.get(i).getId() + ")");
        }
        int entered = input.nextInt() - 1;
        if (entered >= 0 && entered < l.size()) {
            Employee dead = l.get(entered);
            if (dead.getPosition().equalsIgnoreCase("Manager")) {
                System.out.println("Managers cannot be fired at this time.");
            } else {
                h.getEmployeeList().remove(dead);
                System.out.printf("Employee %s has been fired!", dead.getId());
            }
        } else {
            System.out.println("Everyone's job is safe...for now...");
        }
    }   

    public void setEmployeeStats(Employee e, double hours, double wage) {
        e.getPayment().modifyhoursWorked(hours);
        e.getPayment().modifyHourlyRate(wage);
    }

    // Handle the edits to employees and hotel features;
    public void managerModify(Scanner input, Hotel h) {
        int entered;
        do {
            System.out.println("Select an option, boss:" +
                    "\n1) Change hours/wages\n2) Pay employees" +
                    "\n3) Fire employee\n4) Review hotel earnings");
            entered = input.nextInt();
            if (entered > 3 || entered < 1) {
                System.out.println("Enter a number from the list of options.");
            }
        } while (entered > 3 || entered < 1);

        switch (entered) {
            case 1:
                System.out.println("Select an employee from the list " +
                        "(enter the number in the list, or 0 to cancel):");
                List<Employee> l = h.getEmployeeList();
                for (int i = 1; i <= l.size(); i++) {
                    System.out.println(i + ". " + l.get(i).getName() +
                            " (" + l.get(i).getId() + ")");
                }
                entered = input.nextInt();
                if (entered > 0) {
                    Employee e = l.get(entered);
                    System.out.println("Current logged hours: " +
                            e.getPayment().getHoursWorked() + "\nCurrent" +
                            " wage: $" + e.getPayment().getHourlyRate());
                    System.out.println("Enter new logged hours amount: ");
                    double hours = input.nextDouble();
                    System.out.println("Enter new wage: $");
                    double wage = input.nextDouble();
                    setEmployeeStats(e, hours, wage);
                }
                break;
            case 2:
                payAll(h);
                break;
            case 3:
                fireEmployee(input, h);
                break;
            case 4:
                generateFinancialReport(h);
                break;
            default:
        }
    }
    // Get a general date and pay all
    public void payAll(Hotel h) {
        for(Employee employee : h.getEmployeeList()) {
            EmployeePayment employeePayment = employee.getPayment();

            //reset hour back to 0
            employeePayment.processPayment(h);
            System.out.println("made to " + employee.getName());
        }
    }
    // Generate a financial report of all the earnings made.
    public void generateFinancialReport(Hotel h) {
        System.out.println("Hotel " + h.getName() + " has made $" +
                h.getRevenue() + " in total profit.");
    }

    // Manager can update pricing of the room if needed.
    public void updateRoomPricing(Room room, double price){
        if(price <= 0.0){
            System.out.println("Price of room cannot be negative!");
        }
        room.setPrice(price);
    }
    
}
