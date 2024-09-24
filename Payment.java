public abstract class Payment{
    // Method to calculate the total amount for a transaction
    public abstract double calculateTotal();

    // Method to process a payment (could be extended for diff. types: cash, card, etc.)
    public abstract void processPayment(double amount);

    // Method to apply a discount 
    public abstract void applyDiscount(double discountAmount);

    // Method to handle refunds (in case of cancellations)
    public abstract void processRefund(double refundAmount);

    // Method to generate a detailed receipt of the payment transaction
    public abstract String generateReceipt();

    // Method to calculate taxes on the payment
    public abstract double calculateTaxes(double amount);

    // Method to record payment information for tracking and auditing purposes
    public abstract void recordTransaction(String transactionDetails);

    // Method to add loyalty points for guest payments
    public void addLoyaltyPoints(int points) {
        // Logic to add loyalty points to guest account
    }
    
    // Method to check if payment has been processed successfully
    public boolean isPaymentSuccessful() {
        // Logic to check payment status
        return false; // Placeholder
    }
}