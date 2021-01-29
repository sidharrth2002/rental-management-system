import java.util.ArrayList;

public class Admin extends User{
    public Admin() {}

    public Admin(String userCode, String name, String username, String password, boolean approvalStatus) {
        super(userCode, name, username, password, "", approvalStatus);
    }

    @Override
    public String toCSVString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\"" + getUserID() + "\"" + ",")
                .append("\"" + getName() + "\"" + ",")
                .append("\"" + getUsername() + "\"" + ",")
                .append("\"" + getPassword() + "\"" + ",")
                .append("\"" + getApprovalStatus() + "\"");
        return sb.toString();
    }
}
