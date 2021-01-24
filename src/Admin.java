public class Admin extends User{
    private static int adminnum;
    public Admin() {

    }

    public Admin(String userCode, String name, String username, String password, boolean approvalStatus) {
        super(userCode + adminnum++, name, username, password, approvalStatus);
    }

    @Override
    public String toCSVString() {
        return null;
    }

}
