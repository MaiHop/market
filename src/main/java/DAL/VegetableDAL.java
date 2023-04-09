/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.Vegetable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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
    

    public List filterVegetable(String name, String catagoryid, String unit, String amount, String price) {
        List<Vegetable> vegetable;
        session.beginTransaction();
        String hql ="";
        String amountquery = null;
        if(!amount.equals("")){
            amountquery = " AND " +amount;
            if(price.equals("Descending")){
            hql = "FROM Vegetable  "
                + "WHERE Vegetable_Name LIKE :name "
                + "AND str(CatagoryID) LIKE :catagoryid "
                + "AND Unit LIKE :unit "
                + amountquery
                + " ORDER BY Price DESC  ";
            }else if(price.equals("Ascending")){
                hql = "FROM Vegetable  "
                    + "WHERE Vegetable_Name LIKE :name "
                    + "AND str(CatagoryID) LIKE :catagoryid "
                    + "AND Unit LIKE :unit "
                    + amountquery
                    + " ORDER BY Price ASC  ";
            }else{
                hql = "FROM Vegetable  "
                    + "WHERE Vegetable_Name LIKE :name "
                    + "AND str(CatagoryID) LIKE :catagoryid "
                    + "AND Unit LIKE :unit "
                    + amountquery;
            }         
        }else{
            if(price.equals("Descending")){
            hql = "FROM Vegetable  "
                + "WHERE Vegetable_Name LIKE :name "
                + "AND str(CatagoryID) LIKE :catagoryid "
                + "AND Unit LIKE :unit "
                + amountquery
                + " ORDER BY Price DESC  ";
            }else if(price.equals("Ascending")){
                hql = "FROM Vegetable  "
                    + "WHERE Vegetable_Name LIKE :name "
                    + "AND str(CatagoryID) LIKE :catagoryid "
                    + "AND Unit LIKE :unit "
                    + amountquery
                    + " ORDER BY Price ASC  ";
            }else{
                hql = "FROM Vegetable  "
                    + "WHERE Vegetable_Name LIKE :name "
                    + "AND str(CatagoryID) LIKE :catagoryid "
                    + "AND Unit LIKE :unit "
                    + amountquery;
            }     
        }
        vegetable = session.createQuery(hql)
                .setParameter("name", "%" + name + "%")
                .setParameter("catagoryid",  "%" + catagoryid + "%" )
                .setParameter("unit", "%" + unit+ "%").list();
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
    
//        public static void main(String[] args) {
//        
//        try{
//            factory = new Configuration().configure().buildSessionFactory();
//        }catch(Throwable ex){
//            System.err.println("Failed to create sessionFactory object."+ ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//        VegetableDAL odDAL = new VegetableDAL();
//        //List<Vegetable> od = odDAL.findbyname();
//        //List<Vegetable> od = odDAL.filterVegetable("o","1", "kg", "30<= Amount AND Amount <=100", "");       
//        for(Vegetable v : od){
//            System.out.println("ID: "+ v.getVegetableID());
//            System.out.println("Name: "+ v.getVegetable_Name());
//        }                        
//    }
}
