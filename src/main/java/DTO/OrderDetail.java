/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.io.Serializable;
import static java.nio.file.Files.delete;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Data
@Entity
@Table(name = "OrderDetail")
public class OrderDetail implements Serializable{
    @Id
    private int OrderID;
            
    @Id
    private int VegetableID;   
   
    @Column
    private int Quantity;
    @Column
    private float Price;
    
    @ManyToOne(fetch = FetchType.EAGER, optional=false, cascade = CascadeType.REMOVE)
    @MapsId("OrderID")
    @JoinColumn(name = "OrderID", nullable = false, foreignKey = @ForeignKey(name="fk_order"),insertable=false, updatable=false)
    private Orders orders;
    
    @ManyToOne(fetch = FetchType.EAGER, optional=false)
    @MapsId("VegetableID")
    @JoinColumn(name = "VegetableID", nullable = false, foreignKey = @ForeignKey(name="fk_vegetable"),insertable=false, updatable=false)
    private Vegetable vegetable;
    
    
    
}
