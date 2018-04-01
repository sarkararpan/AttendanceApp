package att2.com.wrc.aas;

public class User {
    private String name;
    private String email;
    private String lastClass;
    private boolean verified;
    private String accessLevel;

    public User() {
    }

    public User(String name, String email, String lastClass, boolean verified, String accessLevel) {
        this.name = name;
        this.email = email;
        this.lastClass = lastClass;
        this.verified = verified;
        this.accessLevel = accessLevel;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLastClass() {
        return lastClass;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastClass(String lastClass) {
        this.lastClass = lastClass;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

}
