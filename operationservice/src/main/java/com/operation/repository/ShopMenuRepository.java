package com.operation.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphQuerydslPredicateExecutor;
import com.operation.entity.ShopMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopMenuRepository  extends JpaRepository<ShopMenu, Integer>, EntityGraphJpaRepository<ShopMenu, Integer>, EntityGraphQuerydslPredicateExecutor<ShopMenu> {
}
