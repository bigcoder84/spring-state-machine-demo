package cn.bigcoder.statemachine.springstatemachinedemo.supplier.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author: Jindong.Tian
 * @date: 2025-03-23
 **/
@Data
@Builder
public class SupplierOrderResDto {

    private Integer code;

    private String message;

    /**
     * 第三方订单号
     */
    private String orderId;
}
