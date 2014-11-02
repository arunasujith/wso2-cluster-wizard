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
public class RegistryXMLBean {

    private String dbConfigName;
    private String dataSource;

    private String remoteInstanceURL;
    private String remoteInstanceID;
    private String remoteInstanceDbConfig;
    private boolean remoteInstanceReadOnly;
    private boolean remoteInstanceEnableCache;
    private String remoteInstanceRegistryRoot;
    private String remoteInstanceCacheID;

    private String mountPathSystemConfig;
    private boolean mountPathSystemOverwrite;
    private String mountSystemConfigInstanceID;
    private String mountSystemConfigTargetPath;

    private String mountPathSystemGovernance;
    private boolean mountPathGovernanceOverwrite;
    private String mountSystemGovernanceInstanceID;
    private String mountSystemGovernanceTargetPath;

    public String getDbConfigName() {
        return dbConfigName;
    }

    public void setDbConfigName(String dbConfigName) {
        this.dbConfigName = dbConfigName;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getRemoteInstanceURL() {
        return remoteInstanceURL;
    }

    public void setRemoteInstanceURL(String remoteInstanceURL) {
        this.remoteInstanceURL = remoteInstanceURL;
    }

    public String getRemoteInstanceID() {
        return remoteInstanceID;
    }

    public void setRemoteInstanceID(String remoteInstanceID) {
        this.remoteInstanceID = remoteInstanceID;
    }

    public String getRemoteInstanceDbConfig() {
        return remoteInstanceDbConfig;
    }

    public void setRemoteInstanceDbConfig(String remoteInstanceDbConfig) {
        this.remoteInstanceDbConfig = remoteInstanceDbConfig;
    }

    public boolean isRemoteInstanceReadOnly() {
        return remoteInstanceReadOnly;
    }

    public void setRemoteInstanceReadOnly(boolean remoteInstanceReadOnly) {
        this.remoteInstanceReadOnly = remoteInstanceReadOnly;
    }

    public boolean isRemoteInstanceEnableCache() {
        return remoteInstanceEnableCache;
    }

    public void setRemoteInstanceEnableCache(boolean remoteInstanceEnableCache) {
        this.remoteInstanceEnableCache = remoteInstanceEnableCache;
    }

    public String getRemoteInstanceRegistryRoot() {
        return remoteInstanceRegistryRoot;
    }

    public void setRemoteInstanceRegistryRoot(String remoteInstanceRegistryRoot) {
        this.remoteInstanceRegistryRoot = remoteInstanceRegistryRoot;
    }

    public String getRemoteInstanceCacheID() {
        return remoteInstanceCacheID;
    }

    public void setRemoteInstanceCacheID(String remoteInstanceCacheID) {
        this.remoteInstanceCacheID = remoteInstanceCacheID;
    }

    public String getMountPathSystemConfig() {
        return mountPathSystemConfig;
    }

    public void setMountPathSystemConfig(String mountPathSystemConfig) {
        this.mountPathSystemConfig = mountPathSystemConfig;
    }

    public String getMountSystemConfigInstanceID() {
        return mountSystemConfigInstanceID;
    }

    public void setMountSystemConfigInstanceID(String mountSystemConfigInstanceID) {
        this.mountSystemConfigInstanceID = mountSystemConfigInstanceID;
    }

    public String getMountSystemConfigTargetPath() {
        return mountSystemConfigTargetPath;
    }

    public void setMountSystemConfigTargetPath(String mountSystemConfigTargetPath) {
        this.mountSystemConfigTargetPath = mountSystemConfigTargetPath;
    }

    public String getMountPathSystemGovernance() {
        return mountPathSystemGovernance;
    }

    public void setMountPathSystemGovernance(String mountPathSystemGovernance) {
        this.mountPathSystemGovernance = mountPathSystemGovernance;
    }

    public String getMountSystemGovernanceInstanceID() {
        return mountSystemGovernanceInstanceID;
    }

    public void setMountSystemGovernanceInstanceID(String mountSystemGovernanceInstanceID) {
        this.mountSystemGovernanceInstanceID = mountSystemGovernanceInstanceID;
    }

    public String getMountSystemGovernanceTargetPath() {
        return mountSystemGovernanceTargetPath;
    }

    public void setMountSystemGovernanceTargetPath(String mountSystemGovernanceTargetPath) {
        this.mountSystemGovernanceTargetPath = mountSystemGovernanceTargetPath;
    }

    public boolean isMountPathSystemOverwrite() {
        return mountPathSystemOverwrite;
    }

    public void setMountPathSystemOverwrite(boolean mountPathSystemOverwrite) {
        this.mountPathSystemOverwrite = mountPathSystemOverwrite;
    }

    public boolean isMountPathGovernanceOverwrite() {
        return mountPathGovernanceOverwrite;
    }

    public void setMountPathGovernanceOverwrite(boolean mountPathGovernanceOverwrite) {
        this.mountPathGovernanceOverwrite = mountPathGovernanceOverwrite;
    }

}
