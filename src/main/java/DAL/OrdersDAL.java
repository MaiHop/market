/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;



import DTO.Customers;
import DTO.OrderDetail;
import DTO.Orders;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        String hql = "FROM Orders AS o WHERE o.Date LIKE :date";
        orders = session.createQuery(hql)
                .setParameter("date","%" + date + "%" ).list();
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
    public static void main(String[] args) throws ParseException {
        
        try{
            factory = new Configuration().configure().buildSessionFactory();
           
        }catch(Throwable ex){
            System.err.println("Failed to create sessionFactory object."+ ex);
            throw new ExceptionInInitializerError(ex);
        }
        
        OrdersDAL dal = new OrdersDAL();
        List<OrderDetail> odd = dal.getOrder(1).getListorder();
        for(OrderDetail d : odd){
           
            System.out.println("DAL.OrdersDAL.main()"+ d.getVegetable().getVegetable_Name());
        }
}
}
