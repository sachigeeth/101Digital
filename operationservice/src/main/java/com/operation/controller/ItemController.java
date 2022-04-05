package com.operation.controller;

import com.operation.entity.Item;
import com.operation.service.ItemService;
import com.operation.vo.ItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity createItem(@Valid @RequestBody Item item) {
        return this.itemService.createItem(item);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateItem(@Valid @RequestBody Item item) {
        return this.itemService.updateItem(item);
    }

    @DeleteMapping("{itemId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Item> deleteItem(@PathVariable("itemId") Integer itemId) {
        return this.itemService.deleteItem(itemId);
    }

    @PostMapping("/itemSearch")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Item> itemSearch(@RequestBody ItemVo itemVo) {
        return this.itemService.itemSearch(itemVo);
    }


}
