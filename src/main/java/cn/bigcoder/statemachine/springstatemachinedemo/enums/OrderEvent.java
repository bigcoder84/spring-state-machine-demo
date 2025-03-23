package cn.bigcoder.statemachine.springstatemachinedemo.enums;

/**
 * @author: Jindong.Tian
 * @date: 2025-03-22
 **/
public enum OrderEvent {
    BOOK_ORDER("下单"),
    DISPATCH("派单"),
    SP_CONFIRM("服务商司机接单"),
    ;

    private String desc;

    OrderEvent(String desc) {
        this.desc = desc;
    }
}
