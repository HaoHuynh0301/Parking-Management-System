import com.github.sarxos.webcam.Webcam;
import libs.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private JPanel pannel_login;
    private JPanel pannel_signup;
    private JButton btn_sigup;
    private JPanel pannel_parking;
    private JTextField txt_name;
    private JTextField txt_modecode;
    private JTextField txt_dob;
    private JTextField txt_age;
    private static Webcam webcam = Webcam.getDefault();
    private Image image;
    private static boolean Flag = true;
    private functions f = new functions();
    private Imagetaker t = new Imagetaker();
    private timerActivity myTask = new timerActivity(img_capture_in, img_capture_out);

    private String general_ID;
    private String general_code;
    private String general_datetime;

    private dateTime dt = new dateTime();

    private Timer timer = new Timer();

    private fileActivity file = new fileActivity();


    private static Connection conn;
    private static connectDB connect = new connectDB("car", "root", "hao152903");

    public Screen() {
        super("Car Parking Control and Management   ");
        this.setContentPane(this.pannel_main);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        pannel_signup.setVisible(false);

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

                String tmp_code = txt_code.getText();

                if("".equals(tmp_code)) {
                    JOptionPane.showMessageDialog(null, "Nhập vào mã số", "Thông báo", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        boolean Flag = connect.select_ID(tmp_code, conn);
                        if(Flag == true) {
                            try {
                                general_datetime = dt.getDateTime();
                                System.out.println(general_datetime + " " + general_ID);
                                boolean Flag_dateTime = connect.select_date(general_datetime, conn);
                                if(Flag_dateTime == false) {
                                    connect.insert_datetime(general_datetime, conn);
                                    file.createFolder_date(tmp_code, general_datetime);
                                } else {
                                    System.out.println("Folder was exited");
                                }
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }

                            general_code = tmp_code;
                            txt_code.setText("");
                            t.imageCapture(webcam, img_capture_in);
                            t.saveImage(webcam, general_code, general_datetime);
                        } else {
                            JOptionPane.showMessageDialog(null, "Mã số không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                            txt_code.setText("");
                        }

                    } catch (SQLException throwables) {
                        System.out.println(throwables.toString());
                    }
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
                    if(txt_code.getText().equals(general_code)) {
                        txt_code.setText("");
                        t.setImages(webcam, img_capture_in);
                        t.imageCapture(webcam, img_capture_out);

                    } else {
                        JOptionPane.showMessageDialog(null, "Mã số không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        btn_signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    pannel_login.setVisible(false);
                    pannel_signup.setVisible(true);
            }
        });

        btn_sigup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pannel_login.setVisible(true);
                pannel_signup.setVisible(false);
                String tmp_name = txt_name.getText();
                int tmp_age = Integer.parseInt(txt_age.getText());
                String tmp_motocode = txt_modecode.getText();
                String tmp_dob = txt_dob.getText();
                String ID = f.generateAlphabet();
                general_ID = ID;

                System.out.println(ID);

                file.createFolder_user(ID);

                try {
                    connect.insert_card(ID, 1, conn);
                    connect.insert_customer(ID, tmp_name, tmp_age, tmp_motocode, tmp_dob, conn);
                    JOptionPane.showMessageDialog(null, "ĐĂNG KÝ THÀNH CÔNG", "Thông báo", JOptionPane.ERROR_MESSAGE);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
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
        conn = connect.connection(conn);
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
        pannel_login.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pannel_signup.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_quiting.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_clear.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_start.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_signup.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pannel_signup.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Đăng ký", TitledBorder.LEFT, TitledBorder.TOP));
        btn_sigup.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
