/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.Customers;
import DAL.CustomerDAL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CustomerBLL {
    CustomerDAL customers;
    public CustomerBLL() {
        customers = new CustomerDAL();
    }
    public List LoadCustomers(int page) throws SQLException {
        int numofrecords = 30;
        List list = customers.loadCustomers();
        int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return list.subList(from, Math.min(to, size));
    }
    public List findbyName(String name) throws SQLException {
        List list = new ArrayList();

        list = customers.findbyName(name);

        return list;

    }
    public Customers getCustomer(int customerID) throws SQLException
    {
        Customers o = customers.getCustomer(customerID);
        return o;
    }
    public boolean addCustomer(Customers c) throws SQLException {
        boolean result = customers.addCustomer(c);
        return result;
    }

    public boolean updateCustomer(Customers c) throws SQLException {
        boolean result = customers.updateCustomers(c);
        return result;
    }
    public boolean deleteCustomer(Customers c) throws SQLException {
        boolean result = customers.deleteCustomers(c);
        return result;
    }
//    public static void main(String[] args) throws SQLException {
//        CustomerBLL bll = new CustomerBLL();
//        Customers customers = bll.getCustomer((char)1);
//        System.out.println(customers.getFullname());
//        System.out.println(customers.getAddress());
//    }

}
