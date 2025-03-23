package cn.bigcoder.statemachine.springstatemachinedemo.supplier.impl;

import cn.bigcoder.statemachine.springstatemachinedemo.supplier.SupplierProxyService;
import cn.bigcoder.statemachine.springstatemachinedemo.supplier.dto.SupplierOrderReqDto;
import cn.bigcoder.statemachine.springstatemachinedemo.supplier.dto.SupplierOrderResDto;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * @author: Jindong.Tian
 * @date: 2025-03-23
 **/
@Service
public class SupplierProxyImpl implements SupplierProxyService {

    @Override
    public SupplierOrderResDto createOrder(SupplierOrderReqDto reqDto) {
        return SupplierOrderResDto.builder()
                .code(0)
                .orderId(UUID.randomUUID().toString().replace("-", "")).build();
    }
}
