package com.xdaocloud.framework.dto.request.organization;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotBlank;
import com.xdaocloud.base.common.IBaseRequest;

public class UpdateOrganizationRequest implements IBaseRequest, Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "组织名称不能为空")
    private String name;

    /**
     * 上级组织Id
     */
    private Long parentId;

    /**
     * 类型, 10:公司, 20:部门
     */
    private Integer type;

    /**
     * 国家
     */
    private String nation;

    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String county;
    /**
     * 联系地址
     */
    private String address;
    /**
     * Logo
     */
    private String logo;
    /**
     * 单位类型
     */
    private String unitType;
    /**
     * 行业类型
     */
    private String industryType;
    /**
     * 公司规模
     */
    private Integer memberSize;
    /**
     * 生产总值
     */
    private String totalOutputValue;
    /**
     * 联系人
     */
    private String linkman;
    /**
     * 固定电话
     */
    private String telephone;
    /**
     * 移动电话
     */
    private String mobilePhone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 描述
     */
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public Integer getMemberSize() {
        return memberSize;
    }

    public void setMemberSize(Integer memberSize) {
        this.memberSize = memberSize;
    }

    public String getTotalOutputValue() {
        return totalOutputValue;
    }

    public void setTotalOutputValue(String totalOutputValue) {
        this.totalOutputValue = totalOutputValue;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
