/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom;

import java.awt.Color;
import java.awt.FlowLayout;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author
 */
public class TrangChu {
     JFrame TrangChu=new JFrame("Quán Ăn Đức Thịnh");
     JMenuBar menuBar=new JMenuBar();
     JPanel panelKhuVuc1 =new JPanel();
     JPanel panelKhuVuc2 =new JPanel();
     JPanel panelKhuVuc3 =new JPanel();
     
     JLabel tendangnhap=new JLabel("Tài khoản");
     
     JLabel maBanChon=new JLabel("Mã Bàn Chọn");
     JTextField textMaBanChon=new JTextField();
     JTextField textMaMonAn=new JTextField("Mã Món");
     JLabel maMonAn=new JLabel("Mã Món Ăn");
     JLabel tongTien=new JLabel("Tồng Tiền");
     JLabel loaitien=new JLabel("VND");
     JTextField textTongTien=new JTextField();
     JButton ThanhToan=new JButton("Thanh Toán");
     
     JComboBox LoaiMonAn=new JComboBox();
     JComboBox TenMonAn=new JComboBox();
     JSpinner SoLuong=new JSpinner();
     JButton DatHang=new JButton("Đặt Hàng");
     JButton Xoa=new JButton("Xóa");
     JTextField textmaMonAnXoa=new JTextField();
     JLabel xoamonan=new JLabel("Mã Món Ăn Xóa");
     
     JTable table=new JTable();
     JScrollPane pane=new JScrollPane(table);
     DefaultTableModel model=new DefaultTableModel();
     Object [] columns={"Tên Món Ăn","Số Lượng","Đơn Giá","Tống Giá"};
     Font font=new Font("",1,22);
     Object[]row=new Object[4];
     
         JMenuBar menu1 = new JMenuBar();
    JMenu thongtin = new JMenu("Thông Tin");
    JMenu chinhsua = new JMenu("Chỉnh sửa");
    JMenuItem doimk  = new JMenuItem("Đổi mật khẩu");
    JMenuItem dangxuat = new JMenuItem("Đăng Xuất");
    JMenuItem ban = new JMenuItem("Bàn");
    JMenuItem monan = new JMenuItem("Món Ăn");
    JMenuItem doanhthu = new JMenuItem("Doanh thu");
    JMenuItem taikhoan = new JMenuItem("Tài khoản");
    
    public String userTrangChu;
    public int phanQuyenTrangChu;
    public String tenDangNhap;
    
    FormLogin n=new FormLogin();
    
    JButton update=new JButton("Update");
     
    public void hienthi(){
        
        System.out.println(tenDangNhap);
        //Setting form 
        TrangChu.setSize(1300,720);
        TrangChu.setLocation(10, 10);
        TrangChu.setLayout(null);
        
        TrangChu.add(menuBar);
        
        
        TrangChu.show();
        TrangChu.setDefaultCloseOperation(TrangChu.EXIT_ON_CLOSE);
        
//        panelKhuVuc1.setSize(700,150);
        panelKhuVuc1.setBackground(Color.GRAY);
        panelKhuVuc1.setBounds(0, 60, 700, 200);
        panelKhuVuc1.setLayout(new FlowLayout(FlowLayout.LEFT));
        TrangChu.add(panelKhuVuc1);

        
        panelKhuVuc2.setBackground(Color.GRAY);
        panelKhuVuc2.setBounds(0, 270, 700, 200);
        panelKhuVuc2.setLayout(new FlowLayout(FlowLayout.LEFT));
        TrangChu.add(panelKhuVuc2);
        
        panelKhuVuc3.setBackground(Color.GRAY);
        panelKhuVuc3.setBounds(0, 480, 700, 200);
        panelKhuVuc3.setLayout(new FlowLayout(FlowLayout.LEFT));
        TrangChu.add(panelKhuVuc3);
        
        maBanChon.setBounds(570,25,80,20);
        TrangChu.add(maBanChon);
        
        textMaBanChon.setBounds(650,25,50,20);
        TrangChu.add(textMaBanChon);
        
        //chinh sua table,vitri,hinhdang
        pane.setBounds(700,200,600,350);
        TrangChu.add(pane);
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setBackground(Color.white);
        table.setForeground(Color.black);
        table.setRowHeight(30);
        table.setFont(font);
        
        LoaiMonAn.setBounds(800,50,200,30);
        TrangChu.add(LoaiMonAn);
        
        TenMonAn.setBounds(1060, 50, 200, 30);
        TrangChu.add(TenMonAn);
        
        SoLuong.setBounds(800,90,50,40);
        TrangChu.add(SoLuong);
        
        DatHang.setBounds(860, 90, 100, 40);
        TrangChu.add(DatHang);
        
        textMaMonAn.setBounds(1210, 90,50, 40);
        TrangChu.add(textMaMonAn);
        
        maMonAn.setBounds(1130, 90, 80,40);
        TrangChu.add(maMonAn);
        
        Xoa.setBounds(970, 90, 100, 40);
        TrangChu.add(Xoa);
        
        tongTien.setBounds(750, 550, 60, 40);
        TrangChu.add(tongTien);
        
        textTongTien.setBounds(820,550, 150,40);
        TrangChu.add(textTongTien);
        
        loaitien.setBounds(970, 550, 30, 40);
        TrangChu.add(loaitien);
        
        ThanhToan.setBounds(1010, 550,100, 40);
        TrangChu.add(ThanhToan);
        
        textmaMonAnXoa.setBounds(1210,150,50,40);
        TrangChu.add(textmaMonAnXoa);
        
        xoamonan.setBounds(1110,150, 120, 40);
        TrangChu.add(xoamonan);
        
        tendangnhap.setBounds(1100,10,100,40);
        tendangnhap.setText(userTrangChu);
        TrangChu.add(tendangnhap);
        
        menu1.setBounds(0, 0,140,30);
        TrangChu.add(menu1);
        thongtin.setBounds(180, 150, 100, 40);
        menu1.add(thongtin);
        thongtin.add(doimk);
        thongtin.add(dangxuat);
        chinhsua.setBounds(250,150 , 100, 40);
        menu1.add(chinhsua);
        menu1.add(chinhsua);
        chinhsua.add(ban);
        chinhsua.add(monan);
        chinhsua.add(doanhthu);
        chinhsua.add(taikhoan);
        update.setBounds(1200,0,100,25);
        TrangChu.add(update);
        
        
                  
        updateBan();
        updateLMonAn();
        taoHoaDon();
        
        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int i=table.getSelectedRow();
                String a;
                a= model.getValueAt(i, 0).toString();
                try {
                    String Url="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
                    Connection conn=DriverManager.getConnection(Url);
                    String sql="select MaMonAn from MonAn where TenMonAn=?";
                    PreparedStatement stm=conn.prepareStatement(sql);
                    stm.setString(1,a);
                    ResultSet rs=stm.executeQuery();
                    while(rs.next()){
                        textmaMonAnXoa.setText(rs.getString(1));
                    }
                    
                } catch (Exception e1) {
                }
            }
        });
        
        
    }
    
    void updateBan(){
        try {
            String Url="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
                        Connection conn=DriverManager.getConnection(Url);
                        String sql="select * from Ban";
                        PreparedStatement stm=conn.prepareStatement(sql);
                        ResultSet rs=stm.executeQuery();
                        while(rs.next()){
                            JButton a=new JButton(rs.getString(2)+"                                 ");
                            a.setSize(70,70);
                            if(rs.getString(3).equals("Trống"))
                                a.setBackground(Color.CYAN);
                            else
                                a.setBackground(Color.red);
                            if(rs.getString(4).equals("1"))
                                panelKhuVuc1.add(a);
                            else if(rs.getString(4).equals("2"))
                                panelKhuVuc2.add(a);
                            else{
                                panelKhuVuc3.add(a);
                            }
                            a.setName(rs.getString(1));
                            a.addActionListener(new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    textMaBanChon.setText(a.getName());
                                        try {
                                            deleteData();
                                            String sql1="select TenMonAn,count,Gia,sum(Gia*count) from MonAn,ThongTinHoaDon,HoaDon where MonAn.MaMonAn=ThongTinHoaDon.MaMonAn and HoaDon.MaHoaDon=ThongTinHoaDon.MaHoaDon and TrangThaiHoaDon=0 and MaBan=? group by TenMonAn,count,Gia";
                                            PreparedStatement stm1=conn.prepareStatement(sql1);
                                            stm1.setInt(1,(int)Float.parseFloat(textMaBanChon.getText()));
                                            ResultSet rs1=stm1.executeQuery();
                                            int tongtien=0;
                                            while(rs1.next()){
                                                themtable(rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4));
                                                tongtien=tongtien+(int)Float.parseFloat(rs1.getString(4));
                                            }
                                            textTongTien.setText(String.valueOf(tongtien));
                                    } catch (Exception e1) {
                                    }
                                }
                            });
                        }
                        
        } catch (Exception e) {
        }
    }
    void updateLMonAn(){
        try {
            String Url="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
            Connection conn=DriverManager.getConnection(Url);
            String sql="select LoaiMonAn from MonAn group by LoaiMonAn";
            PreparedStatement stm=conn.prepareStatement(sql);
            ResultSet rs=stm.executeQuery();
            while(rs.next()){
                String name=rs.getString(1);
                LoaiMonAn.addItem(name);
            }
            
        } catch (Exception e) {
        }
        updateTenMonAn((String)LoaiMonAn.getSelectedItem());
        
        LoaiMonAn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println((String)LoaiMonAn.getSelectedItem());
                updateTenMonAn((String)LoaiMonAn.getSelectedItem());
            }
        });
    }
    void updateTenMonAn(String a){
        try {
            TenMonAn.removeAllItems();
            String Url="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
            Connection conn=DriverManager.getConnection(Url);
            String sql="select TenMonAn from MonAn Where LoaiMonAn=?";
            PreparedStatement stm=conn.prepareStatement(sql);
            stm.setString(1,a);
            ResultSet rs=stm.executeQuery();
            while(rs.next()){
                String name=rs.getString(1);
                TenMonAn.addItem(name);
            }
            
        } catch (Exception e) {
        }
        TenMonAn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e1) {
                try {
                    String Url="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
                    Connection conn=DriverManager.getConnection(Url);
                    String sql1="select MaMonAn from MonAn where TenMonAn=?";
                    PreparedStatement stm1=conn.prepareStatement(sql1);
                    stm1.setString(1,(String)TenMonAn.getSelectedItem());
                    ResultSet rs1=stm1.executeQuery();
                    while(rs1.next()){
                        textMaMonAn.setText(rs1.getString(1));
                    }
                } catch (Exception e2) {
                }
            }
        });
    }
    
    void taoHoaDon(){
        DatHang.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String Urlkt="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
                    Connection conn=DriverManager.getConnection(Urlkt);
                    String sqlkt="select TrangThai from Ban where MaBan=?";
                    PreparedStatement stmkt=conn.prepareStatement(sqlkt);
                    stmkt.setInt(1,(int)Float.parseFloat(textMaBanChon.getText()));
                    ResultSet rskt=stmkt.executeQuery();
                    while(rskt.next()){
                        if(rskt.getString(1).equals("Đã chọn")){
                            String sql2="select max(MaThongTinHoaDon) from ThongTinHoaDon";
                            PreparedStatement stm2=conn.prepareStatement(sql2);
                            ResultSet rs2=stm2.executeQuery();
                            int b=0;
                            while(rs2.next()){
                                b=rs2.getInt(1)+1;
                                System.out.println(b);
                            }
                            
                            String sql="select MaHoaDon from HoaDon where MaBan=? and TrangThaiHoaDon=0";
                            PreparedStatement stm=conn.prepareStatement(sql);
                            int a=0;
                            stm.setInt(1,(int)Float.parseFloat(textMaBanChon.getText()));
                            ResultSet rs=stm.executeQuery();
                            while(rs.next()){
                                a=(int)Float.parseFloat(rs.getString(1));
//                                mahoadon=(int)Float.parseFloat(rs.getString(1));
                            }
                            
                            
                            String sql3="insert into ThongTinHoaDon values(?,?,?,?)";
                            PreparedStatement stm3=conn.prepareStatement(sql3);
                            stm3.setInt(1, b);
                            stm3.setInt(2, a);
                            stm3.setInt(3,(int)Float.parseFloat(textMaMonAn.getText()));
                            stm3.setInt(4,(int)Float.parseFloat(SoLuong.getValue().toString()));
                            if((int)Float.parseFloat(SoLuong.getValue().toString())==0){
                                JOptionPane.showMessageDialog(maBanChon,"Vui lòng nhập số lượng!");
                            }
                            else{
                                stm3.executeUpdate();
                                
                                int dongia=0;
                                String sql4="select Gia from MonAn where TenMonAn=?";
                                PreparedStatement stm4=conn.prepareStatement(sql4);
                                stm4.setString(1,(String)TenMonAn.getSelectedItem());
                                ResultSet rs4=stm4.executeQuery();
                                while(rs4.next()){
                                    dongia=(int)Float.parseFloat(rs4.getString(1));
                                }
                                
                                int gia=(int)Float.parseFloat(SoLuong.getValue().toString())*dongia;
                                JOptionPane.showMessageDialog(TrangChu,"Đặt món thành công!");
                                
                                themtable((String)TenMonAn.getSelectedItem(),SoLuong.getValue().toString(),String.valueOf(dongia),String.valueOf(gia));
                                int tongtien=(int)Float.parseFloat(textTongTien.getText());
                                tongtien=tongtien+gia;
                                textTongTien.setText(String.valueOf(tongtien));
                            }
                            
                   
                        }
                        else{
                            String sql="select max(MaHoaDon) from HoaDon";
                            PreparedStatement stm=conn.prepareStatement(sql);
                            ResultSet rs=stm.executeQuery();
                            int a=0;
                            while(rs.next()){
                                a=rs.getInt(1)+1;
                            }
                    
                            String sql1="insert into HoaDon (MaHoaDon,MaBan,TrangThaiHoaDon) values(?,?,0)";
                            PreparedStatement stm1=conn.prepareStatement(sql1);
                            stm1.setInt(1, a);
                            stm1.setInt(2,(int)Float.parseFloat(textMaBanChon.getText()));
                            stm1.executeUpdate();
                             
                            String sql2="select max(MaThongTinHoaDon) from ThongTinHoaDon";
                            PreparedStatement stm2=conn.prepareStatement(sql2);
                            ResultSet rs2=stm2.executeQuery();
                            int b=0;
                            while(rs2.next()){
                                b=rs2.getInt(1)+1;
                                System.out.println(b);
                            }
                            
                            String sql3="insert into ThongTinHoaDon values(?,?,?,?)";
                            PreparedStatement stm3=conn.prepareStatement(sql3);
                            stm3.setInt(1, b);
                            stm3.setInt(2, a);
                            stm3.setInt(3,(int)Float.parseFloat(textMaMonAn.getText()));
                            stm3.setInt(4,(int)Float.parseFloat(SoLuong.getValue().toString()));
                            if((int)Float.parseFloat(SoLuong.getValue().toString())==0){
                                JOptionPane.showMessageDialog(maBanChon,"Vui lòng nhập số lượng!");
                            }
                            else{
                                stm3.executeUpdate();
                                JOptionPane.showMessageDialog(TrangChu,"Đặt món thành công!");
                                String sql4="update Ban set TrangThai=N'Đã chọn' where MaBan=?";
                                PreparedStatement stm4=conn.prepareStatement(sql4);
                                stm4.setInt(1,(int)Float.parseFloat(textMaBanChon.getText()));
                                stm4.executeUpdate();
                                updateKhuVuc();
                                
                                String sql5="select Gia from MonAn where TenMonAn=?";
                                PreparedStatement stm5=conn.prepareStatement(sql5);
                                stm5.setString(1,(String)TenMonAn.getSelectedItem());
                                ResultSet rs5=stm5.executeQuery();
                                int dongia=0;
                                while(rs5.next()){
                                    dongia=(int)Float.parseFloat(rs5.getString(1));
                                }
                                
                                int gia=(int)Float.parseFloat(SoLuong.getValue().toString())*dongia;
                                JOptionPane.showMessageDialog(TrangChu,"Đặt món thành công!");
                                
                                themtable((String)TenMonAn.getSelectedItem(),SoLuong.getValue().toString(),String.valueOf(dongia),String.valueOf(gia));
                                int tongtien=(int)Float.parseFloat(textTongTien.getText());
                                tongtien=tongtien+gia;
                                textTongTien.setText(String.valueOf(tongtien));
                            }
                            
                        }
                    }
                    
                } catch (Exception e1) {
                    
                }
            }
        });
        
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                 updateKhuVuc();
            }
        });
        
        
        Xoa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(textmaMonAnXoa.getText().equals(""))
                    JOptionPane.showMessageDialog(maBanChon,"Vui Lòng Nhập Món Ăn Cần Xóa");
                else{
                    try {
                        String Urlkt="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
                        Connection conn=DriverManager.getConnection(Urlkt);
                        String sqlkt="select MaHoaDon from HoaDon where TrangThaiHoaDon=0 and MaBan=?";
                        PreparedStatement stmkt=conn.prepareStatement(sqlkt);
                        stmkt.setInt(1,(int)Float.parseFloat(textMaBanChon.getText()));
                        ResultSet rskt=stmkt.executeQuery();
                        int mahoadon=0;
                        while(rskt.next()){
                            mahoadon=rskt.getInt(1);
                        }
                        String sql="delete from ThongTinHoaDon where MaHoaDon=? and MaMonAn=?";
                        PreparedStatement stm=conn.prepareStatement(sql);
                        stm.setInt(1, mahoadon);
                        stm.setInt(2,(int)Float.parseFloat(textmaMonAnXoa.getText()));
                        stm.executeUpdate();
                        
                    } catch (Exception e1) {
                         JOptionPane.showMessageDialog(maBanChon,"Không tồn tại!");
                    }
                    
                    int i=table.getSelectedRow();
                    int giaxoa;
                    giaxoa=(int)Float.parseFloat(model.getValueAt(i, 3).toString());
                    textTongTien.setText(String.valueOf((int)Float.parseFloat(textTongTien.getText())-giaxoa));
                    model.removeRow(i);
                    JOptionPane.showMessageDialog(maBanChon,"Xóa Thành Công");
                }
            }
        });
        
        ThanhToan.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(textMaBanChon.getText().equals(""))
                    JOptionPane.showMessageDialog(maBanChon,"Vui Lòng Chọn Bàn Thanh Toán");
                else{
                    try {
                        String Url="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan;user=sa;password=thinh6699";
                        Connection conn=DriverManager.getConnection(Url);
                        String sql="update HoaDon set TrangThaiHoaDon=1,NgayRa=getdate() where MaBan=?";
                        PreparedStatement stm=conn.prepareStatement(sql);
                        stm.setInt(1,(int)Float.parseFloat(textMaBanChon.getText()));
                        stm.executeUpdate();
                        
                        String sql1="update Ban set TrangThai=N'Trống' where MaBan=?";
                        PreparedStatement stm1=conn.prepareStatement(sql1);
                        stm1.setInt(1,(int)Float.parseFloat(textMaBanChon.getText()));
                        stm1.executeUpdate();
                        updateKhuVuc();
                    } catch (Exception e1) {
                    }
                    
                    deleteData();
                    textTongTien.setText("0");
                    JOptionPane.showMessageDialog(maBanChon,"Thanh Toán Thành Công");
                }
            }
        });
        
        doimk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DoiMatKhau n=new DoiMatKhau();
                n.userDMK=userTrangChu;
                n.tenDangNhapDMK=tenDangNhap;
                n.hienthi();
            }
        });
        
        ban.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(phanQuyenTrangChu==1){
                    Ban n=new Ban();
                    n.hienthi();
                }
                else
                    JOptionPane.showMessageDialog(maBanChon,"Loại Tài Khoản Của Bạn Không Thể Vào Chức Năng Này!");
            }
        });
        
        taikhoan.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(phanQuyenTrangChu==1){
                    TaiKhoan n=new TaiKhoan();
                    n.hienthi();           
                }
                else{
                    JOptionPane.showMessageDialog(maBanChon,"Loại Tài Khoản Của Bạn Không Thể Vào Chức Năng Này!");
                }
            }
        });
        
        doanhthu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(phanQuyenTrangChu==1){
                    DoanhThu n=new DoanhThu();
                    n.hienthi();
                }
                else{
                    JOptionPane.showMessageDialog(maBanChon,"Loại Tài Khoản Của Bạn Không Thể Vào Chức Năng Này!");
                }
            }
        });
        
        dangxuat.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                FormLogin n=new FormLogin();
                TrangChu.setVisible(false);
                n.hienthi();
            }
        });
        
        monan.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(phanQuyenTrangChu==1){
                    MenuDoAn n=new MenuDoAn();
                    n.hienthi();
                    
                }
                else{
                    JOptionPane.showMessageDialog(maBanChon,"Loại Tài Khoản Của Bạn Không Thể Vào Chức Năng Này!");
                }
            }
        });
        

        
    }
    void themtable(String a,String b,String c,String d){
        row[0]=a;
        row[1]=b;
        row[2]=c;
        row[3]=d;
        model.addRow(row);
    }
    void deleteData(){
        int n;
        n=table.getRowCount();
        for(int i=0;i<n;i++){
            model.removeRow(0);
        }
    }
    void updateKhuVuc(){
         panelKhuVuc1.removeAll();
         panelKhuVuc2.removeAll();
         panelKhuVuc3.removeAll();
         TrangChu.setVisible(false);
         TrangChu.setVisible(true);
         updateBan();
    }
    
}
