public class Manager extends Employee{

    private Hotel hotel;
    public Manager(String ID) {
        super(ID);
    }

    public Manager(String ID, String name, String position, double wage) {
        super(ID, name, position, wage);
    }

    //Fire an employee and remove from the list of employee;
    public void fireEmployee(String employeeID){
        if(hotel != null){
            boolean employeeFound = false;

            for(Employee employee : hotel.getEmployeeList()){
                if(employee.getId().equals(employeeID)){
                    hotel.getEmployeeList().remove(employee);
                    System.out.printf("%s has been fired!", employee.getName());
                    employeeFound = true;
                }
            }
            if(!employeeFound){
                System.out.println("There is no employee with the provided ID in the system");
            }
        }
        else{
            System.out.println("Hotel is not initialized");
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
