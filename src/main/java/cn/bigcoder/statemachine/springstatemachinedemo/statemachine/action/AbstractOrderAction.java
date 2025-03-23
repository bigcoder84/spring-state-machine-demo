package cn.bigcoder.statemachine.springstatemachinedemo.statemachine.action;

import cn.bigcoder.statemachine.springstatemachinedemo.enums.OrderEvent;
import cn.bigcoder.statemachine.springstatemachinedemo.enums.OrderState;
import cn.bigcoder.statemachine.springstatemachinedemo.repository.OrderInfoPoRepository;
import cn.bigcoder.statemachine.springstatemachinedemo.repository.po.OrderInfoPo;
import cn.bigcoder.statemachine.springstatemachinedemo.statemachine.config.StateMachineConstants;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

@Slf4j
public abstract class AbstractOrderAction<T, R> implements Action<OrderState, OrderEvent> {

    @Override
    public void execute(StateContext<OrderState, OrderEvent> context) {
        R response;
        try {
            if (context.getException() != null) {
                log.warn("BaseOrderAction execute exception, event:{}", context.getEvent(), context.getException());
                throw context.getException();
            } else {
                T request = (T) context.getMessageHeader(StateMachineConstants.REQUEST);
                OrderInfoPo orderInfo = (OrderInfoPo) context.getMessageHeader(StateMachineConstants.ORDER);
                AbstractOrderAction proxy;
                try {
                    // 尝试获取代理对象
                    proxy = (AbstractOrderAction) AopContext.currentProxy();
                } catch (IllegalStateException e) {
                    // 如果没有代理对象，使用当前对象本身
                    proxy = (AbstractOrderAction) this;
                }
                response = (R) proxy.onExecute(context, request, orderInfo);
            }
            context.getExtendedState().getVariables().put(StateMachineConstants.RESPONSE, response);
        } catch (Exception e) {
            context.getExtendedState().getVariables().put(StateMachineConstants.EXCEPTION_KEY, e);
        }
    }


    public abstract R onExecute(StateContext<OrderState, OrderEvent> context, final T request,
            final OrderInfoPo orderInfo) throws Exception;
}
