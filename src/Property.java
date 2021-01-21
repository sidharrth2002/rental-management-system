import java.util.Date;

//convert to builder design pattern
public class Property {
    // fields
    private static int count = 0; // property object counter
    private String ID;

    private String name; // property name
    private String address; // address, fix length? format?
    private String project;
    private String description;
    private String type;
    private String photo; // store the location of the photo?
    private double price; // current market price of the property
    private char rating; // temporary type for now, can possibly use enum
    private Date timePutOnMarket; // initial market date, mutable type!
    private boolean activeStatus; //active or inactive
    private Agent agent;
    private Owner owner;

    // private constructor to be called by builder only
    private Property() {
        this.ID = generateID();
    }

    // returns a new property ID based on number of objects created
    private String generateID() {
        return "p" + ++count;
    }

    // fields accessor methods
    public String getID() { return ID; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getProject() { return project; }
    public String getDescription() { return description; }
    public String getType() { return type; }
    public String getPhoto() { return photo; }
    public double getPrice() { return price; }
    public char getRating() { return rating; }
    public Date getInitialMarketDate() { return new Date(timePutOnMarket.getTime()); }
    public boolean getStatus() { return activeStatus; } // duplicated
    public Agent getAgent() { return agent; }
    public Owner getOwner() { return owner; }

    // fields mutator methods
    public void setID(String ID) { this.ID = ID; } // make private?
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setProject(String project) { this.project = project; }
    public void setDescription(String description) { this.description = description; }
    public void setType(String type) { this.type = type; }
    public void setPhoto(String photo) { this.photo = photo; }
    public void setPrice(double price) { this.price = price; }
    public void setRating(char rating) { this.rating = rating; }
    public void setInitialMarketDate(Date date) { timePutOnMarket = new Date(date.getTime()); }
    public void setStatus(boolean activeStatus) { this.activeStatus = activeStatus; }
    public void setAgent(Agent agent) { this.agent = agent; }
    public void setOwner(Owner owner) { this.owner = owner; }

    // returns true if  a tenant is assigned to the property
    public boolean isAssigned() {
        return activeStatus;
    }

    // converts property to a csv format string
    public String toCSVString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\"" + getID() + "\"" + ", ")
                .append("\"" + getName() + "\"" + ", ")
                .append("\"" + getAddress() + "\"" + ", ")
                .append("\"" + getProject() + "\"" + ", ")
                .append("\"" + getDescription() + "\"" + ", ")
                .append("\"" + getType() + "\"" + ", ")
                .append("\"" + getPhoto() + "\"" + ", ")
                .append("\"" + getPrice() + "\"" + ", ")
                .append("\"" + getRating() + "\"" + ", ")
                .append("\"" + getInitialMarketDate() + "\"" + ", ")
                .append("\"" + getStatus() + "\", ");
        if(getOwner() != null) {
            sb.append("\"Owner\"");
            sb.append("\"" + getOwner().getUserID() + "\", ");
        } else if (getAgent() != null) {
            sb.append("\"Agent\"");
            sb.append("\"" + getAgent().getUserID() + "\", ");
        }
        return sb.toString();
    }

    // builder as static inner class, Joshua Bloch's Builder DP style
    public static class Builder {
        // all fields same as Property except ID and count
        private String name;
        private String address;
        private String project;
        private String description;
        private String type;
        private String photo;
        private double price;
        private char rating;
        private Date timePutOnMarket;
        private boolean activeStatus;
        private Agent agent;
        private Owner owner;

        public Builder() {}

        public Builder withID(String ID) {
            //this.ID = ID;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withProject(String project) {
            this.project = project;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withPhoto(String photo) {
            this.photo = photo;
            return this;
        }

        public Builder withPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder withRating(char rating) {
            this.rating = rating;
            return this;
        }

        public Builder withInitialMarketDate(Date date) {
            timePutOnMarket = new Date(date.getTime());
            return this;
        }

        public Builder withActiveStatus(boolean activeStatus) {
            this.activeStatus = activeStatus;
            return this;
        }

        public Builder withAgent(Agent agent) {
            this.agent = agent;
            return this;
        }

        public Builder withOwner(Owner owner) {
            this.owner = owner;
            return this;
        }

        // called to generate the final property object
        public Property build() {
            // check to ensure either agent or owner, but not both, is given
            if (agent == null ^  owner != null) {
                throw new IllegalArgumentException("Property build error: Must assign either an agent or an owner but not both");
            }

            Property property = new Property();

            property.setName(name); // required
            property.setAddress(address); // required
            property.setProject(project); // required
            property.setDescription(description); // optional
            property.setType(type); // ??
            property.setPhoto(photo); // ??
            property.setPrice(price); // required
            property.setRating(rating); // optional
            property.setInitialMarketDate(timePutOnMarket); // required
            property.setStatus(activeStatus); // optional, default is false
            property.setAgent(agent); // required but not allowed if owner is set
            property.setOwner(owner); // required but not allowed if agent is set

            return property;
        }
    }
}

