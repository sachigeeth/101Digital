package com.operation.serviceimpl;

import com.operation.entity.Item;
import com.operation.entity.QItem;
import com.operation.repository.ItemRepository;
import com.operation.service.ItemService;
import com.operation.util.DateUtil;
import com.operation.util.MasterDataStatus;
import com.operation.vo.ItemVo;
import com.querydsl.core.BooleanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity createItem(Item item) {
        ResponseEntity responseEntity;
        Item dbItemName = this.itemRepository.findByItemNameAndStatusNot(item.getItemName(), MasterDataStatus.DELETED.getStatusSeq());
        Item dbItemNameAndCode = this.itemRepository.findByItemNameAndItemCodeAndStatusNot(item.getItemName(), item.getItemCode(), MasterDataStatus.DELETED.getStatusSeq());
        if (dbItemName != null) {
            responseEntity = new ResponseEntity<>("Duplicate Item Name", HttpStatus.BAD_REQUEST);
        } else if (dbItemNameAndCode != null) {
            responseEntity = new ResponseEntity<>("Duplicate Item Name & Code", HttpStatus.BAD_REQUEST);
        } else {
            this.itemRepository.save(item);
            responseEntity = new ResponseEntity<>(item, HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @Override
    @Transactional
    public ResponseEntity updateItem(Item item) {
        ResponseEntity responseEntity;
        Optional<Item> dbItem = this.itemRepository.findById(item.getItemId());
        if (dbItem.isPresent()) {
            this.itemRepository.save(item);
            responseEntity = new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(item, HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    @Override
    @Transactional
    public ResponseEntity<Item> deleteItem(Integer itemId) {
        Optional<Item> dbItem = this.itemRepository.findById(itemId);
        ResponseEntity<Item> responseEntity;
        if (dbItem.isPresent()) {
            dbItem.get().setStatus(MasterDataStatus.DELETED.getStatusSeq());
            this.itemRepository.save(dbItem.get());
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    public List<Item> itemSearch(ItemVo itemVo) {
        List<Item> itemList = new ArrayList<>();
        try {
            QItem qItem = QItem.item;
            BooleanBuilder builder = new BooleanBuilder();
            if (itemVo.getItemName() != null) {
                builder.and(qItem.itemName.containsIgnoreCase(itemVo.getItemName()));
            }
            if (itemVo.getItemCode() != null) {
                builder.and(qItem.itemCode.containsIgnoreCase(itemVo.getItemCode()));
            }
            if (itemVo.getStatus() != null) {
                builder.and(qItem.status.eq(itemVo.getStatus()));
            }
            if (itemVo.getCreatedFromDate() != null) {
                Date createdToDate = DateUtil.setTimeToDate(itemVo.getCreatedFromDate(), 23, 59, 59);
                builder.and(qItem.createdDate.after(createdToDate));
            }
            if (itemVo.getCreatedToDate() != null) {
                Date createdToDate = DateUtil.setTimeToDate(itemVo.getCreatedToDate(), 23, 59, 59);
                builder.and(qItem.createdDate.before(createdToDate));
            }
            itemList = (List<Item>) this.itemRepository.findAll(builder);

        } catch (Exception e) {
            logger.error("Item Search Error :", e);
        }
        return itemList;
    }


}
