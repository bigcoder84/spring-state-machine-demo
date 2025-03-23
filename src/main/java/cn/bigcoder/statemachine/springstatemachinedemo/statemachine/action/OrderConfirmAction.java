package cn.bigcoder.statemachine.springstatemachinedemo.statemachine.action;

import cn.bigcoder.statemachine.springstatemachinedemo.enums.OrderEvent;
import cn.bigcoder.statemachine.springstatemachinedemo.enums.OrderState;
import cn.bigcoder.statemachine.springstatemachinedemo.repository.po.OrderInfoPo;
import cn.bigcoder.statemachine.springstatemachinedemo.supplier.SupplierProxyService;
import cn.bigcoder.statemachine.springstatemachinedemo.vo.req.OrderCreateReq;
import cn.bigcoder.statemachine.springstatemachinedemo.vo.res.OrderCreateRes;
import javax.annotation.Resource;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 派单操作
 *
 * @author: Jindong.Tian
 * @date: 2025-03-22
 **/
@Component
public class OrderConfirmAction extends AbstractOrderAction<OrderCreateReq, OrderCreateRes> {

    @Resource
    private SupplierProxyService supplierProxyService;

    @Override
    @Transactional
    public OrderCreateRes onExecute(StateContext<OrderState, OrderEvent> context, OrderCreateReq request,
            OrderInfoPo orderInfo) throws Exception {

        return null;
    }
}
