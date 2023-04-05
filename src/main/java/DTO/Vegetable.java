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
import lombok.Data;

/**
 *
 * @author Admin
 */
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
    
    
    @OneToMany (fetch = FetchType.LAZY, targetEntity = OrderDetail.class)
    private List<OrderDetail> listorder;
    
    @ManyToOne(fetch = FetchType.EAGER, optional=false)
    @JoinColumn(name = "CatagoryID", nullable = false, foreignKey = @ForeignKey(name="fk_Category"),insertable=false, updatable=false)
    private Category category ;
}
