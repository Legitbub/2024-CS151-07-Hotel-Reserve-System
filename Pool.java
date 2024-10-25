public class Pool extends Amenity {
    private float length;
    private float width;
    private float maxDepth;
    private float waterTemp;
    private Employee lifeguard;

    public Pool(String name, String description, float basePrice) {
        super(name, description, basePrice);
    }

    public Pool(String name, String description, float basePrice, float length, float width, float maxDepth,
            float waterTemp) {
        super(name, description, basePrice);
        this.length = length;
        this.width = width;
        this.maxDepth = maxDepth;
        this.waterTemp = waterTemp;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(float maxDepth) {
        this.maxDepth = maxDepth;
    }

    public float getWaterTemp() {
        return waterTemp;
    }

    public void setWaterTemp(float waterTemp) {
        this.waterTemp = waterTemp;
    }

    public Employee getLifeguard() {
        return lifeguard;
    }

    public void setLifeguard(Employee lifeguard) {
        this.lifeguard = lifeguard;
    }
}