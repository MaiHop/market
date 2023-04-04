/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import DTO.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.ManyToAny;

/**
 *
 * @author Admin
 */
@Data
@Entity
@Table (name ="orders")
public class Orders{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int OrderID;    
    
    @Column
    private int CustomerID;
    
    @Column (name ="Date")
    @Temporal(value = TemporalType.DATE)
    private Date Date;
    
    @Column
    private float Total;
    
    @Column
    private String Note;
    
    @OneToMany (fetch = FetchType.LAZY, targetEntity = Customers.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CustomerID", nullable = false, foreignKey = @ForeignKey(name="fk_customer"),insertable=false, updatable=false)
    private List<Customers> listcus;
}
