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
        String tmp_datetime = this.formatter.format(date);
        tmp_datetime = tmp_datetime.replaceAll("/","");
        tmp_datetime = tmp_datetime.replaceAll(" ", "");
        tmp_datetime = tmp_datetime.replaceAll(":", "");
        return tmp_datetime;
    }
}
