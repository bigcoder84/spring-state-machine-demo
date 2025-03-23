create table order_info
(
    order_id      varchar(64)                        not null comment '订单号'
        primary key,
    user_id       int                                not null comment '用户ID',
    state         int                                not null comment '订单状态',
    amount        bigint                             not null comment '订单金额',
    coupon_amount bigint                             not null comment '订单优惠金额',
    supplier_ids  varchar(255)                       not null comment '勾选的服务商列表',
    start_address varchar(255)                       not null comment '订单起始位置',
    end_address   varchar(255)                       not null comment '订单结束位置',
    start_lat double null,
    start_lng double null,
    end_lat double null,
    end_lng double null,
    create_time   datetime default CURRENT_TIMESTAMP not null comment '订单创建时间',
    update_time   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '订单创建时间'
) comment '订单表' collate = utf8mb4_general_ci;

create table order_item
(
    id             int auto_increment comment '主键ID'
        primary key,
    order_id       varchar(64) not null comment '订单ID',
    third_order_id varchar(64) not null comment '第三方订单ID',
    supplier_id    int         not null comment '供应商ID'
) comment '订单子项表' collate = utf8mb4_general_ci;

