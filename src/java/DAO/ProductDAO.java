/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Product;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ProductDAO {
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT sp.masp, sp.tensp, sp.mota, sp.soluong, sp.dongia, sp.hinhanh, dm.tendm, ncc.tenncc "
                + "FROM tblSanPham sp "
                + "JOIN tblDanhMuc dm ON sp.madm = dm.madm "
                + "JOIN tblNhaCungCap ncc ON sp.mancc = ncc.mancc "
                + "WHERE sp.trangthai = 1";

        try ( Connection con = DBUtils.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("masp");
                String name = rs.getString("tensp");
                String desc = rs.getString("mota");
                int quantity = rs.getInt("soluong");
                double price = rs.getDouble("dongia");
                String imgPath = rs.getString("hinhanh");
                String category = rs.getString("tendm");
                String supplier = rs.getString("tenncc");

                Product sp = new Product(id, name, desc, imgPath, category, supplier, quantity, price);
                list.add(sp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Product getProductByID(String prodID) {
        Product sp = null;
        String sql = "SELECT sp.masp, sp.tensp, sp.mota, sp.soluong, sp.dongia, sp.hinhanh, dm.tendm, ncc.tenncc "
                + "FROM tblSanPham sp "
                + "JOIN tblDanhMuc dm ON sp.madm = dm.madm "
                + "JOIN tblNhaCungCap ncc ON sp.mancc = ncc.mancc "
                + "WHERE sp.masp = ? AND sp.trangthai = 1";

        try ( Connection con = DBUtils.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, prodID);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String id = rs.getString("masp");
                    String name = rs.getString("tensp");
                    String desc = rs.getString("mota");
                    int quantity = rs.getInt("soluong");
                    double price = rs.getDouble("dongia");
                    String imgPath = rs.getString("hinhanh");
                    String category = rs.getString("tendm");
                    String supplier = rs.getString("tenncc");

                    sp = new Product(id, name, desc, imgPath, category, supplier, quantity, price);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sp;
    }
}
