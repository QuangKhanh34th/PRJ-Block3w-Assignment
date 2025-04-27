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

    public int addProduct(Product target) {
        Connection cn = null;
        int result = 0;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "INSERT INTO tblSanPham (masp, tensp, mota, soluong, dongia, hinhanh, trangthai, madm, mancc)"
                        + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = cn.prepareStatement(sql);
                stmt.setString(1, target.getProdID());
                stmt.setString(2, target.getProdName());
                stmt.setString(3, target.getProdDescription());
                stmt.setInt(4, target.getProdQuantity());
                stmt.setDouble(5, target.getProdPrice());
                stmt.setString(6, target.getProdImagePath());
                stmt.setInt(7, 1);
                stmt.setString(8, target.getProdCategory());
                stmt.setString(9, target.getProdSupplier());

                result = stmt.executeUpdate();
                stmt.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
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

    public int deleteProduct(String prodID) {
        Connection cn = null;
        int result = 0;

        try {
            cn = DBUtils.getConnection();

            if (cn != null) {
                String sql = "UPDATE tblSanPham SET trangthai = 0 WHERE masp = ?";
                PreparedStatement stmt = cn.prepareStatement(sql);
                stmt.setString(1, prodID);
                result = stmt.executeUpdate();
                stmt.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public int updateProduct(Product target) {
        Connection cn = null;
        int result = 0;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "UPDATE tblSanPham SET tensp = ?, mota = ?, soluong = ?, dongia = ?, madm = ?, mancc = ? WHERE masp = ?";
                PreparedStatement stmt = cn.prepareStatement(sql);
                stmt.setString(1, target.getProdName());
                stmt.setString(2, target.getProdDescription());
                stmt.setInt(3, target.getProdQuantity());
                stmt.setDouble(4, target.getProdPrice());
                stmt.setString(5, target.getProdCategory());
                stmt.setString(6, target.getProdSupplier());
                stmt.setString(7, target.getProdID());

                result = stmt.executeUpdate();
                stmt.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
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
