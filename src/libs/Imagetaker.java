package libs;

import com.github.sarxos.webcam.Webcam;
import libs.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Imagetaker {
    private ImageIcon img;

    public Imagetaker() {
        img = new ImageIcon("src/media/noimg.png");
    }

    public void imageCapture(Webcam webcam, JLabel j) {
        functions f = new functions();
        Image image = webcam.getImage();
        Image tmp = f.getScaledImage(image, 300, 200);
        j.setIcon(new ImageIcon(tmp));
    }

    public void imageClear(JLabel j1, JLabel j2, JLabel j3) {
        j1.setIcon(new ImageIcon("src/media/noimg.png"));
        j2.setIcon(new ImageIcon("src/media/noimg.png"));
        j3.setIcon(new ImageIcon("src/media/noimg.png"));
    }

    public void saveImage(Webcam webcam, String folder_user, String folder_datetime){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        functions f = new functions();

        String file_name = new String("src/data/" + folder_user + "/" + folder_datetime + "/" + dtf.format(now) + ".jpg");
        try{
            ImageIO.write(webcam.getImage(), "JPG", new File(file_name));
        }
        catch (Exception e) {
            System.out.println(e+"");
        }
    }

    public void setImages(Webcam webcam, JLabel j) {
        functions f = new functions();
        try {
            Image img = ImageIO.read(new File("test.jpg"));
            Image tmp = f.getScaledImage(img, 300, 200);
            j.setIcon(new ImageIcon(tmp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
