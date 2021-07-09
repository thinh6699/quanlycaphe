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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
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
public class MenuDoAn {
    JFrame frame =new JFrame();
    JTable table=new JTable();
    
    JTextField textId=new JTextField();
    JTextField textName=new JTextField();
    JTextField textLname=new JTextField();
    JTextField textGia=new JTextField();
    
    JButton btnAdd=new JButton("Thêm");
    JButton btnDelete=new JButton("Xóa");
    JButton btnUpdate=new JButton("Sửa");
    
    JScrollPane pane=new JScrollPane(table);
    
    DefaultTableModel model=new DefaultTableModel();
    Object [] columns={"Mã Đồ Ăn","Tên Đồ Ăn","Tên Đồ Ăn","Đơn Giá"};
    Font font=new Font("",1,22);
    
    Object[]row=new Object[4];
    JLabel id = new JLabel("Nhập mã món ăn:");
    JLabel name = new JLabel("Nhập tên món ăn:");
    JLabel loai = new JLabel("Nhập loại:");
    JLabel gia = new JLabel("Nhập giá bán:");
    
    
    void hienthi(){
        id.setBounds(50, 220, 200, 25);
        name.setBounds(50, 250, 200, 25);
        loai.setBounds(50, 280, 200, 25);
        gia.setBounds(50, 310, 200, 25);
        
        
        
        textId.setBounds(200,220,150,25);
        textName.setBounds(200,250,150, 25);
        textLname.setBounds(200, 280,150, 25);
        textGia.setBounds(200,310,150, 25);
      
        
        btnAdd.setBounds(400,220, 100, 25);
        btnUpdate.setBounds(400, 250, 100, 25);
        btnDelete.setBounds(400, 280, 100, 25);
        
        pane.setBounds(0,0,880,200);
        
        frame.setLayout(null);
        
        frame.add(pane);
        
        frame.add(id);
        frame.add(name);
        frame.add(loai);
        frame.add(gia);
        
        
        frame.add(textGia);
        frame.add(textName);
        frame.add(textId);
        frame.add(textLname);
        
        frame.add(btnAdd);
        frame.add(btnDelete);
        frame.add(btnUpdate);
        
        
        frame.setSize(900, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setRowHeight(30);
        table.setFont(font);
        
        updateTable();
  
        
        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int i=table.getSelectedRow();
                textId.setText(model.getValueAt(i,0).toString());
                textName.setText(model.getValueAt(i,1).toString());
                textLname.setText(model.getValueAt(i,2).toString());
                textGia.setText(model.getValueAt(i,3).toString());
            }
        });
        
        
        
        
       
        
        btnAdd.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(textId.getText().equals("")||textName.getText().equals("")||textLname.getText().equals("")||textGia.getText().equals(""))
                    JOptionPane.showMessageDialog(btnAdd, "Vui Lòng Nhập Đầy Đủ Thông Tin");
                else{
                    try {
                        String Url="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
                        Connection conn=DriverManager.getConnection(Url);
                        String sql="insert into MonAn values(?,?,?,?)";
                        PreparedStatement stm=conn.prepareStatement(sql);
                        stm.setInt(1,(int)Float.parseFloat(textId.getText()));
                        stm.setString(2,textName.getText());
                        stm.setString(3,textLname.getText());
                        stm.setFloat(4,Float.parseFloat(textGia.getText()));
                        stm.executeUpdate();
                        JOptionPane.showMessageDialog(btnAdd, "Thêm Thành Công");
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(btnAdd, "Mã Món Ăn Đã Tồn Tại");
                    }
                    updateTable();
                }
            }
        });
        

        
        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(textId.getText().equals(""))
                    JOptionPane.showMessageDialog(btnAdd, "Vui Lòng Nhập Mã Món Ăn Cần xóa");
                else{
                    try {
                        String Url="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
                        Connection conn=DriverManager.getConnection(Url);
                        String sql="delete from MonAn where MaMonAn=?";
                        PreparedStatement stm=conn.prepareStatement(sql);
                        stm.setInt(1,(int)Float.parseFloat(textId.getText()));
                        stm.executeUpdate();
                        JOptionPane.showMessageDialog(btnAdd, "Xóa Thành Công");
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(btnAdd, "Mã Món Ăn Không Tồn Tại");
                    }
                    updateTable();
                }
            }
        });
        
        btnUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(textId.getText().equals("")||textName.getText().equals("")||textLname.getText().equals("")||textGia.getText().equals(""))
                    JOptionPane.showMessageDialog(btnAdd, "Vui Lòng Nhập Đầy Đủ Thông Tin");
                else{
                    try {
                        String Url="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
                        Connection conn=DriverManager.getConnection(Url);
                        String sql="update MonAn set TenMonAn=?,LoaiMonAn=?,Gia=? where MaMonAn=?";
                        PreparedStatement stm=conn.prepareStatement(sql);
                        stm.setString(1,textName.getText());
                        stm.setString(2, textLname.getText());
                        stm.setFloat(3,Float.parseFloat(textGia.getText()));
                        stm.setInt(4,(int)Float.parseFloat(textId.getText()));
                        stm.executeUpdate();
                        JOptionPane.showMessageDialog(btnAdd,"Cập Nhật Thành Công");
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(btnAdd,"Không Tìm Thấy");
                    }
                    updateTable();
                }
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
        String sql="select * from MonAn";
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
