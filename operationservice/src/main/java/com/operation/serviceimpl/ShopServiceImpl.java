package com.operation.serviceimpl;

import com.operation.entity.QShop;
import com.operation.entity.Shop;
import com.operation.repository.ShopRepository;
import com.operation.service.ShopService;
import com.operation.util.DateUtil;
import com.operation.util.MasterDataStatus;
import com.operation.vo.ShopVo;
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
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public ResponseEntity createShop(Shop shop) {
        ResponseEntity responseEntity;
        if (shop.getAddressBook() != null) {
            shop.getAddressBook().setStatus(MasterDataStatus.APPROVED.getStatusSeq());
        }
        this.shopRepository.save(shop);
        responseEntity = new ResponseEntity<>(shop, HttpStatus.CREATED);
        return responseEntity;
    }

    @Override
    public ResponseEntity updateShop(Shop shop) {
        ResponseEntity<Shop> responseEntity;
        Optional<Shop> dbShop = this.shopRepository.findById(shop.getShopId());
        if (dbShop.isPresent()) {
            this.shopRepository.save(shop);
            responseEntity = new ResponseEntity<>(shop, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(shop, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Shop> deleteShop(Integer shopId) {
        Optional<Shop> dbShop = this.shopRepository.findById(shopId);
        ResponseEntity<Shop> responseEntity;
        if (dbShop.isPresent()) {
            dbShop.get().setStatus(MasterDataStatus.DELETED.getStatusSeq());
            dbShop.get().getAddressBook().setStatus(MasterDataStatus.DELETED.getStatusSeq());
            this.shopRepository.save(dbShop.get());
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    public List<Shop> shopSearch(ShopVo shopVo) {
        List<Shop> shopList = new ArrayList<>();
        try {
            QShop qShop = QShop.shop;
            BooleanBuilder builder = new BooleanBuilder();

            if (shopVo.getShopName() != null) {
                builder.and(qShop.shopName.containsIgnoreCase(shopVo.getShopName()));
            }

            if (shopVo.getShopTypeId() != null) {
                builder.and(qShop.shopTypeId.eq(shopVo.getShopTypeId()));
            }

            if (shopVo.getCountryId() != null) {
                builder.and(qShop.addressBook.countryId.eq(shopVo.getCountryId()));
            }

            if (shopVo.getLocationId() != null) {
                builder.and(qShop.addressBook.locationId.eq(shopVo.getLocationId()));
            }
            if (shopVo.getStatus() != null) {
                builder.and(qShop.status.eq(shopVo.getStatus()));
            }

            if (shopVo.getCreatedFromDate() != null) {
                Date createdToDate = DateUtil.setTimeToDate(shopVo.getCreatedFromDate(), 23, 59, 59);
                builder.and(qShop.createdDate.after(createdToDate));
            }
            if (shopVo.getCreatedToDate() != null) {
                Date createdToDate = DateUtil.setTimeToDate(shopVo.getCreatedToDate(), 23, 59, 59);
                builder.and(qShop.createdDate.before(createdToDate));
            }
            shopList = (List<Shop>) this.shopRepository.findAll(builder);
        } catch (Exception e) {
            logger.error("Shop Search Error : ", e.getMessage());
        }
        return shopList;
    }

}
