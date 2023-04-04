/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Admin
 */
@Embeddable
public class OrderDetailkey implements Serializable {
    @Column (name = "OrderID")
    private int OrderID;
    @Column (name = "VegetableID")
    private int VegetableID;
}
