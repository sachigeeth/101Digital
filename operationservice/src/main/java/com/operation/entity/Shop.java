package com.operation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.operation.util.SharedModel;
import com.operation.util.ShopType;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "shop", schema = "operation")
public class Shop extends SharedModel {
    private Integer shopId;
    private String shopName;
    private Integer shopTypeId;
    private Integer addressBookId;

    private AddressBook addressBook;

    private String shopTypeDescription;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHOP_G1")
    @SequenceGenerator(name = "SHOP_G1", sequenceName = "shop_id", schema = "operation", allocationSize = 1)
    @Column(name = "shop_id", nullable = false, precision = 0, unique = true)
    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    @Basic
    @Column(name = "shop_name", nullable = false)
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Basic
    @Column(name = "shop_type_id", nullable = false)
    public Integer getShopTypeId() {
        return shopTypeId;
    }

    public void setShopTypeId(Integer shopTypeId) {
        this.shopTypeId = shopTypeId;
        if (shopTypeId != null) {
            this.setShopTypeDescription(ShopType.findOne(shopTypeId).getShopTypeDescription());
        }
    }

    @Transient
    public Integer getAddressBookId() {
        return addressBookId;
    }

    public void setAddressBookId(Integer addressBookId) {
        this.addressBookId = addressBookId;
    }

    public String getShopTypeDescription() {
        return shopTypeDescription;
    }

    public void setShopTypeDescription(String shopTypeDescription) {
        this.shopTypeDescription = shopTypeDescription;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_book_id")
    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

}
