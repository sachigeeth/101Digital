package com.operation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShopMenu is a Querydsl query type for ShopMenu
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QShopMenu extends EntityPathBase<ShopMenu> {

    private static final long serialVersionUID = -1602829684L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShopMenu shopMenu = new QShopMenu("shopMenu");

    public final com.operation.util.QSharedModel _super = new com.operation.util.QSharedModel(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath menuDescription = createString("menuDescription");

    public final SetPath<Item, QItem> menuItems = this.<Item, QItem>createSet("menuItems", Item.class, QItem.class, PathInits.DIRECT2);

    public final StringPath menuName = createString("menuName");

    public final QShop shop;

    public final NumberPath<Integer> shopId = createNumber("shopId", Integer.class);

    public final NumberPath<Integer> shopMenuId = createNumber("shopMenuId", Integer.class);

    //inherited
    public final NumberPath<Integer> status = _super.status;

    public QShopMenu(String variable) {
        this(ShopMenu.class, forVariable(variable), INITS);
    }

    public QShopMenu(Path<? extends ShopMenu> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShopMenu(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShopMenu(PathMetadata metadata, PathInits inits) {
        this(ShopMenu.class, metadata, inits);
    }

    public QShopMenu(Class<? extends ShopMenu> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shop = inits.isInitialized("shop") ? new QShop(forProperty("shop"), inits.get("shop")) : null;
    }

}

