import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class Property {

    public Property(String ID, String name, String address, String description, String photo, double price, char rating, Date timePutOnMarket, boolean activeStatus, boolean assigned, String type, Agent agent, Owner owner, Tenant tenant) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.description = description;
        this.photo = photo;
        this.price = price;
        this.rating = rating;
        this.timePutOnMarket = timePutOnMarket;
        this.activeStatus = activeStatus;
        this.assigned = assigned;
        this.type = type;
        this.agent = agent;
        this.owner = owner;
        this.tenant = tenant;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    // FIELDS
    private String ID;
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

    public Date getTimePutOnMarket() {
        return timePutOnMarket;
    }

    public void setTimePutOnMarket(Date timePutOnMarket) {
        this.timePutOnMarket = timePutOnMarket;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

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

    public String toCSVString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\"" + getID() + "\"" + ",")
                .append("\"" + getName() + "\"" + ",")
                .append("\"" + getAddress() + "\"" + ",")
                .append("\"" + getDescription() + "\"" + ",")
                .append("\"" + getPhoto() + "\"" + ",")
                .append("\"" + getPrice() + "\"" + ",")
                .append("\"" + getRating() + "\"" + ",")
                .append("\"" + getInitialMarketDate() + "\"" + ",")
                .append("\"" + getStatus() + "\"" + ",")
                .append("\"" + getAssigned() + "\"")
                .append("\"" + getType() + "\"");
        if(getOwner() != null) {
            sb.append("\"Owner\"");
            sb.append("\"" + getOwner().getUserID() + "\"");
        } else if (getAgent() != null) {
            sb.append("\"Agent\"");
            sb.append("\"" + getAgent().getUserID() + "\"");
        }
        if(getTenant() != null) {
            sb.append("\"Tenant\"");
            sb.append("\"" + getTenant().getUserID() + "\"");
        }
        return sb.toString();
    }
}

