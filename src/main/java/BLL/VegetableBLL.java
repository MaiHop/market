/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.VegetableDAL;
import DTO.Vegetable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class VegetableBLL {
    VegetableDAL vegetable;
    public VegetableBLL() {
        vegetable = new VegetableDAL();
    }
    public List LoadVegetables(int page) throws SQLException {
        int numofrecords = 30;
        List list = vegetable.loadVegetable();
        int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return list.subList(from, Math.min(to, size));
    }
    public List LoadVegetablesCbb(int page) throws SQLException {
        int numofrecords = 3;
        List list = vegetable.loadVegetable();
        int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return list.subList(from, Math.min(to, size));
    }
    public List findbyname(String name) throws SQLException {
        List list = new ArrayList();

        list = vegetable.findbyname(name);

        return list;

    }
    public List filterVegetable(String name, String catagoryid, String unit, String amount, String price) throws SQLException {
        List list = new ArrayList();
        list = vegetable.filterVegetable(name, catagoryid, unit, amount, price);
        return list;

    }
    public Vegetable getVegetable(int vegetableID) throws SQLException
    {
        Vegetable o = vegetable.getVegetable(vegetableID);
        return o;
    }
    public boolean addVegetable(Vegetable v) throws SQLException {
        boolean result = vegetable.addVegetable(v);
        return result;
    }

    public boolean updateVegetable(Vegetable v) throws SQLException {
        boolean result = vegetable.updateVegetable(v);
        return result;
    }
    public boolean deleteVegetable(Vegetable v) throws SQLException {
        boolean result = vegetable.deleteVegetable(v);
        return result;
    }
     public long getCountVegetable(){
           try {
               return vegetable.getCountVegetable();
           }
           catch (Exception e) {
               e.printStackTrace();
           }
           return 0;
    }
      public List<Vegetable> getListVegetable() {

       try{
           return vegetable.loadVegetable();
       }
       catch (Exception e) {
           e.printStackTrace();
       }
       return null;
    }
}
