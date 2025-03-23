package cn.bigcoder.statemachine.springstatemachinedemo.statemachine;

import lombok.Data;

/**
 * @author: Jindong.Tian
 * @date: 2025-03-22
 **/
@Data
public class BaseOrderReqDto {

    private long timestamp;

    private Integer userId;

    private String orderId;
}
