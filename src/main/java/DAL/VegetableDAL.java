/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.Vegetable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Admin
 */
public class VegetableDAL {
    private static SessionFactory factory;
    Session session;
    public VegetableDAL() {
        session = HibernateUtils.getSessionFactory().openSession();
    }
    public List loadVegetable() {
        List<Vegetable> vegetable;
        session.beginTransaction();
        vegetable = session.createQuery("FROM Vegetable", Vegetable.class).list();
        session.getTransaction().commit();
        return vegetable;
    }
    
    public List filterVegetable() {
        List<Object[]> vegetable;
        session.beginTransaction();
        String hql = "FROM Vegetable AS v INNER JOIN v.Category";
        vegetable = session.createQuery(hql).list();
        session.getTransaction().commit();
        return vegetable;
    }
    public List findbyname(String name ) {
        List<Vegetable> vegetable;
        session.beginTransaction();
        String hql = "FROM Vegetable AS v WHERE v.Vegetable_Name LIKE :name";
        vegetable = session.createQuery(hql)
                .setParameter("name","%" + name + "%" ).list();
        session.getTransaction().commit();
        return vegetable;
    }
    public Vegetable getVegetable(int VegetableID)
    {
        session.beginTransaction();
        Vegetable v = session.get(Vegetable.class, VegetableID);
        session.getTransaction().commit();
        return v;
    }
    public boolean addVegetable(Vegetable v)
    {
        session.beginTransaction();
        session.save(v);
        session.getTransaction().commit();
        return true;
    }
    public boolean updateVegetable(Vegetable v)
    {
        session.beginTransaction();
        session.update(v);
        session.getTransaction().commit();
        return true;
    }
    public boolean deleteVegetable(Vegetable v)
    {
        session.beginTransaction();
        session.delete(v);
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
        VegetableDAL odDAL = new VegetableDAL();
        
        Vegetable od = odDAL.getVegetable(1);
        
                //if(odd.getCategory().getName().contains("Fruit")){
                    System.out.println("CatagoryID: "+ od.getCategory().getName());
                    
                    //System.out.println("Category Name: "+ odd.getCategory().getName());
                //}
                
                //System.out.println("Description: "+ odd.getVegetable().getVegetableName());
            
            
    }
}
