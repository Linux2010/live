package cn.com.myproject.logi.entity.PO;

import cn.com.myproject.util.BasePO;

/**
 * Created by JYP on 2017/9/14 0014.
 */
public class LogiPlatform extends BasePO{

    private String platId;

    private String platformName;

    private String config;

    private Integer isOpen;

    private String code;

    public String getPlatId() {
        return platId;
    }

    public void setPlatId(String platId) {
        this.platId = platId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
