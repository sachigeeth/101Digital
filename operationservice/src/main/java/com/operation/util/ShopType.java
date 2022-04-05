package com.operation.util;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ShopType {
    COFFEE(1, "COFFEE"),
    BREAD(2, "BREAD"),
    CAKE(3, "CAKE");

    private final Integer shopTypeId;
    private final String shopTypeDescription;

    ShopType(Integer shopTypeId, String shopTypeDescription) {
        this.shopTypeId = shopTypeId;
        this.shopTypeDescription = shopTypeDescription;
    }

    public Integer getShopTypeId() {
        return shopTypeId;
    }

    public String getShopTypeDescription() {
        return shopTypeDescription;
    }

    public static ShopType findOne(Integer shopTypeId) {
        return Arrays.stream(ShopType.values())
                .filter(x -> x.shopTypeId.equals(shopTypeId))
                .findFirst()
                .orElse(null);
    }
}
