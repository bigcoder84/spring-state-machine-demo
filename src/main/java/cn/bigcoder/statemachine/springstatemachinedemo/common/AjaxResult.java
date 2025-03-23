package cn.bigcoder.statemachine.springstatemachinedemo.common;

import lombok.Data;
import lombok.Getter;

/**
 * @author: Jindong.Tian
 * @date: 2025-03-22
 **/
@Getter
public class AjaxResult<T> {

    private int code;

    private String message;

    private T data;

    public AjaxResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <R> AjaxResult<R> success(R data) {
        return new AjaxResult<R>(0, "success", data);
    }

    public static <R> AjaxResult<R> success() {
        return success(null);
    }
}
