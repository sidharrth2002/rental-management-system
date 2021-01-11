import java.awt.*;
import java.util.Date;

public class Property {
    // FIELDS
    private String name; // praoperty name
    private String address; // address, fix length? format?
    private String description;
    private String photo; // store the location of the photo
    private double price; // current market price of the property
    private char rating; // temporary type for now
    private Date timePutOnMarket;
    private boolean activeStatus; //active or inactive
    private boolean assigned;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    private Agent agent;
    private Owner owner;
    private Tenant tenant;

    // incomplete constructor
    public Property(String name, String address, double price, Date date) {
        setName(name);
        setAddress(address);
        setPrice(price);
        setInitialMarketDate(date);
    }

    public boolean getAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    // ACCESSORS
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getDescription() { return description; }
    public String getPhoto() { return photo; }
    public double getPrice() { return price; }
    public char getRating() { return rating; }
    public Date getInitialMarketDate() { return timePutOnMarket; }
    public boolean getStatus() { return activeStatus; }

    // MUTATORS
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setDescription(String description) { this.description = description; }
    public void setPhoto(String photo) {
        // correct scale?
        this.photo = photo;
    }
    public void setPrice(double price) { this.price = price; }
    public void setRating(char rating) { this.rating = rating; }
    public void setInitialMarketDate(Date date) { timePutOnMarket = new Date(date.getTime()); }
    public void setStatus(boolean status) { this.activeStatus = status; }

}

