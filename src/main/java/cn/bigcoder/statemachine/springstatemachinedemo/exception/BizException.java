package cn.bigcoder.statemachine.springstatemachinedemo.exception;

import cn.bigcoder.statemachine.springstatemachinedemo.enums.BizErrorCode;

/**
 * @author: Jindong.Tian
 * @date: 2025-03-22
 **/
public class BizException extends RuntimeException {

    private BizErrorCode errorCode;

    public BizException(BizErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
