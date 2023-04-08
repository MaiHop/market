/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

/**
 *
 * @author Admin
 */
//@FilterDefs({
//    @FilterDef(name= "filterVegetable_Name", parameters = {
//        @ParamDef(name = "vegname", type ="java.lang.String")
//    }),
//    @FilterDef(name = "filterCatagoryID", parameters = {
//        @ParamDef(name = "cataid", type ="java.lang.Integer")
//    }),
//    @FilterDef(name = "filterUnit", parameters = {
//        @ParamDef(name = "unit", type ="java.lang.String")
//    }),
//    @FilterDef(name = "filterAmount", parameters = {
//        @ParamDef(name = "amountmin", type ="java.lang.Integer"),
//        @ParamDef(name = "amountmax", type ="java.lang.Integer")
//    }),
//    @FilterDef(name = "filterPrice", parameters = {
//        @ParamDef(name = "price1", type ="java.lang.String")
//    }),
//})
//@Filters({
//    @Filter(name = "filterVegetable_Name", condition = "Vegetable_Name LIKE :vegname"),
//    @Filter(name = "filterCatagoryID", condition = "CatagoryID =:cataid "),
//    @Filter(name = "filterUnit", condition = "Unit LIKE :unit "),
//    @Filter(name = "filterAmount1", condition = "Amount1  < :amountmin "),
//    @Filter(name = "filterAmount2", condition = "Amount2  >= :amountmin "),
//    @Filter(name = "filterAmount3", condition = "Amount3  <= :amountmax AND Amount3>= :   amountmin"),
//    @Filter(name = "Price", condition = "Order by Price  :price1")
//    
//})
@Data
@Entity
@Table (name ="Vegetable")
public class Vegetable {
    
    @Id
    private int VegetableID;
    @Column 
    private int CatagoryID ;
    @Column 
    private String Vegetable_Name;
    @Column
    private String Unit;
    @Column
    private int Amount;
    @Column 
    private String Image;
    @Column
    private float Price;
    
    @Transient
    private String Amountfilter;
    @Transient
    private String Pricefilter;
    
    @OneToMany (fetch = FetchType.LAZY, targetEntity = OrderDetail.class)
    @JoinColumn(name = "OrderID")
    private List<OrderDetail> listorder;
    
    @ManyToOne(fetch = FetchType.EAGER, optional=false)
    @JoinColumn(name = "CatagoryID", nullable = false, foreignKey = @ForeignKey(name="fk_Category"),insertable=false, updatable=false)
    private Category category ;
}
