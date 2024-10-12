import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee{

    private Hotel hotel;
    private List<Employee> employeeList;
    public Manager(String ID) {
        super(ID);
    }

    public Manager(String ID, String name, String position, double wage, List<Employee> employeeList) {
        super(ID, name, position, wage);
        //Manager will have access to employeeList
        this.employeeList = employeeList;
    }

    //Fire an employee and remove from the list of employee;
    public void fireEmployee(String employeeID){
        if(hotel != null){
            //removeIf will prevent CoccurentModification Error
            boolean employeeFound = hotel.getEmployeeList().removeIf(employee -> employee.getId().equals(employeeID));
            
            if(!employeeFound){
                System.out.println("There is no employee with the provided ID in the system");
            }
            else{
                System.out.printf("%s has been fired!", employeeID);

            }
        }
        else{
            System.out.println("Hotel is not initialized");
        }
    }

    public void setWage(){

    }
    //Change wages + hours;
    public void modifyEmployeeDetails(String employeeID){

    }
    //Get a general date and pay all
    public void payAll(){
        for(Employee employee : hotel.getEmployeeList()){
            EmployeePayment employeePayment = employee.getPayment();
            double totalPaymentAfterTax = employeePayment.calculateTaxes();

            employeePayment.processPayment(totalPaymentAfterTax);
            //reset hour back to 0
            employee.setHoursWorked(0);
            System.out.println("Payment of $" + totalPaymentAfterTax + " made to " + employee.getName());

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
