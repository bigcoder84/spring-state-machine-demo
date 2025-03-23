package cn.bigcoder.statemachine.springstatemachinedemo.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: Jindong.Tian
 * @date: 2025-03-22
 **/
@TableName("order_info")
@Data
public class OrderInfoPo {

    @TableId(type = IdType.INPUT)
    private String orderId;

    private Integer userId;

    private Integer state;

    /**
     * 支付金额
     */
    private Long amount;

    /**
     * 优惠金额
     */
    private Long couponAmount;

    /**
     * 勾选的运力商id
     */
    private String supplierIds;

    private String startAddress;

    private String endAddress;

    /**
     * 起点位置纬度
     */
    private Integer startLat;

    /**
     * 起始位置经度
     */
    private Integer startLng;

    /**
     * 终点位置纬度
     */
    private Integer endLat;

    /**
     * 终点位置经度
     */
    private Integer endLng;

}
