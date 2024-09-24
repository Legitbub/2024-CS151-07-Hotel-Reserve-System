public class GuestPayment extends Payment {

    private double roomCharges;
    private double amenityCharges;
    private int loyaltyPoints;

    public GuestPayment(double roomCharges, double amenityCharges, int loyaltyPoints) {
        this.roomCharges = roomCharges;
        this.amenityCharges = amenityCharges;
        this.loyaltyPoints = loyaltyPoints;
    }

    // Method to calculate the total guest charges
    @Override
    public double calculateTotal() {
        return roomCharges + amenityCharges;
    }

    // Method to apply loyalty points to reduce the total bill
    public void redeemLoyaltyPoints(int points) {
        double discount = points * 0.01; // point = $0.01
        applyDiscount(discount);
        loyaltyPoints -= points;
    }

    // Method to process guest payment
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing guest payment of $" + amount);
    }

    // Method to apply discount 
    @Override
    public void applyDiscount(double discountAmount) {
        double total = calculateTotal();
        roomCharges = Math.max(0, total - discountAmount);// Charges >= 0
    }

    // Method to calculate taxes on the total bill
    @Override
    public double calculateTaxes(double amount) {
        double taxRate = 0.10; 
        return amount * taxRate;
    }

    // Method to generate receipt for guest
    @Override
    public String generateReceipt() {
        return "Guest Payment Receipt: Room Charges: $" + roomCharges + ", Amenity Charges: $" + amenityCharges + 
               ", Total: $" + calculateTotal();
    }

    // Method to process refund for guest in case of cancellations
    @Override
    public void processRefund(double refundAmount) {
        System.out.println("Processing refund of $" + refundAmount + " for the guest.");
    }

    // Method to record guest payment transaction for future reference
    @Override
    public void recordTransaction(String transactionDetails) {
        System.out.println("Recording guest transaction: " + transactionDetails);
    }

    // Method to add amenity charges 
    public void addAmenityCharges(double amount) {
        amenityCharges += amount;
    }

}
