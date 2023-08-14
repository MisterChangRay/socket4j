package com.socket4j.base.pojo.config;

import java.util.List;
import java.util.Map;

public class ClientConfig {
    /**
     * ip 黑名单
     */
    private Map<String, Boolean> ipBlackList;

    private List<MessageDefine> codes;


    public Map<String, Boolean> getIpBlackList() {
        return ipBlackList;
    }

    public void setIpBlackList(Map<String, Boolean> ipBlackList) {
        this.ipBlackList = ipBlackList;
    }

    public List<MessageDefine> getCodes() {
        return codes;
    }

    public void setCodes(List<MessageDefine> codes) {
        this.codes = codes;
    }
}
