package att2.com.wrc.aas;

public class Teacher {
    private String name;
    private String email;
    private String lastclass;
    private boolean verified;
    private String accesslevel;

    public Teacher() {
    }

    public Teacher(String name, String email, String lastclass, boolean verified, String accesslevel) {
        this.name = name;
        this.email = email;
        this.lastclass = lastclass;
        this.verified = verified;
        this.accesslevel = accesslevel;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLastclass() {
        return lastclass;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getAccesslevel() {
        return accesslevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastclass(String lastclass) {
        this.lastclass = lastclass;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

}
