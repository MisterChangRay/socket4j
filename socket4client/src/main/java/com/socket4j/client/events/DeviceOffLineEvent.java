package com.socket4j.client.events;

import org.springframework.context.ApplicationEvent;

public class DeviceOffLineEvent extends ApplicationEvent {
    public DeviceOffLineEvent(Object source) {
        super(source);
    }
}
