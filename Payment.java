public interface Payment{
    // Method to calculate the total amount for a transaction
    public double calculateTotal();

    // Method to process a payment (could be extended for diff. types: cash, card, etc.)
    public void processPayment();

    // Method to generate a detailed receipt of the payment transaction
    public String generateReceipt();

    // Method to record payment information for tracking and auditing purposes
    public void recordTransaction();

    // Method to add loyalty points for guest payments
    default void addLoyaltyPoints(int points){

    }
  
    //Method to calculate taxes for both guess and employee
    public double calculateTaxes();

    // Method to check if payment has been processed successfully
    default boolean isPaymentSuccessful(){
        return false; //Placeholder
    }
}