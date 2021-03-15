package libs;

import com.github.sarxos.webcam.Webcam;
import libs.*;
import javax.swing.*;
import java.awt.*;

public class Videotaker extends Thread {
    private Webcam webcam;
    private boolean Flag;
    private JLabel image_holder;
    private functions f;

    public Videotaker(Webcam w, boolean f, JLabel j) {
        this.f = new functions();
        this.webcam = w;
        this.Flag = f;
        this.image_holder = j;
    }

    @Override
    public void run() {
        while(true) {
            Image image = webcam.getImage();
            Image tmp = this.f.getScaledImage(image, 600, 400);
            image_holder.setIcon(new ImageIcon(tmp));
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
