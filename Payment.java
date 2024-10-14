public interface Payment{
    // Method to calculate the total amount for a transaction
    double calculateTotal();

    // Method to process a payment (could be extended for diff. types: cash, card, etc.)
    void processPayment();

    // Method to generate a detailed receipt of the payment transaction
    String generateReceipt();

    // Method to record payment information for tracking and auditing purposes
    void recordTransaction();
  
    //Method to calculate taxes for both guest and employee
    double calculateTaxes();

    // Method to check if payment has been processed successfully
    default boolean isPaymentSuccessful(){
        return false; //Placeholder
    }
}