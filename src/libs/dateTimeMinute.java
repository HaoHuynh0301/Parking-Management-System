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

    public String getDefaultTimeMinute(String tmp_dtm) {
            String date = tmp_dtm.substring(0, 8);
            String H = tmp_dtm.substring(8, 10);
            String m = tmp_dtm.substring(10, 12);
            String s = tmp_dtm.substring(12, 14);
            String rs = date + " " + H + ":" + m + ":" + s;
            return rs;
    }


    public String getDateTimeMinute() {
        this.formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.date = new Date();
        String tmp_datetime = this.formatter.format(date);
        tmp_datetime = tmp_datetime.replaceAll("/","");
        tmp_datetime = tmp_datetime.replaceAll(" ", "");
        tmp_datetime = tmp_datetime.replaceAll(":", "");
        return tmp_datetime;
    }
}
