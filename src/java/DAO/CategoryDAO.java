/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Category;
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
public class CategoryDAO {

    public List<Category> getAllCategory() {
        Connection cn = null;
        List<Category> result = new ArrayList<>();

        try {
            cn = DBUtils.getConnection();

            if (cn != null) {
                String sql = "SELECT madm, tendm FROM tblDanhMuc WHERE trangthai = 1";
                PreparedStatement stmt = cn.prepareStatement(sql);
                ResultSet table = stmt.executeQuery();

                while (table.next()) {
                    String categoryID = table.getString("madm");
                    String categoryName = table.getString("tendm");

                    result.add(new Category(categoryID, categoryName));
                }
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    //get Category that is active only
    public Category getCategoryByID(String categoryID) {
        Connection cn = null;
        Category result = null;

        try {
            cn = DBUtils.getConnection();
            
            if (cn!=null) {
                String sql = "SELECT madm, tendm FROM tblDanhMuc WHERE madm = ? AND trangthai = 1";
                PreparedStatement stmt = cn.prepareStatement(sql);
                stmt.setString(1, categoryID);
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    String id = rs.getString("madm");
                    String name = rs.getString("tendm");
                    
                    result = new Category(id, name);
                }
                
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
}
