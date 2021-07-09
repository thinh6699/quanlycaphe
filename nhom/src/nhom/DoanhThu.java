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
import javax.swing.JTextField;

/**
 *
 * @author nguye
 */
public class DoanhThu {
    JFrame DoanhThu=new JFrame("Doanh Thu");
    JLabel ngayBatDau=new JLabel("Ngày Bắt Đầu");
    JLabel ngayKetThuc=new JLabel("Ngày Kết Thúc");
    JTextField texttongTien=new JTextField();
    JButton timKiem=new JButton("Tìm");
    JLabel tien=new JLabel("Tổng Tiền");
    JTextField textNgayVao=new JTextField();
    JTextField textNgayRa=new JTextField();
    JLabel vnd=new JLabel("VND");
    
    void hienthi(){
        DoanhThu.setSize(600,600);
        DoanhThu.setLayout(null);
        DoanhThu.setLocation(550, 100);
        DoanhThu.show();
        
        ngayBatDau.setBounds(120,60,100,40);
        DoanhThu.add(ngayBatDau);
        
        textNgayVao.setBounds(120,110,100, 30);
        DoanhThu.add(textNgayVao);
        
        ngayKetThuc.setBounds(300,60, 100,40);
        DoanhThu.add(ngayKetThuc);
        
        textNgayRa.setBounds(300, 110, 100,30);
        DoanhThu.add(textNgayRa);
        
        tien.setBounds(220,160,100,40);
        DoanhThu.add(tien);
        
        texttongTien.setBounds(150,200,220,30);
        DoanhThu.add(texttongTien);
        
        vnd.setBounds(380,195, 100, 40);
        DoanhThu.add(vnd);
        
        timKiem.setBounds(250,300,60,60);
        DoanhThu.add(timKiem);
        update();
        
    }
    
    void update(){
        timKiem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(textNgayRa.getText().equals("")||textNgayVao.getText().equals("")){
                    JOptionPane.showMessageDialog(texttongTien,"Vui Lòng Nhập Đầy Đủ Thông Tin!");
                }
                
                else{
                    try {
                    String Urlkt="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";//đia chỉ sql cần liên kết
                    Connection conn=DriverManager.getConnection(Urlkt);//gán địa chỉ vào conn.giúp java tìm được server sql
                    String sqlkt="select sum(count*Gia) from HoaDon,ThongTinHoaDon,MonAn where HoaDon.MaHoaDon=ThongTinHoaDon.MaHoaDon and ThongTinHoaDon.MaMonAn=MonAn.MaMonAn and TrangThaiHoaDon=1 and  NgayVao>=? and NgayRa<=?";//câu lệnh sql
                    PreparedStatement stmkt=conn.prepareStatement(sqlkt);//tạo biến stmkt để gán các ? ở trong sql vào các textbox
                    stmkt.setString(1,textNgayVao.getText());//gán vào các textbox
                    stmkt.setString(2,textNgayRa.getText());
                    ResultSet rskt=stmkt.executeQuery();//chạy sql
                    while(rskt.next()){//liệt kê các thành phần sql tìm được
                        texttongTien.setText(rskt.getString(1));//gán giá trị tìm được
                    } 
                    JOptionPane.showMessageDialog(texttongTien,"Tìm Hoàn Tất");
                    } catch (Exception e1) {
                    JOptionPane.showMessageDialog(texttongTien,"Cú Pháp Nhập Không Hợp Lệ!");
                    }
                
                }
                    
                
            }
        });
    }
}
