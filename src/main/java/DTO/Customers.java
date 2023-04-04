/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table (name ="Customers")
public class Customers {
    @Id
    private int CustomerID; 
    
    @Column
    private String Password; 
    
    @Column
    private String Fullname; 
    
    @Column
    private String Address; 
    
    @Column
    private String City;
    
    @OneToMany (fetch = FetchType.LAZY, targetEntity=Orders.class)
    
    private List<Orders> orders;
}
