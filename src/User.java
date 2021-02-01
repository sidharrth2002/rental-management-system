import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private String name;
    private String userID;
    private String username;
    private String password;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isApprovalStatus() {
        return approvalStatus;
    }

    private boolean approvalStatus;
    private List<String> propertyCodes = new ArrayList<>();
    private ArrayList<Property> propertyList = new ArrayList<>();

    public abstract String toCSVString();

    public User() {}

    public User(String userCode, String name, String username, String password, String phone, boolean approvalStatus) {
        this.userID = userCode;
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.approvalStatus = approvalStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Property> getPropertyList() {
        return this.propertyList;
    }

    public void setPropertyList(ArrayList<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public boolean getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public void addProperty(Property property) {
        this.propertyList.add(property);
    }

    public void deleteProperty(Property property) {
        propertyList.remove(property);
    }

    public void setPropertyCodes(List<String> propertyCodes) {
        this.propertyCodes = propertyCodes;
    }

    public List<String> getPropertyCodes() {
        return propertyCodes;
    }
}
