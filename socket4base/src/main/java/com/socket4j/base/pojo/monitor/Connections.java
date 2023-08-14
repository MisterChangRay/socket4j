package com.socket4j.base.pojo.monitor;

public class Connections {
    private String ip;
    private String onLineTime;
    private String uniqueId;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOnLineTime() {
        return onLineTime;
    }

    public void setOnLineTime(String onLineTime) {
        this.onLineTime = onLineTime;
    }
}
