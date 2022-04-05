package com.operation.service;

import com.operation.entity.ShopMenu;
import com.operation.vo.MenuVo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ShopMenuService {
    ResponseEntity createMenu(ShopMenu shopMenu);

    ResponseEntity updateMenu(ShopMenu shopMenu);

    ResponseEntity<ShopMenu> deleteMenu(Integer shopMenuId);

    List<ShopMenu> menuSearch(MenuVo menuVo);
}
