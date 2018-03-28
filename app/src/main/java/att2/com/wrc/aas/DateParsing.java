package att2.com.wrc.aas;


import java.text.SimpleDateFormat;
import java.util.Date;

/* Support Class for Date Parsing*/
public class DateParsing {
    private Date today;

    public DateParsing() {
        today = new Date();
    }

    public String getCurrentDateString() {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        return formatDate.format(today);
    }
}
