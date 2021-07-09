/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverAction;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import static javafx.scene.input.KeyCode.J;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguye
 */
public class Ban {
    JFrame ban=new JFrame("Bàn");

    
    
    JTextField textMaBan,textTenBan,textMaKhuVuc;
    JButton them,sua,xoa;
    JPanel panelInput,panelButton,panelDataView;
    JLabel maBan,tenBan,maKhuVuc;

    JTable table=new JTable();
    JScrollPane pane=new JScrollPane(table);
    DefaultTableModel model=new DefaultTableModel();
    Object [] columns={"Mã Bàn","Tên Bàn","Trạng Thái","Khu Vực"};
    Font font=new Font("",1,22);
    
    Object[]row=new Object[4];
    
    
  
    
    Ban(){
        ban.setSize(500,500);
        ban.setLocation(300, 150);
        ban.setLayout(null);
        
        //chinh sua table,vitri,hinhdang
        pane.setBounds(0,120,500,350);
        ban.add(pane);
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setBackground(Color.white);
        table.setForeground(Color.black);
        table.setRowHeight(30);
        table.setFont(font);
        
        
        textMaBan=new  JTextField();
        
        textTenBan=new JTextField();
        
        textMaKhuVuc =new JTextField();
        
        them=new JButton("Thêm");
        sua=new JButton("Sửa");
        xoa=new JButton("Xóa");
        panelInput=new JPanel();
        panelButton=new JPanel();
        panelDataView=new JPanel();
        maBan=new JLabel("Mã Bàn");
        tenBan=new JLabel("Tên Bàn");
        maKhuVuc=new JLabel("MKV");
      
        panelInput.setLayout(null);
        panelInput.setSize(240,100);
        panelInput.setLocation(0,20);
        ban.add(panelInput);
        panelButton.setLayout(null);
        panelButton.setSize(300,80);
        panelButton.setLocation(200,20);
        panelButton.setLayout(new FlowLayout());
        ban.add(panelButton);

        maBan.setBounds(10,10,50,20);
        panelInput.add(maBan);
        tenBan.setBounds(10, 40,50,20);
        panelInput.add(tenBan);
        maKhuVuc.setBounds(10,70,50,20);
        panelInput.add(maKhuVuc);
        
        textMaBan.setBounds(70,10,130,20);
        panelInput.add(textMaBan);
        textTenBan.setBounds(70,40,130,20);
        panelInput.add(textTenBan);
        textMaKhuVuc.setBounds(70,70,130,20);
        panelInput.add(textMaKhuVuc);
        
        panelButton.add(them);
        
        panelButton.add(sua);
        
        panelButton.add(xoa);
        
        update();
        
        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int i=table.getSelectedRow();
                textMaBan.setText(model.getValueAt(i, 0).toString());
                textTenBan.setText(model.getValueAt(i, 1).toString());
                textMaKhuVuc.setText(model.getValueAt(i, 3).toString());
                
            }
        });
        
        xoa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                row[0]=textMaBan.getText();
                if(textMaBan.getText().equals("")){
                        JOptionPane.showMessageDialog(panelButton,"Vui Lòng Nhập Mã Bàn Cần Xóa!");
                    }
                else{
                    try {
                        String Url="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
                        Connection conn=DriverManager.getConnection(Url);
                        String sql="delete from Ban where MaBan=?";
                        PreparedStatement stm=conn.prepareStatement(sql);
                        stm.setInt(1,(int) Float.parseFloat(textMaBan.getText()));
                        stm.executeUpdate();
                        JOptionPane.showMessageDialog(panelButton,"Xóa Thành Công!");
                        update();
                    } catch (Exception e1) {
                         JOptionPane.showMessageDialog(panelButton,"Lỗi Kết Nối SQL: "+e1);
                    }
                }
            }
        });
        
        
        them.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    row[0]=textMaBan.getText();
                    row[1]=textTenBan.getText();
                    row[2]="Trống";
                    row[3]=textMaKhuVuc.getText();
                    if(textMaBan.getText().equals("") || textTenBan.getText().equals("") ||textMaKhuVuc.getText().equals("")){
                        JOptionPane.showMessageDialog(panelButton,"Vui Lòng Nhập Thông Tin Đầy Đủ!");
                    }
                    else{
                        try {
                            String Url="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
                            Connection conn=DriverManager.getConnection(Url);
                            String sql="insert into Ban values (?,?,N'Trống',?)";
                            PreparedStatement stm=conn.prepareStatement(sql);
                            stm.setInt(1,(int) Float.parseFloat(textMaBan.getText()));
                            stm.setString(2,textTenBan.getText());
                            stm.setInt(3,(int)Float.parseFloat(textMaKhuVuc.getText()));
                            stm.executeUpdate();
                            JOptionPane.showMessageDialog(panelButton,"Thêm Thành Công!");
                            update();
                        }
                        catch (Exception e1) {
                             JOptionPane.showMessageDialog(panelButton,"Kết Nối SQL Thất Bại!" +e1);
                        }
                       
                    }
            }
        });
        
        sua.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                row[0]=textMaBan.getText();
                row[1]=textTenBan.getText();
                row[2]="Trống";
                row[3]=textMaKhuVuc.getText();
                if(textMaBan.getText().equals("")|| textTenBan.getText().equals("")||textMaKhuVuc.getText().equals("")){
                    JOptionPane.showMessageDialog(panelInput,"Vui Lòng Nhập Đầy Đủ Thông Tin!");
                }
                else{
                    try {
                        String Url="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
                        Connection conn=DriverManager.getConnection(Url);
                            String sql="update Ban set TenBan=?,MaKhuVuc=? where MaBan=?";
                            PreparedStatement stm=conn.prepareStatement(sql);
                            stm.setInt(3,(int) Float.parseFloat(textMaBan.getText()));
                            stm.setString(1,textTenBan.getText());
                            stm.setInt(2,(int)Float.parseFloat(textMaKhuVuc.getText()));
                            stm.executeUpdate();
                            JOptionPane.showMessageDialog(panelInput,"Cập Nhật Thành Công");
                            update();
                        
                        
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(panelInput,"Lỗi SQL: "+e1);
                    }
                }
            }
        });
        
    }
  



    
public void hienthi(){
    ban.show();
}
public void clearText(){
    textMaBan.setText(null);
    textTenBan.setText(null);
    textMaKhuVuc.setText(null);
}

void inputData(){
    try {
        String Url="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
        Connection conn=DriverManager.getConnection(Url);
        String sql="select * from Ban";
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
        JOptionPane.showMessageDialog(panelButton,"Kết Nối SQL Thất Bại!");
    }
}

void deleteData(){
    int n;
    n=table.getRowCount();
    for(int i=0;i<n;i++){
        model.removeRow(0);
    }
}
void update(){
    deleteData();
    inputData();
    
}
 
}

