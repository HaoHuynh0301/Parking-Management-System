package libs;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class functions implements Serializable {

    public static Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    public static void Initialization(JLabel j1, JLabel j2, JLabel j3) {
//        j1.setIcon(new ImageIcon("src/media/noimg.png"));
        j2.setIcon(new ImageIcon("src/media/noimg.png"));
        j3.setIcon(new ImageIcon("src/media/noimg.png"));
    }
}
