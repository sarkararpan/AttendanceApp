package att2.com.wrc.aas;

/**
 * Created by Aurghya on 24-03-2018.
 */

public class Student {
    private long sid;
    private String name;
    private long semester;
    private  String cid;


    public Student() {
    }

    public Student(long sid, String name, long semester, String cid) {
        this.sid = sid;
        this.name = name;
        this.semester = semester;
        this.cid = cid;
    }

    public long getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public long getSemester() {
        return semester;
    }

    public String getCid() {
        return cid;
    }
}
