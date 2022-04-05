package com.operation.controller;

import com.operation.entity.Shop;
import com.operation.service.ShopService;
import com.operation.vo.ShopVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("shops")
public class ShopController {

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity createShop(@Valid @RequestBody Shop shop) {
        return this.shopService.createShop(shop);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateShop(@Valid @RequestBody Shop shop) {
        return this.shopService.updateShop(shop);
    }

    @DeleteMapping("{shopId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Shop> deleteShop(@PathVariable("shopId") Integer shopId) {
        return this.shopService.deleteShop(shopId);
    }

    @PostMapping("/shopSearch")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Shop> ShopSearch(@RequestBody ShopVo shopVo) {
        return this.shopService.shopSearch(shopVo);
    }
}
