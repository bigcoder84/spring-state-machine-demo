package cn.bigcoder.statemachine.springstatemachinedemo.vo.req;

import cn.bigcoder.statemachine.springstatemachinedemo.statemachine.BaseOrderReqDto;
import java.util.List;
import lombok.Data;

/**
 * @author: Jindong.Tian
 * @date: 2025-03-22
 **/
@Data
public class OrderCreateReq extends BaseOrderReqDto {

    /**
     * 勾选的运力商id
     */
    private List<Integer> supplierIds;

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