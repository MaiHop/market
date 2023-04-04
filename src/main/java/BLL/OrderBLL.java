/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.Orders;
import DAL.OrdersDAL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class OrderBLL {
    OrdersDAL orders;
    public OrderBLL() {
        orders = new OrdersDAL();
    }
    public List LoadOrders(int page) throws SQLException {
        int numofrecords = 30;
        List list = orders.loadOrders();
        int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return list.subList(from, Math.min(to, size));
    }
    public List findbyDate(Date date) throws SQLException {
        List list = new ArrayList();

        list = orders.findbyDate(date);

        return list;

    }
    public Orders getOrder(int orderID) throws SQLException
    {
        Orders o = orders.getOrder(orderID);
        return o;
    }
    public boolean addOrder(Orders o) throws SQLException {
        boolean result = orders.addOrder(o);
        return result;
    }

    public boolean updateOrder(Orders o) throws SQLException {
        boolean result = orders.updateOrders(o);
        return result;
    }
    public boolean deleteOrder(Orders o) throws SQLException {
        boolean result = orders.deleteOrders(o);
        return result;
    }

}
