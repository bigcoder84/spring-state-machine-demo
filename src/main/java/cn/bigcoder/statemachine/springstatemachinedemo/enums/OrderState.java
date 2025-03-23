package cn.bigcoder.statemachine.springstatemachinedemo.enums;

import lombok.Getter;

/**
 * @author: Jindong.Tian
 * @date: 2025-03-22
 **/
@Getter
public enum OrderState {
    INIT(1, "初始化"),
    UN_DISPATCHING(2, "未派单"),
    SP_DISPATCHING(3, "服务商派单中"),
    SP_DISPATCHED(4, "服务商已派单"),
    ;

    private Integer state;
    private String desc;

    OrderState(Integer state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    public static OrderState getByState(Integer state) {
        for (OrderState orderState : OrderState.values()) {
            if (orderState.state.equals(state)) {
                return orderState;
            }
        }
        return null;
    }
}
