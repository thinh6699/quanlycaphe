/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author nguye
 */
public class DAO {
    private Connection conn;
    public  DAO(){
        try {
            String uRL="jdbc:sqlserver://127.0.0.1:1433;databaseName=quanliquanan";
            String user="sa";
            String pass="123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn=DriverManager.getConnection(uRL, user, pass);
            System.out.println("Thành công");
            String sql="select * from DangNhap";
 
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1, "vongthixoan");
            ps.setString(2,"654321");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
      
             
        } catch (Exception e) {
            System.out.println("Lỗi "+e);
        }
    }
    
    public void hienthi(){
        String sql="select count(*) from DangNhap where TenDangNhap='?' and MatKhau='?'";
        try {
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1, "vongthixoan");
            ps.setString(2,"654321");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("a");
    }
            
            
    public static void main(String[] args) {
        DAO a=new DAO();
        
       
        
    }
}
