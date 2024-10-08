public abstract class Payment{
    // Method to calculate the total amount for a transaction
    public abstract double calculateTotal();

    // Method to process a payment (could be extended for diff. types: cash, card, etc.)
    public abstract void processPayment(double amount);

    // Method to generate a detailed receipt of the payment transaction
    public abstract String generateReceipt();

    // Method to record payment information for tracking and auditing purposes
    public abstract void recordTransaction(String transactionDetails);

    // Method to add loyalty points for guest payments
    public void addLoyaltyPoints(int points) {
        // Logic to add loyalty points to guest account
    }
    //Method to calculate taxes for both guess and employee
    public abstract double calculateTaxes();

    
    
    // Method to check if payment has been processed successfully
    public boolean isPaymentSuccessful() {
        // Logic to check payment status
        return false; // Placeholder
    }
}