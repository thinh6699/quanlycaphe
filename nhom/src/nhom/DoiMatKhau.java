/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author nguye
 */
public class DoiMatKhau {
    JFrame formpasswordchange = new JFrame("Đổi Mật Khẩu");
    JLabel title = new JLabel("Đổi Mật Khẩu");
    JLabel matkhaucu = new JLabel("Mật khẩu cũ:");
    JLabel matkhaumoi = new JLabel("Mật khẩu mới:");
    JLabel nhaplaimatkhau = new JLabel("Nhập lại mật khẩu:");
    JPasswordField mk1 = new JPasswordField(20);
    JPasswordField mk2 = new JPasswordField(20);
    JPasswordField mk3 = new JPasswordField(20);
    JButton doi = new JButton("Đổi");
    JLabel userJLabel=new JLabel("User");
    
    public String userDMK; //để lấy dữ liệu TenUser bên Form TrangChu;
    public String tenDangNhapDMK;//để lấy dữ liệu TênTaiKHoan bên Form TrangChu;
    
    public void hienthi(){  
        formpasswordchange.setSize(600, 600);
        formpasswordchange.show();
        formpasswordchange.setLayout(null);
        formpasswordchange.setLocationRelativeTo(null);
        
        title.setBounds(250, 30, 300, 100);
        formpasswordchange.add(title);
        
        matkhaucu.setBounds(150, 120, 80, 40);
        formpasswordchange.add(matkhaucu);
        
        matkhaumoi.setBounds(150, 180, 90, 40);
        formpasswordchange.add(matkhaumoi);
        
        nhaplaimatkhau.setBounds(150, 240, 500,40);
        formpasswordchange.add(nhaplaimatkhau);
        
        mk1.setBounds(280, 120, 200, 40);
        formpasswordchange.add(mk1);
        
        mk2.setBounds(280, 180, 200, 40);
        formpasswordchange.add(mk2);
        
        mk3.setBounds(280, 240, 200, 40);
        formpasswordchange.add(mk3);
        
        doi.setBounds(230, 330, 90, 40);
        formpasswordchange.add(doi);
        

        
        userJLabel.setBounds(200,30,250, 40);
        userJLabel.setText("User Đang Sử Dụng Của:"+userDMK);
        formpasswordchange.add(userJLabel);
        
        
        update();
    }
    //Hàm này để lấy thông tin của User và các câu lệnh sự kiện button
    void update(){
        //Đây là hàm sự kiện khi click vào nút doi
        doi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                if(mk1.getText().equals("")||mk2.getText().equals("")||mk3.getText().equals(""))//Nếu như 3 cái textbox kia để trống sẽ hiện thông báo dưới
                    JOptionPane.showMessageDialog(matkhaumoi,"Vui Lòng Nhập Đầy Đủ Thông Tin");
                else{
                    try {                     //Kết lối sql(giống if else kiểm tra,nếu như kết nối thành công sql thì sẽ chạy cái try,sai thì chạy cái catch
                        String Url="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";//Hàm tìm địa chỉ sql
                        Connection conn=DriverManager.getConnection(Url);//Hàm liên kết sql
                        String sql1="select MatKhau from Dangnhap where TenDangNhap=?";    //Câu lệnh sql kiểm tra mât khẩu cũ của tài khoản 
                        PreparedStatement stm1=conn.prepareStatement(sql1);//dòng lệnh gắn các đuôi stm.[tên] bằng cái ?,đối vs câu lệnh sql chạy trên địa chỉ conn
                        stm1.setString(1,tenDangNhapDMK);//gán giá trị ? trong sql
                        ResultSet rs1=stm1.executeQuery();
                        String sosanh=null;
                        while(rs1.next()){
                            sosanh=rs1.getString(1);//gán pass cũ vào sosanh để so sánh với textbox1(mk1)
                        }
                        if(mk1.getText().equals(sosanh)){
                            if(mk2.getText().equals(mk3.getText())){//so sánh 2 chuỗi trong 2 textbox MK2 và MK3,đúng thì chạy

                            String sql="update Dangnhap set MatKhau=? where TenDangNhap=?";    //Câu lệnh sql cần thực hiện
                            PreparedStatement stm=conn.prepareStatement(sql);//dòng lệnh gắn các đuôi stm.[tên] bằng cái ?,đối vs câu lệnh sql chạy trên địa chỉ conn
                            stm.setString(1,mk2.getText());//gán ? thứ 1 bằng textbox mk1
                            stm.setString(2,tenDangNhapDMK);//gán ? thứ 2 bằng giá trị lưu ở trên (là cái tenDangNhapMK ở Form TrangChu)
                            stm.executeUpdate();
                            JOptionPane.showMessageDialog(matkhaumoi,"Đổi Mật Khẩu Thành Công");//Hàm thông báo
                            clear();//hàm xóa thông tin
                        }
                        else{
                            JOptionPane.showMessageDialog(matkhaumoi,"Mật Khẩu Mới Không Trùng Nhau!");
                            clear();
                        }
                            
                        }
                        else{
                            JOptionPane.showMessageDialog(matkhaumoi, "Mật Khẩu Cũ Không Đúng");
                            clear();
                        }

                    } catch (Exception e1) {
                    }
                }
            }
        });
    }
    void clear(){//hàm sẽ xóa các giá trị trong textbox khi thay đổi mật khẩu thành công ,hay thất bại
        mk1.setText("");
        mk2.setText("");
        mk3.setText("");
    }
}
