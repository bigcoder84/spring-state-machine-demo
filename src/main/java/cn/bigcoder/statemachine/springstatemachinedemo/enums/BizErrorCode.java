package cn.bigcoder.statemachine.springstatemachinedemo.enums;

/**
 * @author: Jindong.Tian
 * @date: 2025-03-22
 * @description:
 **/
public enum BizErrorCode {

    PARAM_CHECK_FAILED(1001, "参数较验失败"),
    ;

    private Integer code;
    private String desc;

    BizErrorCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
