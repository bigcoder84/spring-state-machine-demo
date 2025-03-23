package cn.bigcoder.statemachine.springstatemachinedemo.controller;

import cn.bigcoder.statemachine.springstatemachinedemo.common.AjaxResult;
import cn.bigcoder.statemachine.springstatemachinedemo.service.OrderService;
import cn.bigcoder.statemachine.springstatemachinedemo.statemachine.BaseOrderReqDto;
import cn.bigcoder.statemachine.springstatemachinedemo.vo.req.OrderCreateReq;
import cn.bigcoder.statemachine.springstatemachinedemo.vo.res.OrderCreateRes;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Jindong.Tian
 * @date: 2025-03-22
 **/
@RestController
@RequestMapping("/taxi/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    public AjaxResult<OrderCreateRes> createOrder(@RequestBody OrderCreateReq req) throws Exception {
        return AjaxResult.success(orderService.createOrder(req));
    }

    @PostMapping("/dispatch")
    public AjaxResult dispatch(@RequestBody BaseOrderReqDto req) throws Exception {
        orderService.dispatch(req);
        return AjaxResult.success();
    }
}
