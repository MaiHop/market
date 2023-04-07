/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.OrdersDetailDAL;
import DTO.OrderDetail;
import DTO.Orders;
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
    public List findbyID(int id) throws SQLException {
        List list = new ArrayList();
        list = orderdetail.findbyId(id);
        return list;
    }
    public boolean addorupdateOrdersDetail(OrderDetail odd) throws SQLException {
        boolean result = orderdetail.addorUpdateOrderDetail(odd);
        return result;
    }
    public boolean deleteOrdersDetail(OrderDetail odd) throws SQLException {
        boolean result = orderdetail.deleteOrderDetail(odd);
        return result;
    }
}
