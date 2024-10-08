public class Manager extends Employee{
    public Manager(String ID) {
        super(ID);
    }

    public Manager(String ID, String name, String position, double wage) {
        super(ID, name, position, wage);
    }

    //Assign an Employee to a Hotel
    public void assignEmployee(Employee employee, Hotel assignHotel){

    }
    //Fire an employee and remove from the list of employee based on their ID.
    public void fireEmployee(int employeeID){

    }
    //Generate a financial report of all the earnings made.
    public void generateFinancialReport(){

    }
    //Not sure if this should be added or not
    /* 
    public void promoteEmployee(int employeeID){

    }
    */
    public void updateRoomPricing(Room room, double price){

    }
    
}
