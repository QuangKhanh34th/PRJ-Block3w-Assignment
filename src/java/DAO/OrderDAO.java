/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.DetailedOrder;
import Model.Order;
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
public class OrderDAO {

    //get all order of a specific user
    public List<Order> getAllOrders(String username) {
        Connection cn = null;
        List<Order> result = new ArrayList<>();

        try {
            cn = DBUtils.getConnection();

            if (cn != null) {
                String sql = "SELECT mahd, ngaydh, trangthaixuly, makh FROM tblHoaDon WHERE makh = ?";
                PreparedStatement stmt = cn.prepareStatement(sql);
                stmt.setString(1, username);
                ResultSet table = stmt.executeQuery();

                while (table.next()) {
                    String orderID = table.getString("mahd");
                    String orderTime = table.getString("ngaydh");
                    int orderState = table.getInt("trangthaixuly");
                    String orderUser = table.getString("makh");

                    result.add(new Order(orderID, orderTime, orderState, orderUser));
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

    public Order getOrderByID(String targetID) {
        Connection cn = null;
        Order result = null;

        try {
            cn = DBUtils.getConnection();

            if (cn != null) {
                String sql = "SELECT mahd, ngaydh, trangthaixuly, makh FROM tblHoaDon WHERE mahd = ?";
                PreparedStatement stmt = cn.prepareStatement(sql);
                stmt.setString(1, targetID);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String orderID = rs.getString("mahd");
                    String orderTime = rs.getString("ngaydh");
                    int orderState = rs.getInt("trangthaixuly");
                    String orderUser = rs.getString("makh");

                    result = new Order(orderID, orderTime, orderState, orderUser);
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

    //targetID = orderID
    public List<DetailedOrder> getDetailedOrderByID(String username, String targetID) {
        Connection cn = null;
        List<DetailedOrder> result = new ArrayList<>();

        try {
            cn = DBUtils.getConnection();

            if (cn != null) {
                String sql = "SELECT chd.mahd, chd.masp, chd.soluong, chd.dongia, chd.khuyenmai, chd.giatri \n"
                        + "FROM tblChiTietHoaDon chd JOIN tblHoaDon hd ON chd.mahd = hd.mahd\n"
                        + "WHERE hd.makh = ? AND chd.mahd = ?";
                PreparedStatement stmt = cn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, targetID);
                ResultSet table = stmt.executeQuery();

                while (table.next()) {
                    String detailedID = table.getString("mahd");
                    String detailedProdID = table.getString("masp");
                    int detailedAmount = table.getInt("soluong");
                    double detailedPrice = table.getDouble("dongia");
                    double detailedDiscount = table.getDouble("khuyenmai");
                    double detailedTruePrice = table.getDouble("giatri");

                    result.add(new DetailedOrder(detailedID,
                            detailedProdID, detailedAmount,
                            detailedPrice, detailedDiscount,
                            detailedTruePrice));
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

    public int createOrder(Order newOrder) {
        Connection cn = null;
        int result = 0;

        try {
            cn = DBUtils.getConnection();

            if (cn != null) {
                String sql = "INSERT INTO tblHoaDon (mahd, ngaydh, trangthaixuly, makh) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = cn.prepareStatement(sql);

                stmt.setString(1, newOrder.getOrderID());
                stmt.setString(2, newOrder.getOrderTime());
                stmt.setInt(3, newOrder.getOrderState());
                stmt.setString(4, newOrder.getOrderUser());

                result = stmt.executeUpdate();

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

    //use list of detailed order as paramter to add multiple DetailedOrder under one Order
    public int createDetailedOrders(List<DetailedOrder> detailedOrdersList, String orderID) {
        Connection cn = null;
        int result = 0;
        try {
            cn = DBUtils.getConnection();

            if (cn != null) {
                String sql = "INSERT INTO tblChiTietHoaDon (mahd, masp, soluong, dongia, khuyenmai, giatri) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = cn.prepareStatement(sql);

                for (DetailedOrder detailedOrder : detailedOrdersList) {
                    stmt.setString(1, orderID);
                    stmt.setString(2, detailedOrder.getDetailedProdID());
                    stmt.setInt(3, detailedOrder.getDetailedAmount());
                    stmt.setDouble(4, detailedOrder.getDetailedPrice());
                    stmt.setDouble(5, detailedOrder.getDetailedDiscount());
                    stmt.setDouble(6, detailedOrder.getDetailedTruePrice());

                    stmt.addBatch(); //Add the insert statement to the batch for multiple execute at once
                }

                int[] results = stmt.executeBatch(); //execute the stored query
                result = results.length; //Number of rows affected
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
