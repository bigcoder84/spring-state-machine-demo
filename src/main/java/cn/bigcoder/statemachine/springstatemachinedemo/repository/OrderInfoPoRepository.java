package cn.bigcoder.statemachine.springstatemachinedemo.repository;

import cn.bigcoder.statemachine.springstatemachinedemo.enums.OrderState;
import cn.bigcoder.statemachine.springstatemachinedemo.repository.mapper.OrderInfoPoMapper;
import cn.bigcoder.statemachine.springstatemachinedemo.repository.po.OrderInfoPo;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

/**
 * @author: Jindong.Tian
 * @date: 2025-03-23
 **/
@Repository
public class OrderInfoPoRepository {

    @Resource
    private OrderInfoPoMapper orderInfoPoMapper;

    public void updateById(OrderInfoPo orderInfoPo) {
        orderInfoPoMapper.updateById(orderInfoPo);
    }

    public void updateState(String orderId, OrderState currentOrderState, OrderState targetOrderState) {
        OrderInfoPo orderInfoPo = new OrderInfoPo();
        orderInfoPo.setOrderId(orderId);
        orderInfoPo.setState(targetOrderState.getState());
        LambdaUpdateWrapper<OrderInfoPo> wrapper =
                new LambdaUpdateWrapper<OrderInfoPo>()
                        .eq(OrderInfoPo::getState, currentOrderState.getState())
                        .eq(OrderInfoPo::getOrderId, orderId);
        orderInfoPoMapper.update(orderInfoPo, wrapper);
    }
}
