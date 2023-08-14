package com.socket4j.client.consts;

import io.netty.util.AttributeKey;

/**
 * netty keys
 */
public class AttrKeys {

    // 上线时间
    public static AttributeKey ONLINE_TIME = AttributeKey.newInstance("ONLINE_TIME");

    public static AttributeKey OFFLINE_TIME = AttributeKey.newInstance("OFFLINE_TIME");

    // 每个连接的唯一识别号
    public static AttributeKey UNIQUE_NO = AttributeKey.newInstance("UNIQUE_NO");
}