package cn.bigcoder.statemachine.springstatemachinedemo.repository.mapper;

import cn.bigcoder.statemachine.springstatemachinedemo.repository.po.OrderInfoPo;
import cn.bigcoder.statemachine.springstatemachinedemo.repository.po.OrderItemPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: Jindong.Tian
 * @date: 2025-03-22
 **/
@Mapper
public interface OrderItemPoMapper extends BaseMapper<OrderItemPo> {

}
