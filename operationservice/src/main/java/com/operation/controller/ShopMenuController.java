package com.operation.controller;

import com.operation.entity.ShopMenu;
import com.operation.service.ShopMenuService;
import com.operation.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("menus")
public class ShopMenuController {

    private final ShopMenuService shopMenuService;

    @Autowired
    public ShopMenuController(ShopMenuService shopMenuService) {
        this.shopMenuService = shopMenuService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity createMenu(@Valid @RequestBody ShopMenu shopMenu) {
        return this.shopMenuService.createMenu(shopMenu);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateMenu(@Valid @RequestBody ShopMenu shopMenu) {
        return this.shopMenuService.updateMenu(shopMenu);
    }

    @DeleteMapping("{shopMenuId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ShopMenu> deleteMenu(@PathVariable("shopMenuId") Integer shopMenuId) {
        return this.shopMenuService.deleteMenu(shopMenuId);
    }

    @PostMapping("/menuSearch")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<ShopMenu> menuSearch(@RequestBody MenuVo menuVo) {
        return this.shopMenuService.menuSearch(menuVo);
    }
}
