import java.util.ArrayList;

public class Tenant extends User {
    public String getICNumber() {
        return ICNumber;
    }

    public void setICNumber(String ICNumber) {
        this.ICNumber = ICNumber;
    }

    private String ICNumber;

    public Tenant(String userCode, String name, String username, String password, String ICNumber) {
        super(userCode, name, username, password);
        this.ICNumber = ICNumber;
    }

    public String toCSVString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getUserID() + ",,")
                .append(getName() + ",,")
                .append(getUsername() + ",,")
                .append(getPassword() + ",,")
                .append(getApprovalStatus() + ",,")
                .append(getICNumber() + ",,");
        ArrayList<Property> propertyList = getPropertyList();
        for(int i = 0; i < propertyList.size(); i++) {
            sb.append(propertyList.get(i).getID());
            sb.append(",");
        }
        return sb.toString();
    }
}
