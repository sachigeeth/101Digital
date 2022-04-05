package com.operation.service;

import com.operation.entity.Item;
import com.operation.vo.ItemVo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ItemService {
    ResponseEntity createItem(Item item);

    ResponseEntity updateItem(Item item);

    ResponseEntity<Item> deleteItem(Integer itemId);

    List<Item> itemSearch(ItemVo itemVo);
}
