package libs;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dateTimeMinute {
    SimpleDateFormat formatter;
    Date date;

    public dateTimeMinute() {
        this.formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.date = new Date();
    }


    public String getDateTimeMinute() {
        String tmp_datetime = this.formatter.format(date);
        tmp_datetime = tmp_datetime.replaceAll("/","");
        tmp_datetime = tmp_datetime.replaceAll(" ", "");
        tmp_datetime = tmp_datetime.replaceAll(":", "");
        return tmp_datetime;
    }
}
