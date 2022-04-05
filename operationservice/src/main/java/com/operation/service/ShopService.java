package com.operation.service;

import com.operation.entity.Shop;
import com.operation.vo.ShopVo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ShopService {
    ResponseEntity createShop(Shop shop);

    ResponseEntity updateShop(Shop shop);

    ResponseEntity<Shop> deleteShop(Integer shopId);

    List<Shop> shopSearch(ShopVo shopVo);
}
