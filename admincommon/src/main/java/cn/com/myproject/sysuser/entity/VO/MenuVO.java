package cn.com.myproject.sysuser.entity.VO;



import cn.com.myproject.sysuser.entity.PO.SysResource;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by liyang-macbook on 2017/7/11.
 */
public class MenuVO {

    private String id;
    private String name;
    private String url;
    private String icon;
    private String active = "";
    private String open = "";
    private LinkedHashSet<MenuVO> childs;


    public MenuVO() {

    }

    public MenuVO(SysResource resource) {
        this.id = resource.getResourceId();
        this.name = resource.getResourceName();
        this.url = resource.getValue();
        this.icon = resource.getIcon();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<MenuVO> getChilds() {
        return childs;
    }

    public void setChilds(LinkedHashSet<MenuVO> childs) {
        this.childs = childs;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }
}
