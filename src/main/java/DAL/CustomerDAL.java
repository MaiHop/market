/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;



import DTO.Customers;
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
        List<Customers> customers;
        session.beginTransaction();
        String hql = "FROM Customers AS c WHERE c.Fullname LIKE :name";
        customers = session.createQuery(hql)
                .setParameter("name","%" + name + "%" ).list();
        session.getTransaction().commit();
        return customers;
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
         CustomerDAL odDAL = new CustomerDAL();
         Customers c = new Customers();
         c.setCustomerID(10);
         c.setFullname("dsdfs");
         c.setPassword("afsada");
         c.setAddress("222222");
         c.setCity("BinhDuong");
         odDAL.updateCustomers(c);
         Customers customers =  odDAL.getCustomer(10);
         //for(Iterator iterator = customers.iterator(); iterator.hasNext();){
                //Customers cate = (Customers) iterator.next();
                System.out.println("CatagoryID: "+ customers.getCustomerID());
                System.out.println("Name: "+ customers.getFullname());
                System.out.println("Description: "+ customers.getAddress());
            //}
    }

}
