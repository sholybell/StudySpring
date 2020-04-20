package com.holybell.mybatis.entity;

import java.util.Date;

public class SendExpressAgentService {

    private static final long serialVersionUID = -4221438046554324218L;
    private int userId; // 商户Id
    private String nickName; // 商户昵称
    private String province; // 省份
    private String city; // 城市
    private String county; // 区县
    private String address; // 详细地址
    private Date createTime; // 创建时间
    private String mobile; // 联系方式
    private int status; // 开通状态  0 商户关闭  1 城市顾问关闭 2 打开

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}