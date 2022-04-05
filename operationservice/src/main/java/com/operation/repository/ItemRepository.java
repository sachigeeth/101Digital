package com.operation.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphQuerydslPredicateExecutor;
import com.operation.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>, EntityGraphJpaRepository<Item, Integer>, EntityGraphQuerydslPredicateExecutor<Item> {

    Item findByItemNameAndStatusNot(String itemName, Integer status);

    Item findByItemNameAndItemCodeAndStatusNot(String itemName,String ItemCode, Integer status);
}
