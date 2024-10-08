public class EmployeePayment extends Payment {

    private double hourlyRate;
    private double hoursWorked;

    public EmployeePayment(double hourlyRate, double hoursWorked) {
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    // Method to log hours worked
    public void logHours(double hours) {
        this.hoursWorked += hours;
    }

    // Method to calculate total payment based on hours worked
    @Override
    public double calculateTotal() {
        return hourlyRate * hoursWorked;
    }

    // Method to process payment
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " for the employee.");
    }

    // Method to modify hourly rate 
    public void modifyHourlyRate(double newRate) {
        this.hourlyRate = newRate;
    }

    // Method to modify hoursWorked 
    public void modifyhoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    // Method to generate employee paycheck details
    @Override
    public String generateReceipt() {
        return "Employee Payment Receipt: Total Hours Worked: " + hoursWorked + ", Total Payment: $" + calculateTotal();
    }

    @Override
    public void recordTransaction(String transactionDetails) {
        System.out.println("Recording employee transaction: " + transactionDetails);
    }
}
