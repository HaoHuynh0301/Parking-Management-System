import com.github.sarxos.webcam.Webcam;
import libs.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private JButton btn_manage;
    private JPanel pannel_manage;
    private JTextField txt_ad_usr;
    private JPasswordField txt_ad_pw;
    private JButton btn_ad_signin;
    private JButton btn_return;
    private JPanel pannel_list;
    private JScrollPane panneL_scroll;
    private JList list_customers;
    private JButton btn_delete;
    private JButton btn_list_return;
    private JButton btn_export;
    private static Webcam webcam = Webcam.getDefault();
    private Image image;
    private static boolean Flag = true;
    private functions f = new functions();
    private Imagetaker t = new Imagetaker();
    private timerActivity myTask = new timerActivity(img_capture_in, img_capture_out);

    private String general_ID;
    private String general_datetime;
    private String general_dateMinute;

    private dateTimeMinute dtm = new dateTimeMinute();

    private dateTime dt = new dateTime();

    private Timer timer = new Timer();

    private fileActivity file = new fileActivity();

    private DefaultListModel list_model = new DefaultListModel();

    private int selection_index = -1;


    private static Connection conn;
    private static connectDB connect = new connectDB("car", "root", "hao152903");

    public Screen() {
        super("Car Parking Control and Management   ");
        this.setContentPane(this.pannel_main);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        pannel_signup.setVisible(false);

        pannel_manage.setVisible(false);

        pannel_list.setVisible(false);

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
                        boolean Flag_selecte_ID = connect.select_ID(tmp_code, conn);
                        if(Flag_selecte_ID == true) {
                            try {
                                general_datetime = dt.getDateTime();
                                general_dateMinute = dtm.getDateTimeMinute();
                                general_ID = tmp_code;
                                boolean Flag_dateTime = connect.select_date(general_datetime, general_ID, conn);
                                if(Flag_dateTime == false) {
                                    connect.insert_datetime(general_datetime, general_ID, conn);
                                    file.createFolder_date(tmp_code, general_datetime);
                                } else {
                                    System.out.println("Folder was exited");
                                }
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            connect.insertParkingDate(conn, general_dateMinute, general_ID, 1);
                            connect.update_customer_date_in(general_dateMinute, conn);
                            txt_code.setText("");
                            t.setImages_Image(webcam, new ImageIcon(webcam.getImage()) ,img_capture_in);
                            t.saveImage(webcam, general_ID, general_datetime, general_dateMinute);
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

                String tmp_code = txt_code.getText();

                if("".equals(tmp_code)) {
                    JOptionPane.showMessageDialog(null, "Nhập vào mã số", "Thông báo", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean tmp_Flag = false;
                    try {
                        tmp_Flag = connect.select_ID(tmp_code, conn);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    if(tmp_Flag == true) {
                        txt_code.setText("");
                        ResultSet tmp_rs = null;
                        String tmp_dateTimeMinute= "";
                        String img_path = "";
                        try {
                            tmp_rs = connect.selection(tmp_code, conn);
                            while(tmp_rs.next()) {
                                tmp_dateTimeMinute = tmp_rs.getString("newest_date");
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        try {
                            connect.insertParkingDate(conn, dtm.getDateTimeMinute(), general_ID, 2);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        img_path = "D:\\Carparking_2\\src\\data\\" + tmp_code + "\\" + tmp_dateTimeMinute.substring(0, 8) + "\\" + tmp_dateTimeMinute + "_in.jpg";
                        t.setImages_Path(webcam, img_path, img_capture_in);
                        t.setImages_Image(webcam, new ImageIcon(webcam.getImage()), img_capture_out);
                        t.saveImage_out(webcam, tmp_code, tmp_dateTimeMinute.substring(0, 8), tmp_dateTimeMinute);

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
                    JOptionPane.showMessageDialog(null, "ĐĂNG KÝ THÀNH CÔNG. MÃ SỐ XE CỦA BẠN LÀ: " + ID, "Thông báo", JOptionPane.ERROR_MESSAGE);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        btn_manage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "ĐĂNG NHẬP VỚI QUYỀN QUẢN LÝ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                pannel_login.setVisible(false);
                pannel_manage.setVisible(true);
            }
        });
        btn_return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pannel_manage.setVisible(false);
                pannel_login.setVisible(true);
            }
        });

        btn_list_return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pannel_list.setVisible(false);
                pannel_login.setVisible(true);
            }
        });

        btn_ad_signin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp_usr = txt_ad_usr.getText();
                String tmp_pd = txt_ad_pw.getText();
                if("".equals(tmp_usr) || "".equals(tmp_pd)) {
                    JOptionPane.showMessageDialog(null, "Nhập vào mã số", "Thông báo", JOptionPane.ERROR_MESSAGE);
                } else if("admin".equals(tmp_usr) || "admin".equals(tmp_pd)) {
                    JOptionPane.showMessageDialog(null, "ĐĂNG NHẬP THÀNH CÔNG", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    txt_ad_usr.setText("");
                    txt_ad_pw.setText("");
                    pannel_manage.setVisible(false);
                    pannel_list.setVisible(true);
                    try {
                        selectListCustomer();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "VUI LÒNG NHẬP LẠI THÔNG TIN ĐĂNG NHẬP", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btn_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String select_value = "";
                if(list_customers.getSelectedIndex() != -1) {
                    select_value = list_customers.getSelectedValue() + "";
                    String[] list_String = select_value.split(";");
                    select_value = list_String[0];
                    try {
                        connect.select_Name(select_value, conn);
                        selectListCustomer();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "VUI LÒNG CHỌN NGƯỜI DÙNG", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btn_export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String select_value = "";
                String text = "";
                if(list_customers.getSelectedIndex() != -1) {
                    select_value = list_customers.getSelectedValue() + "";
                    String[] list_String = select_value.split(";");
                    select_value = list_String[0];
                    try {
                        String tmp_ID = connect.select_ID_byName(select_value, conn);
                        ResultSet rs = connect.select_ParkingTime(conn, tmp_ID);
                        while(rs.next()) {
                            text = text + rs.getString("id") + ";" + rs.getString("pdate") + ";" + rs.getInt("status") + "\n";
                        }
                        file.writterInformation(conn, tmp_ID, text);

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "VUI LÒNG CHỌN NGƯỜI DÙNG", "Thông báo", JOptionPane.ERROR_MESSAGE);
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

    private void selectListCustomer() throws SQLException {
        list_model.clear();
        ResultSet rs = connect.select_list_customer(conn);
        while(rs.next()) {
            String tmp_dtm = dtm.getDefaultTimeMinute(rs.getString("newest_date"));
            list_model.addElement(rs.getString("name") + "; Gửi xe lần cuối: " + tmp_dtm);
        }
        list_customers.setModel(list_model);
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
        pannel_list.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_quiting.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_clear.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_start.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_signup.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_manage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_ad_signin.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_return.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_delete.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_export.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_list_return.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pannel_signup.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Đăng ký", TitledBorder.LEFT, TitledBorder.TOP));
        pannel_list.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Danh sách", TitledBorder.LEFT, TitledBorder.TOP));
        pannel_manage.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Quản lý", TitledBorder.LEFT, TitledBorder.TOP));
        btn_sigup.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
