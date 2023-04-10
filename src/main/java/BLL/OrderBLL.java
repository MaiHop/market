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
    private OrdersDAL odDAL;
    public OrderBLL() {
        odDAL = new OrdersDAL();
    }
    public List LoadOrders(int page) throws SQLException {
        int numofrecords = 30;
        List list = odDAL.loadOrders();
        int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return list.subList(from, Math.min(to, size));
    }
    public List findbyDate(Date date) throws SQLException {
        List list = new ArrayList();

        list = odDAL.findbyDate(date);

        return list;

    }
    public Orders getOrder(int orderID) throws SQLException
    {
        Orders o = odDAL.getOrder(orderID);
        return o;
    }
    public boolean addOrder(Orders o) throws SQLException {
        boolean result = odDAL.addOrder(o);
        return result;
    }

    public boolean updateOrder(Orders o) throws SQLException {
        boolean result = odDAL.updateOrders(o);
        return result;
    }
    public boolean deleteOrder(Orders o) throws SQLException {
        boolean result = odDAL.deleteOrders(o);
        return result;
    }
    public long getTotal(){
           try {
               return odDAL.getTotal();
           }
           catch (Exception e) {
               e.printStackTrace();
           }
           return 0;
    }
      public List<Orders> getListOrder() {

       try{
           return odDAL.loadOrders();
       }
       catch (Exception e) {
           e.printStackTrace();
       }
       return null;
    }
}
