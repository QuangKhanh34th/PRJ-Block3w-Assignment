/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Supplier;
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
public class SupplierDAO {

    public List<Supplier> getAllSupplier() {
        Connection cn = null;
        List<Supplier> result = new ArrayList<>();

        try {
            cn = DBUtils.getConnection();

            if (cn != null) {
                String sql = "SELECT mancc, tenncc, diachi FROM tblNhaCungCap WHERE trangthai = 1";
                PreparedStatement stmt = cn.prepareStatement(sql);
                ResultSet table = stmt.executeQuery();

                while (table.next()) {
                    String supplierID = table.getString("mancc");
                    String supplierName = table.getString("tenncc");
                    String supplierAddress = table.getString("diachi");

                    result.add(new Supplier(supplierID, supplierName, supplierAddress));
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

    public Supplier getSupplierByID(String supplierID) {
        Connection cn = null;
        Supplier result = null;

        try {
            cn = DBUtils.getConnection();
            
            if (cn!=null) {
                String sql = "SELECT mancc, tenncc, diachi from tblNhaCungCap WHERE mancc = ?";
                PreparedStatement stmt = cn.prepareStatement(sql);
                stmt.setString(1, supplierID);
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    String id = rs.getString("mancc");
                    String supplierName = rs.getString("tenncc");
                    String supplierAddress = rs.getString("diachi");
                    
                    result = new Supplier(id, supplierName, supplierAddress);
                }
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
