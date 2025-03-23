package cn.bigcoder.statemachine.springstatemachinedemo.statemachine.config;

import cn.bigcoder.statemachine.springstatemachinedemo.enums.OrderEvent;
import cn.bigcoder.statemachine.springstatemachinedemo.enums.OrderState;
import cn.bigcoder.statemachine.springstatemachinedemo.repository.po.OrderInfoPo;
import cn.bigcoder.statemachine.springstatemachinedemo.statemachine.action.OrderConfirmAction;
import cn.bigcoder.statemachine.springstatemachinedemo.statemachine.action.OrderDispatchAction;
import cn.bigcoder.statemachine.springstatemachinedemo.statemachine.action.OrderInitAction;
import java.util.EnumSet;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

/**
 * @description: 订单状态机
 */
@Configuration
@EnableStateMachineFactory
public class OrderStateMachineConfig extends
        StateMachineConfigurerAdapter<OrderState, OrderEvent> {

    @Resource
    private OrderInitAction orderInitAction;
    @Resource
    private OrderDispatchAction orderDispatchAction;
    @Resource
    private OrderConfirmAction orderConfirmAction;

    /**
     * 配置状态
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states)
            throws Exception {
        states.withStates()
                .initial(OrderState.INIT)
                .states(EnumSet.allOf(OrderState.class));
    }

    /**
     * 配置状态转换事件关系
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions)
            throws Exception {
        // 下单
        transitions.withExternal()
                .source(OrderState.INIT)
                .target(OrderState.UN_DISPATCHING)
                .event(OrderEvent.BOOK_ORDER)
                .action(orderInitAction);
        // 派单
        transitions.withExternal()
                .source(OrderState.UN_DISPATCHING)
                .target(OrderState.SP_DISPATCHING)
                .event(OrderEvent.DISPATCH)
                .action(orderDispatchAction);

        // 服务商确认
        transitions.withExternal()
                .source(OrderState.SP_DISPATCHING)
                .target(OrderState.SP_DISPATCHED)
                .event(OrderEvent.SP_CONFIRM)
                .action(orderConfirmAction);
        transitions.withExternal()
                .source(OrderState.UN_DISPATCHING)
                .target(OrderState.SP_DISPATCHED)
                .event(OrderEvent.SP_CONFIRM)
                .action(orderConfirmAction);

    }

    /**
     * StateMachinePersister配置
     *
     * @author yvesdong
     * @date 2024/2/20 14:11
     */
    @Bean
    public StateMachinePersister<OrderState, OrderEvent, OrderInfoPo> persister() {
        return new DefaultStateMachinePersister<>(
                new StateMachinePersist<OrderState, OrderEvent, OrderInfoPo>() {

                    @Override
                    public void write(StateMachineContext<OrderState, OrderEvent> stateMachineContext,
                            OrderInfoPo order)
                            throws Exception {
                        // do nothing: 不依赖于状态机的持久化管理
                    }

                    @Override
                    public StateMachineContext<OrderState, OrderEvent> read(OrderInfoPo orderInfo)
                            throws Exception {
                        // 读取当前订单状态
                        OrderState orderState = OrderState.getByState(orderInfo.getState());
                        if (orderState == null) {
                            throw new RuntimeException("state not exist");
                        }
                        return new DefaultStateMachineContext<>(orderState, null,
                                null, null);
                    }
                });
    }
}