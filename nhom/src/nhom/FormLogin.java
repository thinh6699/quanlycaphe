/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.*;

/**
 *
 * @author nguye
 */
public class FormLogin implements ActionListener{
    JFrame formLogin=new JFrame("Đăng Nhập");
    JButton Login=new JButton("Đăng Nhập");
    JPanel panel=new JPanel();
    JButton Exit=new JButton("Thoát");
    JTextField TextFieldTaiKhoan=new JTextField();
    JPasswordField TextFieldMatKhau = new JPasswordField(20);
    
    
    public void hienthi(){
        //Frame login
        
        formLogin.setSize(300,250);
        formLogin.setLocation(550, 230);
        
        //panel
       
        panel.setLayout(null);
        formLogin.add(panel);

        //button
        Login.addActionListener(this);
        Login.setBounds(20,150,100,20);
        panel.add(Login);
       
      
        Exit.addActionListener(this);
        Exit.setBounds(150,150,100,20);
        panel.add(Exit);
        
        
        //label
        JLabel taiKhoan=new JLabel("Tài Khoản: ");
        taiKhoan.setBounds(10,50,100,20);
        panel.add(taiKhoan);
        JLabel matKhau=new JLabel("Mật Khẩu : ");
        matKhau.setBounds(10,100, 100, 20);
        panel.add(matKhau);
        
        //textbox
        
        TextFieldTaiKhoan.setBounds(100,50,150,20);
        panel.add(TextFieldTaiKhoan);
       
        
        TextFieldMatKhau.setBounds(100,100,150,20);
        panel.add(TextFieldMatKhau);
        
        
                
        formLogin.show();
        formLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*hàm bấm tắt form dừng chương trình*/
        
       
        

    }
    
    

    @Override
    
    public void actionPerformed(ActionEvent e) {
        TrangChu n =new TrangChu();
        try {
            if(TextFieldTaiKhoan.getText().equals("")||TextFieldMatKhau.getText().equals("")){
                JOptionPane.showMessageDialog(panel,"Vui Lòng Nhập Thông Tin Đầy Đủ!");
            }
            else{
                String Url="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
                Connection conn=DriverManager.getConnection(Url);
                String sql="select count(*) from Dangnhap where TenDangNhap=? and MatKhau=?";
                PreparedStatement stm=conn.prepareStatement(sql);
                stm.setString(1,TextFieldTaiKhoan.getText());
                stm.setString(2,TextFieldMatKhau.getText());
                ResultSet rs=stm.executeQuery();
                while(rs.next()){
                    if((int)Float.parseFloat(rs.getString(1))==1){
                        String sql1="select Ten,type from Dangnhap where TenDangNhap=? and MatKhau=?";
                        PreparedStatement stm1=conn.prepareStatement(sql1);
                        stm1.setString(1,TextFieldTaiKhoan.getText());
                        stm1.setString(2,TextFieldMatKhau.getText());
                        ResultSet rs1=stm1.executeQuery();
                        TrangChu n1=new TrangChu();
                        while(rs1.next()){
                            n1.userTrangChu=rs1.getString(1);
                            n1.phanQuyenTrangChu=(int)Float.parseFloat(rs1.getString(2));
                            n1.tenDangNhap=TextFieldTaiKhoan.getText();
                        }
                        formLogin.setVisible(false);
                        n1.hienthi();
                    }
                    else{
                        JOptionPane.showMessageDialog(panel,"Sai Tài Khoản Hoặc Mật Khẩu.Vui Lòng Kiểm Tra Lại!");
                        clear();
                    }
                        
                }
            }
        } catch (Exception e2) {
            System.out.println("Kết nối thất bại");
        }

    }

    
    void clear(){
        TextFieldTaiKhoan.setText("");
        TextFieldMatKhau.setText("");
    }
}
