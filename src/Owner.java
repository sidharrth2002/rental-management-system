import java.util.ArrayList;

public class Owner extends User {
    public String getOwnershipCode() {
        return ownershipCode;
    }

    public void setOwnershipCode(String ownershipCode) {
        this.ownershipCode = ownershipCode;
    }

    private String ownershipCode;

    public String toCSVString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\"" + getUserID() + "\"" + ",")
                .append("\"" + getName() + "\"" + ",")
                .append("\"" + getUsername() + "\"" + ",")
                .append("\"" + getPassword() + "\"" + ",")
                .append("\"" + getApprovalStatus() + "\"" + ",")
                .append("\"" + getOwnershipCode() + "\"" + ",")
                .append("\"" + getPhone() + "\"" + ",");
        sb.append("\"[");
        ArrayList<Property> propertyList = getPropertyList();
        for(int i = 0; i < propertyList.size(); i++) {
            sb.append(propertyList.get(i).getID());
            sb.append(",");
        }
        sb.append("]\"");
        return sb.toString();
    }

    public Owner(String userCode, String name, String username, String password, String ownershipCode, String phone, boolean approvalStatus) {
        super(userCode, name, username, password, phone, approvalStatus);
        this.ownershipCode = ownershipCode;
    }
}
