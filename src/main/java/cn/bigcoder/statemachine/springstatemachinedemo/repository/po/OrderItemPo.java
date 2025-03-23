package cn.bigcoder.statemachine.springstatemachinedemo.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: Jindong.Tian
 * @date: 2025-03-22
 **/
@TableName("order_item")
@Data
public class OrderItemPo {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String orderId;

    private String thirdOrderId;

    private Integer supplierId;

}
