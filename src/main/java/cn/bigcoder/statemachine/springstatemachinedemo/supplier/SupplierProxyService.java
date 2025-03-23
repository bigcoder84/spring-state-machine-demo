package cn.bigcoder.statemachine.springstatemachinedemo.supplier;

import cn.bigcoder.statemachine.springstatemachinedemo.supplier.dto.SupplierOrderReqDto;
import cn.bigcoder.statemachine.springstatemachinedemo.supplier.dto.SupplierOrderResDto;

/**
 * @author: Jindong.Tian
 * @date: 2025-03-23
 * @description:
 **/
public interface SupplierProxyService {

    SupplierOrderResDto createOrder(SupplierOrderReqDto reqDto);
}
