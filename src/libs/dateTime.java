package libs;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dateTime {
    SimpleDateFormat formatter;
    Date date;

    public dateTime() {
        this.formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.date = new Date();
    }

    public String getDateTime() {
        return this.formatter.format(date);
    }
}
