package cn.bigcoder.statemachine.springstatemachinedemo.statemachine;

import cn.bigcoder.statemachine.springstatemachinedemo.enums.OrderEvent;
import cn.bigcoder.statemachine.springstatemachinedemo.enums.OrderState;
import cn.bigcoder.statemachine.springstatemachinedemo.repository.mapper.OrderInfoPoMapper;
import cn.bigcoder.statemachine.springstatemachinedemo.repository.po.OrderInfoPo;
import cn.bigcoder.statemachine.springstatemachinedemo.statemachine.config.StateMachineConstants;
import java.rmi.ServerException;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

/**
 * @author: Jindong.Tian
 * @date: 2025-03-22
 **/
@Slf4j
@Service
public class StateMachineService {

    @Resource
    private OrderInfoPoMapper orderInfoPoMapper;

    @Resource
    private StateMachineFactory<OrderState, OrderEvent> stateMachineFactory;
    @Resource
    private StateMachinePersister<OrderState, OrderEvent, OrderInfoPo> stateMachinePersister;

    public <T extends BaseOrderReqDto, R> R sendEvent(OrderEvent event, T request, Class<R> resType) throws Exception {
        String eventName = event.name();
        OrderInfoPo orderInfo = orderInfoPoMapper.selectById(request.getOrderId());
        if (orderInfo == null) {
            throw new RuntimeException("订单不存在");
        }
        // 初始化状态机实例
        StateMachine<OrderState, OrderEvent> stateMachine = stateMachineFactory.getStateMachine();
        // 初始化状态机状态
        stateMachinePersister.restore(stateMachine, orderInfo);
        // 触发事件
        boolean tag = stateMachine.sendEvent(
                MessageBuilder.withPayload(event)
                        .setHeader(StateMachineConstants.REQUEST, request)
                        .setHeader(StateMachineConstants.ORDER, orderInfo)
                        .build());
        if (!tag) {
            throw new ServerException("状态不匹配");
        }
        // 获取action里抛出的异常
        Exception exception = stateMachine
                .getExtendedState()
                .get(StateMachineConstants.EXCEPTION_KEY, Exception.class);
        if (exception != null) {
            // 如果执行过程中有异常，则抛出
            throw exception;
        }
        R response = stateMachine.getExtendedState().get(StateMachineConstants.RESPONSE, resType);
        if (response == null) {
            return null;
        }
        return response;
    }

}
