package cn.bigcoder.statemachine.springstatemachinedemo.service;

import cn.bigcoder.statemachine.springstatemachinedemo.statemachine.BaseOrderReqDto;
import cn.bigcoder.statemachine.springstatemachinedemo.vo.req.OrderCreateReq;
import cn.bigcoder.statemachine.springstatemachinedemo.vo.res.OrderCreateRes;

/**
 * @author: Jindong.Tian
 * @date: 2025-03-22
 * @description:
 **/
public interface OrderService {

    OrderCreateRes createOrder(OrderCreateReq req) throws Exception;

    void dispatch(BaseOrderReqDto req) throws Exception;
}
