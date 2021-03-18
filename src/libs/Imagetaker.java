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

//    public void imageCapture(Webcam webcam, JLabel j) {
//        functions f = new functions();
//        Image image = webcam.getImage();
//        Image tmp = f.getScaledImage(image, 300, 200);
//        j.setIcon(new ImageIcon(tmp));
//    }

    public void imageClear(JLabel j1, JLabel j2, JLabel j3) {
        j1.setIcon(new ImageIcon("src/media/noimg.png"));
        j2.setIcon(new ImageIcon("src/media/noimg.png"));
        j3.setIcon(new ImageIcon("src/media/noimg.png"));
    }

    public void saveImage(Webcam webcam, String folder_user, String folder_datetime, String file_name_dateTimMinute){
        file_name_dateTimMinute = file_name_dateTimMinute.replaceAll("/","");
        file_name_dateTimMinute = file_name_dateTimMinute.replaceAll(" ", "");
        file_name_dateTimMinute = file_name_dateTimMinute.replaceAll(":", "");

        functions f = new functions();

        String file_name = "D:\\Carparking_2\\src\\data\\" + folder_user + "\\" + folder_datetime + "\\" + file_name_dateTimMinute + "_in.jpg";
        try{
            ImageIO.write(webcam.getImage(), "JPG", new File(file_name));
        }
        catch (Exception e) {
            System.out.println(e+"");
        }
    }

    public void setImages_Path(Webcam webcam, String tmp_path, JLabel j) {
        System.out.println(tmp_path);
        j.setIcon(new ImageIcon(tmp_path));
    }

    public void setImages_Image(Webcam webcam, ImageIcon img, JLabel j) {
        j.setIcon(img);
    }

//    public void getImageOut(Webcam webcam, JLabel j, String tmp_id) {
//        functions f = new functions();
//        String folder_date_name = tmp_id.get
//        Image img = ImageIO.rea
//    }

}
