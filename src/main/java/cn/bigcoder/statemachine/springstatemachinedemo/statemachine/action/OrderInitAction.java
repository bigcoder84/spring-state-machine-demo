package cn.bigcoder.statemachine.springstatemachinedemo.statemachine.action;

import cn.bigcoder.statemachine.springstatemachinedemo.enums.OrderEvent;
import cn.bigcoder.statemachine.springstatemachinedemo.enums.OrderState;
import cn.bigcoder.statemachine.springstatemachinedemo.repository.OrderInfoPoRepository;
import cn.bigcoder.statemachine.springstatemachinedemo.repository.mapper.OrderInfoPoMapper;
import cn.bigcoder.statemachine.springstatemachinedemo.repository.po.OrderInfoPo;
import cn.bigcoder.statemachine.springstatemachinedemo.vo.req.OrderCreateReq;
import cn.bigcoder.statemachine.springstatemachinedemo.vo.res.OrderCreateRes;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 初始化操作
 *
 * @author: Jindong.Tian
 * @date: 2025-03-22
 **/
@Component
public class OrderInitAction extends AbstractOrderAction<OrderCreateReq, OrderCreateRes> {

    @Resource
    private OrderInfoPoRepository orderInfoPoRepository;

    @Override
    @Transactional
    public OrderCreateRes onExecute(StateContext<OrderState, OrderEvent> context, OrderCreateReq request,
            OrderInfoPo orderInfo) throws Exception {
        // 订单那初始化操作.......
        orderInfoPoRepository.updateState(orderInfo.getOrderId(), context.getSource().getId(),
                context.getTarget().getId());
        return OrderCreateRes.builder().orderId(orderInfo.getOrderId()).build();
    }
}
