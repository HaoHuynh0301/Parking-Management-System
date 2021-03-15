package libs;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class timerActivity extends TimerTask {
    private JLabel j_in;
    private JLabel j_out;

    public timerActivity(JLabel j1, JLabel j2) {
        this.j_in = j1;
        this.j_out = j2;
    }

    @Override
    public void run() {
        this.j_in.setIcon(new ImageIcon("src/media/noimg.png"));
        this.j_out.setIcon(new ImageIcon("src/media/noimg.png"));
    }

    public JLabel getJ() {
        return j_in;
    }

    public void setJ(JLabel j) {
        this.j_in = j;
    }
}
