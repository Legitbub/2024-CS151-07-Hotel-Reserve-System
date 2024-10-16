import java.util.ArrayList;
import java.util.InputMismatchException;
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
            System.out.println(i + ". " + l.get(i - 1).getName() +
                    " (" + l.get(i - 1).getId() + ")");
        }
        boolean correct = false;
        int entered = 0;
        while (!correct) {
            try {
                entered = input.nextInt();
                if (entered > 0) {
                    Employee dead = l.get(entered - 1);
                    if (dead.getPosition().equalsIgnoreCase("Manager")) {
                        System.out.println("Managers cannot be fired at this time.");
                    } else {
                        h.getEmployeeList().remove(dead);
                        System.out.printf("Employee %s has been fired!\n", dead.getId());
                    }
                } else {
                    System.out.print("Everyone's job is safe...for now...");
                }
                correct = true;
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                System.out.print("Invalid input. The souls of the employees " +
                        "are just waiting to be destroyed...\nSelect an " +
                        "employee to crush the dreams of (enter the number " +
                        "in the list, or 0 to show mercy): ");
                input.nextLine();
            }
        }
        input.nextLine();
    }

    public void setEmployeeStats(Employee e, double hours, double wage) {
        e.getPayment().modifyhoursWorked(hours);
        e.getPayment().modifyHourlyRate(wage);
        System.out.println("Successfully modified " + e.getName() + "'s details.");
    }

    // Handle the edits to employees and hotel features;
    public void managerModify(Scanner input, Hotel h) {
        int entered = 0;
        do {
            System.out.println("Select an option, boss:" +
                    "\n1) Change hours/wages\n2) Pay employees" +
                    "\n3) Fire employee\n4) Review hotel earnings");
            boolean correct = false;
            while (!correct) {
                try {
                    entered = input.nextInt();
                    correct = true;
                } catch (InputMismatchException e) {
                    System.out.print("Invalid entry. Try again: ");
                    input.nextLine();
                }
            }
            if (entered > 4 || entered < 1) {
                System.out.println("Enter a number from the list of options.");
            }
        } while (entered > 4 || entered < 1);

        switch (entered) {
            case 1:
                System.out.println("Select an employee from the list " +
                        "(enter the number in the list, or 0 to cancel):");
                List<Employee> l = h.getEmployeeList();
                for (int i = 1; i <= l.size(); i++) {
                    System.out.println(i + ". " + l.get(i - 1).getName() +
                            " (" + l.get(i - 1).getId() + ")");
                }
                boolean correct = false;
                Employee em = null;
                double hours = 0;
                double wage = 0;
                while (!correct) {
                    try {
                        entered = input.nextInt();
                        correct = true;
                        em = l.get(entered - 1);
                        System.out.printf("Current logged hours: " +
                                em.getPayment().getHoursWorked() + "\nCurrent" +
                                " wage: $%.2f\n", em.getPayment().getHourlyRate());
                    } catch (InputMismatchException | IndexOutOfBoundsException e) {
                        System.out.print("Invalid input. Try again: ");
                        input.nextLine();
                        correct = false;
                    }
                }
                correct = false;
                while (!correct) {
                    try {
                        System.out.print("Enter new logged hours amount: ");
                        hours = input.nextDouble();
                        correct = true;
                    } catch (InputMismatchException e) {
                        System.out.print("Invalid input. Try again: ");
                        input.nextLine();
                    }
                }
                correct = false;
                while (!correct) {
                    try {
                        System.out.print("Enter new wage: $");
                        wage = input.nextDouble();
                        correct = true;
                    } catch (InputMismatchException e) {
                        System.out.print("Invalid input. Try again: ");
                        input.nextLine();
                    }
                }
                setEmployeeStats(em, hours, wage);
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
        input.nextLine();
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
        System.out.printf("Hotel " + h.getName() + " has made $%.2f" +
                " in total profit.\n", h.getRevenue());
    }

    public void viewOccupantHistory(Room room) {
        System.out.println("Viewing occupant history for Room ID: " + room.getRoomID());
        room.displayOccupantHistory();
    }

    // Manager can update pricing of the room if needed.
    public void updateRoomPricing(Room room, double price){
        if(price <= 0.0){
            System.out.println("Price of room cannot be negative!");
        }
        room.setPrice(price);
    }
    
}
