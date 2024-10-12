public class GuestPayment implements Payment {

    private double roomCharges;
    private double amenityCharges;
    private int loyaltyPoints;
    private double total;

    public GuestPayment(Guest guest) {
        this.amenityCharges = 0;
        this.loyaltyPoints = fetchLoyaltyPoints(guest);
    }

    // Fetch existing loyalty points from the guest's profile
    private int fetchLoyaltyPoints(Guest guest) {
        return guest.getRewardsPoints();
    }

    //assigns the value of roomCharges 
    public void addRoomcharges(double roomCharges) {
        this.roomCharges = roomCharges;
    }

    // Method to add amenity charges 
    public void addAmenityCharges(double amount) {
        amenityCharges += amount;
    }

    // Method to calculate the total guest charges
    @Override
    private double calculateTotal() {
        return roomCharges + amenityCharges + calculateTaxes();
    }

    // Method to apply loyalty points to reduce the total bill
    private void redeemLoyaltyPoints() {
        double discount = loyaltyPoints * 0.01; // point = $0.01
        applyDiscount(discount);
        loyaltyPoints = 0;
    }

    // Method to process guest payment
    @Override
    public void processPayment() {
        redeemLoyaltyPoints();
        System.out.println("Processing guest payment of $" + calculateTotal());
        guest.setRewardsPoints(roomCharges + amenityCharges)
        System.out.println(generateReceipt());
        recordTransaction();
    }

    // Method to apply discount
    public void applyDiscount(double discountAmount) {
        roomCharges = Math.max(0, roomCharges - discountAmount);// Charges >= 0
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
        return "Guest Payment Receipt: Room Charges: $" + roomCharges + ", Amenity Charges: $" + amenityCharges + ", Taxes: $" + calculateTaxes +
               ", Total: $" + calculateTotal();
    }

    // Method to process refund for guest in case of cancellations
    public void processRefund(double refundAmount) {
        System.out.println("Processing refund of $" + refundAmount + " for the guest.");
    }

     // Method to record guest payment transaction for future reference
    @Override
    public void recordTransaction() {
        System.out.println("Recording guest transaction: " + generateReceipt());
    }
    
}
