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
public class Axis2XMLBean {
    private String domain;
    private String localMemberHost;
    private int localMemberPort;
    private String membershipScheme;
    //the details of the node we are going to connect
    private String otherLocalMemberHost;
    private int otherLocalMemberPort;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLocalMemberHost() {
        return localMemberHost;
    }

    public void setLocalMemberHost(String localMemberHost) {
        this.localMemberHost = localMemberHost;
    }

    public int getLocalMemberPort() {
        return localMemberPort;
    }

    public void setLocalMemberPort(int localMemberPort) {
        this.localMemberPort = localMemberPort;
    }

    public String getMembershipScheme() {
        return membershipScheme;
    }

    public void setMembershipScheme(String membershipScheme) {
        this.membershipScheme = membershipScheme;
    }

    public String getOtherLocalMemberHost() {
        return otherLocalMemberHost;
    }

    public void setOtherLocalMemberHost(String otherLocalMemberHost) {
        this.otherLocalMemberHost = otherLocalMemberHost;
    }

    public int getOtherLocalMemberPort() {
        return otherLocalMemberPort;
    }

    public void setOtherLocalMemberPort(int otherLocalMemberPort) {
        this.otherLocalMemberPort = otherLocalMemberPort;
    }
    
//    public void getString(){
//        System.out.println(
//        domain+"   "+
//     localMemberHost+"   "+
//     localMemberPort+"   "+
//     membershipScheme+"   "+
//    //the details of the node we are going to connect
//    otherLocalMemberHost+"   "+
//     otherLocalMemberPort
//        );
//    }
//    
    
}
