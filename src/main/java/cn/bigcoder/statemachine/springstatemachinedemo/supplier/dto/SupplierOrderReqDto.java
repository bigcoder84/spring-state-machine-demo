package cn.bigcoder.statemachine.springstatemachinedemo.supplier.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author: Jindong.Tian
 * @date: 2025-03-23
 **/
@Data
@Builder
public class SupplierOrderReqDto {

    private String thirdOrderId;

    private String userCode;


}
