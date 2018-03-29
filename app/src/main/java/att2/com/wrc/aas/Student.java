package att2.com.wrc.aas;

/**
 * Created by Aurghya on 24-03-2018.
 */

public class Student {
    private String name;
    private long semester;
    private  String cid;


    public Student() {
    }

    public Student(String name, long semester, String cid) {
        this.name = name;
        this.semester = semester;
        this.cid = cid;
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
