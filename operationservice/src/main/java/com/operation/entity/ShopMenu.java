package com.operation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.operation.util.SharedModel;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "shop_menu", schema = "operation")
public class ShopMenu extends SharedModel {
    private Integer shopMenuId;
    private String menuName;
    private Integer shopId;
    private String menuDescription;

    private Shop shop;

    private Set<Item> menuItems = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHOP_MENU_G1")
    @SequenceGenerator(name = "SHOP_MENU_G1", sequenceName = "shop_menu_id", schema = "operation", allocationSize = 1)
    @Column(name = "shop_menu_id", nullable = false, precision = 0, unique = true)
    public Integer getShopMenuId() {
        return shopMenuId;
    }

    public void setShopMenuId(Integer shopMenuId) {
        this.shopMenuId = shopMenuId;
    }

    @Basic
    @Column(name = "menu_name", nullable = false)
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Basic
    @Column(name = "shop_id", nullable = false)
    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    @Basic
    @Column(name = "menu_description", nullable = false)
    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", insertable = false, updatable = false)
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "shop_menu_items", schema = "operation",
            joinColumns = @JoinColumn(name = "shop_menu_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    public Set<Item> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(Set<Item> menuItems) {
        this.menuItems = menuItems;
    }

}
