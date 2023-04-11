/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;



import DTO.Category;
import DTO.Customers;
import DTO.Vegetable;
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
public class CategoryDAL {
    private static SessionFactory factory;
    Session session;
    public CategoryDAL() {
        session = HibernateUtils.getSessionFactory().openSession();
    }
    public List loadCategory() {
        List<Category> category;
        session.beginTransaction();
        category = session.createQuery("FROM Category", Category.class).list();
        session.getTransaction().commit();
        return category;
    }
    
    public Category findbyname(String name ) {
        List<Category> category;
        Category cateneed = null;
        session.beginTransaction();
        String hql = "FROM Category AS c WHERE c.Name LIKE :name";
        category = session.createQuery(hql).setParameter("name","%"+ name +"%"  ).list();
        for (Category c : category){
            if(c.getName().equals(name)){
                cateneed = c;
            }
        }
        session.getTransaction().commit();
        return cateneed;
    }
    
    public Category getCategory(int CategoryID)
    {
        session.beginTransaction();
        Category c = session.get(Category.class, CategoryID);
        session.getTransaction().commit();
        return c;
    }
    
    public boolean addCategory(Category c)
    {
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
        return true;
    }
    
    public boolean updateCategory(Category c)
    {
        session.beginTransaction();
        session.update(c);
        session.getTransaction().commit();
        return true;
    }
    
    public boolean deleteCategory(Category c)
    {
        session.beginTransaction();
        session.delete(c);
        session.getTransaction().commit();
        return true;
    }
   
}
