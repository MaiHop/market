/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;



import DAL.*;
import DTO.Orders;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Admin
 */
public class OrdersDAL {
    private static SessionFactory factory;
    Session session;
    public OrdersDAL() {
        session = HibernateUtils.getSessionFactory().openSession();
    }
    public List loadOrders() {
        List<Orders> orders;
        session.beginTransaction();
        orders = session.createQuery("FROM Orders", Orders.class).list();
        session.getTransaction().commit();
        return orders;
    }
    public List findbyDate(Date date ) {
        List<Orders> orders;
        session.beginTransaction();
        orders = session.createQuery("FROM Orders WHERE orders.Date LIKE '%"+ date+"%'" ).list();
        session.getTransaction().commit();
        return orders;
    }
    public Orders getOrder(int OrderID)
    {
        session.beginTransaction();
        Orders o = session.get(Orders.class, OrderID);
        session.getTransaction().commit();
        return o;
    }
    public boolean addOrder(Orders c)
    {
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
        return true;
    }
    public boolean updateOrders(Orders c)
    {
        session.beginTransaction();
        session.update(c);
        session.getTransaction().commit();
        return true;
    }
    public boolean deleteOrders(Orders c)
    {
        session.beginTransaction();
        session.delete(c);
        session.getTransaction().commit();
        return true;
    }

}
