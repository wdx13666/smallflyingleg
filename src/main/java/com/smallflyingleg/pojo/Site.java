package com.smallflyingleg.pojo;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * CMS站点表
 * </p>
 *
 * @author wdx
 * @since 2019-06-03
 */
@TableName("sf_site")
public class Site extends Model<Site> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "site_id", type = IdType.AUTO)
    private Integer siteId;
    /**
     * 配置ID
     */
    private Integer configId;
    /**
     * 上传ftp
     */
    private Integer ftpUploadId;
    /**
     * 域名
     */
    private String domain;
    /**
     * 路径
     */
    private String sitePath;
    /**
     * 网站名称
     */
    private String siteName;
    /**
     * 简短名称
     */
    private String shortName;
    /**
     * 协议
     */
    private String protocol;
    /**
     * 动态页后缀
     */
    private String dynamicSuffix;
    /**
     * 静态页后缀
     */
    private String staticSuffix;
    /**
     * 静态页存放目录
     */
    private String staticDir;
    /**
     * 是否使用将首页放在根目录下
     */
    private String isIndexToRoot;
    /**
     * 是否静态化首页
     */
    private String isStaticIndex;
    /**
     * 后台本地化
     */
    private String localeAdmin;
    /**
     * 前台本地化
     */
    private String localeFront;
    /**
     * 模板方案
     */
    private String tplSolution;
    /**
     * 终审级别
     */
    private Integer finalStep;
    /**
     * 审核后(1:不能修改删除;2:修改后退回;3:修改后不变)
     */
    private Integer afterCheck;
    /**
     * 是否使用相对路径
     */
    private String isRelativePath;
    /**
     * 是否开启回收站
     */
    private String isRecycleOn;
    /**
     * 域名别名
     */
    private String domainAlias;
    /**
     * 域名重定向
     */
    private String domainRedirect;
    /**
     * 是否主站
     */
    private Integer isMaster;
    /**
     * 父站点id
     */
    private Integer parentId;
    /**
     * 首页模板
     */
    private String tplIndex;
    /**
     * 访问路径
     */
    private String accessPath;
    /**
     * 站点关键字
     */
    private String keywords;
    /**
     * 站点描述
     */
    private String description;


    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public Integer getFtpUploadId() {
        return ftpUploadId;
    }

    public void setFtpUploadId(Integer ftpUploadId) {
        this.ftpUploadId = ftpUploadId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSitePath() {
        return sitePath;
    }

    public void setSitePath(String sitePath) {
        this.sitePath = sitePath;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getDynamicSuffix() {
        return dynamicSuffix;
    }

    public void setDynamicSuffix(String dynamicSuffix) {
        this.dynamicSuffix = dynamicSuffix;
    }

    public String getStaticSuffix() {
        return staticSuffix;
    }

    public void setStaticSuffix(String staticSuffix) {
        this.staticSuffix = staticSuffix;
    }

    public String getStaticDir() {
        return staticDir;
    }

    public void setStaticDir(String staticDir) {
        this.staticDir = staticDir;
    }

    public String getIsIndexToRoot() {
        return isIndexToRoot;
    }

    public void setIsIndexToRoot(String isIndexToRoot) {
        this.isIndexToRoot = isIndexToRoot;
    }

    public String getIsStaticIndex() {
        return isStaticIndex;
    }

    public void setIsStaticIndex(String isStaticIndex) {
        this.isStaticIndex = isStaticIndex;
    }

    public String getLocaleAdmin() {
        return localeAdmin;
    }

    public void setLocaleAdmin(String localeAdmin) {
        this.localeAdmin = localeAdmin;
    }

    public String getLocaleFront() {
        return localeFront;
    }

    public void setLocaleFront(String localeFront) {
        this.localeFront = localeFront;
    }

    public String getTplSolution() {
        return tplSolution;
    }

    public void setTplSolution(String tplSolution) {
        this.tplSolution = tplSolution;
    }

    public Integer getFinalStep() {
        return finalStep;
    }

    public void setFinalStep(Integer finalStep) {
        this.finalStep = finalStep;
    }

    public Integer getAfterCheck() {
        return afterCheck;
    }

    public void setAfterCheck(Integer afterCheck) {
        this.afterCheck = afterCheck;
    }

    public String getIsRelativePath() {
        return isRelativePath;
    }

    public void setIsRelativePath(String isRelativePath) {
        this.isRelativePath = isRelativePath;
    }

    public String getIsRecycleOn() {
        return isRecycleOn;
    }

    public void setIsRecycleOn(String isRecycleOn) {
        this.isRecycleOn = isRecycleOn;
    }

    public String getDomainAlias() {
        return domainAlias;
    }

    public void setDomainAlias(String domainAlias) {
        this.domainAlias = domainAlias;
    }

    public String getDomainRedirect() {
        return domainRedirect;
    }

    public void setDomainRedirect(String domainRedirect) {
        this.domainRedirect = domainRedirect;
    }

    public Integer getIsMaster() {
        return isMaster;
    }

    public void setIsMaster(Integer isMaster) {
        this.isMaster = isMaster;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTplIndex() {
        return tplIndex;
    }

    public void setTplIndex(String tplIndex) {
        this.tplIndex = tplIndex;
    }

    public String getAccessPath() {
        return accessPath;
    }

    public void setAccessPath(String accessPath) {
        this.accessPath = accessPath;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    protected Serializable pkVal() {
        return this.siteId;
    }

    @Override
    public String toString() {
        return "Site{" +
        ", siteId=" + siteId +
        ", configId=" + configId +
        ", ftpUploadId=" + ftpUploadId +
        ", domain=" + domain +
        ", sitePath=" + sitePath +
        ", siteName=" + siteName +
        ", shortName=" + shortName +
        ", protocol=" + protocol +
        ", dynamicSuffix=" + dynamicSuffix +
        ", staticSuffix=" + staticSuffix +
        ", staticDir=" + staticDir +
        ", isIndexToRoot=" + isIndexToRoot +
        ", isStaticIndex=" + isStaticIndex +
        ", localeAdmin=" + localeAdmin +
        ", localeFront=" + localeFront +
        ", tplSolution=" + tplSolution +
        ", finalStep=" + finalStep +
        ", afterCheck=" + afterCheck +
        ", isRelativePath=" + isRelativePath +
        ", isRecycleOn=" + isRecycleOn +
        ", domainAlias=" + domainAlias +
        ", domainRedirect=" + domainRedirect +
        ", isMaster=" + isMaster +
        ", parentId=" + parentId +
        ", tplIndex=" + tplIndex +
        ", accessPath=" + accessPath +
        ", keywords=" + keywords +
        ", description=" + description +
        "}";
    }
}
