public interface class Payment{
    // Method to calculate the total amount for a transaction
    public double calculateTotal();

    // Method to process a payment (could be extended for diff. types: cash, card, etc.)
    public void processPayment(double amount);

    // Method to generate a detailed receipt of the payment transaction
    public String generateReceipt();

    // Method to record payment information for tracking and auditing purposes
    public void recordTransaction(String transactionDetails);

    // Method to add loyalty points for guest payments
    public void addLoyaltyPoints(int points) {
        // Logic to add loyalty points to guest account
    }
    //Method to calculate taxes for both guess and employee
    public double calculateTaxes();

    
    
    // Method to check if payment has been processed successfully
    public boolean isPaymentSuccessful() {
        
    }
}