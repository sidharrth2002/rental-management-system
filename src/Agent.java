import java.util.ArrayList;

public class Agent extends User {
    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    private String licenseCode;

    public String toCSVString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\"" + getUserID() + "\"" + ",")
                .append("\"" + getName() + "\"" + ",")
                .append("\"" + getUsername() + "\"" + ",")
                .append("\"" + getPassword() + "\"" + ",")
                .append("\"" + getApprovalStatus() + "\"" + ",")
                .append("\"" + getLicenseCode() + "\"" + ",");
        sb.append("\"[");
        ArrayList<Property> propertyList = getPropertyList();
        for(int i = 0; i < propertyList.size(); i++) {
            sb.append(propertyList.get(i).getID());
            sb.append(",");
        }
        sb.append("\"]");
        return sb.toString();
    }

    public Agent(String userCode, String name, String username, String password, String licenseCode) {
        super(userCode, name, username, password);
        this.licenseCode = licenseCode;
    }
}
