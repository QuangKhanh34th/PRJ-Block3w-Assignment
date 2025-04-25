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

/**
 *
 * @author ASUS
 */
public class UserDAO {
    // Hàm kiểm tra đăng nhập (trả về User nếu đúng, null nếu sai)
    public User checkLogin(String tentk, String matkhau) {
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
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return user;
    }
}
