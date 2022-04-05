package com.operation.serviceimpl;

import com.operation.entity.QShopMenu;
import com.operation.entity.ShopMenu;
import com.operation.repository.ShopMenuRepository;
import com.operation.service.ShopMenuService;
import com.operation.util.DateUtil;
import com.operation.util.MasterDataStatus;
import com.operation.vo.MenuVo;
import com.querydsl.core.BooleanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShopMenuServiceImpl implements ShopMenuService {

    private final ShopMenuRepository shopMenuRepository;
    private final Logger logger = LoggerFactory.getLogger(ShopMenuServiceImpl.class);

    @Autowired
    public ShopMenuServiceImpl(ShopMenuRepository shopMenuRepository) {
        this.shopMenuRepository = shopMenuRepository;
    }

    @Override
    public ResponseEntity createMenu(ShopMenu shopMenu) {
        ResponseEntity responseEntity;
        this.shopMenuRepository.save(shopMenu);
        responseEntity = new ResponseEntity<>(shopMenu, HttpStatus.CREATED);
        return responseEntity;
    }

    @Override
    public ResponseEntity updateMenu(ShopMenu shopMenu) {
        ResponseEntity responseEntity;
        Optional<ShopMenu> dbMenu = this.shopMenuRepository.findById(shopMenu.getShopMenuId());
        if (dbMenu.isPresent()) {
            this.shopMenuRepository.save(shopMenu);
            responseEntity = new ResponseEntity<>(shopMenu, HttpStatus.CREATED);
        } else {
            responseEntity = new ResponseEntity<>(shopMenu, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<ShopMenu> deleteMenu(Integer shopMenuId) {
        Optional<ShopMenu> dbShopMenu = this.shopMenuRepository.findById(shopMenuId);
        ResponseEntity<ShopMenu> responseEntity;
        if (dbShopMenu.isPresent()) {
            dbShopMenu.get().setStatus(MasterDataStatus.DELETED.getStatusSeq());
            this.shopMenuRepository.save(dbShopMenu.get());
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    public List<ShopMenu> menuSearch(MenuVo menuVo) {
        List<ShopMenu> shopMenuList = new ArrayList<>();
        try {
            QShopMenu qShopMenu = QShopMenu.shopMenu;
            BooleanBuilder builder = new BooleanBuilder();

            if (menuVo.getMenuName() != null) {
                builder.and(qShopMenu.menuName.containsIgnoreCase(menuVo.getMenuName()));
            }

            if (menuVo.getShopId() != null) {
                builder.and(qShopMenu.shopId.eq(menuVo.getShopId()));
            }

            if (menuVo.getItemId() != null) {
                builder.and(qShopMenu.menuItems.any().itemId.eq(menuVo.getItemId()));
            }

            if (menuVo.getStatus() != null) {
                builder.and(qShopMenu.status.eq(menuVo.getStatus()));
            }
            if (menuVo.getCreatedFromDate() != null) {
                Date createdToDate = DateUtil.setTimeToDate(menuVo.getCreatedFromDate(), 23, 59, 59);
                builder.and(qShopMenu.createdDate.after(createdToDate));
            }
            if (menuVo.getCreatedToDate() != null) {
                Date createdToDate = DateUtil.setTimeToDate(menuVo.getCreatedToDate(), 23, 59, 59);
                builder.and(qShopMenu.createdDate.before(createdToDate));
            }

        } catch (Exception e) {
            logger.error("Menu Searh Error :", e.getMessage());
        }

        return shopMenuList;
    }
}
