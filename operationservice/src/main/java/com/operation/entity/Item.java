package com.operation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.operation.util.SharedModel;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "item", schema = "operation")
public class Item extends SharedModel {
    private Integer itemId;
    private String itemName;
    private String itemCode;
    private Double itemPrice;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_G1")
    @SequenceGenerator(name = "ITEM_G1", sequenceName = "item_id", schema = "operation", allocationSize = 1)
    @Column(name = "item_id", nullable = false, precision = 0, unique = true)
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "item_name", nullable = false)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Basic
    @Column(name = "item_code", nullable = false)
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @Basic
    @Column(name = "item_price", nullable = false)
    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

}
