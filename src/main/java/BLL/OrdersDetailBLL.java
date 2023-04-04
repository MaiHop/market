/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.OrdersDetailDAL;
import DTO.OrderDetail;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class OrdersDetailBLL {
    OrdersDetailDAL orderdetail;
    public OrdersDetailBLL() {
        orderdetail = new OrdersDetailDAL();
    }
    public List LoadOrders(int page) throws SQLException {
        int numofrecords = 30;
        List list = orderdetail.loadOrderDetail();
        int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return list.subList(from, Math.min(to, size));
    }
    public List findbyID(int id) throws SQLException {
        List list = new ArrayList();

        list = orderdetail.findbyId(id);

        return list;

    }
}
