package cn.bigcoder.statemachine.springstatemachinedemo.utils;

import cn.hutool.core.lang.Snowflake;

/**
 * 订单id生成器
 * @author: Jindong.Tian
 * @date: 2025-03-23
 **/
public class IdGenerator {

    private static final Snowflake snowFlake = new Snowflake();

    public static String generateId() {
        return String.valueOf(snowFlake.nextId());
    }
}
