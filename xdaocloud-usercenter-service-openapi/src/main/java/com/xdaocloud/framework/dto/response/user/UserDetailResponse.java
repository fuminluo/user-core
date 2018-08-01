package com.xdaocloud.framework.dto.response.user;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDetailResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String username;

    private String avatar;

    private String phone;

    private String email;

    private String nickname;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    public UserDetailResponse() {}

    public UserDetailResponse(Long id, String name, String username, String avatar, String phone, String email, String nickname) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.avatar = avatar;
        this.phone = phone;
        this.email = email;
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

}
