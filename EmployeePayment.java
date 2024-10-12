public class EmployeePayment implements  Payment {

    private double hourlyRate;
    private double hoursWorked;

    public EmployeePayment(Employee employee) {
        this.hourlyRate = employee.getWage();
        this.hoursWorked = employee.getHoursWorked;
    }

    // Method to log hours worked
    public void logHours(double hours) {
        this.hoursWorked += hours;
    }

    // Method to calculate total payment based on hours worked
    @Override
    public double calculateTotal() {
        return hourlyRate * hoursWorked - calculateTaxes();
    }

    @Override
    public double calculateTaxes() {
        double totalIncomeBeforeTax = hourlyRate * hoursWorked;
        double incomeAfterTax = 0;
        if(totalIncomeBeforeTax <= 10099/12){
            incomeAfterTax = totalIncomeBeforeTax * 0.01;
        }
        else if(incomeAfterTax >= 10100/12 && incomeAfterTax <= 23942/12){
            incomeAfterTax = totalIncomeBeforeTax * 0.02;
        }
        else if(incomeAfterTax >= 23942/12 && incomeAfterTax <= 37788/12){
            incomeAfterTax = totalIncomeBeforeTax * 0.04;
        }
        else if(incomeAfterTax >= 37789/12 && incomeAfterTax <= 52455/12){
            incomeAfterTax = totalIncomeBeforeTax * 0.06;
        }
        else if(incomeAfterTax >= 52456/12 && incomeAfterTax <= 66295/12){
            incomeAfterTax = totalIncomeBeforeTax * 0.08;
        }
        else{
            incomeAfterTax = totalIncomeBeforeTax * 0.093;
        }

        return incomeAfterTax;
    }

    // Method to process payment
    @Override
    public void processPayment() {
        System.out.println("Processing payment of $" + calculateTotal() +" for the employee.");
        this.hoursWorked = 0;
        employee.setHoursWorked(0);
        generateReceipt()

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
        return "Employee Payment Receipt: Total Hours Worked: " + hoursWorked + ", Total taxes: $"+calculateTaxes()+", Total Payment: $" + calculateTotal();
    }


    @Override
    public void recordTransaction() {
        System.out.println("Recording employee transaction: " + generateReceipt());
    }
}
