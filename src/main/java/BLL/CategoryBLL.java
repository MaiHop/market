/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.CategoryDAL;
import DTO.Category;
import DTO.Vegetable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CategoryBLL {
    private CategoryDAL cateDAL;
    public CategoryBLL() {
        cateDAL = new CategoryDAL();
    }
    public Category findbyname(String name) throws SQLException {
        Category c = cateDAL.findbyname(name);
        return c;

    }
}
