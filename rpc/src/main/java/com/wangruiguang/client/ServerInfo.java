package com.wangruiguang.client;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

public class ServerInfo {

    /**
     * 地址
     */
    private String host;

    /**
     * 端口
     */
    private int port;

    /**
     * 协议
     */
    private String protocol;

    public ServerInfo(String host, int port, String protocol) {
        this.host = host;
        this.port = port;
        this.protocol = protocol;
    }

    public static ServerInfo create(String input) {
        List<String> infoList = Splitter.on(":").omitEmptyStrings().splitToList(input);
        String host = infoList.get(0);
        int port = NumberUtils.toInt(infoList.get(1));
        String protocol = infoList.get(2);

        return new ServerInfo(host,port,protocol);
    }

    public String createPublishAddress() {
        return this.getHost() + ":" + this.getPort() + ":" + this.getProtocol();
     }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ServerInfo that = (ServerInfo) o;

        return new EqualsBuilder()
                .append(host, that.host)
                .append(port, that.port)
                .append(protocol, that.protocol)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(host)
                .append(port)
                .append(protocol)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "ServerInfo{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", protocol='" + protocol + '\'' +
                '}';
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
