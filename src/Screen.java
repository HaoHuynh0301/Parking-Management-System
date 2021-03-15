import com.github.sarxos.webcam.Webcam;
import libs.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Timer;

public class Screen extends JFrame{
    private JPanel pannel_main;
    private JPanel pannel_capture;
    private JLabel image_holder;
    private JPanel pannel_image_capture;
    private JLabel img_capture_in;
    private JLabel img_capture_out;
    private JPanel pannel_in;
    private JPanel pannel_out;
    private JPanel pannel_infor;
    private JTextField txt_code;
    private JButton btn_parking;
    private JButton btn_quiting;
    private JButton btn_start;
    private JButton btn_signup;
    private JButton btn_clear;
    private static Webcam webcam = Webcam.getDefault();
    private Image image;
    private static boolean Flag = true;
    private functions f = new functions();
    private Imagetaker t = new Imagetaker();
    private timerActivity timer_both = new timerActivity(img_capture_in, img_capture_out);
    private Timer timer_count = new Timer();

    private String code = new String();

    private static Connection conn;
    private static connectDB connect = new connectDB("car", "root", "hao152903");

    public Screen() {
        super("Car Parking Control and Management   ");
        this.setContentPane(this.pannel_main);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        createBorder();
        f.Initialization(image_holder, img_capture_in, img_capture_out);

        btn_start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Videotaker(webcam, Flag, image_holder).start();
                btn_start.setText("KẾT THÚC");

            }
        });

        btn_parking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp_code = new String(txt_code.getText());
                if("".equals(tmp_code)) {
                    JOptionPane.showMessageDialog(null, "Nhập vào mã số", "Thông báo", JOptionPane.ERROR_MESSAGE);
                } else {
                    code = txt_code.getText();
                    txt_code.setText("");
                    t.imageCapture(webcam, img_capture_in);
                    t.saveImage(webcam);

                    timer_count.schedule(timer_both, 0, 3000);
                }
            }
        });

        btn_clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.imageClear(image_holder, img_capture_in, img_capture_out);
            }
        });

        btn_quiting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp_code = new String(txt_code.getText());
                if("".equals(tmp_code)) {
                    JOptionPane.showMessageDialog(null, "Nhập vào mã số", "Thông báo", JOptionPane.ERROR_MESSAGE);
                } else {
                    if(txt_code.getText().equals(code)) {
                        txt_code.setText("");
                        t.getImages(webcam, img_capture_out);

                        timer_count.schedule(timer_both, 1000, 3000);
                    } else {
                        JOptionPane.showMessageDialog(null, "Mã số không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
    }

    public static void main(String args[]) {
        Screen screen = new Screen();
        screen.setContentPane(new Screen().pannel_main);
        screen.pack();
        screen.setVisible(true);
        ImageCapture();
        connect.connection(conn);
    }

    private static void ImageCapture() {
        webcam.setViewSize(new Dimension(320, 240));
        webcam.open();
    }

    private void createBorder() {
        pannel_capture.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pannel_in.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pannel_out.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pannel_infor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_parking.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    }
}
