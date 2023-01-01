public class Property {
    private City city;
    private String street;
    private int rooms;
    private int price;
    private Integer type;
    private Boolean isRent = false;
    private int numberOfBuilding;
    private int numberOfFloor;
    private User user;

    public int getPrice() {
        return price;
    }

    public Integer getType() {
        return type;
    }

    public int getRooms() {
        return rooms;
    }

    public Boolean getRent() {
        return isRent;
    }

    public Property(City city, String street, int rooms, int price, Integer type, Boolean isRent, int numberOfBuilding, int numberOfFloor, User user) {
        this.city = city;
        this.street = street;
        this.rooms = rooms;
        this.price = price;
        this.type = type;
        this.isRent = isRent;
        this.numberOfBuilding = numberOfBuilding;
        this.numberOfFloor = numberOfFloor;
        this.user = user;
    }
}