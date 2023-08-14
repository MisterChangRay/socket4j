package com.socket4j.base.pojo.monitor;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientInfo {
    private String clientName;
    private String clientIp;
    private String portStart;
    private String portEnd;
    /**
     * 上报时本地时间
     */
    private Long postTime;

    /**
     * 启动时时间
     */
    private Long bootTime;

    /**
     * 总连接数
     */
    private AtomicInteger connectionCount;

    private BigInteger totalUploadBytes;
    private BigInteger totalDownloadBytes;

    public BigInteger getTotalUploadBytes() {
        return totalUploadBytes;
    }

    public void setTotalUploadBytes(BigInteger totalUploadBytes) {
        this.totalUploadBytes = totalUploadBytes;
    }

    public BigInteger getTotalDownloadBytes() {
        return totalDownloadBytes;
    }

    public void setTotalDownloadBytes(BigInteger totalDownloadBytes) {
        this.totalDownloadBytes = totalDownloadBytes;
    }

    public AtomicInteger getConnectionCount() {
        return connectionCount;
    }

    public void setConnectionCount(AtomicInteger connectionCount) {
        this.connectionCount = connectionCount;
    }

    public Long getBootTime() {
        return bootTime;
    }

    public void setBootTime(Long bootTime) {
        this.bootTime = bootTime;
    }

    public Long getPostTime() {
        return postTime;
    }

    public void setPostTime(Long postTime) {
        this.postTime = postTime;
    }



    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getPortStart() {
        return portStart;
    }

    public void setPortStart(String portStart) {
        this.portStart = portStart;
    }

    public String getPortEnd() {
        return portEnd;
    }

    public void setPortEnd(String portEnd) {
        this.portEnd = portEnd;
    }
}
