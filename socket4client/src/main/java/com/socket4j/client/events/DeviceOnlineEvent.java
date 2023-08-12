package com.socket4j.client.events;

import org.springframework.context.ApplicationEvent;

public class DeviceOnlineEvent  extends ApplicationEvent {
    public DeviceOnlineEvent(Object source) {
        super(source);
    }
}
