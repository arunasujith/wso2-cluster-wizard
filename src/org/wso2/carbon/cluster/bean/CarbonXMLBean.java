/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wso2.carbon.cluster.bean;

/**
 *
 * @author aruna
 */
public class CarbonXMLBean {
    
    private int PortOffset;
    private String mgtHostName;
    private String hostName;

    public int getPortOffset() {
        return PortOffset;
    }

    public void setPortOffset(int PortOffset) {
        this.PortOffset = PortOffset;
    }

    public String getMgtHostName() {
        return mgtHostName;
    }

    public void setMgtHostName(String mgtHostName) {
        this.mgtHostName = mgtHostName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
    
    
    
}
