/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;


import DAL.*;
import DTO.Customers;
import DTO.Orders;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author caothanh
 */
public class HibernateUtils {
    
    private static final SessionFactory sessionFactory = buildSessionFactory();
 
    
 
    private static SessionFactory buildSessionFactory() {
        
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder() //
                .configure() // Load hibernate.cfg.xml from resource folder by default
                .build();
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    public static void close() {
        getSessionFactory().close();
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            Customers od = new Customers();
            session.beginTransaction();
                        List<Customers> cus = session.createQuery("from customers").list();
                        cus.forEach(System.out::println);
                        session.getTransaction().commit();       
   }
}
}

