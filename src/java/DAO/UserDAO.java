/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.User;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class UserDAO {

    // Hàm kiểm tra đăng nhập (trả về User nếu đúng, null nếu sai)
    public User checkUser(String tentk, String matkhau) {
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM tblTaiKhoan WHERE tentk = ? AND matkhau = ? AND trangthai = 1";
            ps = conn.prepareStatement(sql);
            ps.setString(1, tentk);
            ps.setString(2, matkhau);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getString("tentk"),
                        rs.getString("matkhau"),
                        rs.getBoolean("trangthai"),
                        rs.getString("email"),
                        rs.getString("nhomtk")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    public User checkUser(String tentk) {
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM tblTaiKhoan WHERE tentk = ? AND trangthai = 1";
            ps = conn.prepareStatement(sql);
            ps.setString(1, tentk);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getString("tentk"),
                        rs.getString("matkhau"),
                        rs.getBoolean("trangthai"),
                        rs.getString("email"),
                        rs.getString("nhomtk")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    
    //return 1 if successfully add, else return 0 for checking in servlets
    public int addUser(User input) {
        int result = 0;
        Connection cn = null;

        try {
            cn = DBUtils.getConnection();
            
            if (cn!=null) {
                String sql ="INSERT INTO tblTaiKhoan (tentk, matkhau, trangthai, email, nhomtk) VALUES (?,?,?,?,?)";
                PreparedStatement stmt = cn.prepareStatement(sql);
                stmt.setString(1, input.getUsername());
                stmt.setString(2, input.getPassword());
                stmt.setInt(3, 1); //default to active
                stmt.setString(4, input.getEmail());
                stmt.setString(5, input.getUserGroup());
                result = stmt.executeUpdate();
                stmt.close();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[CustomerDAO.java] error adding Customer");
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
            
        return result;
    }
}
