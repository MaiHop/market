/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;



import DTO.Customers;
import DTO.OrderDetail;
import DTO.Orders;
import DTO.Vegetable;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Admin
 */
public class OrdersDetailDAL {
    private static SessionFactory factory;
    Session session;
    public OrdersDetailDAL() {
        session = HibernateUtils.getSessionFactory().openSession();
    }

    public List findbyId(int orderId ) {
        List<OrderDetail> OrderDetail;
        session.beginTransaction();
        String hql = "FROM OrderDetail AS o WHERE o.OrderID  =:orderId";
        OrderDetail = session.createQuery(hql)
                .setParameter("orderId", orderId).list();
        session.getTransaction().commit();
        return OrderDetail;
    }
    public OrderDetail getOrderDetail(int OrderID)
    {
        session.beginTransaction();
        OrderDetail o = session.get(OrderDetail.class, OrderID);
        session.getTransaction().commit();
        return o;
    }
    public boolean addorUpdateOrderDetail(OrderDetail od)
    {
        session.beginTransaction();
        session.saveOrUpdate(od);
        session.getTransaction().commit();
        return true;
    }
    public boolean deleteOrderDetail(OrderDetail od)
    {
        session.beginTransaction();
        String hql = "DELETE FROM OrderDetail "  + "WHERE OrderID  = :orderID ";
        Query query = session.createQuery(hql);
        query.setParameter("orderID", od.getOrderID());
        query.executeUpdate();
        session.getTransaction().commit();
        return true;
    }
    public static void main(String[] args) {
        
        try{
            factory = new Configuration().configure().buildSessionFactory();
           
        }catch(Throwable ex){
            System.err.println("Failed to create sessionFactory object."+ ex);
            throw new ExceptionInInitializerError(ex);
        }
         OrdersDetailDAL odDAL = new OrdersDetailDAL();
        List<OrderDetail> odd = odDAL.findbyId(54);
         for(OrderDetail od : odd){
                System.out.println("CatagoryID: "+ od.getOrderID());
                System.out.println("Name: "+ od.getVegetableID());
         }
                
            //}
    }
}
