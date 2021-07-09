/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguye
 */
public class TaiKhoan {
    JFrame frame =new JFrame();
    JTable table=new JTable();
    
    JTextField textUser=new JTextField();
    JTextField textTaiKhoan=new JTextField();
    JTextField textMatKhau=new JTextField();
    JTextField textLoai=new JTextField();
    
    JLabel ten=new JLabel("User");
    JLabel taikhoan=new JLabel("Tài Khoản");
    JLabel matkhau =new JLabel("Mật Khẩu");
    JLabel loai=new JLabel("Loại Tài Khoản");
    
    JButton btnAdd=new JButton("Thêm");
    JButton btnDelete=new JButton("Xóa");
    JButton btnUpdate=new JButton("Sửa");
    JButton btnTrangChu=new JButton("Đóng Form");
    
    JScrollPane pane=new JScrollPane(table);
    
    DefaultTableModel model=new DefaultTableModel();
    Object [] columns={"User","Tài Khoản","Mật Khẩu","Loại Tài Khoản"};
    Font font=new Font("",1,22);
    
    Object[]row=new Object[4];
    void hienthi(){
        
        ten.setBounds(10,220,100,25);
        frame.add(ten);
        taikhoan.setBounds(10,250,100,25);
        frame.add(taikhoan);
        matkhau.setBounds(10,280,100,25);
        frame.add(matkhau);
        loai.setBounds(10,310,100,25);
        frame.add(loai);
        
        textUser.setBounds(100,220,100,25);
        textTaiKhoan.setBounds(100,250,100, 25);
        textMatKhau.setBounds(100, 280,100, 25);
        textLoai.setBounds(100,310,100, 25);
        
        btnAdd.setBounds(230,220, 100, 25);
        btnDelete.setBounds(230, 265, 100, 25);
        btnUpdate.setBounds(230, 310, 100, 25);
        
        
        pane.setBounds(0,0,880,200);
        
        frame.setLayout(null);
        
        frame.add(pane);
        
        frame.add(textUser);
        frame.add(textTaiKhoan);
        frame.add(textMatKhau);
        frame.add(textLoai);
        frame.add(btnAdd);
        frame.add(btnDelete);
        frame.add(btnUpdate);
        
        frame.setSize(900, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setBackground(Color.cyan);
        table.setForeground(Color.white);
        table.setRowHeight(30);
        table.setFont(font);
        
        btnTrangChu.setBounds(400, 310, 120, 25);
        frame.add(btnTrangChu);
        
        table.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mouseClicked(MouseEvent e){
            
            // i = the index of the selected row
            int i = table.getSelectedRow();
            textUser.setText(model.getValueAt(i, 0).toString());
            textTaiKhoan.setText(model.getValueAt(i, 1).toString());
            textMatKhau.setText(model.getValueAt(i, 2).toString());
            textLoai.setText(model.getValueAt(i, 3).toString());
        }
        });
        
        updateTable();
        
        
        
        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(textUser.getText().equals("")||textTaiKhoan.getText().equals("")||textMatKhau.getText().equals("")||textLoai.getText().equals("")){
                    JOptionPane.showMessageDialog(btnAdd, "Vui Lòng Nhập Đầy Đủ Thông Tin");
                }
                else{
                    try {
                        String Url="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
                        Connection conn=DriverManager.getConnection(Url);
                        String sql="insert into Dangnhap values(?,?,?,?)";
                        PreparedStatement stm=conn.prepareStatement(sql);
                        stm.setString(1, textUser.getText());
                        stm.setString(2, textTaiKhoan.getText());
                        stm.setString(3, textMatKhau.getText());
                        stm.setInt(4,(int)Float.parseFloat(textLoai.getText()));
                        stm.executeUpdate();
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(btnAdd, "Mã Tài Khoản Đã Tồn Tại");
                    }
                    
                    updateTable();
                }
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(textTaiKhoan.getText().equals("")){
                    JOptionPane.showMessageDialog(btnAdd, "Vui Lòng Chọn Tài Khoản");
                }
                else{
                    try {
                        String Url="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
                        Connection conn=DriverManager.getConnection(Url);
                        String sql="delete from Dangnhap where TenDangNhap=?";
                        PreparedStatement stm=conn.prepareStatement(sql);
                        stm.setString(1, textTaiKhoan.getText());
                        stm.executeUpdate();
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(btnAdd,"Không Tìm Thấy");
                    }
                    JOptionPane.showMessageDialog(btnAdd,"Xóa Thành Công");
                    updateTable();
                    
                }
            }
        });
        
        btnUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(textUser.getText().equals("")||textTaiKhoan.getText().equals("")||textMatKhau.getText().equals("")||textLoai.getText().equals("")){
                    JOptionPane.showMessageDialog(btnAdd, "Vui Lòng Chọn Thông Tin Cần Sửa");
                }
                else{
                    try {
                        String Url="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
                        Connection conn=DriverManager.getConnection(Url);
                        String sql="update Dangnhap set Ten=?,MatKhau=?,type=? where TenDangNhap=?";
                        PreparedStatement stm=conn.prepareStatement(sql);
                        stm.setString(1, textUser.getText());
                        stm.setString(2, textTaiKhoan.getText());
                        stm.setInt(3,(int)Float.parseFloat(textMatKhau.getText()));
                        stm.setString(4, textLoai.getText());
                        stm.executeUpdate();
                    } catch (Exception e1) {
                         JOptionPane.showMessageDialog(btnAdd,"Không Tìm Thấy");
                    }
                    JOptionPane.showMessageDialog(btnAdd,"Cập Nhật Thành Công");
                    updateTable();
                }
            }
        });
        
        btnTrangChu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
    }
    
    void deleteData(){
        int n;
        n=table.getRowCount();
        for(int i=0;i<n;i++){
            model.removeRow(0);
        }
    }
    void themDataTable(String a,String b,String c,String d){
        row[0]=a;
        row[1]=b;
        row[2]=c;
        row[3]=d;
        model.addRow(row);
    }
    
    void updateTable(){
        deleteData();
        try {
        String Url="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
        Connection conn=DriverManager.getConnection(Url);
        String sql="select * from Dangnhap";
        PreparedStatement stm=conn.prepareStatement(sql);
        ResultSet rs=stm.executeQuery();
        while(rs.next()){
            row[0]=rs.getString(1);
            row[1]=rs.getString(2);
            row[2]=rs.getString(3);
            row[3]=rs.getString(4);
            model.addRow(row);
        }
        
        } catch (Exception e) {
        JOptionPane.showMessageDialog(btnAdd,"Kết Nối SQL Thất Bại!");
        }
    }
    
    
}

