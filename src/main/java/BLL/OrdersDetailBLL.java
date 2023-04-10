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
    private OrdersDetailDAL oddDAL;
    public OrdersDetailBLL() {
        oddDAL = new OrdersDetailDAL();
    }
    public List findbyID(int id) throws SQLException {
        List list = new ArrayList();
        list = oddDAL.findbyId(id);
        return list;
    }
    public boolean addorupdateOrdersDetail(OrderDetail odd) throws SQLException {
        boolean result = oddDAL.addorUpdateOrderDetail(odd);
        return result;
    }
    public boolean deleteOrdersDetail(OrderDetail odd) throws SQLException {
        boolean result = oddDAL.deleteOrderDetail(odd);
        return result;
    }
}
