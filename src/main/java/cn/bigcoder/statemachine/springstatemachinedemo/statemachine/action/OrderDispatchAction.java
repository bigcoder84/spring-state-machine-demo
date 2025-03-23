package cn.bigcoder.statemachine.springstatemachinedemo.statemachine.action;

import cn.bigcoder.statemachine.springstatemachinedemo.enums.OrderEvent;
import cn.bigcoder.statemachine.springstatemachinedemo.enums.OrderState;
import cn.bigcoder.statemachine.springstatemachinedemo.repository.OrderInfoPoRepository;
import cn.bigcoder.statemachine.springstatemachinedemo.repository.mapper.OrderItemPoMapper;
import cn.bigcoder.statemachine.springstatemachinedemo.repository.po.OrderInfoPo;
import cn.bigcoder.statemachine.springstatemachinedemo.repository.po.OrderItemPo;
import cn.bigcoder.statemachine.springstatemachinedemo.statemachine.BaseOrderReqDto;
import cn.bigcoder.statemachine.springstatemachinedemo.supplier.SupplierProxyService;
import cn.bigcoder.statemachine.springstatemachinedemo.supplier.dto.SupplierOrderReqDto;
import cn.bigcoder.statemachine.springstatemachinedemo.supplier.dto.SupplierOrderResDto;
import cn.bigcoder.statemachine.springstatemachinedemo.vo.req.OrderCreateReq;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;

/**
 * 派单操作。保证幂等，
 *
 * @author: Jindong.Tian
 * @date: 2025-03-22
 **/
@Component
public class OrderDispatchAction extends AbstractOrderAction<BaseOrderReqDto, Boolean> {

    @Resource
    private SupplierProxyService supplierProxyService;
    @Resource
    private OrderItemPoMapper orderItemPoMapper;
    @Autowired
    private OrderInfoPoRepository orderInfoPoRepository;

    @Override
    public Boolean onExecute(StateContext<OrderState, OrderEvent> context, BaseOrderReqDto request,
            OrderInfoPo orderInfo) throws Exception {

        OrderEvent event = context.getEvent();
        State<OrderState, OrderEvent> source = context.getSource();
        State<OrderState, OrderEvent> target = context.getTarget();

        // 执行派单逻辑
        List<Integer> supplierIds = JSONUtil.toBean(
                orderInfo.getSupplierIds(),
                new TypeReference<List<Integer>>() {
                }, // 泛型类型捕获
                false // 是否跳过转换错误（按需设置）
        );

        AtomicReference<Boolean> success = new AtomicReference<>(true);

        for (Integer supplierId : supplierIds) {
            // 调用运力商接口下单
            SupplierOrderReqDto supplierOrderReqDto = SupplierOrderReqDto.builder()
                    .thirdOrderId(orderInfo.getOrderId())
                    .userCode(orderInfo.getUserId().toString())
                    .build();
            SupplierOrderResDto thirdOrder = supplierProxyService.createOrder(supplierOrderReqDto);
            if (thirdOrder.getCode() == 0) {
                // 记录第三方单号和当前订单关系
                OrderItemPo orderItemPo = new OrderItemPo();
                orderItemPo.setOrderId(orderInfo.getOrderId());
                orderItemPo.setSupplierId(supplierId);
                orderItemPo.setThirdOrderId(thirdOrder.getOrderId());
                orderItemPoMapper.insert(orderItemPo);
            } else {
                success.set(false);
            }
        }
        if (success.get()) {
            orderInfoPoRepository.updateState(request.getOrderId(), source.getId(), target.getId());
        }
        return success.get();
    }
}
