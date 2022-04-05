package com.operation.vo;

import java.util.Date;

public class ItemVo {
    private String ItemName;
    private String itemCode;
    private Integer status;
    private Date createdFromDate;
    private Date createdToDate;

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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
