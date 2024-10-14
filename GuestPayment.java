public class GuestPayment implements Payment {

    private double roomCharges;
    private double amenityCharges;
    private int loyaltyPoints;

    public GuestPayment() {
        this.amenityCharges = 0;
        this.loyaltyPoints = 0;
    }

    public double getAmenityCharges() {
        return amenityCharges;
    }

    public double getRoomCharges() {
        return roomCharges;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    //assigns the value of roomCharges
    public void addRoomCharges(double roomCharges) {
        this.roomCharges += roomCharges;
    }

    // Method to add amenity charges 
    public void addAmenityCharges(double amount) {
        amenityCharges += amount;
    }

    // Method to calculate the total guest charges
    @Override
    public double calculateTotal() {
        return roomCharges + amenityCharges + calculateTaxes();
    }

    public void addRewards(double points) {
        loyaltyPoints += points;
    }

    // Method to apply loyalty points to reduce the total bill
    private void redeemLoyaltyPoints() {
        double discount = loyaltyPoints * 0.01; // point = $0.01
        roomCharges = Math.max(0, roomCharges - discount); // Charges >= 0
        loyaltyPoints = 0;
        System.out.printf("Reduced room bill by $%.2f", discount);
        System.out.println(" with loyalty points!");
    }

    // Method to process guest payment
    @Override
    public void processPayment() {
        if (loyaltyPoints > 0) {
            redeemLoyaltyPoints();
        }
        System.out.println("Processing guest payment of $" + calculateTotal());
        addRewards(roomCharges + amenityCharges);
        recordTransaction();
    }

    // Method to calculate taxes on the total bill
    @Override
    public double calculateTaxes() {
        double taxRate = 0.10; 
        return (roomCharges + amenityCharges) * taxRate;
    }

    // Method to generate receipt for guest
    @Override
    public String generateReceipt() {
        return "Guest Payment Receipt: Room Charges: $" + roomCharges +
                ", Amenity Charges: $" + amenityCharges + ", Taxes: $" +
                calculateTaxes() + ", Total: $" + calculateTotal();
    }

    // Method to process refund for guest in case of cancellations
    public void processRefund(double refundAmount) {
        System.out.println("Processing refund of $" + refundAmount + " for the guest.");
    }

     // Method to record guest payment transaction for future reference
    @Override
    public void recordTransaction() {
        System.out.println("Recording guest transaction:\n" + generateReceipt());
    }
    
}
