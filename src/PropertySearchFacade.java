import java.util.*;

//singleton- only one propertysearchfacade
public class PropertySearchFacade {
    private static PropertySearchFacade instance = new PropertySearchFacade();

    private ArrayList<Property> properties = new ArrayList<>();

    public static PropertySearchFacade getInstance() {
        return instance;
    }

    private PropertySearchFacade() {}

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property property) {
        properties.add(property);
    }

    public void deleteProperty(Property property) {
        properties.remove(property);
    }

    public ArrayList<Property> getByPrice() {
        ArrayList<Property> sortedProperty = new ArrayList<>(properties);
        Collections.sort(sortedProperty, new Comparator<Property>() {
            @Override
            public int compare(final Property property1, final Property property2) {
                return Double.compare(property1.getPrice(), property2.getPrice());
            }
        });
        return sortedProperty;
    }

    public ArrayList<Property> getByKeyword(String keyword) {
        ArrayList<Property> filteredProperty = new ArrayList<>();
        for (Property property: properties) {
            if (property.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                    property.getDescription().toLowerCase().contains(keyword.toLowerCase()) ||
                    property.getAddress().toLowerCase().contains(keyword.toLowerCase())
                ) {
                filteredProperty.add(property);
            }
        }
        return filteredProperty;
    }

    public ArrayList<Property> getByType(String type) {
        ArrayList<Property> filteredProperty = new ArrayList<>();
        for (Property property: properties) {
            if (property.getType().equalsIgnoreCase(type)) {
                filteredProperty.add(property);
            }
        }
        return filteredProperty;
    }

    //Ahmed
    public ArrayList<Property> getByDate(Date from, Date to) {
        return null;
    }

    public ArrayList<Property> getByActive() {
        ArrayList<Property> filteredProperty = new ArrayList<>();
        for (Property property: properties) {
            if (property.getStatus()) {
                filteredProperty.add(property);
            }
        }
        return filteredProperty;
    }

    public ArrayList<Property> getByInactive() {
        ArrayList<Property> filteredProperty = new ArrayList<>();
        for (Property property: properties) {
            if (!property.getStatus()) {
                filteredProperty.add(property);
            }
        }
        return filteredProperty;
    }

    public ArrayList<Property> getByAssigned() {
        ArrayList<Property> filteredProperty = new ArrayList<>();
        for (Property property: properties) {
            if (property.getStatus()) {
                filteredProperty.add(property);
            }
        }
        return filteredProperty;
    }

    public ArrayList<Property> getByUnassigned() {
        ArrayList<Property> filteredProperty = new ArrayList<>();
        for (Property property: properties) {
            if (!property.getStatus()) {
                filteredProperty.add(property);
            }
        }
        return filteredProperty;
    }

}