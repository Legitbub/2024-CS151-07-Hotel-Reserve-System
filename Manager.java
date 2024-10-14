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
        for (int i = 1; i <= l.size(); i++) {
            System.out.println(i + ". " + l.get(i).getName() +
                    " (" + l.get(i).getId() + ")");
        }
        int entered = input.nextInt();
        Employee dead = l.get(entered);
        if (dead.getPosition().equalsIgnoreCase("Manager")) {
            System.out.println("Managers cannot be fired at this time.");
        } else {
            h.getEmployeeList().remove(dead);
            System.out.printf("Employee %s has been fired!", dead.getId());
        }
    }

    public void setWage(){

    }
    // Handle the edits to employees and hotel features;
    public void managerModify(Scanner input, Hotel h) {
        int entered;
        do {
            System.out.println("Select an option, boss:" +
                    "\n1) Change hours/wages\n2) Pay employees" +
                    "\n3) Fire employee");
            entered = input.nextInt();
            if (entered > 3 || entered < 1) {
                System.out.println("Enter a number from the list of options.");
            }
        } while (entered > 3 || entered < 1);

        switch (entered) {
            case 1:

            case 2:
                payAll(h);
                break;
            case 3:
                fireEmployee(input, h);
                break;
            default:
        }
    }
    //Get a general date and pay all
    public void payAll(Hotel h) {
        for(Employee employee : h.getEmployeeList()){
            EmployeePayment employeePayment = employee.getPayment();

            //reset hour back to 0
            employeePayment.processPayment();
            System.out.println("made to " + employee.getName());
            }
    }
    //Generate a financial report of all the earnings made. 
    public void generateFinancialReport(){

    }

    //Manager can update pricing of the room if needed.
    public void updateRoomPricing(Room room, double price){
        room.setPrice(price);
    }
    
}
