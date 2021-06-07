package libs;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dateTime {
    SimpleDateFormat formatter;
    Date date;

    public dateTime() {
        this.formatter = new SimpleDateFormat("yyyy/MM/dd");
        this.date = new Date();
    }

    public String getDateTime() {
        this.formatter = new SimpleDateFormat("yyyy/MM/dd");
        this.date = new Date();
        String tmp_datetime = this.formatter.format(date);
        tmp_datetime = tmp_datetime.replaceAll("/","");
        tmp_datetime = tmp_datetime.replaceAll(" ", "");
        tmp_datetime = tmp_datetime.replaceAll(":", "");
        System.out.println(tmp_datetime);
        return tmp_datetime;
    }
}
