package com.socket4j.client.events;

import com.socket4j.base.pojo.dto.Message;
import org.springframework.context.ApplicationEvent;

public class DataReceived extends ApplicationEvent {
    Message message;

    public DataReceived(Object source) {
        super(source);
    }
}
