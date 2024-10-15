public class Pool extends Amenity implements Reservable{
    private int length;
    private int width;
    private int maxDepth;

    public Pool(){};
    
    public Pool(String name, String description, double price){
        super();
    }

    public Pool(String name, String description, double price, int length, int width, int maxDepth){
        super(name, description, price);
        this.length = length;
        this.width = width;
        this.maxDepth = maxDepth;
    }
}