package cn.bigcoder.statemachine.springstatemachinedemo.service.impl;

import cn.bigcoder.statemachine.springstatemachinedemo.enums.OrderEvent;
import cn.bigcoder.statemachine.springstatemachinedemo.enums.OrderState;
import cn.bigcoder.statemachine.springstatemachinedemo.repository.mapper.OrderInfoPoMapper;
import cn.bigcoder.statemachine.springstatemachinedemo.repository.po.OrderInfoPo;
import cn.bigcoder.statemachine.springstatemachinedemo.service.OrderService;
import cn.bigcoder.statemachine.springstatemachinedemo.statemachine.BaseOrderReqDto;
import cn.bigcoder.statemachine.springstatemachinedemo.statemachine.StateMachineService;
import cn.bigcoder.statemachine.springstatemachinedemo.utils.IdGenerator;
import cn.bigcoder.statemachine.springstatemachinedemo.vo.req.OrderCreateReq;
import cn.bigcoder.statemachine.springstatemachinedemo.vo.res.OrderCreateRes;
import cn.hutool.json.JSONUtil;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: Jindong.Tian
 * @date: 2025-03-22
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private StateMachineService stateMachineService;
    @Resource
    private OrderInfoPoMapper orderInfoPoMapper;

    @Override
    public OrderCreateRes createOrder(OrderCreateReq req) throws Exception {
        String orderId = IdGenerator.generateId();
        req.setOrderId(orderId);
        OrderInfoPo orderInfoPo = buildOrderInfo(req);
        orderInfoPoMapper.insert(orderInfoPo);
        return stateMachineService.sendEvent(OrderEvent.BOOK_ORDER, req, OrderCreateRes.class);
    }

    /**
     * 该方法可以由job异步触发
     *
     * @param req
     * @throws Exception
     */
    @Override
    public void dispatch(BaseOrderReqDto req) throws Exception {
        stateMachineService.sendEvent(OrderEvent.DISPATCH, req, Boolean.class);
    }

    private static OrderInfoPo buildOrderInfo(OrderCreateReq req) {
        if (req == null) {
            return null;
        }
        OrderInfoPo orderInfoPo = new OrderInfoPo();
        orderInfoPo.setOrderId(req.getOrderId());
        orderInfoPo.setUserId(req.getUserId());
        orderInfoPo.setSupplierIds(JSONUtil.toJsonStr(req.getSupplierIds()));
        orderInfoPo.setStartAddress(req.getStartAddress());
        orderInfoPo.setEndAddress(req.getEndAddress());
        orderInfoPo.setStartLat(req.getStartLat());
        orderInfoPo.setStartLng(req.getStartLng());
        orderInfoPo.setEndLat(req.getEndLat());
        orderInfoPo.setEndLng(req.getEndLng());
        orderInfoPo.setState(OrderState.INIT.getState());
        orderInfoPo.setAmount(0L);
        orderInfoPo.setCouponAmount(0L);
        return orderInfoPo;
    }

}
