/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;



import DAL.*;
import DTO.Customers;
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
public class CustomerDAL {
    private static SessionFactory factory;
    Session session;
    public CustomerDAL() {
        session = HibernateUtils.getSessionFactory().openSession();
    }
    public List loadCustomers() {
        List<Customers> customers;
        session.beginTransaction();
        customers = session.createQuery("FROM Customers", Customers.class).list();
        session.getTransaction().commit();
        return customers;
    }
    public List findbyName(String name ) {
        List<Customers> orders;
        session.beginTransaction();
        orders = session.createQuery("FROM Customers WHERE customers.Fullname LIKE '%"+ name+"%'" ).list();
        session.getTransaction().commit();
        return orders;
    }
    public Customers getCustomer(int CustomerID)
    {
        session.beginTransaction();
        Customers o = session.get(Customers.class, CustomerID);
        session.getTransaction().commit();
        return o;
    }
    public boolean addCustomer(Customers c)
    {
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
        return true;
    }
    public boolean updateCustomers(Customers c)
    {
        session.beginTransaction();
        session.update(c);
        session.getTransaction().commit();
        return true;
    }
    public boolean deleteCustomers(Customers c)
    {
        session.beginTransaction();
        session.delete(c);
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
        CustomerDAL category = new CustomerDAL();
        List<Customers> cus = category.loadCustomers();
        for(Customers c : cus){
 
                System.out.println("CatagoryID: "+ c.getCustomerID());
                System.out.println("Name: "+ c.getAddress());
                System.out.println("Description: "+ c.getFullname());
                System.out.println("Description: "+ c.getOrders().getCustomerID());
                
        }
    }

}
