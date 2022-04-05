package com.operation.vo;

import java.util.Date;

public class MenuVo {
    private Integer shopId;
    private String menuName;
    private Integer itemId;
    private Integer status;
    private Date createdFromDate;
    private Date createdToDate;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedFromDate() {
        return createdFromDate;
    }

    public void setCreatedFromDate(Date createdFromDate) {
        this.createdFromDate = createdFromDate;
    }

    public Date getCreatedToDate() {
        return createdToDate;
    }

    public void setCreatedToDate(Date createdToDate) {
        this.createdToDate = createdToDate;
    }
}
