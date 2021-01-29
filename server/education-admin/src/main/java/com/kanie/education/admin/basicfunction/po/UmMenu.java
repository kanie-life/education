package com.kanie.education.admin.basicfunction.po;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class UmMenu implements Serializable {

    @ApiModelProperty(value = "唯一标识")
    private Long menuId;

    @ApiModelProperty(value = "菜单父标识")
    private Long parentMenuId;

    @ApiModelProperty(value = "菜单编码，起000，每三位一级")
    private String menuCode;

    @ApiModelProperty(value = "菜单名称")
    private String title;

    @ApiModelProperty(value = "前端名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "是否可用，Y：可用；N：不可用")
    private String isEnable;

    @ApiModelProperty(value = "前端隐藏")
    private Integer hidden;

    @ApiModelProperty(value = "前端图标")
    private String icon;

    private static final long serialVersionUID = 1L;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Long parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getHidden() {
        return hidden;
    }

    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuId=").append(menuId);
        sb.append(", parentMenuId=").append(parentMenuId);
        sb.append(", menuCode=").append(menuCode);
        sb.append(", name=").append(name);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", sort=").append(sort);
        sb.append(", isEnable=").append(isEnable);
        sb.append(", hidden=").append(hidden);
        sb.append(", icon=").append(icon);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}