package att2.com.wrc.aas;

/**
 * Created by Aurghya on 24-03-2018.
 */

public class Student {
    private long sid;
    private String name;

    public Student() {
    }

    public Student(long sid, String name) {

        this.sid = sid;
        this.name = name;
    }

    public long getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public void setName(String name) {
        this.name = name;
    }
}
